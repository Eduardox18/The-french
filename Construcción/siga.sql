-- MySQL dump 10.13  Distrib 5.7.18, for macos10.12 (x86_64)
--
-- Host: localhost    Database: siga
-- ------------------------------------------------------
-- Server version	5.7.18

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Actividad`
--

DROP TABLE IF EXISTS `Actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Actividad` (
  `idActividad` int(11) NOT NULL AUTO_INCREMENT,
  `nombreActividad` varchar(45) NOT NULL,
  `profesorActividad` varchar(45) NOT NULL,
  `horaActividad` time NOT NULL,
  `diaActividad` date NOT NULL,
  PRIMARY KEY (`idActividad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='tipoAsesoria';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Actividad`
--

LOCK TABLES `Actividad` WRITE;
/*!40000 ALTER TABLE `Actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumno`
--

DROP TABLE IF EXISTS `alumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumno` (
  `matriculaAlumno` varchar(9) NOT NULL,
  `nombreAlumno` varchar(45) NOT NULL,
  `apPaternoAlumno` varchar(45) NOT NULL,
  `apMaternoAlumno` varchar(45) DEFAULT NULL,
  `telefonoAlumno` int(10) NOT NULL,
  `noInscripcionAlumno` int(10) NOT NULL,
  `correoEAlumno` varchar(45) NOT NULL,
  `semestreAlumno` int(1) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  PRIMARY KEY (`matriculaAlumno`),
  KEY `fk_alumno_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_alumno_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alumnoTieneHistorialObservaciones`
--

DROP TABLE IF EXISTS `alumnoTieneHistorialObservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `alumnoTieneHistorialObservaciones` (
  `HistorialObservaciones_idHistorialObservaciones` int(11) NOT NULL,
  `Alumno_matriculaAlumno` varchar(9) NOT NULL,
  PRIMARY KEY (`HistorialObservaciones_idHistorialObservaciones`,`Alumno_matriculaAlumno`),
  KEY `fk_HistorialObservaciones_has_Alumno_Alumno1_idx` (`Alumno_matriculaAlumno`),
  KEY `fk_HistorialObservaciones_has_Alumno_HistorialObservaciones_idx` (`HistorialObservaciones_idHistorialObservaciones`),
  CONSTRAINT `fk_HistorialObservaciones_has_Alumno_Alumno1` FOREIGN KEY (`Alumno_matriculaAlumno`) REFERENCES `alumno` (`matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_HistorialObservaciones_has_Alumno_HistorialObservaciones1` FOREIGN KEY (`HistorialObservaciones_idHistorialObservaciones`) REFERENCES `historialObservaciones` (`idHistorialObservaciones`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumnoTieneHistorialObservaciones`
--

LOCK TABLES `alumnoTieneHistorialObservaciones` WRITE;
/*!40000 ALTER TABLE `alumnoTieneHistorialObservaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `alumnoTieneHistorialObservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asesor`
--

DROP TABLE IF EXISTS `asesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asesor` (
  `noPersonalAsesor` int(11) NOT NULL AUTO_INCREMENT,
  `nombreAsesor` varchar(45) NOT NULL,
  `apPaternoAsesor` varchar(45) NOT NULL,
  `apMaternoAsesor` varchar(45) DEFAULT NULL,
  `telefonoAsesor` int(10) NOT NULL,
  `correoEAsesor` varchar(45) NOT NULL,
  `horarioAtencion` varchar(45) NOT NULL,
  `noGrupos` int(2) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  PRIMARY KEY (`noPersonalAsesor`),
  KEY `fk_asesor_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_asesor_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asesor`
--

LOCK TABLES `asesor` WRITE;
/*!40000 ALTER TABLE `asesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `asesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asesoria`
--

DROP TABLE IF EXISTS `asesoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asesoria` (
  `noAsesoria` int(11) NOT NULL AUTO_INCREMENT,
  `asesoria_idActividad` int(11) NOT NULL,
  `palabraAsesoria` varchar(12) NOT NULL,
  PRIMARY KEY (`noAsesoria`,`asesoria_idActividad`),
  KEY `fk_asesoria_asesoria1_idx` (`asesoria_idActividad`),
  CONSTRAINT `fk_asesoria_asesoria1` FOREIGN KEY (`asesoria_idActividad`) REFERENCES `actividad` (`idActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asesoria`
--

LOCK TABLES `asesoria` WRITE;
/*!40000 ALTER TABLE `asesoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `asesoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autoevaluación`
--

DROP TABLE IF EXISTS `autoevaluación`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autoevaluación` (
  `idAutoevaluación` int(11) NOT NULL AUTO_INCREMENT,
  `nombreElaborador` varchar(45) NOT NULL,
  `nombreMaterial` varchar(45) NOT NULL,
  `habilidadesRequeridas` varchar(45) NOT NULL,
  `fechaElaboracion` date NOT NULL,
  `contenidosEvaluados` varchar(45) NOT NULL,
  `codificacion` varchar(45) NOT NULL,
  `objetivo` varchar(45) NOT NULL,
  `sala` varchar(5) NOT NULL,
  `nivel` int(2) NOT NULL,
  `portafolioEvidencias_idportafolioEvidencias` int(11) NOT NULL,
  PRIMARY KEY (`idAutoevaluación`),
  KEY `fk_autoevaluación_portafolioEvidencias1_idx` (`portafolioEvidencias_idportafolioEvidencias`),
  CONSTRAINT `fk_autoevaluación_portafolioEvidencias1` FOREIGN KEY (`portafolioEvidencias_idportafolioEvidencias`) REFERENCES `portafolioEvidencias` (`idportafolioEvidencias`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoevaluación`
--

LOCK TABLES `autoevaluación` WRITE;
/*!40000 ALTER TABLE `autoevaluación` DISABLE KEYS */;
/*!40000 ALTER TABLE `autoevaluación` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aviso`
--

DROP TABLE IF EXISTS `aviso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aviso` (
  `idAviso` int(11) NOT NULL AUTO_INCREMENT,
  `nombreAviso` varchar(45) NOT NULL COMMENT '	',
  `lugarAviso` varchar(45) NOT NULL,
  `horaAviso` time NOT NULL,
  `fechaAviso` date NOT NULL,
  `descripcionAviso` varchar(45) NOT NULL,
  PRIMARY KEY (`idAviso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `aviso`
--

LOCK TABLES `aviso` WRITE;
/*!40000 ALTER TABLE `aviso` DISABLE KEYS */;
/*!40000 ALTER TABLE `aviso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bitacora`
--

DROP TABLE IF EXISTS `bitacora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bitacora` (
  `idBitacora` int(11) NOT NULL AUTO_INCREMENT,
  `noBitacora` int(2) NOT NULL,
  `tiempoEmpleado` time NOT NULL,
  `codigoActividad` int(6) NOT NULL,
  `comentario` varchar(45) NOT NULL,
  `desempenoSeccion` int(3) NOT NULL,
  `fechaBitacora` date NOT NULL,
  `materialesSugeridos` varchar(45) NOT NULL,
  `observacionesAsesor` varchar(45) NOT NULL,
  `resultadoAutoevaluacion` int(5) NOT NULL,
  `portafolioEvidencias_idportafolioEvidencias` int(11) NOT NULL,
  PRIMARY KEY (`idBitacora`),
  KEY `fk_Bitacora_portafolioEvidencias1_idx` (`portafolioEvidencias_idportafolioEvidencias`),
  CONSTRAINT `fk_Bitacora_portafolioEvidencias1` FOREIGN KEY (`portafolioEvidencias_idportafolioEvidencias`) REFERENCES `portafolioEvidencias` (`idportafolioEvidencias`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bitacora`
--

LOCK TABLES `bitacora` WRITE;
/*!40000 ALTER TABLE `bitacora` DISABLE KEYS */;
/*!40000 ALTER TABLE `bitacora` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `calendarioActividades`
--

DROP TABLE IF EXISTS `calendarioActividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `calendarioActividades` (
  `idCalendarioActividades` int(11) NOT NULL AUTO_INCREMENT,
  `fechaLimiteExamen` date NOT NULL,
  `seccionCalendarioActividades` int(5) NOT NULL,
  `materialReportar` varchar(45) NOT NULL,
  `moduloCalendarioActividades` int(2) NOT NULL,
  `convCalendarioActividades` varchar(45) NOT NULL,
  `Coordinador_noPersonalCoordinador` int(11) NOT NULL,
  PRIMARY KEY (`idCalendarioActividades`),
  KEY `fk_CalendarioActividades_Coordinador1_idx` (`Coordinador_noPersonalCoordinador`),
  CONSTRAINT `fk_CalendarioActividades_Coordinador1` FOREIGN KEY (`Coordinador_noPersonalCoordinador`) REFERENCES `coordinador` (`noPersonalCoordinador`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendarioActividades`
--

LOCK TABLES `calendarioActividades` WRITE;
/*!40000 ALTER TABLE `calendarioActividades` DISABLE KEYS */;
/*!40000 ALTER TABLE `calendarioActividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conversacion`
--

DROP TABLE IF EXISTS `conversacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `conversacion` (
  `noconversacion` int(11) NOT NULL AUTO_INCREMENT,
  `asesoria_idActividad` int(11) NOT NULL,
  `palabraConversacion` varchar(12) NOT NULL,
  PRIMARY KEY (`noconversacion`,`asesoria_idActividad`),
  KEY `fk_conversacion_asesoria1_idx` (`asesoria_idActividad`),
  CONSTRAINT `fk_conversacion_asesoria1` FOREIGN KEY (`asesoria_idActividad`) REFERENCES `actividad` (`idActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conversacion`
--

LOCK TABLES `conversacion` WRITE;
/*!40000 ALTER TABLE `conversacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `conversacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coordinador`
--

DROP TABLE IF EXISTS `coordinador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coordinador` (
  `noPersonalCoordinador` int(11) NOT NULL AUTO_INCREMENT,
  `nombreCoordinador` varchar(45) NOT NULL,
  `apPaternoCoordinador` varchar(45) NOT NULL,
  `apMaternoCoordinador` varchar(45) DEFAULT NULL,
  `correoECoordinador` varchar(45) NOT NULL,
  `telefonoCoordinador` int(15) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  `horarioAtencion_idhorarioAtencion` int(11) NOT NULL,
  PRIMARY KEY (`noPersonalCoordinador`),
  KEY `fk_coordinador_usuario1_idx` (`usuario_idusuario`),
  KEY `fk_coordinador_horarioAtencion1_idx` (`horarioAtencion_idhorarioAtencion`),
  CONSTRAINT `fk_coordinador_horarioAtencion1` FOREIGN KEY (`horarioAtencion_idhorarioAtencion`) REFERENCES `horarioAtencion` (`idhorarioAtencion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_coordinador_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinador`
--

LOCK TABLES `coordinador` WRITE;
/*!40000 ALTER TABLE `coordinador` DISABLE KEYS */;
/*!40000 ALTER TABLE `coordinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `idCurso` int(11) NOT NULL AUTO_INCREMENT,
  `creditosCurso` int(5) NOT NULL,
  `codigoCurso` int(20) NOT NULL,
  `nombreCurso` varchar(45) NOT NULL,
  `noHorasTeoria` int(5) NOT NULL,
  `noHorasPractica` int(5) NOT NULL,
  `prerrequisitoCurso` varchar(45) NOT NULL,
  `limiteAlumnosCurso` int(5) NOT NULL,
  `moduloCurso` int(5) NOT NULL,
  `nrcCurso` int(10) NOT NULL,
  `periodoCurso` varchar(45) NOT NULL,
  `fechaInicioCurso` varchar(45) NOT NULL,
  `fechaFinCurso` varchar(45) NOT NULL,
  `seccion_idseccion` int(11) NOT NULL,
  `asesor_noPersonalAsesor` int(11) NOT NULL,
  PRIMARY KEY (`idCurso`),
  KEY `fk_curso_seccion1_idx` (`seccion_idseccion`),
  KEY `fk_curso_asesor1_idx` (`asesor_noPersonalAsesor`),
  CONSTRAINT `fk_curso_asesor1` FOREIGN KEY (`asesor_noPersonalAsesor`) REFERENCES `asesor` (`noPersonalAsesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_seccion1` FOREIGN KEY (`seccion_idseccion`) REFERENCES `seccion` (`idseccion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `diasFestivosCalendario`
--

DROP TABLE IF EXISTS `diasFestivosCalendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `diasFestivosCalendario` (
  `iddiasFestivosCalendario` int(11) NOT NULL AUTO_INCREMENT,
  `diaFestivo` date NOT NULL,
  `motivoFestivo` varchar(45) NOT NULL,
  `calendarioActividades_idCalendarioActividades` int(11) NOT NULL,
  PRIMARY KEY (`iddiasFestivosCalendario`,`calendarioActividades_idCalendarioActividades`),
  KEY `fk_diasFestivosCalendario_calendarioActividades1_idx` (`calendarioActividades_idCalendarioActividades`),
  CONSTRAINT `fk_diasFestivosCalendario_calendarioActividades1` FOREIGN KEY (`calendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`idCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `diasFestivosCalendario`
--

LOCK TABLES `diasFestivosCalendario` WRITE;
/*!40000 ALTER TABLE `diasFestivosCalendario` DISABLE KEYS */;
/*!40000 ALTER TABLE `diasFestivosCalendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grupoAlumno`
--

DROP TABLE IF EXISTS `grupoAlumno`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `grupoAlumno` (
  `idGrupoAlumno` int(11) NOT NULL AUTO_INCREMENT,
  `noAlumnosRegistrados` int(5) NOT NULL,
  `periodo` varchar(45) NOT NULL,
  `seccion` varchar(45) NOT NULL,
  `curso_idCurso` int(11) NOT NULL,
  PRIMARY KEY (`idGrupoAlumno`),
  KEY `fk_grupoAlumno_curso1_idx` (`curso_idCurso`),
  CONSTRAINT `fk_grupoAlumno_curso1` FOREIGN KEY (`curso_idCurso`) REFERENCES `curso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupoAlumno`
--

LOCK TABLES `grupoAlumno` WRITE;
/*!40000 ALTER TABLE `grupoAlumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `grupoAlumno` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historialObservaciones`
--

DROP TABLE IF EXISTS `historialObservaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `historialObservaciones` (
  `idHistorialObservaciones` int(11) NOT NULL AUTO_INCREMENT,
  `noObservacion` varchar(45) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`idHistorialObservaciones`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historialObservaciones`
--

LOCK TABLES `historialObservaciones` WRITE;
/*!40000 ALTER TABLE `historialObservaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `historialObservaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hojaSeguimiento`
--

DROP TABLE IF EXISTS `hojaSeguimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hojaSeguimiento` (
  `idHojaSeguimiento` int(11) NOT NULL AUTO_INCREMENT,
  `horasEmpleadas` varchar(45) NOT NULL,
  `fechaInicioSesion` varchar(45) NOT NULL,
  `fechaTerminoSesion` varchar(45) NOT NULL,
  `evaluacion` varchar(45) NOT NULL,
  `codificacionMateriales` varchar(45) NOT NULL,
  `analisis` varchar(45) NOT NULL,
  `idioma` varchar(45) NOT NULL,
  `materialSeguimiento` varchar(45) NOT NULL,
  `modulo` varchar(45) NOT NULL,
  `nivel` varchar(45) NOT NULL,
  `objetivo` varchar(45) NOT NULL,
  `seccion` varchar(45) NOT NULL,
  `trabajadorEspSeccion` varchar(45) NOT NULL,
  `trabajadorIntroSeccion` varchar(45) NOT NULL,
  `portafolioEvidencias_idportafolioEvidencias` int(11) NOT NULL,
  `Asesor_noPersonalAsesor` int(11) NOT NULL,
  PRIMARY KEY (`idHojaSeguimiento`),
  KEY `fk_HojaSeguimiento_portafolioEvidencias1_idx` (`portafolioEvidencias_idportafolioEvidencias`),
  KEY `fk_HojaSeguimiento_Asesor1_idx` (`Asesor_noPersonalAsesor`),
  CONSTRAINT `fk_HojaSeguimiento_Asesor1` FOREIGN KEY (`Asesor_noPersonalAsesor`) REFERENCES `asesor` (`noPersonalAsesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_HojaSeguimiento_portafolioEvidencias1` FOREIGN KEY (`portafolioEvidencias_idportafolioEvidencias`) REFERENCES `portafolioEvidencias` (`idportafolioEvidencias`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hojaSeguimiento`
--

LOCK TABLES `hojaSeguimiento` WRITE;
/*!40000 ALTER TABLE `hojaSeguimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `hojaSeguimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `horarioAtencion`
--

DROP TABLE IF EXISTS `horarioAtencion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `horarioAtencion` (
  `idhorarioAtencion` int(11) NOT NULL AUTO_INCREMENT,
  `horaInicio` time NOT NULL,
  `horaFin` time NOT NULL,
  PRIMARY KEY (`idhorarioAtencion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarioAtencion`
--

LOCK TABLES `horarioAtencion` WRITE;
/*!40000 ALTER TABLE `horarioAtencion` DISABLE KEYS */;
/*!40000 ALTER TABLE `horarioAtencion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscripcion` (
  `GrupoAlumno_idGrupoAlumno` int(11) NOT NULL,
  `Alumno_matriculaAlumno` varchar(9) NOT NULL,
  `fecha_inscripcion` date NOT NULL,
  `noVaucher` int(20) NOT NULL,
  PRIMARY KEY (`GrupoAlumno_idGrupoAlumno`,`Alumno_matriculaAlumno`),
  KEY `fk_GrupoAlumno_has_Alumno_Alumno1_idx` (`Alumno_matriculaAlumno`),
  KEY `fk_GrupoAlumno_has_Alumno_GrupoAlumno1_idx` (`GrupoAlumno_idGrupoAlumno`),
  CONSTRAINT `fk_GrupoAlumno_has_Alumno_Alumno1` FOREIGN KEY (`Alumno_matriculaAlumno`) REFERENCES `alumno` (`matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_GrupoAlumno_has_Alumno_GrupoAlumno1` FOREIGN KEY (`GrupoAlumno_idGrupoAlumno`) REFERENCES `grupoAlumno` (`idGrupoAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
/*!40000 ALTER TABLE `inscripcion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mesCalendario`
--

DROP TABLE IF EXISTS `mesCalendario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `mesCalendario` (
  `idmesCalendario` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `calendarioActividades_idCalendarioActividades` int(11) NOT NULL,
  PRIMARY KEY (`idmesCalendario`,`calendarioActividades_idCalendarioActividades`),
  KEY `fk_mesCalendario_calendarioActividades1_idx` (`calendarioActividades_idCalendarioActividades`),
  CONSTRAINT `fk_mesCalendario_calendarioActividades1` FOREIGN KEY (`calendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`idCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesCalendario`
--

LOCK TABLES `mesCalendario` WRITE;
/*!40000 ALTER TABLE `mesCalendario` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesCalendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodoVacacional`
--

DROP TABLE IF EXISTS `periodoVacacional`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodoVacacional` (
  `idperiodoVacacional` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `calendarioActividades_idCalendarioActividades` int(11) NOT NULL,
  PRIMARY KEY (`idperiodoVacacional`,`calendarioActividades_idCalendarioActividades`),
  KEY `fk_periodoVacacional_calendarioActividades1_idx` (`calendarioActividades_idCalendarioActividades`),
  CONSTRAINT `fk_periodoVacacional_calendarioActividades1` FOREIGN KEY (`calendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`idCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodoVacacional`
--

LOCK TABLES `periodoVacacional` WRITE;
/*!40000 ALTER TABLE `periodoVacacional` DISABLE KEYS */;
/*!40000 ALTER TABLE `periodoVacacional` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `portafolioEvidencias`
--

DROP TABLE IF EXISTS `portafolioEvidencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `portafolioEvidencias` (
  `idportafolioEvidencias` int(11) NOT NULL AUTO_INCREMENT,
  `reglamento` varchar(45) NOT NULL,
  `CalendarioActividades_idCalendarioActividades` int(11) NOT NULL,
  `inscripcion_GrupoAlumno_idGrupoAlumno` int(11) NOT NULL,
  `inscripcion_Alumno_matriculaAlumno` varchar(9) NOT NULL,
  PRIMARY KEY (`idportafolioEvidencias`),
  KEY `fk_portafolioEvidencias_CalendarioActividades1_idx` (`CalendarioActividades_idCalendarioActividades`),
  KEY `fk_portafolioEvidencias_inscripcion1_idx` (`inscripcion_GrupoAlumno_idGrupoAlumno`,`inscripcion_Alumno_matriculaAlumno`),
  CONSTRAINT `fk_portafolioEvidencias_CalendarioActividades1` FOREIGN KEY (`CalendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`idCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_portafolioEvidencias_inscripcion1` FOREIGN KEY (`inscripcion_GrupoAlumno_idGrupoAlumno`, `inscripcion_Alumno_matriculaAlumno`) REFERENCES `inscripcion` (`GrupoAlumno_idGrupoAlumno`, `Alumno_matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portafolioEvidencias`
--

LOCK TABLES `portafolioEvidencias` WRITE;
/*!40000 ALTER TABLE `portafolioEvidencias` DISABLE KEYS */;
/*!40000 ALTER TABLE `portafolioEvidencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `recepcionista`
--

DROP TABLE IF EXISTS `recepcionista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `recepcionista` (
  `idRecepcionista` int(11) NOT NULL AUTO_INCREMENT,
  `nombreRecepcionista` varchar(45) NOT NULL,
  `telefonoRecepcionista` int(15) NOT NULL,
  `correoERecepcionista` varchar(45) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  `horarioAtencion_idhorarioAtencion` int(11) NOT NULL,
  PRIMARY KEY (`idRecepcionista`),
  KEY `fk_Recepcionista_usuario1_idx` (`usuario_idusuario`),
  KEY `fk_recepcionista_horarioAtencion1_idx` (`horarioAtencion_idhorarioAtencion`),
  CONSTRAINT `fk_Recepcionista_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_recepcionista_horarioAtencion1` FOREIGN KEY (`horarioAtencion_idhorarioAtencion`) REFERENCES `horarioAtencion` (`idhorarioAtencion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `recepcionista`
--

LOCK TABLES `recepcionista` WRITE;
/*!40000 ALTER TABLE `recepcionista` DISABLE KEYS */;
/*!40000 ALTER TABLE `recepcionista` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservacion`
--

DROP TABLE IF EXISTS `reservacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservacion` (
  `idreservacion` int(11) NOT NULL AUTO_INCREMENT,
  `fechaReservacion` date NOT NULL,
  `fechaRegistro` date NOT NULL,
  `CalendarioActividades_idCalendarioActividades` int(11) NOT NULL,
  `actividad_idActividad` int(11) NOT NULL,
  `alumno_matriculaAlumno` varchar(9) NOT NULL,
  PRIMARY KEY (`idreservacion`),
  KEY `fk_reservacion_CalendarioActividades1_idx` (`CalendarioActividades_idCalendarioActividades`),
  KEY `fk_reservacion_actividad1_idx` (`actividad_idActividad`),
  KEY `fk_reservacion_alumno1_idx` (`alumno_matriculaAlumno`),
  CONSTRAINT `fk_reservacion_CalendarioActividades1` FOREIGN KEY (`CalendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`idCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservacion_actividad1` FOREIGN KEY (`actividad_idActividad`) REFERENCES `actividad` (`idActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservacion_alumno1` FOREIGN KEY (`alumno_matriculaAlumno`) REFERENCES `alumno` (`matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservacion`
--

LOCK TABLES `reservacion` WRITE;
/*!40000 ALTER TABLE `reservacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retroalimentación`
--

DROP TABLE IF EXISTS `retroalimentación`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `retroalimentación` (
  `noRetroalimentación` int(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `nombreRetroalimentacion` varchar(45) NOT NULL,
  `idiomaRetroalimentacion` varchar(45) NOT NULL,
  `tipoRetroalimentacion` varchar(45) NOT NULL,
  `inscripcion_GrupoAlumno_idGrupoAlumno` int(11) NOT NULL,
  `inscripcion_Alumno_matriculaAlumno` varchar(9) NOT NULL,
  `seccion_idseccion` int(11) NOT NULL,
  PRIMARY KEY (`noRetroalimentación`),
  KEY `fk_retroalimentación_inscripcion1_idx` (`inscripcion_GrupoAlumno_idGrupoAlumno`,`inscripcion_Alumno_matriculaAlumno`),
  KEY `fk_retroalimentación_seccion1_idx` (`seccion_idseccion`),
  CONSTRAINT `fk_retroalimentación_inscripcion1` FOREIGN KEY (`inscripcion_GrupoAlumno_idGrupoAlumno`, `inscripcion_Alumno_matriculaAlumno`) REFERENCES `inscripcion` (`GrupoAlumno_idGrupoAlumno`, `Alumno_matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_retroalimentación_seccion1` FOREIGN KEY (`seccion_idseccion`) REFERENCES `seccion` (`idseccion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retroalimentación`
--

LOCK TABLES `retroalimentación` WRITE;
/*!40000 ALTER TABLE `retroalimentación` DISABLE KEYS */;
/*!40000 ALTER TABLE `retroalimentación` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seccion`
--

DROP TABLE IF EXISTS `seccion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seccion` (
  `idseccion` int(11) NOT NULL AUTO_INCREMENT,
  `noSeccion` int(2) NOT NULL,
  PRIMARY KEY (`idseccion`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seccion`
--

LOCK TABLES `seccion` WRITE;
/*!40000 ALTER TABLE `seccion` DISABLE KEYS */;
/*!40000 ALTER TABLE `seccion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimiento`
--

DROP TABLE IF EXISTS `seguimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seguimiento` (
  `SeguimientoCurso_idSeguimientoCurso` int(11) NOT NULL,
  `curso_idCurso` int(11) NOT NULL,
  PRIMARY KEY (`SeguimientoCurso_idSeguimientoCurso`,`curso_idCurso`),
  KEY `fk_SeguimientoCurso_has_curso1_curso1_idx` (`curso_idCurso`),
  KEY `fk_SeguimientoCurso_has_curso1_SeguimientoCurso1_idx` (`SeguimientoCurso_idSeguimientoCurso`),
  CONSTRAINT `fk_SeguimientoCurso_has_curso1_SeguimientoCurso1` FOREIGN KEY (`SeguimientoCurso_idSeguimientoCurso`) REFERENCES `seguimientoCurso` (`idSeguimientoCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_SeguimientoCurso_has_curso1_curso1` FOREIGN KEY (`curso_idCurso`) REFERENCES `curso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimiento`
--

LOCK TABLES `seguimiento` WRITE;
/*!40000 ALTER TABLE `seguimiento` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `seguimientoCurso`
--

DROP TABLE IF EXISTS `seguimientoCurso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `seguimientoCurso` (
  `idSeguimientoCurso` int(11) NOT NULL AUTO_INCREMENT COMMENT '	',
  `noSeguimiento` varchar(45) NOT NULL,
  `descripcionSeguimiento` varchar(45) NOT NULL,
  PRIMARY KEY (`idSeguimientoCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimientoCurso`
--

LOCK TABLES `seguimientoCurso` WRITE;
/*!40000 ALTER TABLE `seguimientoCurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimientoCurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tallerLectura`
--

DROP TABLE IF EXISTS `tallerLectura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tallerLectura` (
  `noTallerLectura` int(11) NOT NULL AUTO_INCREMENT,
  `asesoria_idActividad` int(11) NOT NULL,
  PRIMARY KEY (`noTallerLectura`,`asesoria_idActividad`),
  KEY `fk_tallerLectura_asesoria1_idx` (`asesoria_idActividad`),
  CONSTRAINT `fk_tallerLectura_asesoria1` FOREIGN KEY (`asesoria_idActividad`) REFERENCES `actividad` (`idActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tallerLectura`
--

LOCK TABLES `tallerLectura` WRITE;
/*!40000 ALTER TABLE `tallerLectura` DISABLE KEYS */;
/*!40000 ALTER TABLE `tallerLectura` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(9) NOT NULL,
  `passwordUsuario` varchar(256) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'S15011624','c45bb4815941a3722ca2182bf4e707afc74bbdc745077722cbeff4db3fa4ce7c'),(2,'S15011613','85a72bef03d94c5692c7d6a3d7b6621b4f517be44d12e241d6866f3114374ead');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-19 12:08:32
