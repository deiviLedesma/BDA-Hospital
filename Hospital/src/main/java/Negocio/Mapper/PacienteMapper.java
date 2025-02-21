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
    public Paciente toEntity(PacienteDTONuevo pacienteDTO){
        if(pacienteDTO == null){
            return null;
        }
        return new Paciente(pacienteDTO.getNombre(), pacienteDTO.getApellidoPaterno(), pacienteDTO.getApellidoMaterno(), pacienteDTO.getFechaNacimiento(),
                pacienteDTO.getCorreo(), pacienteDTO.getContrasenia(), pacienteDTO.getTelefono(), pacienteDTO.getCalle(), pacienteDTO.getColonia(), 
                pacienteDTO.getNumero());
    }
    
    public PacienteDTONuevo toNuevoDTO(Paciente paciente){
        if(paciente==null){
            return null;
        }
        return new PacienteDTONuevo(paciente.getNombre(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getFechaNacimiento(), paciente.getCorreoElectronico(),
                paciente.getTelefono(), paciente.getCalle(), paciente.getColonia(), paciente.getNumero(), paciente.getContrasenia());
    }
    
    public PacienteDTOViejo toViejoDTO(Paciente paciente){
        if(paciente==null){
            return null;
        }
        return new PacienteDTOViejo(paciente.getIdPaciente(),paciente.getNombre(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getFechaNacimiento(), paciente.getCorreoElectronico(),
                paciente.getTelefono(), paciente.getCalle(), paciente.getColonia(), paciente.getNumero(), paciente.getContrasenia());
    }
    
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
