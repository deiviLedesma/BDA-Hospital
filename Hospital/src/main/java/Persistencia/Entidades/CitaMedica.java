/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class CitaMedica {
    
    private int idCita;
    private boolean programada;
    private LocalDate diaSemana;
    private LocalTime hora;
    private String folio;
    private String estado;
    private int idPaciente;
    private int idMedico;

    public CitaMedica() {
    }

    public CitaMedica(int idCita, boolean programada, LocalDate diaSemana, LocalTime hora, int idPaciente, int idMedico) {
        this.idCita = idCita;
        this.programada = programada;
        this.diaSemana = diaSemana;
        this.hora = hora;
//        this.folio = folio;
//        this.estado = estado;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public CitaMedica(boolean programada, LocalDate diaSemana, LocalTime hora, int idPaciente, int idMedico) {
        this.programada = programada;
        this.diaSemana = diaSemana;
        this.hora = hora;
//        this.folio = folio;
//        this.estado = estado;
        this.idPaciente = idPaciente;
        this.idMedico = idMedico;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public boolean isProgramada() {
        return programada;
    }

    public void setProgramada(boolean programada) {
        this.programada = programada;
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

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    @Override
    public String toString() {
        return "CitaMedica{" + "idCita=" + idCita + ", programada=" + programada + ", diaSemana=" + diaSemana + ", hora=" + hora + ", folio=" + folio + ", estado=" + estado + ", idPaciente=" + idPaciente + ", idMedico=" + idMedico + '}';
    }
    
    
    
    
}
