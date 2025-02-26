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
    /**
     * constructor de la consulta que conecta con la base de datos
     * @param conexion conexion con la base de datos
     */
    public ConsultaBO(IConexion conexion) {
        this.consultaDAO = new ConsultaDAO(conexion);
    }
    /**
     * metod que manda la consulta a que se agrege en la capa de persistencia
     * @param consulta  consulta a agregar
     * @return regresa el resultado de la agregacion, verdadero si se agrego, falso en caso contrario
     * @throws PersistenciaException
     * @throws NegocioException 
     */
    public boolean registrarConsulta(ConsultaDTO consulta) throws PersistenciaException, NegocioException {
        validarConsulta(consulta);
        Consulta consultaMapper = mapper.toEntity(consulta);
        Consulta ConsultaAgregada = consultaDAO.agregarCOnsulta(consultaMapper);
        return ConsultaAgregada != null;
        
    }
    /**
     * metodo que obtiene una consulta por el id
     * @param idConsulta consulta la cual queremos obtener
     * @return  regresa la consulta que estamos buscando
     * @throws PersistenciaException 
     */
    public Consulta obtenerConsultaPorId(int idConsulta) throws PersistenciaException {
        if (idConsulta <= 0) {
            throw new PersistenciaException("El ID de la consulta debe ser mayor a 0.");
        }
        return consultaDAO.obtenerConsultaPorId(idConsulta);
    }
    /**
     * metodo que valida las consultas antes de ser agregadas
     * @param consulta  consulta antes de ser validada
     * @throws Negocio.Exception.NegocioException
     * @throws PersistenciaException 
     */
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
