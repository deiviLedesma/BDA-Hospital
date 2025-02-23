/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Mapper;

import Negocio.DTO.MedicoDTONuevo;
import Negocio.DTO.MedicoDTOViejo;
import Persistencia.Entidades.Medico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public class MedicoMapper {
    
    public Medico toEntity(MedicoDTONuevo medicoNuevo){
        if(medicoNuevo == null){
            return null;
        }
        return new Medico(medicoNuevo.getNombre(), medicoNuevo.getApellidoPaterno()
                , medicoNuevo.getApellidoMaterno(), 
                medicoNuevo.getEspecialidad(), 
                medicoNuevo.getCeludaProfesional(), 
                medicoNuevo.getEstado(), medicoNuevo.getContrasenia());
    }
    
    public MedicoDTONuevo toNuevoDTO(Medico medico){
        if(medico == null){
            return null;
        }
        return new MedicoDTONuevo(medico.getNombre(), medico.getApellidoPaterno(), 
                medico.getApellidoMaterno(), 
                medico.getEspecialidad(), 
                medico.getCeludaProfesional(),
                medico.getEstado(), medico.getContrasenia());
    }
    
    public MedicoDTOViejo toViejoDTO(Medico medico){
       if(medico == null){
            return null;
        }
        return new MedicoDTOViejo(medico.getIdMedico(),medico.getNombre(), medico.getApellidoPaterno(), 
                medico.getApellidoMaterno(), 
                medico.getEspecialidad(), 
                medico.getCeludaProfesional(), 
                medico.getEstado(), medico.getContrasenia());
    }
    
    public List<MedicoDTOViejo> toDTOViejoList(List<Medico> listaMedicos){
        if(listaMedicos == null || listaMedicos.isEmpty()){
            return null;
        }
        List<MedicoDTOViejo> pacientesDTOViejos = new ArrayList<>();
        for(Medico medico : listaMedicos){
            pacientesDTOViejos.add(toViejoDTO(medico));
        }
        return pacientesDTOViejos;
    }
}
