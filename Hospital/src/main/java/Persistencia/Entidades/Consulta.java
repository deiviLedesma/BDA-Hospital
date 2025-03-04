/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import java.time.LocalDate;

/**
 *
 * @author Ramón Zamudio
 */
public class Consulta {
    private int idConsulta;
    private LocalDate fechaConsulta;
    private String diagnostico;
    private String tratamiento;
    private int cita;

    public Consulta() {
    }
    
    public Consulta(int idConsulta, LocalDate fechaConsulta, String diagnostico, String tratamiento, int cita) {
        this.idConsulta = idConsulta;
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    public Consulta(LocalDate fechaConsulta, String diagnostico, String tratamiento, int cita) {
        this.fechaConsulta = fechaConsulta;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    public int getIdConsulta() {
        return idConsulta;
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

    public int getCita() {
        return cita;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
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

    public void setCita(int cita) {
        this.cita = cita;
    }

    @Override
    public String toString() {
        return "Consulta{" + "idConsulta=" + idConsulta + ", fechaConsulta=" + fechaConsulta + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", cita=" + cita + '}';
    }
  
}
