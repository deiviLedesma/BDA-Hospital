/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia.Entidades;

import java.time.LocalTime;

/**
 *
 * @author rodri
 */
public class CitaMedica {
    
    private int idCita;
    private boolean programada;
    private String dia;
    private LocalTime hora;
    private String folio;
    private String estado;
    private int idPaciente;
    private int idMedico;
}
