package co.com.edu.poli.ces3.tallerMetodos.servlet;

import co.com.edu.poli.ces3.tallerMetodos.controller.CtrUsuario;
import co.com.edu.poli.ces3.tallerMetodos.dto.DtoUsuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "usuarioServlet", value = "/usuario")
public class UsuarioServlet extends MyServlet {
    private String message;

    private GsonBuilder gsonBuilder;

    private Gson gson;

    private ArrayList<DtoUsuario> users;

    CtrUsuario ctr = new CtrUsuario();

    public void init() {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
        ArrayList<Object> usuarios = new ArrayList<>();

        DtoUsuario usuario1 = new DtoUsuario();
        usuario1 .id = 10;
        usuario1 .setNombre("Valen");
        usuario1 .setCorreo("valen@gmail.com");
        usuario1 .setContrasena("7346235690");

        usuarios.add(usuario1);

        for (int i = 0; i < usuarios.size(); i++)
        {
            System.out.println(usuarios.get(i));
        }
        message = "I'm the best!!!";
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        JsonObject body = this.getParamsFromPost(req);
        DtoUsuario std = new DtoUsuario(
                body.get("correo").getAsString(),
                body.get("nombre").getAsString(),
                body.get("contrasena").getAsString()
        );

        DtoUsuario nuevoUsuario = ctr.addUsuario(std);

        out.print(gson.toJson(nuevoUsuario));
        out.flush();


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        String usuarioIdParam = req.getParameter("id");

        if (usuarioIdParam != null && !usuarioIdParam.isEmpty()) {
            int usuarioId = Integer.parseInt(usuarioIdParam);
            DtoUsuario usuario = ctr.getUsuarioById(usuarioId);
            out.print(gson.toJson(usuario));
        } else {
            ArrayList<DtoUsuario> usuarios = ctr.getAllUsuarios();
            out.print(gson.toJson(usuarios));
        }

        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");
        BufferedReader reader = req.getReader();
        StringBuilder stringBuilder = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }

        JsonObject body = gson.fromJson(stringBuilder.toString(), JsonObject.class);
        int usuarioId = body.get("id").getAsInt();

        DtoUsuario updatedUsuario = new DtoUsuario(
                body.get("correo").getAsString(),
                body.get("nombre").getAsString(),
                body.get("contrasena").getAsString()
        );

        DtoUsuario resultado = ctr.updateUsuario(usuarioId, updatedUsuario);

        out.print(gson.toJson(resultado));
        out.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletOutputStream out = resp.getOutputStream();
        resp.setContentType("application/json");

        int usuarioId = Integer.parseInt(req.getParameter("id"));

        ctr.deleteUsuario(usuarioId);

        out.print(gson.toJson("Eliminado"));
        out.flush();
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getMethod();
        switch (method){
            case "PATCH":
                this.doPatch(req, resp);
                break;
            default:
                super.service(req, resp);
        }

    }

    protected void doPatch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("Entro al metodo patch!!!");
    }

}
