// model/Juego.java
package model;

import java.util.*;

public class Juego {
    private String nombre;
    private CPU cpuRequerida;
    private GPU gpuRequerida;
    private int ramMinima;
    private int almacenamientoMin;
    private List<String> sistemasOperativos;
    private Map<String, Double> ponderadores;

    public Juego(String nombre, CPU cpuSeleccionada, GPU gpuRequerida, int ramMinima,
            int almacenamientoMin, List<String> soSeleccionado, Map<String, Double> ponderadores) {
        this.nombre = nombre;
        this.cpuRequerida = cpuSeleccionada;
        this.gpuRequerida = gpuRequerida;
        this.ramMinima = ramMinima;
        this.almacenamientoMin = almacenamientoMin;
        this.sistemasOperativos = soSeleccionado;
        this.ponderadores = ponderadores;
    }

    public String getNombre() {
        return nombre;
    }

    public CPU getCpuRequerida() {
        return cpuRequerida;
    }

    public GPU getGpuRequerida() {
        return gpuRequerida;
    }

    public int getRamMinima() {
        return ramMinima;
    }

    public int getAlmacenamientoMin() {
        return almacenamientoMin;
    }

    public List<String> getSistemasOperativos() {
        return sistemasOperativos;
    }

    public Map<String, Double> getPonderadores() {
        return ponderadores;
    }
}