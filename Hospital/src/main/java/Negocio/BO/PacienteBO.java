/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.BO;

import Negocio.DTO.PacienteDTOInicioSesion;
import Negocio.DTO.PacienteDTONuevo;
import Negocio.DTO.PacienteDTOViejo;
import Negocio.Exception.NegocioException;
import Negocio.Mapper.PacienteMapper;
import Persistencia.Conexion.IConexion;
import Persistencia.DAO.IPacienteDAO;
import Persistencia.DAO.PacienteDAO;
import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author SDavidLedesma
 */
public class PacienteBO {

    private static final Logger LOG = Logger.getLogger(PacienteBO.class.getName());
    private final IPacienteDAO pacienteDAO;
    private final PacienteMapper mapper = new PacienteMapper();

    public PacienteBO(IConexion conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
    }

    
    
    public boolean agregarPaciente(PacienteDTONuevo paciente)throws NegocioException {
        if(paciente == null){
            throw new NegocioException("El paciente no puede ser nulo.");
        }
        if(paciente.getNombre().isEmpty() || paciente.getApellidoPaterno().isEmpty() || paciente.getApellidoPaterno().isEmpty() 
                || paciente.getTelefono().isEmpty() || paciente.getCorreo().isEmpty() || 
                paciente.getCalle().isEmpty()|| paciente.getColonia().isEmpty()||paciente.getNumero().isEmpty()
                ||paciente.getContrasenia().isEmpty()){
            throw new NegocioException("Todos los campos son obligatorios.");
        }
        Paciente pacienteMapper = mapper.toEntity(paciente);
        
        try{
            Paciente pacienteGuardado = pacienteDAO.agregarPaciente(pacienteMapper);
            return pacienteGuardado != null;
        }catch(PersistenciaException ex){
            LOG.log(Level.SEVERE, "Error al guardar paciente en la BD", ex);
            throw new NegocioException("Hubo un error al guardar el paciente.", ex); 
        }
       
    }
    
    public List<PacienteDTOViejo> obtenerPorNombre(String nombre) throws NegocioException{
        try{
            List<Paciente> pacientes = pacienteDAO.buscarPacientePorNombre(nombre);
            if(pacientes==null){
                return null;
            }
            return mapper.toDTOViejoList(pacientes);
        }catch(PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al obtener la lista de activistas", ex);
            throw new NegocioException("No se pudo obtener la lista de activistas.", ex);
        }
    }
    
    public List<PacienteDTOViejo> obtenerTodosPacientes() throws NegocioException{
        List<Paciente> pacientes = pacienteDAO.MostrarTodosLosPacientes();
        return mapper.toDTOViejoList(pacientes);
    }
    
    public PacienteDTOViejo validarUsuario (PacienteDTOInicioSesion pacienteDTOinicioSesion) throws NegocioException{
        try{
            Paciente paciente = pacienteDAO.buscarPacientePorCorreo(pacienteDTOinicioSesion.getCorreo());
            if(paciente == null){
                throw new NegocioException("Usuario no encontrado");
            }
            if(!checarContrasenia(pacienteDTOinicioSesion.getContrasenia(), paciente.getContrasenia())){
                throw new NegocioException("Contraseña incorrecta");
            }
            return mapper.toViejoDTO(paciente);
        }catch(PersistenciaException ex) {
            LOG.log(Level.SEVERE, "Error al acceder a la base de datos", ex);
            throw new NegocioException("Error al validar el usuario. Intente más tarde.", ex);
        }
    }
    
    public boolean actualizarPaciente (int idPaciente, PacienteDTONuevo paciente) throws NegocioException{
        if(idPaciente <= 0){
            throw new NegocioException("El id no es valido");
        }
        if(paciente.getNombre().isEmpty() || paciente.getApellidoPaterno().isEmpty()
                || paciente.getTelefono().isEmpty() || paciente.getCorreo().isEmpty() || 
                paciente.getCalle().isEmpty()|| paciente.getColonia().isEmpty()||paciente.getNumero().isEmpty()
                ||paciente.getContrasenia().isEmpty()){
            throw new NegocioException("Faltan campos por llenar");
        }
        paciente.setNombre(formatoNombre(paciente.getNombre()));
        paciente.setApellidoPaterno(formatoNombre(paciente.getApellidoPaterno()));
        
        if (paciente.getApellidoMaterno() != null && !paciente.getApellidoMaterno().trim().isEmpty()) {
        paciente.setApellidoMaterno(formatoNombre(paciente.getApellidoMaterno()));
        }
        if (paciente.getFechaNacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento no puede estar vacía");
        }
        if (paciente.getTelefono().length() > 10 || paciente.getTelefono().length() > 10) {
            throw new NegocioException("El teléfono debe ser de 10 numeros");
        }
        if (paciente.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser una fecha futura.");
        }
        if (!paciente.getCorreo().matches("^[a-zA-Z0-9._%+-]+@gmail\\.com$")) {
        throw new NegocioException("El correo electrónico debe ser válido y terminar en @gmail.com.");
        }
        Paciente pacienteMapper = mapper.toEntity(paciente);
        
        try{
            return pacienteDAO.actualizarPaciente(idPaciente, pacienteMapper);
        } catch (PersistenciaException e){
            LOG.log(Level.SEVERE, "Error al actualizar al paciente con ID" + idPaciente,e);
            throw new NegocioException("No se pudo actualizar el paciente", e);
        }
        
    }
    
    
    //Para verificar que la contrasenia ingresada y la hasheada coinciden (usar en el inicio de sesion)
    private static boolean checarContrasenia(String contrasenia, String contraseniaHasheada) {
        return BCrypt.checkpw(contrasenia, contraseniaHasheada);
    }
    
    private String formatoNombre(String nombre) {
    nombre = nombre.trim().toLowerCase(); // Convertir todo a minúsculas
    return nombre.substring(0, 1).toUpperCase() + nombre.substring(1); // Primera letra mayúscula
}
}
