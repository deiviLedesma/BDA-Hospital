/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.configuracion;

import Negocio.BO.CitaMedicaBO;
import Negocio.BO.MedicoBO;
import Negocio.BO.PacienteBO;
import Persistencia.Conexion.Conexion;
import Persistencia.Conexion.IConexion;

/**
 *
 * @author rodri
 */
public class DependencyInjector {
    
    public static PacienteBO crearPacienteBO(){
        IConexion conexion = new Conexion();
        PacienteBO pacienteBO = new PacienteBO(conexion);
        
        return pacienteBO;
    }
    
    public static MedicoBO crearMedicoBO(){
        IConexion conexion = new Conexion();
        MedicoBO medicoBO = new MedicoBO(conexion);
        
        return medicoBO;
    }
    
    public static CitaMedicaBO crearCitaMedicaBO(){
        IConexion conexion = new Conexion();
        CitaMedicaBO citaMedicaBO = new CitaMedicaBO(conexion);
        
        return citaMedicaBO;
    }
}
