/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

import Negocio.DTO.MedicoDTOViejo;
import Negocio.Exception.NegocioException;
import Negocio.Mapper.MedicoMapper;
import Persistencia.DAO.IMedicoDAO;
import Persistencia.DAO.IPacienteDAO;
import Persistencia.DAO.MedicoDAO;
import Persistencia.Entidades.Medico;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author SDavidLedesma
 */
public class MedicoBO {

    private static final Logger LOG = Logger.getLogger(MedicoBO.class.getName());
    private final IMedicoDAO medicoDAO;
    private MedicoMapper mapper = new MedicoMapper();

    public MedicoBO(IMedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
    }
    
    public boolean eliminarMedico(int id) throws NegocioException{
        if(id<0){
            throw new NegocioException("El ID debe ser un número válido.");        }
        try {
            return medicoDAO.eliminarMedico(id);
        } catch (PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al dar de baja al medico: " + id, ex);
            throw new NegocioException("No se pudo dar de baja al medico", ex);
        }
    }
    
    public List<MedicoDTOViejo> obtenerTodos() throws NegocioException {
        List<Medico> listaMedicos = medicoDAO.consultarTodosMedicos();
        return mapper.toDTOViejoList(listaMedicos);
    }
    
    public List<MedicoDTOViejo> obtenerXEspecialidad(String especialidad) throws NegocioException {
        List<Medico> listaMedicos = medicoDAO.consultarPorEspecialidad(especialidad);
        return mapper.toDTOViejoList(listaMedicos);
    }
}
