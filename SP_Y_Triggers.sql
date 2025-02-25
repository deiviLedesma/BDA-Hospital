-- PROCEDIMIENTO PARA INSERTAR UN CLIENTE
DELIMITER $$
CREATE PROCEDURE insertarPaciente(
	IN p_nombre VARCHAR(25),
    IN p_apellidoPaterno VARCHAR(25),
    IN p_apellidoMaterno VARCHAR(25),
    IN p_fechaNacimiento DATE,
    IN p_correoElectronico VARCHAR(30),
    IN p_telefono VARCHAR(15),
    IN p_contrasenia varchar(125),
    IN p_calle varchar(125),
	IN p_colonia varchar(125),
	IN p_numero varchar(125)
)
BEGIN
	INSERT INTO pacientes (nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento,
						   correoElectronico, telefono, contrasenia, calle, colonia, numero)
			    VALUES (p_nombre, p_apellidoPaterno, p_apellidoMaterno, p_fechaNacimiento,
						p_correoElectronico, p_telefono, p_contrasenia, p_calle, p_colonia, p_numero);
END $$
DELIMITER ;

-- procedimiento para insertar una consulta
DELIMITER $$
CREATE PROCEDURE insertarConsulta(
	IN p_idCita INT,
    IN p_fechaConsulta DATE,
    IN p_diagnostico VARCHAR(255),
    IN p_tratamiento VARCHAR(255)
)
BEGIN
	INSERT INTO consulta (idCita, fechaConsulta, diagnostico, tratamiento)
				VALUES (p_idCita, p_fechaConsulta, p_diagnostico, p_tratamiento);
END $$
DELIMITER ;

-- Procedimiento para programar una cita
DELIMITER $$
CREATE PROCEDURE programarCita(
	IN p_tipo ENUM('PROGRAMADA', 'NO PROGRAMADA'),
	IN p_fechaHora DATETIME,
    IN p_folio VARCHAR(25),
    IN p_estado ENUM('PENDIENTE', 'DISPONIBLE', 'FINALIZADO', 'CANCELADO'),
    IN p_idPaciente INT,
    IN p_idMedico INT
)
BEGIN
	INSERT INTO citaMedica (tipo, fechaHora, folio, estado, idPaciente, idMedico)
				VALUE (p_tipo, p_fechaHora, p_folio, p_estado, p_idPaciente, p_idMedico);
END $$
DELIMITER ;

-- Procedimiento para cancelar una cita
DELIMITER $$
CREATE PROCEDURE cancelarCita(
	IN p_idCita INT
)
BEGIN
	UPDATE citaMedica
    SET	estado = 'CANCELADO'
	WHERE idCita = p_idCita AND TIMESTAMPDIFF(HOUR, NOW(), fechaHora) >24;
END $$
DELIMITER ;

-- Procedimiento para generar consulta sin cita previa
DELIMITER $$
CREATE PROCEDURE generarConsultaSinCita(
	IN p_idPaciente INT
)
BEGIN
	DECLARE v_idMedico INT;
    DECLARE v_folio VARCHAR(8);
    
    -- seleccionar un médico disponible
    SELECT idMedico INTO v_idMedico FROM medicos WHERE estado = 'ACTIVO' LIMIT 1;
    
    -- Generar folo aleatorio
    SET v_folio = CONCAT(LEFT(UUID(), 8));
    
    -- insertar la consulta sin cita
    INSERT INTO citaMedica (tipo, fechaHora, folio, estado, idPaciente, idMedico)
				VALUES ('NO PROGRAMADA', NOW(), v_folio, 'DISPONIBLE', p_idPaciente, v_idMedico);
		END $$
        DELIMITER ;
        
-- triggers
        
        
-- Trigger para auditoría de inserción de pacientes
DELIMITER $$
CREATE TRIGGER auditoriaPacientes
AFTER INSERT ON pacientes
FOR EACH ROW
BEGIN
    INSERT INTO auditoria(tablaAfectada, tipoAccion, descripcion, fechHora, usuarioResponsable)
    VALUES('pacientes', 'INSERT', CONCAT('Nuevo paciente registrado: ', NEW.idPaciente), NOW(), NEW.idPaciente);
END $$
DELIMITER ;



-- Trigger para auditoría de inserción de medicos
DELIMITER $$
CREATE TRIGGER auditoriaMedicos
AFTER INSERT ON medicos
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (usuarioResponsable, tablaAfectada, tipoAccion, descripcion)
    VALUES (NEW.idMedico, 'medicos', 'INSERT', 
            CONCAT('Nuevo médico registrado: ', NEW.nombre, ' ', NEW.apellidoPaterno, ' ', IFNULL(NEW.apellidoMaterno, ''), ', Especialidad: ', NEW.especialidad, ', Cédula: ', NEW.cedulaProfesional));
END $$
DELIMITER ;

drop trigger auditoriaConsultas;

-- Trigger para auditoría de inserción de consultas
DELIMITER $$
CREATE TRIGGER auditoriaConsultas
AFTER INSERT ON consulta
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (usuarioResponsable, tablaAfectada, tipoAccion, descripcion)
    VALUES (NEW.idConsulta, 'consulta', 'INSERT', 
            CONCAT('Nueva consulta registrada con ID ', NEW.idConsulta, ' para la cita ', NEW.idCita, ' el ', NEW.fechaConsulta, '. Diagnóstico: ', NEW.diagnostico));
END $$
DELIMITER ;

drop trigger auditoriaCitas;
-- Trigger para auditoría para la inserción de citas médicas
DELIMITER $$
CREATE TRIGGER auditoriaCitas
AFTER INSERT ON citamedica
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (usuarioResponsable, tablaAfectada, tipoAccion, descripcion)
    VALUES (NEW.idMedico, 'consulta', 'INSERT', 
            CONCAT('Nueva cita programada para el paciente ', NEW.idPaciente, ' con el médico ', NEW.idMedico, ' el ', NEW.diaSemana, ' a las ', NEW.hora));
END $$
DELIMITER ;

/*
-- Trigger para auditoría para la inserción de direccionesPacientes
DELIMITER $$
CREATE TRIGGER auditoriaDireccionesPacientes
AFTER INSERT ON direcciones_pacientes
FOR EACH ROW
BEGIN
			INSERT INTO auditoria(tablaAfectada,tipoAccion, descripcion, fechaHora, usuarioResponasble)
						VALUES('direcciones_pacientes', 'INSERT', CONCAT('Nueva direccion agregada para el paciente: ', NEW.idPaciente), NOW());
END $$
DELIMITER ;

*/


-- Trigger para auditoría para la inserción de Horarios medicos
DELIMITER $$
CREATE TRIGGER auditoriaHorarios
AFTER INSERT ON horarios_medicos
FOR EACH ROW
BEGIN
    INSERT INTO auditoria (usuarioResponsable, tablaAfectada, tipoAccion, descripcion)
    VALUES (NEW.idHorario, 'medicos', 'INSERT', 
            CONCAT('Nuevo horario agregado para el médico ', NEW.idMedico, ' el ', NEW.diaSemana, ' de ', NEW.horaInicio, ' a ', NEW.horaFin));
END $$
DELIMITER ;

-- Trigger para evitar que un médico reciba citas fuera de su horario
DELIMITER $$
CREATE TRIGGER validar_horario_medico
BEFORE INSERT ON citaMedica
FOR EACH ROW
BEGIN
	DECLARE v_count INT;
    
    SELECT COUNT(*) INTO v_count
    FROM horarios_medicos
    WHERE idMedico = NEW.idMedico
    AND DAYOFWEEK(NEW.fechaHora) = DAYOFWEEK(diaSemana)
    AND TIME(NEW.fechaHora) BETWEEN TIME (horaInicio) AND TIME (horaFin);
    
    IF v_count = 0 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El médico no tiene disponibilidad en ese horario';
	END IF;
END $$
DELIMITER ;

-- Trigger para evitar que un paciente tenga dos citas el mismo dia con el mismo médico
DELIMITER $$
CREATE TRIGGER negarDobleCita
BEFORE INSERT ON citaMedica
FOR EACH ROW
BEGIN
	DECLARE v_count INT;
    
    SELECT COUNT(*) INTO v_count
    FROM citaMedica
    WHERE idPaciente = NEW.idPaciente
    AND idMedico = NEW.idMedico
    AND DATE(fechaHora) = DATE(NEW.fechaHora);
    
    IF v_count > 0 THEN
		SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'El paciente ya tiene cita con este médico en esta fecha';
	END IF;
END $$
DELIMITER ;

-- ------------------------------------------------------------------------------------------
-- Procedimiento para obtener los horarios de cita disponibles del momento

DELIMITER $$

CREATE PROCEDURE ObtenerHorariosDisponibles(
    IN p_idMedico INT,
    IN p_diaSemana ENUM('lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado', 'domingo'),
    IN p_fecha DATE
)
BEGIN
    -- Generar los intervalos de 30 minutos y excluir horarios ocupados
    WITH RECURSIVE intervalos AS (
        -- Caso base: Primer horario del médico
        SELECT horaInicio AS hora
        FROM horarios_medicos
        WHERE idMedico = p_idMedico AND diaSemana = p_diaSemana

        UNION ALL

        -- Caso recursivo: Sumar 30 minutos en cada iteración
        SELECT ADDTIME(hora, '00:30:00')
        FROM intervalos
        WHERE ADDTIME(hora, '00:30:00') < (
            SELECT horaFin FROM horarios_medicos 
            WHERE idMedico = p_idMedico AND diaSemana = p_diaSemana
        )
    )

    -- Mostrar solo los horarios disponibles (sin citas programadas)
    SELECT i.hora 
    FROM intervalos i
    WHERE NOT EXISTS (
        SELECT 1 FROM citaMedica c
        WHERE c.idMedico = p_idMedico 
        AND c.diaSemana = p_fecha
        AND c.hora = i.hora
        AND c.estado = 'PENDIENTE'
    );

END$$

DELIMITER ;



-- ------------------------------------------------------------------------------------------
-- Procedimiento para obtener los horarios para la cita de emergencia

DELIMITER $$

CREATE PROCEDURE ObtenerHorariosDisponiblesEmergencia(
    IN p_idMedico INT,
    IN p_diaSemana ENUM('lunes', 'martes', 'miércoles', 'jueves', 'viernes', 'sábado', 'domingo')
)
BEGIN
    DECLARE v_horaFin TIME;

    -- Obtener la hora de finalización del médico para el día especificado
    SELECT horaFin INTO v_horaFin
    FROM horarios_medicos
    WHERE idMedico = p_idMedico AND diaSemana = p_diaSemana
    LIMIT 1;

    -- Si la hora actual ya pasó la hora de fin - 30 min, no devolver horarios
    IF NOW() >= SUBTIME(v_horaFin, '00:30:00') THEN
        SELECT NULL AS hora, NULL AS idMedico;
    ELSE
    -- Generar los intervalos de 30 minutos y excluir horarios ocupados
    WITH RECURSIVE intervalos AS (
        -- Caso base: hora actual
        SELECT DATE_FORMAT(DATE_ADD(NOW(), INTERVAL 30 - (MINUTE(NOW()) % 30) MINUTE), '%H:%i') AS hora
        FROM horarios_medicos
        WHERE idMedico = p_idMedico AND diaSemana = p_diaSemana

        UNION ALL

        -- Caso recursivo: Sumar 30 minutos en cada iteración
        SELECT ADDTIME(hora, '00:30:00')
        FROM intervalos
        WHERE ADDTIME(hora, '00:30:00') <= (
            SELECT SUBTIME(horaFin,'00:30:00')
            FROM horarios_medicos 
            WHERE idMedico = p_idMedico AND diaSemana = p_diaSemana
        )
    )

    -- Mostrar solo los horarios disponibles (sin citas programadas)
    SELECT i.hora, p_idMedico AS idMedico -- Ahora también retorna el idMedico
    FROM intervalos i
    WHERE NOT EXISTS (
        SELECT 1 FROM citaMedica c
        WHERE c.idMedico = p_idMedico 
        AND c.diaSemana = curdate()
        AND c.hora = i.hora
        AND c.estado = 'PENDIENTE'
    );
    END IF;

END$$

DELIMITER ;

