/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTO;

/**
 *
 * @author SDavidLedesma
 */
public class MedicoDTOViejo {
    
    private int idMedico;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String especialidad;
    private String cedulaProfesional;
    private String estado;
    private String contrasenia;

    public MedicoDTOViejo(String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String cedulaProfesional, String estado,String contrasenia) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
        this.estado = estado;
        this.contrasenia = contrasenia;
    }

    public MedicoDTOViejo(int idMedico, String nombre, String apellidoPaterno, String apellidoMaterno, String especialidad, String cedulaProfesional, String estado, String contrasenia) {
        this.idMedico = idMedico;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.cedulaProfesional = cedulaProfesional;
        this.estado = estado;
        this.contrasenia = contrasenia;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "MedicoDTOViejo{" + "idMedico=" + idMedico + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", cedulaProfesional=" + cedulaProfesional + ", estado=" + estado + ", contrasenia=" + contrasenia + '}';
    }

    
    
}
