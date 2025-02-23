/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia.DAO;

import Persistencia.Entidades.Paciente;
import Persistencia.PersistenciaException;
import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IPacienteDAO {

    public Paciente agregarPaciente(Paciente paciente)throws PersistenciaException;
    
    public List<Paciente> buscarPacientePorNombre(String nombre)throws PersistenciaException;
    
    public List<Paciente> MostrarTodosLosPacientes();
    
    public Paciente buscarPacientePorCorreo (String correo) throws PersistenciaException;
    
    public boolean actualizarPaciente(int idPaciente, Paciente paciente) throws PersistenciaException;
}
