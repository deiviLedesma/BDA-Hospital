/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

import Negocio.DTO.CitaAgendadaDTO;
import Negocio.Exception.NegocioException;
import Negocio.Mapper.CitaMedicaMapper;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.CitaMedicaDAO;
import Persistencia.DAO.ICitaMedicaDAO;
import Persistencia.Entidades.CitaMedica;
import Persistencia.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rodri
 */
public class CitaMedicaBO {
    private static final Logger LOG = Logger.getLogger(CitaMedicaBO.class.getName());
    private final ICitaMedicaDAO citaMedicaDAO;
    private final CitaMedicaMapper mapper = new CitaMedicaMapper();

    public CitaMedicaBO(IConexion conexion) {
        this.citaMedicaDAO = new CitaMedicaDAO(conexion);
    }
    
    public boolean agendarCita(CitaAgendadaDTO cita) throws NegocioException{
        if (cita == null){
            throw new NegocioException("La cita no puede ser nula");
        }
        // Validar ID del paciente
        if (cita.getIdMedico() <= 0) {
            throw new NegocioException("El ID del médico no es válido.");
        }
        // Validar ID del paciente
        if (cita.getIdPaciente() <= 0) {
            throw new NegocioException("El ID del paciente no es válido.");
        }
        // Validar fecha
        if (cita.getDiaSemana().isBefore(LocalDate.now())) {
            throw new NegocioException("No se pueden agendar citas en fechas pasadas.");
        }
        CitaMedica citaMedicaMapper = mapper.toEntity(cita);
        try {
            CitaMedica citaProgramAgendada = citaMedicaDAO.agendarCita(citaMedicaMapper);
            return citaProgramAgendada != null;
        }catch(PersistenciaException ex){
            LOG.log(Level.SEVERE, "Error al agendar la cita en la BD", ex);
            throw new NegocioException("Hubo un error al agendar la cita.", ex);
        }
        
    }
    
    public List<LocalTime> obtenerHorariosDisponibles (int idMedico, LocalDate fecha) throws NegocioException{
        
        if (fecha == null){
            throw new NegocioException("Ingrese una fecha porfavor");
        }
        if (fecha.isBefore(LocalDate.now())) {
            throw new NegocioException("No puedes agendar citas en fechas pasadas.");
        }
        String diaSemana = fecha.getDayOfWeek().name().toLowerCase();
        if (null != diaSemana)switch (diaSemana) {
            case "monday" -> diaSemana = "lunes";
            case "tuesday" -> diaSemana = "martes";
            case "wednesday" -> diaSemana = "miércoles";
            case "thursday" -> diaSemana = "jueves";
            case "friday" -> diaSemana = "viernes";
            case "saturday" -> diaSemana = "sábado";
            case "sunday" -> diaSemana = "domingo";
            default -> {
            }
        }
        try{
        return citaMedicaDAO.obtenerHorariosDisponibles(idMedico, diaSemana, fecha);
        }catch(PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al acceder a la base de datos", ex);
            throw new NegocioException("Error al obtener horarios. Intente más tarde.", ex);
        }
    }
    
    
    
}
