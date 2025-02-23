/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Entidades.Medico;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IMedicoDAO {
    public boolean eliminarMedico(int id) throws Persistencia.PersistenciaException;
    
    public List<Medico> consultarTodosMedicos();
    
    public List<Medico> consultarPorEspecialidad(String especialidad);
    
    public Medico buscarMedicoPorCedula (String cedula) throws PersistenciaException;
}
