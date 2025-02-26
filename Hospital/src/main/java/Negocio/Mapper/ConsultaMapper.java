/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.Mapper;

import Negocio.DTO.ConsultaDTO;
import Persistencia.Entidades.Consulta;

/**
 *
 * @author Ramón Zamudio
 */
public class ConsultaMapper {
    /**
     * metodo que transforma una consulta en constultaDTO
     * @param consulta conutlta a tranformar 
     * @return consulta ya transformada
     */
    public ConsultaDTO toDTO(Consulta consulta) {
        return new ConsultaDTO(
            consulta.getFechaConsulta(),
            consulta.getDiagnostico(),
            consulta.getTratamiento(),
            consulta.getCita()
        );
    }
    /**
     * metodo que transforma una consultaDTO a consulta
     * @param consultaDTO consulta a transformar
     * @return consulta ya transformada
     */
    public Consulta toEntity(ConsultaDTO consultaDTO) {
        Consulta consulta = new Consulta();
        consulta.setFechaConsulta(consultaDTO.getFechaConsulta());
        consulta.setDiagnostico(consultaDTO.getDiagnostico());
        consulta.setTratamiento(consultaDTO.getTratamiento());
        consulta.setCita(consultaDTO.getIdCita());
        return consulta;
    }
}
