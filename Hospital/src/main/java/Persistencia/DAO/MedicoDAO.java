/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Conexion.IConexion;
import Persistencia.Entidades.Medico;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ramón Zamudio
 */
public class MedicoDAO implements IMedicoDAO{
    
    IConexion conexion;
    private static final Logger LOG = Logger.getLogger(MedicoDAO.class.getName());
    
    public MedicoDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    
   
    @Override
    public boolean eliminarMedico(int id) throws PersistenciaException {
        String consultaSQL = " update medicos set estado =  \"INACTIVO\" where idMedico = ?";
        
        try(Connection con = this.conexion.crearConexion();
                PreparedStatement pstmt = con.prepareStatement(consultaSQL)) {
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;
        } catch (SQLException e) {
            if ("23000".equals(e.getSQLState())) { // obtenemos el codigo del error de SQL por ejemplo 23000 es el código estándar para errores de integridad
                throw new PersistenciaException("Error: No se puede eliminar el activista porque está asociado a otras tablas.", e);
            }
            throw new PersistenciaException("Error al eliminar activista.", e);        }
    }
    
    public List<Medico> consultarTodosMedicos(){
        List<Medico> listaMedicos = new ArrayList<>();
        String consultaSQL = "SELECT * FROM MEDICOS";
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
                
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                                rs.getString("apellidoPaterno"),
                                rs.getString("apellidoMaterno"),
                                rs.getString("especialidad"),
                                rs.getString("cedulaProfesional"),
                                rs.getString("estado"),
                                rs.getString("contrasenia")
                );
                listaMedicos.add(medico);
                    }
                }
            
        }catch (SQLException e) {
        LOG.log(Level.SEVERE, "Error al buscar pacientes por nombre", e);
         try {
             throw new PersistenciaException("Error al buscar pacientes por nombre", e);
         } catch (PersistenciaException ex) {
             Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }   catch (PersistenciaException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMedicos;
    }
    
    public List<Medico> consultarPorEspecialidad(String especialidad){
        List<Medico> listaMedicos = new ArrayList<>();
        String consultaSQL = "SELECT * FROM MEDICOS where especialidad = ?";
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
                ps.setString(1, especialidad);
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Medico medico = new Medico(
                    rs.getInt("idMedico"),
                    rs.getString("nombre"),
                                rs.getString("apellidoPaterno"),
                                rs.getString("apellidoMaterno"),
                                rs.getString("especialidad"),
                                rs.getString("cedulaProfesional"),
                                rs.getString("estado"),
                                rs.getString("contrasenia")
                );
                listaMedicos.add(medico);
                    }
                }
            
        }catch (SQLException e) {
        LOG.log(Level.SEVERE, "Error al buscar pacientes por nombre", e);
         try {
             throw new PersistenciaException("Error al buscar pacientes por nombre", e);
         } catch (PersistenciaException ex) {
             Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
         }
    }   catch (PersistenciaException ex) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaMedicos;
    }

    @Override
    public Medico buscarMedicoPorCedula(String cedula) throws PersistenciaException {
        Medico medico = null;
        String consultaSQL = "SELECT * FROM medicos WHERE cedulaProfesional = ?";
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            
            ps.setString(1, cedula);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { //verificamos que se haya obtenido algo
                    // Se crea el objeto medico y se asignan sus propiedades
                    medico = new Medico(
                            rs.getInt("idMedico"),
                            rs.getString("nombre"),
                            rs.getString("apellidoPaterno"),
                            rs.getString("apellidoMaterno"),
                            rs.getString("especialidad"),
                            rs.getString("cedulaProfesional"),
                            rs.getString("estado"),
                            rs.getString("contrasenia")
                    );
                }
            }
        }catch (SQLException e) {
        LOG.log(Level.SEVERE, "Error al buscar medico por cedula", e);
        throw new PersistenciaException("Error al buscar medico por cedula", e);
    }
        return medico;
    }
}

