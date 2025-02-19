/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Persistencia;

import java.util.List;

/**
 *
 * @author Ramón Zamudio
 */
public interface IPacienteDAO {

    public Paciente agregarPaciente(Paciente paciente)throws PersistenciaException;
    
    public List<Paciente> buscarPacientePorNombre(String nombre)throws PersistenciaException;
    
    public List<Paciente> MostrarTodosLosPacientes();
}
