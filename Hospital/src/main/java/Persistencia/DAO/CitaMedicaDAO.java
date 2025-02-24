/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Conexion.IConexion;
import Persistencia.Entidades.CitaMedica;
import Persistencia.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class CitaMedicaDAO implements ICitaMedicaDAO{

    IConexion conexion;
    private static final Logger LOG = Logger.getLogger(PacienteDAO.class.getName());
    
    
    public CitaMedicaDAO(IConexion conexion) {
        this.conexion = conexion;
    }
    
    
    @Override
    public CitaMedica agendarCita(CitaMedica citaMedica) throws PersistenciaException {
        String consultaSQL = "INSERT INTO citamedica (programada, diaSemana, hora, folio, idPaciente, idMedico) VALUES(?,?,?,?,?,?)";
        
        try(Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)){
            
            String folioGenerado = null;
            
            if(citaMedica.isProgramada()== false){
                folioGenerado = generarFolio();
            }
            
            ps.setBoolean(1, citaMedica.isProgramada());
            ps.setDate(2, java.sql.Date.valueOf(citaMedica.getDiaSemana()));
            ps.setTime(3, java.sql.Time.valueOf(citaMedica.getHora()));
            ps.setString(4, folioGenerado);
            ps.setInt(5, citaMedica.getIdPaciente());
            ps.setInt(6, citaMedica.getIdMedico());
            
            int filasAfectadas = ps.executeUpdate();
            if(filasAfectadas == 0){
                LOG.severe("La creacion de la cita fallo, no se inserto ninguna fila");
                throw new PersistenciaException("La creacion de la cita fallo, no se inserto ninguna fila");
            }
            try(ResultSet generatedKeys = ps.getGeneratedKeys()){
                if(generatedKeys.next()){
                    citaMedica.setIdCita(generatedKeys.getInt(1));
                    LOG.info("Cita agendada con exito con el ID: " + citaMedica.getIdCita());
                }else{
                    LOG.severe("Falla al agendar la cita");
                    throw new PersistenciaException("La creacion de la cita fallo no se inserto ninguna fila");
                }
            }
            return citaMedica;
        }catch (SQLException ex) {
            LOG.log(Level.SEVERE, "Error al agendar la cita");
            throw new PersistenciaException("La creacion de la cita fallo, no se inserto ninguna fila");
        }
    
    }
    
    @Override
    public List<LocalTime> obtenerHorariosDisponibles(int idMedico, String diaSemana, LocalDate fecha) throws PersistenciaException {
        List<LocalTime> horarios = new ArrayList<>();
        String consultaSQL = "{CALL ObtenerHorariosDisponibles(?, ?, ?)}";

        try (Connection con = this.conexion.crearConexion();
                CallableStatement cs = con.prepareCall(consultaSQL)) {
            cs.setInt(1, idMedico);
            cs.setString(2, diaSemana);
            cs.setDate(3, java.sql.Date.valueOf(fecha));

            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    horarios.add(rs.getTime("hora").toLocalTime()); // Agrega cada horario disponible
                }
            }
        }catch (SQLException e) {
        LOG.log(Level.SEVERE, "Error al hacer la lista de horarios", e);
        throw new PersistenciaException("Error al hacer la lista de horarios", e);
    }
        return horarios;
    }
    
    
    
    
    
    
    
    public static String generarFolio() {
        Random random = new Random();
        StringBuilder folio = new StringBuilder();
        
        for (int i = 0; i < 8; i++) {
            folio.append(random.nextInt(10)); // Genera un número entre 0 y 9
        }
        
        return folio.toString();
    }

    
    
    
}
