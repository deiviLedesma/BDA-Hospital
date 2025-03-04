/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Conexion.IConexion;
import Persistencia.Entidades.Consulta;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ramón Zamudio
 */
public class ConsultaDAO implements IConsultaDAO{
    IConexion conexion;
    private static final Logger LOG = Logger.getLogger(PacienteDAO.class.getName());

    public ConsultaDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    /**
     * metodo para agregar una consulta
     * @param consulta consulta a agregar 
     * @return regresa la consulta agregada
     * @throws PersistenciaException 
     */
    @Override
    public Consulta agregarCOnsulta(Consulta consulta)throws PersistenciaException {
        String consultaSQL = "insert into consulta(fechaConsulta, diagnostico, tratamiento, idCita) values(?,?,?,?);";
        
        try(Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)){
                
            ps.setDate(1, java.sql.Date.valueOf(consulta.getFechaConsulta()));
            ps.setString(2, consulta.getDiagnostico());
            ps.setString(3, consulta.getTratamiento());
            ps.setInt(4, consulta.getCita());
            
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                LOG.severe("la creacion de la consulta fallo no se inserto ninguna fila");
                throw new PersistenciaException("la creacion de la consulta fallo no se inserto ninguna fila");
            }
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    consulta.setIdConsulta(generatedKeys.getInt(1));
                    LOG.info("Consulta Creado con exito con el ID: "+consulta.getIdConsulta());
                }else{
                    LOG.severe("La creacion de la consulta fallo, no se puedo crear al paciente");
                    throw new PersistenciaException("la creacion de la consulta fallo no se inserto ninguna fila");
                }
            }
            return consulta;
        }catch(SQLException e){
                LOG.log(Level.SEVERE, "Error al crear la consulta");
                throw new PersistenciaException("la creacion de la consulta fallo no se inserto ninguna fila");
        }
    }
    /**
     * metodo que obtiene una consulta por su id
     * @param idConsulta id de la consulta a buscar
     * @return regresa la consulta que buscamos
     * @throws PersistenciaException 
     */
    @Override
    public Consulta obtenerConsultaPorId(int idConsulta) throws PersistenciaException {
        Consulta consulta = null;
        String consultaSQL = "select * from consulta where idConsulta = ?";
        try (Connection con = this.conexion.crearConexion();
            PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            ps.setInt(1, idConsulta);

            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    consulta = new Consulta();
                    consulta.setIdConsulta(rs.getInt("idConsulta"));
                    LocalDate fechaConsulta = rs.getObject("fechaConsulta", LocalDate.class);
                    consulta.setFechaConsulta(fechaConsulta);
                    consulta.setDiagnostico(rs.getString("diagnostico"));
                    consulta.setTratamiento(rs.getString("tratamiento"));
                    consulta.setCita(rs.getInt("idCita"));

                    consulta.setFechaConsulta(rs.getDate("fechaConsulta").toLocalDate());
                    LOG.info("consulta encontrado: " + consulta);
                }else{
                LOG.warning("No se encontró una consulta con ID: " + idConsulta); // no es error, solo advertencia
                }   
            }

        }catch (SQLException ex) {
            LOG.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        return consulta;
    }

    
}
