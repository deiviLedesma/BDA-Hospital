/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospital;

import Negocio.BO.CitaMedicaBO;
import Negocio.BO.MedicoBO;
import Negocio.BO.PacienteBO;
import Negocio.DTO.CitaAgendadaDTO;
import Negocio.DTO.MedicoDTOInicioSesion;
import Negocio.DTO.MedicoDTOViejo;
import Negocio.DTO.PacienteDTOInicioSesion;
import Negocio.DTO.PacienteDTONuevo;
import Negocio.DTO.PacienteDTOViejo;
import Negocio.Exception.NegocioException;
import Persistencia.Conexion.Conexion;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.CitaMedicaDAO;
import Persistencia.DAO.MedicoDAO;
import Persistencia.DAO.PacienteDAO;
import Persistencia.Entidades.CitaMedica;
import Persistencia.Entidades.Medico;
import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author SDavidLedesma
 */
public class Hospital {
    private static String hashearContrasenia(String contrasenia) {
        return BCrypt.hashpw(contrasenia, BCrypt.gensalt(10));
    }
    
    
    //Para verificar que la contrasenia ingresada y la hasheada coinciden (usar en el inicio de sesion)
    private static boolean checarContrasenia(String contrasenia, String contraseniaHasheada) {
        return BCrypt.checkpw(contrasenia, contraseniaHasheada);
    }
    
    public static void main(String[] args) throws SQLException, PersistenciaException, NegocioException{
        IConexion conexionBD = new Conexion();
        PacienteDAO pacienteDAO = new PacienteDAO(conexionBD);
        PacienteBO pacienteBO = new PacienteBO(conexionBD);
        MedicoDAO medicoDAO = new MedicoDAO(conexionBD);
        MedicoBO medicoBO = new MedicoBO(conexionBD);
        CitaMedicaDAO citaMedicaDAO = new CitaMedicaDAO(conexionBD);
        CitaMedicaBO citaMedicaBO = new CitaMedicaBO(conexionBD);
        
        
////      Prueba de encriptación de la contraseña  
//        String contra = "Doctor2";
//        
//        String hashedPW = hashearContrasenia(contra);
//        
//        System.out.println(hashedPW);
//        
//        Boolean checar = checarContrasenia(contra, "$2a$10$AeJyw9wxwjDqUvslg19x6.CAQRCv702p5XBl0kIdMLnd7vs9VtbRW");
//        
//        System.out.println(checar);    
        
        
////      Prueba de conexion  FUNCIONA
//        try (Connection conexion = conexionBD.crearConexion();) {
//
//            if (conexion != null && !conexion.isClosed()) {
//                System.out.println("Conexión exitosa a la base de datos.");
//            } else {
//                System.out.println("No se pudo establecer la conexión.");
//            }
//        } catch (PersistenciaException e) {
//            System.err.println("Error en la conexión: " + e.getMessage());
//            e.printStackTrace();
//        }
 

////      Prueba agregar a nivel persistencia  FUNCIONA
//        try{
//            Paciente paciente1 = new Paciente("rodrigo", "tovar", "canales", LocalDate.of(1995, 10, 27), "rodrigo_tovar@gmail.com", "rodrigoTC01", "6449982403", "san pedro", "capistrano", "2915");
//            Paciente pacienteGuardado = pacienteDAO.agregarPaciente(paciente1);
//            if(pacienteGuardado != null && pacienteGuardado.getIdPaciente()>0){
//                System.out.println("paciente guardado con exito"+pacienteGuardado);
//            }else{
//                System.out.println("No se pudo guardar el paciente");
//            }
//        }catch(PersistenciaException e){
//            System.err.println("Error en la al insertar: " + e.getMessage());
//            e.printStackTrace();
//        }
        
////      Prueba buscar paciente por nombre persistencia FUNCIONA
//        List<Paciente> pacientes =pacienteDAO.buscarPacientePorNombre("ramon");
//        System.out.println(pacientes);
        

////      Prueba mostrar todos los pacientes persistencia
//        List<Paciente> pacientes =pacienteDAO.MostrarTodosLosPacientes();
//        System.out.println(pacientes);



////      Prueba agregar paciente a nivel negocio FUNCIONA
//        Paciente p1 = new Paciente("Marcela", "Vidal", "Acosta", LocalDate.of(2005, 10, 31), "marcea@gmail.com", "Marcela01", "644132262", "san pedro", "capistrano", "2915");
//        PacienteDTONuevo pacienteDTON = new PacienteDTONuevo(p1.getNombre(), p1.getApellidoPaterno(), p1.getApellidoMaterno(), p1.getFechaNacimiento(), p1.getCorreoElectronico(), p1.getTelefono(), p1.getCalle(), p1.getColonia(), p1.getNumero(), p1.getContrasenia());
//        pacienteBO.agregarPaciente(pacienteDTON);

        
////      Mostar todos los pacientes a nivel negocio FUNCIONA
//        System.out.println(pacienteBO.obtenerTodosPacientes());
      
////      Mostrar x nombre a nivel negocio FUNCIONA
//        System.out.println(pacienteBO.obtenerPorNombre("ramon"));
//        List<Medico> medicos =medicoDAO.consultarTodosMedicos();
//        System.out.println(medicos);
//        List<Medico> medicosEspeciales =medicoDAO.consultarPorEspecialidad("Cardiología");
//        System.out.println(medicosEspeciales);
//        
//        medicoDAO.eliminarMedico(1);


////      Agendar Cita a nivel persistencia FUNCIONA
//        try{
//            CitaMedica cita1 = new CitaMedica(true, LocalDate.of(2025, 02, 27),LocalTime.of(11, 30), 1, 5);
//            CitaMedica citaAgendada = citaMedicaDAO.agendarCita(cita1);
//            if(citaAgendada != null && citaAgendada.getIdCita()>0){
//                System.out.println("Cita agendada con exito "+citaAgendada);
//            }else{
//                System.out.println("No se pudo agendar la cita");
//            }
//        }catch(PersistenciaException e){
//            System.err.println("Error en la al insertar: " + e.getMessage());
//            e.printStackTrace();
//        }

////      Agendar Cita a nivel negocio FUNCIONA
//        CitaMedica cita = new CitaMedica(true, LocalDate.of(2025, 02, 26), LocalTime.of(17, 30), 1, 2);
//        CitaAgendadaDTO citaAgendadaDTO = new CitaAgendadaDTO(cita.getIdMedico(), cita.getIdPaciente(), cita.getDiaSemana(), cita.getHora());
//        citaMedicaBO.agendarCita(citaAgendadaDTO);

////      Buscar paciente por correo a nivel persistencia FUNCIONA
//        Paciente paciente = pacienteDAO.buscarPacientePorCorreo("andres@gmail.com");
//        System.out.println(paciente);

////      Buscar paciente por correo a nivel negocio
//        PacienteDTOInicioSesion pacient = new PacienteDTOInicioSesion("andres@gmail.com", "Andres001");
//        PacienteDTOViejo paciente = pacienteBO.validarUsuario(pacient);
//        System.out.println(paciente);

////      Buscar medico por cedula a nivel persistencia FUNCIONA
//        Medico medico = medicoDAO.buscarMedicoPorCedula("2475630");
//        System.out.println(medico);

////      Buscar medico por cedula a nivel negocio
//        MedicoDTOInicioSesion medic = new MedicoDTOInicioSesion("1227765", "Doctor2");
//        MedicoDTOViejo medico = medicoBO.validarUsuario(medic);
//        System.out.println(medico);

        
    }    
}
