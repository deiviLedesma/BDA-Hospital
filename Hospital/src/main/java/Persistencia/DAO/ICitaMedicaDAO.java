/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Entidades.CitaMedica;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author rodri
 */
public interface ICitaMedicaDAO {
    
    public CitaMedica agendarCita(CitaMedica citaMedica) throws Persistencia.PersistenciaException;
    
    public List<LocalTime> obtenerHorariosDisponibles (int idMedico, String diaSemana, LocalDate fecha) throws Persistencia.PersistenciaException;
    
}
