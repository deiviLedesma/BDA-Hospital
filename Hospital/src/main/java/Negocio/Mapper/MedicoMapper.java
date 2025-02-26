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
    /**
     * metodo que transforma un medicoDTO a medico
     * @param medicoNuevo medico a transformar
     * @return regresa al medico ya transformado
     */
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
    /**
     * metodo que transforma un medicoNuevo a medicodto
     * @param medico medico a transformar
     * @return regresa el medicodto transformado
     */
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
    /**
     * metodo que transforma un medico a medicoViejoDTo
     * @param medico medico a transformar
     * @return regresa un medicoViejoDTO 
     */
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
    /**
     * metodo que transforma una lista de medicos a una lista de medicosViejos
     * @param listaMedicos lista de medicos a transformar
     * @return regresa una lista de medicos transformados
     */
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
