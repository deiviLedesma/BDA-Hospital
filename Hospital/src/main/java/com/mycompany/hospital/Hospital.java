/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospital;

import Negocio.BO.PacienteBO;
import Negocio.DTO.PacienteDTONuevo;
import Negocio.DTO.PacienteDTOViejo;
import Negocio.Exception.NegocioException;
import Persistencia.Conexion.Conexion;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.PacienteDAO;
import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author SDavidLedesma
 */
public class Hospital {

    public static void main(String[] args) throws SQLException, PersistenciaException, NegocioException{
        IConexion conexionBD = new Conexion();
        PacienteDAO pacienteDAO = new PacienteDAO(conexionBD);
        PacienteBO paciente = new PacienteBO(conexionBD);
        
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
        
        
//        try{
//            Paciente paciente1 = new Paciente("ramon", "zamudio", "ayala", LocalDate.of(1995, 6, 15), "ramonsebas5@gmail.com", "ramonsebas1", "6441295639", "orion", "las flores", "1313");
//            Paciente pacienteGuardado = pacienteDAO.agregarPaciente(paciente1);
//            if(pacienteGuardado != null && pacienteGuardado.getIdPaciente()>0){
//                System.out.println("paciente guardado con exito"+pacienteGuardado);
//            }else{
//                System.out.println("No se pudo guardar el paciente");
//            }
//        }catch(Exception e){
//            System.err.println("Error en la al insertar: " + e.getMessage());
//            e.printStackTrace();
//        }
        
        
//        List<Paciente> pacientes =pacienteDAO.buscarPacientePorNombre("ramon");
//        System.out.println(pacientes);
        
//        List<Paciente> pacientes =pacienteDAO.MostrarTodosLosPacientes();
//        System.out.println(pacientes);
        //agregar paciente
        Paciente paciente1 = new Paciente("soy", "muy", "puto", LocalDate.of(2005, 11, 15), "aaaa", "dddd", "6125487512", "4454", "sdasd", "4545");
        PacienteDTONuevo pacienteDTON = new PacienteDTONuevo(paciente1.getNombre(), paciente1.getApellidoPaterno(), paciente1.getApellidoMaterno(), paciente1.getFechaNacimiento(), paciente1.getCorreoElectronico(), paciente1.getTelefono(), paciente1.getCalle(), paciente1.getColonia(), paciente1.getNumero(), paciente1.getNumero());
        paciente.agregarPaciente(pacienteDTON);
        //mostar todos
        System.out.println(paciente.obtenerTodosPacientes());
        
        //mostrar x nombre
        System.out.println(paciente.obtenerPorNombre("ramon"));
        
        
    }    
}
