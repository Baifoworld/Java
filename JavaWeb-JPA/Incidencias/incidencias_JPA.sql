-- phpMyAdmin SQL Dump
-- version 5.1.3
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 03-11-2022 a las 18:07:01
-- Versión del servidor: 8.0.28
-- Versión de PHP: 8.0.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `incidencias`
--
CREATE DATABASE IF NOT EXISTS `incidencias` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
USE `incidencias`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Clientes`
--

DROP TABLE IF EXISTS `Clientes`;
CREATE TABLE IF NOT EXISTS `Clientes` (
  `id_cliente` char(9) NOT NULL,
  `nick` varchar(25) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `telefono` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EquiposTrabajo`
--

DROP TABLE IF EXISTS `EquiposTrabajo`;
CREATE TABLE IF NOT EXISTS `EquiposTrabajo` (
  `id_ticket` int NOT NULL,
  `dni` char(9) NOT NULL,
  PRIMARY KEY (`id_ticket`,`dni`),
  KEY `fk_gestores2` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Gestores`
--

DROP TABLE IF EXISTS `Gestores`;
CREATE TABLE IF NOT EXISTS `Gestores` (
  `dni` char(9) NOT NULL,
  `nick` varchar(25) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(250) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `SeguimientoTickets`
--

DROP TABLE IF EXISTS `SeguimientoTickets`;
CREATE TABLE IF NOT EXISTS `SeguimientoTickets` (
  `id_seguimiento` int NOT NULL AUTO_INCREMENT,
  `fecha` timestamp NOT NULL,
  `comentario` varchar(500) NOT NULL,
  `id_ticket` int NOT NULL,
  `dni` char(9) NOT NULL,
  PRIMARY KEY (`id_seguimiento`),
  KEY `fk_tickets` (`id_ticket`),
  KEY `fk_gestores` (`dni`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Tickets`
--

DROP TABLE IF EXISTS `Tickets`;
CREATE TABLE IF NOT EXISTS `Tickets` (
  `id_ticket` int NOT NULL AUTO_INCREMENT,
  `fecha_inicio` timestamp NOT NULL,
  `fecha_fin` timestamp NULL DEFAULT NULL,
  `descripcion` varchar(500) NOT NULL,
  `estado` varchar(50) NOT NULL,
  `id_cliente` char(9) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`id_ticket`),
  KEY `fk_clientes` (`id_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuarios`
--

DROP TABLE IF EXISTS `Usuarios`;
CREATE TABLE IF NOT EXISTS `Usuarios` (
  `nick` varchar(25) NOT NULL,
  `email` varchar(100) NOT NULL,
  `password` varchar(200) NOT NULL,
  `rol` varchar(13) NOT NULL,
  PRIMARY KEY (`nick`)
) ;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Clientes`
--
--

ALTER TABLE `Clientes`
  ADD CONSTRAINT `fk_id_usuario_clientes` FOREIGN KEY (`nick`) REFERENCES `Usuarios` (`nick`);

-- Filtros para la tabla `Gestores`
--
--

ALTER TABLE `Gestores`
  ADD CONSTRAINT `fk_id_usuario_gestores` FOREIGN KEY (`nick`) REFERENCES `Usuarios` (`nick`);

-- Filtros para la tabla `EquiposTrabajo`
--
ALTER TABLE `EquiposTrabajo`
  ADD CONSTRAINT `fk_gestores2` FOREIGN KEY (`dni`) REFERENCES `Gestores` (`dni`),
  ADD CONSTRAINT `fk_ticket` FOREIGN KEY (`id_ticket`) REFERENCES `Tickets` (`id_ticket`);

--
-- Filtros para la tabla `SeguimientoTickets`
--
ALTER TABLE `SeguimientoTickets`
  ADD CONSTRAINT `fk_gestores` FOREIGN KEY (`dni`) REFERENCES `Gestores` (`dni`),
  ADD CONSTRAINT `fk_tickets` FOREIGN KEY (`id_ticket`) REFERENCES `Tickets` (`id_ticket`);

--
-- Filtros para la tabla `Tickets`
--
ALTER TABLE `Tickets`
  ADD CONSTRAINT `fk_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `Clientes` (`id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
