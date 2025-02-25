/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTO;

import java.time.LocalDate;

/**
 *
 * @author SDavidLedesma
 */
public class ConsultaDTO {

    private LocalDate fechaConsulta;
    private String diagnostico;
    private String tratamiento;
    private int idCita;

    public ConsultaDTO(LocalDate fechaConsulta, String diagnostico, String tratamiento, int idCita) {
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.idCita = idCita;
    }

    public ConsultaDTO() {
    }

    public LocalDate getFechaConsulta() {
        return fechaConsulta;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setFechaConsulta(LocalDate fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    @Override
    public String toString() {
        return "ConsultaDTO{" + "fechaConsulta=" + fechaConsulta + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", idCita=" + idCita + '}';
    }
    
    
    
}

