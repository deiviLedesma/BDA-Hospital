/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.configuracion;

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
        PacienteBO activistaBO = new PacienteBO(conexion);
        
        return activistaBO;
    }
}
