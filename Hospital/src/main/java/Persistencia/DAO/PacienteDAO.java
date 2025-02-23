/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Conexion.IConexion;
import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Ramón Zamudio
 */
public class PacienteDAO implements IPacienteDAO {

    IConexion conexion;
    private static final Logger LOG = Logger.getLogger(PacienteDAO.class.getName());
    
    
    public PacienteDAO(IConexion conexion) {
        this.conexion = conexion;
    }

    @Override
    public Paciente agregarPaciente(Paciente paciente) throws PersistenciaException {
        String consultaSQL = "INSERT INTO pacientes(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, correoElectronico, telefono, contrasenia, calle, colonia, numero) VALUES(?,?,?,?,?,?,?,?,?,?)";
        
        try(Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)){
            
            String contraseniaHash = hashearContrasenia(paciente.getContrasenia());
            
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidoPaterno());
            ps.setString(3, paciente.getApellidoMaterno());
            ps.setDate(4, java.sql.Date.valueOf(paciente.getFechaNacimiento()));
            ps.setString(5, paciente.getCorreoElectronico());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, contraseniaHash);
            ps.setString(8, paciente.getCalle());
            ps.setString(9, paciente.getColonia());
            ps.setString(10, paciente.getNumero());
            
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                LOG.severe("la creacion del paciente fallo no se inserto ninguna fila");
                throw new PersistenciaException("la creacion del paciente fallo no se inserto ninguna fila");
            }
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    paciente.setIdPaciente(generatedKeys.getInt(1));
                    LOG.info("Paciente Creado con exito con el ID: "+paciente.getIdPaciente());
                }else{
                    LOG.severe("La creacion del paciente fallo, no se puedo crear al paciente");
                    throw new PersistenciaException("la creacion del paciente fallo no se inserto ninguna fila");
                }
            }
            return paciente;
        }catch(SQLException e){
            LOG.log(Level.SEVERE, "Error al crear Paciente");
            throw new PersistenciaException("la creacion del paciente fallo no se inserto ninguna fila");
        }
    }

    @Override
    public List<Paciente> buscarPacientePorNombre(String nombre) throws PersistenciaException {
        List<Paciente> pacientes = new ArrayList<>();
        String consultaSQL = "select * from pacientes where nombre =  ?";
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
                ps.setString(1, nombre);
                
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Paciente paciente = new Paciente(
                    rs.getInt("idPaciente"),
                    rs.getString("nombre"),
                    rs.getString("apellidoPaterno"),
                    rs.getString("apellidoMaterno"),
                    rs.getDate("fechaNacimiento").toLocalDate(), // Convertimos a LocalDate
                    rs.getString("correoElectronico"),
                    rs.getString("contrasenia"),
                    rs.getString("telefono"),
                    rs.getString("calle"),
                    rs.getString("colonia"),
                    rs.getString("numero")
                );
                pacientes.add(paciente);
                    }
                }
            
        }catch (SQLException e) {
        LOG.log(Level.SEVERE, "Error al buscar pacientes por nombre", e);
        throw new PersistenciaException("Error al buscar pacientes por nombre", e);
    }
        return pacientes;
    }

    @Override
    public List<Paciente> MostrarTodosLosPacientes() {
     List<Paciente> pacientes = new ArrayList<>();
        String consultaSQL = "select * from pacientes ";
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
                
                try(ResultSet rs = ps.executeQuery()){
                    while(rs.next()){
                        Paciente paciente = new Paciente(
                    rs.getInt("idPaciente"),
                    rs.getString("nombre"),
                    rs.getString("apellidoPaterno"),
                    rs.getString("apellidoMaterno"),
                    rs.getDate("fechaNacimiento").toLocalDate(), // Convertimos a LocalDate
                    rs.getString("correoElectronico"),
                    rs.getString("contrasenia"),
                    rs.getString("telefono"),
                    rs.getString("calle"),
                    rs.getString("colonia"),
                    rs.getString("numero")
                );
                pacientes.add(paciente);
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
        return pacientes;
    }
    
    @Override
    public Paciente buscarPacientePorCorreo(String correo) throws PersistenciaException {
        Paciente paciente = null;
        String consultaSQL = "SELECT * FROM pacientes WHERE correoElectronico = ?";
        
        try (Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            
            ps.setString(1, correo);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) { //verificamos que se haya obtenido algo
                    // Se crea el objeto paciente y se asignan sus propiedades
                    paciente = new Paciente( // es el que definimos al inicio
                    
                    rs.getInt("idPaciente"),
                    rs.getString("nombre"),
                    rs.getString("apellidoPaterno"),
                    rs.getString("apellidoMaterno"),
                    rs.getDate("fechaNacimiento").toLocalDate(), // Convertimos a LocalDate
                    rs.getString("correoElectronico"),
                    rs.getString("contrasenia"),
                    rs.getString("telefono"),
                    rs.getString("calle"),
                    rs.getString("colonia"),
                    rs.getString("numero")
                    );
                }
            }
        }catch (SQLException e) {
        LOG.log(Level.SEVERE, "Error al buscar pacientes por nombre", e);
        throw new PersistenciaException("Error al buscar pacientes por nombre", e);
    }
        return paciente;
    }
    
    
    
    //Para encriptar la contrasenia
    private static String hashearContrasenia(String contrasenia) {
        return BCrypt.hashpw(contrasenia, BCrypt.gensalt(10));
    }
    
    
    //Para verificar que la contrasenia ingresada y la hasheada coinciden (usar en el inicio de sesion)
    private static boolean checarContrasenia(String contrasenia, String contraseniaHasheada) {
        return BCrypt.checkpw(contrasenia, contraseniaHasheada);
    }

    
   
    
    
    
    
}