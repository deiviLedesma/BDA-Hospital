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
