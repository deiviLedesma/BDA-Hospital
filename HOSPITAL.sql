CREATE DATABASE IF NOT EXISTS clinica;
USE clinica;


CREATE TABLE paciente (
idPaciente int auto_increment primary key,
nombre varchar(50) not null,
apellidoPaterno varchar(25) not null,
apellidoMaterno varchar(25) null,
fechaNacimiento DATE not null,
correoElectronico varchar(50) unique,
contrasenia varchar (225) not null
);

CREATE TABLE telefonos_pacientes(
idTelefono int auto_increment primary key,
telefono varchar(15),
idPaciente int,
foreign key(idPaciente) references paciente(idPaciente)
);

CREATE TABLE direcciones_pacientes(
idDireccion int auto_increment primary key,
calle varchar (50) null,
colonia varchar (50) null,
numero varchar(7) null,
idPaciente int,
foreign key (idPaciente) references paciente(idPaciente)
);


CREATE TABLE medico(
idMedico int auto_increment primary key,
nombre varchar(50) not null,
apellidoPaterno varchar(50) not null,
apellidoMaterno varchar(50) null,
especialidad varchar(50) not null,
cedulaProfesional varchar(50) not null,
estado ENUM('ACTIVO', 'INACTIVO') DEFAULT 'ACTIVO',
contrasenia varchar(225) not null
-- horario varchar
-- o crear tabla exrta horario manejar rango de horas por dia
);

CREATE TABLE horario_medicos(
idHorario int auto_increment primary key,
diaSemana DATE,
horaInicio datetime,
horaFin datetime,
idMedico int,
foreign key(idMedico) references medico(idMedico)
);

CREATE TABLE consulta(
idConsulta int auto_increment primary key,
folio varchar(255) not null
-- idPaciente
-- idMedico
);

CREATE TABLE citaMedica(
idCita int auto_increment primary key,
fechaHora datetime,
estado enum('ACTIVA','FINALIZADA','PENDIENTE') default 'ACTIVA'
);

CREATE TABLE historial_medico(
idHistorial int auto_increment primary key,
diagnostico varchar(255) not null,
tratamiento varchar(255) null,
fechaConsulta DATE not null
-- idConsulta
);


-- trigger para insertar al historial en caso de auditoria
DELIMITER $$
CREATE TRIGGER insertar_consulta
AFTER INSERT ON consulta
FOR EACH ROW
BEGIN
	-- cada vez que se crea una consulta
    -- crea un regisftro para el hisotrial
    INSERT INTO historial_medico(
    -- idConsulta
    diagnostico,
    tratamiento
    fechaConsulta
    )
    VALUES(
    /*
    NEW.idConsulta, -- SIRVE PARA VINCULAR EL HISOTRIAL CON LA CONSULTA RECIEN HECHA
    -- Tomamos el diagnostico inicial de la tabla consulta
    IFNULL(NEW.diagnostico, 'Sin diagnóstico inicial'),
    NULL, -- El tratamiento puede  ser nulo
    DATE(NEW.fechaConsulta)
  );
    */
END $$
DELIMITER ;

DELIMITER $$
CREATE PROCEDURE agendar_cita(
IN p_idPaciente INT,
IN p_idMedico int,
IN p_fechaConsulta DATETIME
)
BEGIN
	DECLARE v_count INT DEFAULT 0;
    
    -- validar que no exista mas de una cita con el mismo medico en la misma fecha
    SELECT COUNT(*) INTO v_count
    FROM consulta
    WHERE idPaciente = p_idPaciente
		  AND idMedico = p_idMedico
          AND DATE(fechaConsulta) = DATE(p_fechaConsulta);
          
          
IF v_count > 0 THEN
	SIGNAL SQLSTATE '45000'
		SET MESSAGE_TEXT = 'El paciente ya tiene una cita con el médico enesa fecha';
END IF;

INSERT INTO consulta(
	fechaConsulta,
    diagnostico
    -- idPaciente
    -- idMedico
    )
    VALUES(
    p_fechaConsulta,
    'Diagnostico'
    -- p_idPaciente
    -- p_idMedico
    );
END $$
DELIMITER ;
    

