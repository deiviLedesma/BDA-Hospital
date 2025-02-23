/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTO;

/**
 *
 * @author rodri
 */
public class MedicoDTOInicioSesion {
    private String cedulaProfesional;
    private String contrasenia;

    public MedicoDTOInicioSesion() {
    }

    public MedicoDTOInicioSesion(String cedulaProfesional, String contrasenia) {
        this.cedulaProfesional = cedulaProfesional;
        this.contrasenia = contrasenia;
    }

    public String getCedulaProfesional() {
        return cedulaProfesional;
    }

    public void setCedulaProfesional(String cedulaProfesional) {
        this.cedulaProfesional = cedulaProfesional;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "MedicoDTOInicioSesion{" + "cedulaProfesional=" + cedulaProfesional + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
