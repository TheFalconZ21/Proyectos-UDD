// model/GPU.java
package model;

public class GPU extends Componente {
    private int vramGB;
    private boolean soporteRayTracing;

    public GPU(String nombre, int puntuacionBase, int vramGB, boolean soporteRayTracing) {
        super(nombre, puntuacionBase);
        this.vramGB = vramGB;
        this.soporteRayTracing = soporteRayTracing;
    }

    @Override
    public int calcularPuntuacion() {
        int score = puntuacionBase + vramGB * 10;
        if (soporteRayTracing)
            score += 50;
        return score;
    }

    public String getNombre() {
        return nombre;
    }

    public int getvRAM() {
        return vramGB;
    }

    public boolean isRayTracing() {
        return soporteRayTracing;
    }

}
