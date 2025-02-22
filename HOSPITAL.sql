create database HOSPITAL;
use HOSPITAL;

create table pacientes(
idPaciente int auto_increment primary key,
nombre varchar(25) not null,
apellidoPaterno varchar(25) not null,
apellidoMaterno varchar(25) null,
fechaNacimiento DATE not null,
correoElectronico varchar(30) not null unique,
telefono varchar(15) not null unique,
contrasenia varchar(125) not null,
calle varchar(125) not null,
colonia varchar(125) not null,
numero varchar(125) not null
);


create table medicos(
idMedico int auto_increment primary key,
nombre varchar(25) not null,
apellidoPaterno varchar(25) not null,
apellidoMaterno varchar(25) null,
especialidad varchar(30) not null,
cedulaProfesional varchar(50) not null unique,
estado ENUM('ACTIVO', 'INACTIVO') not null default 'ACTIVO',
contrasenia varchar(125) not null
);


create table horarios_medicos(
idHorario int auto_increment primary key,
diaSemana ENUM('lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado', 'domingo') not null,
horaInicio TIME not null,
horaFin TIME not null,
idMedico int,
FOREIGN KEY (idMedico) REFERENCES medicos (idMedico)
);


create table citaMedica(
idCita int auto_increment primary key,
programada boolean not null,
diaSemana DATE not null,
hora TIME not null,
folio varchar(8) unique null,
estado ENUM('PENDIENTE', 'FINALIZADO', 'CANCELADO') not null default 'PENDIENTE', -- pendiente se refiere a que esta asignada pero aun no se atiende
idPaciente int,
idMedico int,
FOREIGN KEY (idPaciente) REFERENCES pacientes (idPaciente),
FOREIGN KEY (idMedico) REFERENCES medicos (idMedico)
);

create table consulta(
idConsulta int auto_increment primary key,
fechaConsulta DATE not null,
diagnostico varchar(255) not null,
tratamiento varchar(255) not null,
idCita int not null,
FOREIGN KEY (idCita) REFERENCES citaMedica (idCita)
);

create table auditoria(
idAuditoria int auto_increment primary key,
fechHora DATETIME not null default CURRENT_TIMESTAMP,
usuarioResponsable int not null, -- da a referencia si el que realiza la accion es el paciente o medico para almacenarlo ahi
tablaAfectada ENUM('pacientes', 'medicos', 'consulta') not null,
tipoAccion ENUM('INSERT', 'UPDATE', '"DELETE"') not null
);

