/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Persistencia;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author Ramón Zamudio
 */
public class Paciente {
    private int idPaciente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private LocalDate fechaNacimiento;
    private String correoElectronico;
    private String contrasenia;
    private String telefono;
    private String calle;
    private String colonia;
    private String numero;

    /**
     * constructor vacío
     */
    public Paciente() {
    }
    
    /**
     * constructor con todos los atributos
     * @param idPaciente
     * @param nombre 
     * @param apellidoPaterno 
     * @param apellidoMaterno 
     * @param fechaNacimiento 
     * @param correoElectronico
     * @param contrasenia
     * @param telefono
     * @param calle
     * @param colonia
     * @param numero 
     */
    public Paciente(int idPaciente, String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String correoElectronico, String contrasenia, String telefono, String calle, String colonia, String numero) {
        this.idPaciente = idPaciente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }
    /**
     * constructor sin id
     * @param nombre
     * @param apellidoPaterno
     * @param apellidoMaterno
     * @param fechaNacimiento
     * @param correoElectronico
     * @param contrasenia
     * @param telefono
     * @param calle
     * @param colonia
     * @param numero 
     */
    public Paciente(String nombre, String apellidoPaterno, String apellidoMaterno, LocalDate fechaNacimiento, String correoElectronico, String contrasenia, String telefono, String calle, String colonia, String numero) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.fechaNacimiento = fechaNacimiento;
        this.correoElectronico = correoElectronico;
        this.contrasenia = contrasenia;
        this.telefono = telefono;
        this.calle = calle;
        this.colonia = colonia;
        this.numero = numero;
    }

    public int getIdPaciente() {
        return idPaciente;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCalle() {
        return calle;
    }

    public String getColonia() {
        return colonia;
    }

    public String getNumero() {
        return numero;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
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

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", nombre=" + nombre + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", fechaNacimiento=" + fechaNacimiento + ", correoElectronico=" + correoElectronico + ", contrasenia=" + contrasenia + ", telefono=" + telefono + ", calle=" + calle + ", colonia=" + colonia + ", numero=" + numero + '}';
    }
    

}
