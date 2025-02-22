/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Entidades.CitaMedica;

/**
 *
 * @author rodri
 */
public interface ICitaMedicaDAO {
    
    public CitaMedica agendarCita(CitaMedica citaMedica) throws Persistencia.PersistenciaException;
    
}
