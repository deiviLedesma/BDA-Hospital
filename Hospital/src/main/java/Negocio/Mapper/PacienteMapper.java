/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Mapper;

import Negocio.DTO.PacienteDTONuevo;
import Negocio.DTO.PacienteDTOViejo;
import Persistencia.Entidades.Paciente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class PacienteMapper {
    /**
     * metodo que convierte un objeto pacienteDTONuevo a Paciente
     * @param pacienteDTO objeto que se transforma a paciente
     * @return regresa el pacienteDTONuevo ya transformado a paciente
     */
    public Paciente toEntity(PacienteDTONuevo pacienteDTO){
        if(pacienteDTO == null){
            return null;
        }
        return new Paciente(pacienteDTO.getNombre(), pacienteDTO.getApellidoPaterno(), pacienteDTO.getApellidoMaterno(), pacienteDTO.getFechaNacimiento(),
                pacienteDTO.getCorreo(), pacienteDTO.getContrasenia(), pacienteDTO.getTelefono(), pacienteDTO.getCalle(), pacienteDTO.getColonia(), 
                pacienteDTO.getNumero());
    }
    /**
     * metodo que transforma un paciente en pacienteDTONuevo
     * @param paciente paciente a transformar
     * @return regresa el paciente ya transformado a pacienteDTONuevo
     */
    public PacienteDTONuevo toNuevoDTO(Paciente paciente){
        if(paciente==null){
            return null;
        }
        return new PacienteDTONuevo(paciente.getNombre(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getFechaNacimiento(), paciente.getCorreoElectronico(),
                paciente.getTelefono(), paciente.getCalle(), paciente.getColonia(), paciente.getNumero(), paciente.getContrasenia());
    }
    /**
     * meotodo que transforma un paciente a pacienteDTOViejo
     * @param paciente paciente a transformar
     * @return regresa el paciente ya transformado en pacienteDTOViejo
     */
    public PacienteDTOViejo toViejoDTO(Paciente paciente){
        if(paciente==null){
            return null;
        }
        return new PacienteDTOViejo(paciente.getIdPaciente(),paciente.getNombre(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getFechaNacimiento(), paciente.getCorreoElectronico(),
                paciente.getTelefono(), paciente.getCalle(), paciente.getColonia(), paciente.getNumero(), paciente.getContrasenia());
    }
    /**
     * metodo que transforma una lista de pacientes a pacientesViejos
     * @param listaPacientes lista de pacientes
     * @return regresa la lista de pacientesViejos
     */
    public List<PacienteDTOViejo> toDTOViejoList(List<Paciente> listaPacientes){
        if(listaPacientes == null || listaPacientes.isEmpty()){
            return null;
        }
        List<PacienteDTOViejo> pacientesDTOViejos = new ArrayList<>();
        for(Paciente paciente : listaPacientes){
            pacientesDTOViejos.add(toViejoDTO(paciente));
        }
        return pacientesDTOViejos;
    }
}
