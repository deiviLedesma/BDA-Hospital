/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

import Negocio.DTO.CitaAgendadaDTO;
import Negocio.DTO.CitaNoAgendadaDTO;
import Negocio.DTO.MedicoDTOViejo;
import Negocio.Exception.NegocioException;
import Negocio.Mapper.CitaMedicaMapper;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.CitaMedicaDAO;
import Persistencia.DAO.ICitaMedicaDAO;
import Persistencia.DAO.IMedicoDAO;
import Persistencia.DAO.MedicoDAO;
import Persistencia.Entidades.CitaMedica;
import Persistencia.Entidades.Medico;
import Persistencia.PersistenciaException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
    private final IMedicoDAO medicoDAO;
    private final CitaMedicaMapper mapper = new CitaMedicaMapper();
    

    public CitaMedicaBO(IConexion conexion) {
        this.citaMedicaDAO = new CitaMedicaDAO(conexion);
        this.medicoDAO = new MedicoDAO(conexion);
    }
    
    public boolean agendarCita(CitaAgendadaDTO cita, String folio) throws NegocioException{
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
            CitaMedica citaProgramAgendada = citaMedicaDAO.agendarCita(citaMedicaMapper, folio);
            return citaProgramAgendada != null;
        }catch(PersistenciaException ex){
            LOG.log(Level.SEVERE, "Error al agendar la cita en la BD", ex);
            throw new NegocioException("Hubo un error al agendar la cita.", ex);
        }
        
    }
    
    public boolean agendarCitaEmergencia(CitaNoAgendadaDTO cita, String folio) throws NegocioException{
        if (cita == null){
            throw new NegocioException("La cita no puede ser nula");
        }
        if (cita.getIdMedico() <= 0) {
            throw new NegocioException("El ID del médico no es válido.");
        }
        if (cita.getIdPaciente() <= 0) {
            throw new NegocioException("El ID del paciente no es válido.");
        }
        CitaMedica citaMedicaMapper = mapper.toEntityNoAgendado(cita);
        try {
            CitaMedica citaNoProgramAgendada = citaMedicaDAO.agendarCita(citaMedicaMapper, folio);
            return citaNoProgramAgendada != null;
        }catch(PersistenciaException ex){
            LOG.log(Level.SEVERE, "Error al agendar la cita en la BD", ex);
            throw new NegocioException("Hubo un error al agendar la cita.", ex);
        }
    }
    
    
    
    
    public Object[] obtenerHorariosDisponibles (List<MedicoDTOViejo> medicos) throws NegocioException{
        List<Object[]> mejoresHorarios = new ArrayList<>();
        String diaSemana = LocalDate.now().getDayOfWeek().name().toLowerCase();
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
            for (MedicoDTOViejo medico : medicos) {
                List<Object[]> horariosConID = citaMedicaDAO.obtenerHorariosDisponibles(medico.getIdMedico(),diaSemana);
                if (!horariosConID.isEmpty()) {
                    Object[] primerElemento = horariosConID.get(0);
                    
                    if (primerElemento[0] != null && (int) primerElemento[1] != 0) {
                        mejoresHorarios.add(horariosConID.get(0));
                    }
                }
            }
            mejoresHorarios.sort((o1, o2) ->
                    LocalTime.parse((String) o1[0]).compareTo(LocalTime.parse((String) o2[0]))
            );
            return mejoresHorarios.get(0);
        }catch(PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al acceder a la base de datos", ex);
            throw new NegocioException("Error al obtener horarios. Intente más tarde.", ex);
        }
    }
    
    public List<String[]> citasPaciente (int idPaciente) throws NegocioException{
        List<String[]> citas = new ArrayList<>();
        if(idPaciente <= 0)
            throw new NegocioException("id de Paciente no válida");
        try{
            citas = citaMedicaDAO.obtenerCitasPaciente(idPaciente);
            
            return citas;
        }catch(PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al acceder a la base de datos", ex);
            throw new NegocioException("Error al obtener citas. Intente más tarde.", ex);
        }
    }
    
    public List<String[]> citasMedicos (int idMedico) throws NegocioException{
        List<String[]> citas = new ArrayList<>();
        if (idMedico <= 0) {
            throw new  NegocioException("id de Medico no válida");
        }
        try{
            citas = citaMedicaDAO.obtenerCitasMedicos(idMedico);
            return citas;
        }catch(PersistenciaException ex){
            LOG.log(Level.SEVERE, "Error al acceder a la base de datos", ex);
            throw new NegocioException("Erro al obtener citas. Intente más tarde", ex);
        }
    }
    
    
    
    
    
}
