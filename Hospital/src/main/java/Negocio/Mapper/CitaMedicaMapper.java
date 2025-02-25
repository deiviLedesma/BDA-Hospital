/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Mapper;

import Negocio.DTO.CitaAgendadaDTO;
import Negocio.DTO.CitaNoAgendadaDTO;
import Persistencia.Entidades.CitaMedica;

/**
 *
 * @author rodri
 */
public class CitaMedicaMapper {
    public CitaMedica toEntity (CitaAgendadaDTO dto){
        if(dto == null){
            return null;
        }
        CitaMedica cita = new CitaMedica();
        cita.setIdMedico(dto.getIdMedico());
        cita.setIdPaciente(dto.getIdPaciente());
        cita.setDiaSemana(dto.getDiaSemana());
        cita.setHora(dto.getHora());
        cita.setProgramada(dto.isProgramada());

        return cita;
    }
    
    // Convierte de Entidad a DTO
    public static CitaAgendadaDTO toDTO(CitaMedica cita) {
        if (cita == null) {
            return null;
        }

        return new CitaAgendadaDTO(
            cita.getIdMedico(),
            cita.getIdPaciente(),
            cita.getDiaSemana(),
            cita.getHora()
            
        );
    }
    
    public CitaMedica toEntityNoAgendado (CitaNoAgendadaDTO dto){
        if(dto == null){
            return null;
        }
        CitaMedica cita = new CitaMedica();
        cita.setIdMedico(dto.getIdMedico());
        cita.setIdPaciente(dto.getIdPaciente());
        cita.setDiaSemana(dto.getDiaSemana());
        cita.setHora(dto.getHora());
        cita.setProgramada(dto.isProgramada());

        return cita;
    }
    
    public static CitaNoAgendadaDTO toDTONoAgendado(CitaMedica cita) {
        if (cita == null) {
            return null;
        }

        return new CitaNoAgendadaDTO(
            cita.getIdMedico(),
            cita.getIdPaciente(),
            cita.getHora()
            
        );
    }
}
