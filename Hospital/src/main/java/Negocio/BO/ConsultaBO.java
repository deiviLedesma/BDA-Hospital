/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

import Negocio.DTO.ConsultaDTO;
import Negocio.Exception.NegocioException;
import Negocio.Mapper.ConsultaMapper;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.ConsultaDAO;
import Persistencia.DAO.IConsultaDAO;
import Persistencia.Entidades.Consulta;
import Persistencia.PersistenciaException;
import java.time.LocalDate;
import java.util.logging.Logger;

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
        Consulta ConsultaAgregada = consultaDAO.agregarCOnsulta(consultaMapper);
        return ConsultaAgregada != null;
        
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
