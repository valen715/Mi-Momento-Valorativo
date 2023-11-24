--
-- Base de datos: `universitas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(5) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `nombre` varchar(200) DEFAULT NULL,
  `contrasena` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
--Datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `correo`, `nombre`, `contrasena`) VALUES
(1, 'usuario1@usuario1.com', 'usuario1', 'usuario1'),
(2, 'usuario2@usuario2.com', 'usuario2', 'usuario2'),
(3, 'usuario3@usuario3.com', 'usuario3', 'usuario3'),
(4, 'usuario4@usuario4.com', 'usuario4', 'usuario4'),
(5, 'usuario5@usuario5.com', 'usuario5', 'usuario5'),
(6, 'usuario6@usuario6.com', 'usuario6', 'usuario6'),
(7, 'usuario7@usuario7.com', 'usuario7', 'usuario7'),
(8, 'usuario8@usuario8.com', 'usuario8', 'usuario8'),
(9, 'usuario9@usuario9.com', 'usuario9', 'usuario9'),
(10, 'usuario0@usuario0.com', 'usuario0', 'usuario0'),
--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `uq_document_user` (`correo`);

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=99;
COMMIT;
