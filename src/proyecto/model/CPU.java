// model/CPU.java
package model;

public class CPU extends Componente {
    private int nucleos;
    private double frecuenciaGHz;

    public CPU(String nombre, int puntuacionBase, int nucleos, double frecuenciaGHz) {
        super(nombre, puntuacionBase);
        this.nucleos = nucleos;
        this.frecuenciaGHz = frecuenciaGHz;
    }

    @Override
    public int calcularPuntuacion() {
        return (int) (puntuacionBase + nucleos * 10 + frecuenciaGHz * 10);
    }

	 public int getNucleos() {
        return nucleos;
    }

    public double getFrecuenciaGHz() {
        return frecuenciaGHz;
    }
}
