package com.valentincolato.alcoholcalculator;

public class Drink {
    private String nombre;
    private Double graduacion;
    private int cant_ing;

    public String getNombre() {
        return nombre;
    }

    public Double getGraduacion() {
        return graduacion;
    }

    public Drink(String nombre, Double graduacion, int cant_ing) {
        this.nombre = nombre;
        this.graduacion = graduacion;
        this.cant_ing = cant_ing;
    }
   /* public Boolean soyValido(){


        return (this.getCant_ing() != 0 && this.getNombre() !="" && this.getGraduacion() != 0);

    }*/
    public void setGraduacion(Double graduacion) {
        this.graduacion = graduacion;
    }

    public int getCant_ing() {
        return cant_ing;
    }

    public void setCant_ing(int cant_ing) {
        this.cant_ing = cant_ing;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
