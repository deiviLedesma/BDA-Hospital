/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTO;

/**
 *
 * @author Ramón Zamudio
 */
public class MedicoDTONuevo {
    private int idMedico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;
    private String celudaProfesional;
    private String estado;
    private String contrasenia;

    public MedicoDTONuevo(int idMedico, String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String celudaProfesional, String estado, String contrasenia) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.celudaProfesional = celudaProfesional;
        this.estado = estado;
        this.contrasenia = contrasenia;
    }

    public MedicoDTONuevo(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String celudaProfesional, String estado, String contrasenia) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.celudaProfesional = celudaProfesional;
        this.estado = estado;
        this.contrasenia = contrasenia;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getCeludaProfesional() {
        return celudaProfesional;
    }

    public String getEstado() {
        return estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setCeludaProfesional(String celudaProfesional) {
        this.celudaProfesional = celudaProfesional;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "MedicoDTONuevo{" + "idMedico=" + idMedico + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", celudaProfesional=" + celudaProfesional + ", estado=" + estado + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
