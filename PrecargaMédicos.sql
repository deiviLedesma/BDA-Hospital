SELECT * FROM MEDICOS;
SELECT * FROM HORARIOS_MEDICOS;

INSERT INTO medicos (nombre, apellidoPaterno, apellidoMaterno, especialidad, cedulaProfesional, estado, contrasenia)
VALUES
	("Daniel", "Miramontes", "Iribe", "Cardiología", "2475630", "ACTIVO", "$2a$10$iaYSNxJt9NlYkle0EPI2peNcbb0lHoCEz3gkJqExKhCepNFIxtvcy"),	 	-- Contraseña Doctor1
    ("Jorge", "Cervantes", "Valdez", "Cardiología", "1227765", "ACTIVO", "$2a$10$AeJyw9wxwjDqUvslg19x6.CAQRCv702p5XBl0kIdMLnd7vs9VtbRW"),		-- Contraseña Doctor2
    ("Roberto", "Cornejo", "Solano", "Pediatria", "2060928", "ACTIVO", "$2a$10$GB.9ZaCID8Dbdbva0OXi7..7rPgbC.qFLHCLTHhTszkbv6Zeyp1Wu"),			-- Contraseña Doctor3
    ("Patricio", "Leon", "Villalobos", "Pediatria", "1320471", "ACTIVO", "$2a$10$TGMUsPhwtXVlovn1JS9o8.koGc3M9mBczfWf/69Jss1NEKh7Nr.u."),		-- Contraseña Doctor4
    ("Berenice", "Juarez", "Sierra", "Dermatologia", "1730708", "ACTIVO", "$2a$10$gBsOcpuVcYxAAwWuL9hKe.Iw1DZZlfnUaEiZQdqoc9T1TYz7/kdyi"),		-- Contraseña Doctor5
    ("Sergio", "Covarrubias", "Cazares", "Dermatologia", "2284209", "ACTIVO", "$2a$10$j5fkVvtpxJDYbq0vO.GToe3NPkvilO0FzFZmOj66tTmFXeLyshr7K"),	-- Contraseña Doctor6
    ("Jesus", "Ortega", "Amaro", "Neurologia", "1568007", "ACTIVO", "$2a$10$XniVIAXEgwGlVQO5V5FgculxFtKEM8/ddrZjO/jWD3FAC5kWjy1sy"),			-- Contraseña Doctor7
    ("Sebastian", "Blanchet", "Sanchez", "Neurologia", "4600184", "ACTIVO", "$2a$10$ayq.QfjYMI2XZ4VnPCUvwOiIrflk2lnpdF8E3Mv9UddlfoNnNxlyS"),	-- Contraseña Doctor8
    ("Marcos", "Medina", "Castillo", "Traumatologia", "2020286", "ACTIVO", "$2a$10$risAdkFgNv/wnVn5o1UDdOIALZUObSTmuJpNUmQIpvGNdfSGeXY2C"),		-- Contraseña Doctor9
    ("Jimena", "Garcia", "Beltran", "Traumatologia", "2020155", "ACTIVO", "$2a$10$XbtGpvYMQL41PItostG6e.KId/kHijafA.pKLcYAl9Of0/Hha55p.");		-- Contraseña Doctor10

INSERT INTO horarios_medicos (diaSemana, horaInicio, horaFin, idMedico)
VALUES
	("lunes","9:00","19:00",1),
    ("martes","9:00","19:00",1),
    ("miércoles","9:00","19:00",1),
    ("jueves","9:00","19:00",1),
    ("viernes","9:00","19:00",1),
    
    ("lunes","16:00","21:00",2),
    ("miércoles","16:00","21:00",2),
    ("viernes","16:00","21:00",2),
    
    ("lunes","9:00","19:00",3),
    ("martes","9:00","19:00",3),
    ("miércoles","9:00","19:00",3),
    ("jueves","9:00","19:00",3),
    ("viernes","9:00","19:00",3),
    
    ("lunes","16:00","21:00",4),
    ("miércoles","16:00","21:00",4),
    ("viernes","16:00","21:00",4),
    
    ("lunes","9:00","19:00",5),
    ("martes","9:00","19:00",5),
    ("miércoles","9:00","19:00",5),
    ("jueves","9:00","19:00",5),
    ("viernes","9:00","19:00",5),
    
    ("lunes","16:00","21:00",6),
    ("miércoles","16:00","21:00",6),
    ("viernes","16:00","21:00",6),
    
    ("lunes","9:00","19:00",7),
    ("martes","9:00","19:00",7),
    ("miércoles","9:00","19:00",7),
    ("jueves","9:00","19:00",7),
    ("viernes","9:00","19:00",7),
    
    ("lunes","16:00","21:00",8),
    ("miércoles","16:00","21:00",8),
    ("viernes","16:00","21:00",8),
    
    ("lunes","9:00","19:00",9),
    ("martes","9:00","19:00",9),
    ("miércoles","9:00","19:00",9),
    ("jueves","9:00","19:00",9),
    ("viernes","9:00","19:00",9),
    
    ("lunes","16:00","21:00",10),
    ("miércoles","16:00","21:00",10),
    ("viernes","16:00","21:00",10);
    
    
    
    
    
    