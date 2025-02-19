/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String consultaSQL = "insert into pacientes (nombre, apellidoPaterno, apellidoMaterno, correoElectronico, contrasenia, telefono, calle, colonia, numero,fechaNacimiento)values (?,?,?,?,?,?,?,?,?,?)";
        
        try(Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellidoPaterno());
            ps.setString(3, paciente.getApellidoPaterno());
            ps.setString(4, paciente.getCorreoElectronico());
            ps.setString(5, paciente.getContrasenia());
            ps.setString(6, paciente.getTelefono());
            ps.setString(7, paciente.getCalle());
            ps.setString(8, paciente.getColonia());
            ps.setString(9, paciente.getNumero());
            ps.setDate(10, java.sql.Date.valueOf(paciente.getFechaNacimiento()));
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
    
}
