/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.hospital;

import Persistencia.Conexion.Conexion;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.PacienteDAO;
import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author SDavidLedesma
 */
public class Hospital {

    public static void main(String[] args) throws SQLException, PersistenciaException{
        IConexion conexionBD = new Conexion();
        PacienteDAO pacienteDAO = new PacienteDAO(conexionBD);
       
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
        
        
        try{
            Paciente paciente1 = new Paciente("ramon", "zamudio", "ayala", LocalDate.of(1995, 6, 15), "ramonsebas5@gmail.com", "ramonsebas1", "6441295639", "orion", "las flores", "1313");
            Paciente pacienteGuardado = pacienteDAO.agregarPaciente(paciente1);
            if(pacienteGuardado != null && pacienteGuardado.getIdPaciente()>0){
                System.out.println("paciente guardado con exito"+pacienteGuardado);
            }else{
                System.out.println("No se pudo guardar el paciente");
            }
        }catch(Exception e){
            System.err.println("Error en la al insertar: " + e.getMessage());
            e.printStackTrace();
        }
        
        /*
        List<Paciente> pacientes =pacienteDAO.buscarPacientePorNombre("ramon");
        System.out.println(pacientes);
        List<Paciente> pacientes =pacienteDAO.MostrarTodosLosPacientes();
        System.out.println(pacientes);
        */
        
    }    
}
