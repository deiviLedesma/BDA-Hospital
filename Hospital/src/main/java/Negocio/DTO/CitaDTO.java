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
public class CitaDTO {

    private String tipo;
    private LocalDate fechaHora;
    private String folio;
    private String estado;

    public CitaDTO() {
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
        this.fechaHora = fechaHora;
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

    @Override
    public String toString() {
        return "CitaDTO{" + "tipo=" + tipo + ", fechaHora=" + fechaHora + ", folio=" + folio + ", estado=" + estado + '}';
    }

}
