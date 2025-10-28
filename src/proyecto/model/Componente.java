// model/Componente.java
package model;

public abstract class Componente {
    protected String nombre;
    protected int puntuacionBase;

    public Componente(String nombre, int puntuacionBase) {
        this.nombre = nombre;
        this.puntuacionBase = puntuacionBase;
    }

    public abstract int calcularPuntuacion();

    public String getNombre() {
        return nombre;
    }

    public int getPuntuacionBase() {
        return puntuacionBase;
    }
}
