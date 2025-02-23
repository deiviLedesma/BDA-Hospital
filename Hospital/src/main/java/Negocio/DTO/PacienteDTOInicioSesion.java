/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Negocio.DTO;

/**
 *
 * @author rodri
 */
public class PacienteDTOInicioSesion {
    private String correo;
    private String contrasenia;

    public PacienteDTOInicioSesion() {
    }

    public PacienteDTOInicioSesion(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    @Override
    public String toString() {
        return "PacienteDTOInicioSesion{" + "correo=" + correo + ", contrasenia=" + contrasenia + '}';
    }
    
    
}
