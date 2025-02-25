/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

/**
 *
 * @author SDavidLedesma
 */
public class ConsultaBO {
   private final IConsultaDAO consultaDAO;
    private static final Logger LOG = Logger.getLogger(ConsultaBO.class.getName());
    private ConsultaMapper mapper = new ConsultaMapper();

    public ConsultaBO(IConexion conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
    }

    public boolean registrarConsulta(ConsultaDTO consulta) throws PersistenciaException, NegocioException {
        validarConsulta(consulta);
        Consulta consultaMapper = mapper.toEntity(consulta);
        try {
            Consulta ConsultaAgregada = consultaDAO.agregarCOnsulta(consultaMapper);
            return ConsultaAgregada != null;
        }catch(PersistenciaException ex){
            LOG.log(Level.SEVERE, "Error al agendar la cita en la BD", ex);
            throw new NegocioException("Hubo un error al agendar la cita.", ex);
        }
        
    }

    public Consulta obtenerConsultaPorId(int idConsulta) throws PersistenciaException {
        if (idConsulta <= 0) {
            throw new PersistenciaException("El ID de la consulta debe ser mayor a 0.");
        }
        return consultaDAO.obtenerConsultaPorId(idConsulta);
    }

    private void validarConsulta(ConsultaDTO consulta)throws Negocio.Exception.NegocioException, PersistenciaException {
        if (consulta == null) {
            throw new PersistenciaException("La consulta no puede ser nula.");
        }
        if (consulta.getFechaConsulta() == null || consulta.getFechaConsulta().isAfter(LocalDate.now())) {
            throw new PersistenciaException("La fecha de la consulta no puede ser futura o nula.");
        }
        if (consulta.getDiagnostico() == null || consulta.getDiagnostico().trim().isEmpty()) {
            throw new PersistenciaException("El diagnóstico no puede estar vacío.");
        }
        if (consulta.getTratamiento() == null || consulta.getTratamiento().trim().isEmpty()) {
           throw new PersistenciaException("El tratamiento no puede estar vacío.");
        }
        if (consulta.getIdCita()<= 0) {
            throw new PersistenciaException("El ID de la cita debe ser válido.");
        }
    }
}
