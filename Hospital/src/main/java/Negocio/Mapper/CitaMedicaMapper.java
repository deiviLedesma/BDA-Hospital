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
    /**
     * metodo que transforma una citadto a una cita
     * @param dto cita a transformar
     * @return regresa la cita ya transformada
     */
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
    
   /**
     * Convierte de Entidad a DTO
     * @param cita cita a transformar
     * @return regresa la cita ya transformada
     */
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
    /**
     * metodo que convierte una cita no agendada a cita medica
     * @param dto cita a transformar
     * @return 
     */
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
    /**
     * metodo que transforma una cita a una citaNoAgendadaDTO
     * @param cita cita a transformar
     * @return regresa la cita ya trasformada
     */
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
