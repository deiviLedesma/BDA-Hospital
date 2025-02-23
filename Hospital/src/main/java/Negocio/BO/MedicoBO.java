/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

import Negocio.DTO.MedicoDTOInicioSesion;
import Negocio.DTO.MedicoDTOViejo;
import Negocio.DTO.PacienteDTOInicioSesion;
import Negocio.DTO.PacienteDTOViejo;
import Negocio.Exception.NegocioException;
import Negocio.Mapper.MedicoMapper;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.IMedicoDAO;
import Persistencia.DAO.IPacienteDAO;
import Persistencia.DAO.MedicoDAO;
import Persistencia.Entidades.Medico;
import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author SDavidLedesma
 */
public class MedicoBO {

    private static final Logger LOG = Logger.getLogger(MedicoBO.class.getName());
    private final IMedicoDAO medicoDAO;
    private MedicoMapper mapper = new MedicoMapper();

    public MedicoBO(IConexion conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
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
    
    public MedicoDTOViejo validarUsuario (MedicoDTOInicioSesion medicoDTOinicioSesion) throws NegocioException{
        try{
            Medico medico = medicoDAO.buscarMedicoPorCedula(medicoDTOinicioSesion.getCedulaProfesional());
            if(medico == null){
                throw new NegocioException("Usuario no encontrado");
            }
            if(!checarContrasenia(medicoDTOinicioSesion.getContrasenia(), medico.getContrasenia())){
                throw new NegocioException("Contraseña incorrecta");
            }
            return mapper.toViejoDTO(medico);
        }catch(PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al acceder a la base de datos", ex);
            throw new NegocioException("Error al validar el usuario. Intente más tarde.", ex);
        }
    }
    
    
    //Para verificar que la contrasenia ingresada y la hasheada coinciden (usar en el inicio de sesion)
    private static boolean checarContrasenia(String contrasenia, String contraseniaHasheada) {
        return BCrypt.checkpw(contrasenia, contraseniaHasheada);
    }
}
