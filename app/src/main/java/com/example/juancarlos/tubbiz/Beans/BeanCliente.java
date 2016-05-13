package com.example.juancarlos.tubbiz.Beans;

/**
 * Clase cliente.
 *
 * @author Juan Carlos Quesada y Pedro Romero
 * @version 12.05.2016
 */

public class BeanCliente {

    String dni, nombre, apellido, email, password;

    /**
     * Constructor.
     *
     * @param dni: contiene dni del cliente.
     * @param nombre:   contiene nombre del cliente.
     * @param apellido: contiene el apellido del cliente.
     * @param email:    contiene el email del cliente.
     * @param password: contiene el password del cliente.
     */
    public BeanCliente(String dni, String nombre, String apellido, String email, String password) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
    }

    /**
     * Getter.
     * @return dni: dni del cliente
     */
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
