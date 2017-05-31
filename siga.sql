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
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividad` (
  `idActividad` int(11) NOT NULL AUTO_INCREMENT,
  `nombreActividad` varchar(45) NOT NULL,
  `tipoActividad` varchar(20) NOT NULL,
  `horaActividad` time NOT NULL,
  `lugarActividad` varchar(45) NOT NULL,
  `diaActividad` date NOT NULL,
  `curso_nrcCurso` int(11) NOT NULL,
  PRIMARY KEY (`idActividad`),
  KEY `fk_actividad_curso1_idx` (`curso_nrcCurso`),
  CONSTRAINT `fk_actividad_curso1` FOREIGN KEY (`curso_nrcCurso`) REFERENCES `curso` (`nrcCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='tipoAsesoria';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (1,'Sobre franceses y torres','Asesoría','15:50:00','','2017-11-06',37182),(2,'Make America great again','Conversación','16:20:00','','2017-10-21',39182),(3,'OUI','Conversación','15:00:00','Salón 101','2017-05-15',37182);
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividadRealizada`
--

DROP TABLE IF EXISTS `actividadRealizada`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividadRealizada` (
  `idactividadRealizada` int(11) NOT NULL AUTO_INCREMENT,
  `codigoActividad` varchar(45) NOT NULL,
  `nombreActividad` varchar(45) NOT NULL,
  `fechaRealizada` date NOT NULL,
  `bitacora_noBitacora` int(11) NOT NULL,
  PRIMARY KEY (`idactividadRealizada`),
  KEY `fk_actividadRealizada_bitacora1_idx` (`bitacora_noBitacora`),
  CONSTRAINT `fk_actividadRealizada_bitacora1` FOREIGN KEY (`bitacora_noBitacora`) REFERENCES `bitacora` (`noBitacora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadRealizada`
--

LOCK TABLES `actividadRealizada` WRITE;
/*!40000 ALTER TABLE `actividadRealizada` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividadRealizada` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `actividadesBitacora`
--

DROP TABLE IF EXISTS `actividadesBitacora`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `actividadesBitacora` (
  `bitacora_noBitacora` int(11) NOT NULL,
  `asistenciaActividad_idasistenciaActividad` int(11) NOT NULL,
  KEY `fk_asistenciaActividad_bitacora2_idx` (`bitacora_noBitacora`),
  KEY `fk_asistenciaActividad_asistenciaActividad1_idx` (`asistenciaActividad_idasistenciaActividad`),
  CONSTRAINT `fk_asistenciaActividad_asistenciaActividad1` FOREIGN KEY (`asistenciaActividad_idasistenciaActividad`) REFERENCES `asistenciaActividad` (`idasistenciaActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_asistenciaActividad_bitacora2` FOREIGN KEY (`bitacora_noBitacora`) REFERENCES `bitacora` (`noBitacora`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividadesBitacora`
--

LOCK TABLES `actividadesBitacora` WRITE;
/*!40000 ALTER TABLE `actividadesBitacora` DISABLE KEYS */;
/*!40000 ALTER TABLE `actividadesBitacora` ENABLE KEYS */;
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
  `telefonoAlumno` int(11) NOT NULL,
  `correoEAlumno` varchar(45) NOT NULL,
  `semestreAlumno` int(1) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  PRIMARY KEY (`matriculaAlumno`),
  KEY `fk_alumno_usuario1_idx` (`usuario_idusuario`),
  CONSTRAINT `fk_alumno_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alumno`
--

LOCK TABLES `alumno` WRITE;
/*!40000 ALTER TABLE `alumno` DISABLE KEYS */;
INSERT INTO `alumno` VALUES ('S15011613','José Andrés','Domínguez','González',211793212,'thesgtpepper23@gmail.com',4,2),('S15011624','Angel Eduardo','Domínguez','Delgado',1231793212,'lalo@gmail.com',4,1);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `telefonoAsesor` int(11) NOT NULL,
  `correoEAsesor` varchar(45) NOT NULL,
  `noGrupos` int(2) NOT NULL,
  `usuario_idusuario` int(11) NOT NULL,
  `horarioAtencion_idhorarioAtencion` int(11) NOT NULL,
  PRIMARY KEY (`noPersonalAsesor`),
  KEY `fk_asesor_usuario1_idx` (`usuario_idusuario`),
  KEY `fk_asesor_horarioAtencion1_idx` (`horarioAtencion_idhorarioAtencion`),
  CONSTRAINT `fk_asesor_horarioAtencion1` FOREIGN KEY (`horarioAtencion_idhorarioAtencion`) REFERENCES `horarioAtencion` (`idhorarioAtencion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_asesor_usuario1` FOREIGN KEY (`usuario_idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asesor`
--

LOCK TABLES `asesor` WRITE;
/*!40000 ALTER TABLE `asesor` DISABLE KEYS */;
INSERT INTO `asesor` VALUES (1,'Karen','Cortés','Verdín',1232422892,'karen@gmail.com',3,3,1),(2,'Juan Carlos','Pérez','Arriaga',373829382,'elrevo@gmail.com',2,4,2);
/*!40000 ALTER TABLE `asesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `asistenciaActividad`
--

DROP TABLE IF EXISTS `asistenciaActividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `asistenciaActividad` (
  `idasistenciaActividad` int(11) NOT NULL AUTO_INCREMENT,
  `reservacion_noReservacion` int(11) NOT NULL,
  `presencia` tinyint(1) NOT NULL,
  PRIMARY KEY (`idasistenciaActividad`),
  KEY `fk_asistenciaActividad_reservacion2_idx` (`reservacion_noReservacion`),
  CONSTRAINT `fk_asistenciaActividad_reservacion2` FOREIGN KEY (`reservacion_noReservacion`) REFERENCES `reservacion` (`noReservacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `asistenciaActividad`
--

LOCK TABLES `asistenciaActividad` WRITE;
/*!40000 ALTER TABLE `asistenciaActividad` DISABLE KEYS */;
INSERT INTO `asistenciaActividad` VALUES (1,3,1);
/*!40000 ALTER TABLE `asistenciaActividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `autoevaluacion`
--

DROP TABLE IF EXISTS `autoevaluacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `autoevaluacion` (
  `noAutoevaluación` int(11) NOT NULL AUTO_INCREMENT,
  `nombreElaborador` varchar(45) NOT NULL,
  `nombreMaterial` varchar(45) NOT NULL,
  `habilidadesRequeridas` varchar(45) NOT NULL,
  `fechaElaboracion` date NOT NULL,
  `contenidosEvaluados` varchar(45) NOT NULL,
  `codificacion` varchar(45) NOT NULL,
  `objetivo` varchar(45) NOT NULL,
  `sala` varchar(5) NOT NULL,
  `nivel` int(2) NOT NULL,
  `resultadoAutoevaluacion` int(5) NOT NULL,
  PRIMARY KEY (`noAutoevaluación`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `autoevaluacion`
--

LOCK TABLES `autoevaluacion` WRITE;
/*!40000 ALTER TABLE `autoevaluacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `autoevaluacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `aviso`
--

DROP TABLE IF EXISTS `aviso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `aviso` (
  `noAviso` int(11) NOT NULL AUTO_INCREMENT,
  `nombreAviso` varchar(45) NOT NULL COMMENT '	',
  `lugarAviso` varchar(45) NOT NULL,
  `horaAviso` time NOT NULL,
  `fechaAviso` date NOT NULL,
  `descripcionAviso` varchar(45) NOT NULL,
  PRIMARY KEY (`noAviso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `noBitacora` int(11) NOT NULL AUTO_INCREMENT,
  `tiempoEmpleado` time NOT NULL,
  `comentario` varchar(45) NOT NULL,
  `fechaBitacora` date NOT NULL,
  `portafolioEvidencias_idportafolioEvidencias` int(11) NOT NULL,
  `autoevaluación_idAutoevaluación` int(11) NOT NULL,
  PRIMARY KEY (`noBitacora`),
  KEY `fk_Bitacora_portafolioEvidencias1_idx` (`portafolioEvidencias_idportafolioEvidencias`),
  KEY `fk_bitacora_autoevaluación1_idx` (`autoevaluación_idAutoevaluación`),
  CONSTRAINT `fk_Bitacora_portafolioEvidencias1` FOREIGN KEY (`portafolioEvidencias_idportafolioEvidencias`) REFERENCES `portafolioEvidencias` (`idportafolioEvidencias`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_bitacora_autoevaluación1` FOREIGN KEY (`autoevaluación_idAutoevaluación`) REFERENCES `autoevaluacion` (`noAutoevaluación`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `noCalendarioActividades` int(11) NOT NULL AUTO_INCREMENT,
  `fechaLimiteExamen` date NOT NULL,
  `materialReportar` varchar(45) NOT NULL,
  `convCalendarioActividades` varchar(45) NOT NULL,
  `Coordinador_noPersonalCoordinador` int(11) NOT NULL,
  PRIMARY KEY (`noCalendarioActividades`),
  KEY `fk_CalendarioActividades_Coordinador1_idx` (`Coordinador_noPersonalCoordinador`),
  CONSTRAINT `fk_CalendarioActividades_Coordinador1` FOREIGN KEY (`Coordinador_noPersonalCoordinador`) REFERENCES `coordinador` (`noPersonalCoordinador`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `calendarioActividades`
--

LOCK TABLES `calendarioActividades` WRITE;
/*!40000 ALTER TABLE `calendarioActividades` DISABLE KEYS */;
INSERT INTO `calendarioActividades` VALUES (1,'2017-06-10','Evidencias','Opcional',92831),(2,'2017-12-10','Examen y evidencias','Obligatoria',92831);
/*!40000 ALTER TABLE `calendarioActividades` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=92832 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coordinador`
--

LOCK TABLES `coordinador` WRITE;
/*!40000 ALTER TABLE `coordinador` DISABLE KEYS */;
INSERT INTO `coordinador` VALUES (92831,'Frida','Aguilar',NULL,'frida@gmail.com',1231231321,5,3);
/*!40000 ALTER TABLE `coordinador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `curso`
--

DROP TABLE IF EXISTS `curso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `curso` (
  `nrcCurso` int(5) NOT NULL,
  `nombreCurso` varchar(45) NOT NULL,
  `creditosCurso` int(5) NOT NULL,
  `codigoCurso` int(6) NOT NULL,
  `noHorasTeoria` int(5) NOT NULL,
  `noHorasPractica` int(5) NOT NULL,
  `prerrequisitoCurso` varchar(45) DEFAULT NULL,
  `limiteAlumnosCurso` int(5) NOT NULL,
  `moduloCurso` int(5) NOT NULL,
  `seccion_idseccion` int(11) NOT NULL,
  `asesor_noPersonalAsesor` int(11) NOT NULL,
  `periodo_idperiodo` int(11) NOT NULL,
  PRIMARY KEY (`nrcCurso`),
  KEY `fk_curso_seccion1_idx` (`seccion_idseccion`),
  KEY `fk_curso_asesor1_idx` (`asesor_noPersonalAsesor`),
  KEY `fk_curso_periodo1_idx` (`periodo_idperiodo`),
  CONSTRAINT `fk_curso_asesor1` FOREIGN KEY (`asesor_noPersonalAsesor`) REFERENCES `asesor` (`noPersonalAsesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_periodo1` FOREIGN KEY (`periodo_idperiodo`) REFERENCES `periodo` (`idperiodo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_curso_seccion1` FOREIGN KEY (`seccion_idseccion`) REFERENCES `seccion` (`idseccion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `curso`
--

LOCK TABLES `curso` WRITE;
/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
INSERT INTO `curso` VALUES (37182,'Francés I',9,281928,20,20,NULL,15,1,1,1,1),(39182,'Inglés II',9,93928,15,25,'Inglés I',15,2,3,2,2);
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
  CONSTRAINT `fk_diasFestivosCalendario_calendarioActividades1` FOREIGN KEY (`calendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`noCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `curso_nrcCurso` int(11) NOT NULL,
  PRIMARY KEY (`idGrupoAlumno`),
  KEY `fk_grupoAlumno_curso1_idx` (`curso_nrcCurso`),
  CONSTRAINT `fk_grupoAlumno_curso1` FOREIGN KEY (`curso_nrcCurso`) REFERENCES `curso` (`nrcCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grupoAlumno`
--

LOCK TABLES `grupoAlumno` WRITE;
/*!40000 ALTER TABLE `grupoAlumno` DISABLE KEYS */;
INSERT INTO `grupoAlumno` VALUES (2,10,37182),(3,11,39182);
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
  `noObservacion` int(2) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  PRIMARY KEY (`idHistorialObservaciones`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `horasEmpleadas` int(2) NOT NULL,
  `evaluacion` varchar(45) NOT NULL,
  `codificacionMateriales` varchar(45) NOT NULL,
  `analisis` varchar(45) NOT NULL,
  `materialSeguimiento` varchar(45) NOT NULL,
  `modulo` int(2) NOT NULL,
  `nivel` int(2) NOT NULL,
  `objetivo` varchar(45) NOT NULL,
  `portafolioEvidencias_idportafolioEvidencias` int(11) NOT NULL,
  `Asesor_noPersonalAsesor` int(11) NOT NULL,
  PRIMARY KEY (`idHojaSeguimiento`),
  KEY `fk_HojaSeguimiento_Asesor1_idx` (`Asesor_noPersonalAsesor`),
  KEY `fk_HojaSeguimiento_portafolioEvidencias1_idx` (`portafolioEvidencias_idportafolioEvidencias`),
  CONSTRAINT `fk_HojaSeguimiento_Asesor1` FOREIGN KEY (`Asesor_noPersonalAsesor`) REFERENCES `asesor` (`noPersonalAsesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_HojaSeguimiento_portafolioEvidencias1` FOREIGN KEY (`portafolioEvidencias_idportafolioEvidencias`) REFERENCES `portafolioEvidencias` (`idportafolioEvidencias`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `horarioAtencion`
--

LOCK TABLES `horarioAtencion` WRITE;
/*!40000 ALTER TABLE `horarioAtencion` DISABLE KEYS */;
INSERT INTO `horarioAtencion` VALUES (1,'08:00:00','13:00:00'),(2,'10:00:00','15:00:00'),(3,'09:00:00','11:00:00');
/*!40000 ALTER TABLE `horarioAtencion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `inscripcion`
--

DROP TABLE IF EXISTS `inscripcion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inscripcion` (
  `idinscripcion` int(11) NOT NULL AUTO_INCREMENT,
  `noVaucher` int(11) NOT NULL,
  `fechaInscripcion` date NOT NULL,
  `grupoAlumno_idGrupoAlumno` int(11) NOT NULL,
  `alumno_matriculaAlumno` varchar(9) NOT NULL,
  PRIMARY KEY (`idinscripcion`),
  KEY `fk_inscripcion_grupoAlumno1_idx` (`grupoAlumno_idGrupoAlumno`),
  KEY `fk_inscripcion_alumno1_idx` (`alumno_matriculaAlumno`),
  CONSTRAINT `fk_inscripcion_alumno1` FOREIGN KEY (`alumno_matriculaAlumno`) REFERENCES `alumno` (`matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_inscripcion_grupoAlumno1` FOREIGN KEY (`grupoAlumno_idGrupoAlumno`) REFERENCES `grupoAlumno` (`idGrupoAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inscripcion`
--

LOCK TABLES `inscripcion` WRITE;
/*!40000 ALTER TABLE `inscripcion` DISABLE KEYS */;
INSERT INTO `inscripcion` VALUES (1,3847217,'2017-01-10',2,'S15011624'),(3,3719912,'2017-07-01',3,'S15011624');
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
  CONSTRAINT `fk_mesCalendario_calendarioActividades1` FOREIGN KEY (`calendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`noCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mesCalendario`
--

LOCK TABLES `mesCalendario` WRITE;
/*!40000 ALTER TABLE `mesCalendario` DISABLE KEYS */;
/*!40000 ALTER TABLE `mesCalendario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `periodo`
--

DROP TABLE IF EXISTS `periodo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `periodo` (
  `idperiodo` int(11) NOT NULL AUTO_INCREMENT,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  PRIMARY KEY (`idperiodo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `periodo`
--

LOCK TABLES `periodo` WRITE;
/*!40000 ALTER TABLE `periodo` DISABLE KEYS */;
INSERT INTO `periodo` VALUES (1,'2017-01-15','2017-07-15'),(2,'2017-07-20','2017-12-17');
/*!40000 ALTER TABLE `periodo` ENABLE KEYS */;
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
  CONSTRAINT `fk_periodoVacacional_calendarioActividades1` FOREIGN KEY (`calendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`noCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
  `inscripcion_idinscripcion` int(11) NOT NULL,
  PRIMARY KEY (`idportafolioEvidencias`),
  KEY `fk_portafolioEvidencias_CalendarioActividades1_idx` (`CalendarioActividades_idCalendarioActividades`),
  KEY `fk_portafolioEvidencias_inscripcion1_idx` (`inscripcion_idinscripcion`),
  CONSTRAINT `fk_portafolioEvidencias_CalendarioActividades1` FOREIGN KEY (`CalendarioActividades_idCalendarioActividades`) REFERENCES `calendarioActividades` (`noCalendarioActividades`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_portafolioEvidencias_inscripcion1` FOREIGN KEY (`inscripcion_idinscripcion`) REFERENCES `inscripcion` (`idinscripcion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `portafolioEvidencias`
--

LOCK TABLES `portafolioEvidencias` WRITE;
/*!40000 ALTER TABLE `portafolioEvidencias` DISABLE KEYS */;
INSERT INTO `portafolioEvidencias` VALUES (1,'Asistir a todos los exámenes',1,1),(2,'No comer en clase y asistir a todas',2,3);
/*!40000 ALTER TABLE `portafolioEvidencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservacion`
--

DROP TABLE IF EXISTS `reservacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservacion` (
  `noReservacion` int(11) NOT NULL AUTO_INCREMENT,
  `fechaReservacion` date NOT NULL,
  `actividad_idActividad` int(11) NOT NULL,
  `alumno_matriculaAlumno` varchar(9) NOT NULL,
  PRIMARY KEY (`noReservacion`),
  KEY `fk_reservacion_actividad1_idx` (`actividad_idActividad`),
  KEY `fk_reservacion_alumno1_idx` (`alumno_matriculaAlumno`),
  CONSTRAINT `fk_reservacion_actividad1` FOREIGN KEY (`actividad_idActividad`) REFERENCES `actividad` (`idActividad`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservacion_alumno1` FOREIGN KEY (`alumno_matriculaAlumno`) REFERENCES `alumno` (`matriculaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservacion`
--

LOCK TABLES `reservacion` WRITE;
/*!40000 ALTER TABLE `reservacion` DISABLE KEYS */;
INSERT INTO `reservacion` VALUES (1,'2017-05-22',1,'S15011624'),(2,'2017-05-22',2,'S15011624'),(3,'2017-05-31',3,'S15011624');
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
  `tipoRetroalimentacion` varchar(45) NOT NULL,
  `seccion_idseccion` int(11) NOT NULL,
  `inscripcion_idinscripcion` int(11) NOT NULL,
  PRIMARY KEY (`noRetroalimentación`),
  KEY `fk_retroalimentación_seccion1_idx` (`seccion_idseccion`),
  KEY `fk_retroalimentación_inscripcion1_idx` (`inscripcion_idinscripcion`),
  CONSTRAINT `fk_retroalimentación_inscripcion1` FOREIGN KEY (`inscripcion_idinscripcion`) REFERENCES `inscripcion` (`idinscripcion`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_retroalimentación_seccion1` FOREIGN KEY (`seccion_idseccion`) REFERENCES `seccion` (`idseccion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seccion`
--

LOCK TABLES `seccion` WRITE;
/*!40000 ALTER TABLE `seccion` DISABLE KEYS */;
INSERT INTO `seccion` VALUES (1,1),(2,2),(3,3);
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
  CONSTRAINT `fk_SeguimientoCurso_has_curso1_curso1` FOREIGN KEY (`curso_idCurso`) REFERENCES `curso` (`nrcCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `seguimientoCurso`
--

LOCK TABLES `seguimientoCurso` WRITE;
/*!40000 ALTER TABLE `seguimientoCurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `seguimientoCurso` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(10) NOT NULL,
  `passwordUsuario` varchar(256) NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'zS15011624','c45bb4815941a3722ca2182bf4e707afc74bbdc745077722cbeff4db3fa4ce7c'),(2,'zS15011613','85a72bef03d94c5692c7d6a3d7b6621b4f517be44d12e241d6866f3114374ead'),(3,'12098','57738c9220798c31cb67b84d3199fb738a44373d52ab979b9489c80adb464a90'),(4,'67291','c5d9abe6b98f309c2a1b598d9f169bbed87b4982cbb2a8faa1d7bc5e6076f0b6'),(5,'92831','75907fd07a199208e8b1bb4b478f518b94eba104447c60432e39621df88380d2');
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

-- Dump completed on 2017-05-31 16:53:30
