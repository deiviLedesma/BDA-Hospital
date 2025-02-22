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
    
    
    
}
