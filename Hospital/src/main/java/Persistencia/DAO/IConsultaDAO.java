/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Entidades.Consulta;
import Persistencia.PersistenciaException;

/**
 *
 * @author Ramón Zamudio
 */
public interface IConsultaDAO {
    public Consulta agregarCOnsulta(Consulta consulta)throws PersistenciaException;
    
    public Consulta obtenerConsultaPorId(int idConsulta)throws PersistenciaException;
}
