/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Presentacion;

/**
 *
 * @author rodri
 */
public class SesionActual {
    private static String usuario;
    private static int idUsuario;
    private static String medico;
    private static int idMedico;

    public static String getMedico() {
        return medico;
    }

    public static void setMedico(String medico) {
        SesionActual.medico = medico;
    }

    public static int getIdMedico() {
        return idMedico;
    }

    public static void setIdMedico(int idMedico) {
        SesionActual.idMedico = idMedico;
    }
    
    

    // Métodos para acceder a los valores
    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        SesionActual.usuario = usuario;
    }

    public static int getIdUsuario() {
        return idUsuario;
    }

    public static void setIdUsuario(int idUsuario) {
        SesionActual.idUsuario = idUsuario;
    }
}
