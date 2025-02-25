/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class CitaNoAgendadaDTO {
    
    private int idMedico;
    private int idPaciente;
    private LocalDate diaSemana;
    private LocalTime hora;
    private boolean programada;
    

    // Constructor vacío
    public CitaNoAgendadaDTO() {
        this.diaSemana = LocalDate.now();
        this.programada = false;
    }

    // Constructor con parámetros
    public CitaNoAgendadaDTO(int idMedico, int idPaciente, LocalTime hora) {
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.diaSemana = LocalDate.now();
        this.hora = hora;
        this.programada = false;
    }

    // Getters y Setters
    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public LocalDate getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(LocalDate diaSemana) {
        this.diaSemana = diaSemana;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public boolean isProgramada() {
        return programada;
    }
}
