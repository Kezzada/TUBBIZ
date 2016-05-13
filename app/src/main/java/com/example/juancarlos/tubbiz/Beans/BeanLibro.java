package com.example.juancarlos.tubbiz.Beans;

import android.graphics.Bitmap;

/**
 * Clase Libro
 * @author Juan Carlos Quesada y Pedro Romero
 * @version 12.05.2016
 */

public class BeanLibro {

    private String isbn, nombre, editorial, autor, genero, tipo;
    private double precio;
    private Bitmap portada;
    /**
     * Constructor
     * @param isbn: contiene el ibsn del libro.
     * @param nombre: contiene el nombre del libro.
     * @param autor: contiene el nombre del autor del libro
     * @param editorial: contiene editorial del libro
     * @param genero: contiene el genero del libro.
     * @param tipo: contiene el tipo de tapa que contiene el libro.
     * @param precio: contiene el precio del libro (decimales) en euros
     * @param portada: contiene la imagen de la portada del libro (bitmap)
     */
    public BeanLibro(String isbn, String nombre, String autor, String editorial, String genero, String tipo, double precio, Bitmap portada) {
        this.isbn = isbn;
        this.nombre = nombre;
        this.autor = autor;
        this.editorial = editorial;
        this.genero = genero;
        this.tipo = tipo;
        this.precio = precio;
        this.portada = portada;
    }

    public BeanLibro(String nombre, double precio, Bitmap portada) {
        this.nombre = nombre;
        this.precio = precio;
        this.portada = portada;
    }

    /**
     * Getter.
     * @return isbn: isbn del libro*/
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bitmap getPortada() {
        return portada;
    }

    public void setPortada(Bitmap portada) {
        this.portada = portada;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
