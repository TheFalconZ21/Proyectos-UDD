// model/PC.java
package model;

public class PC {
    private CPU cpu;
    private GPU gpu;
    private int ramGB;
    private int almacenamientoGB;
    private String sistemaOperativo;

    public PC(CPU cpu, GPU gpu, int ramGB, int almacenamientoGB, String sistemaOperativo) {
        this.cpu = cpu;
        this.gpu = gpu;
        this.ramGB = ramGB;
        this.almacenamientoGB = almacenamientoGB;
        this.sistemaOperativo = sistemaOperativo;
    }

    public CPU getCpu() {
        return cpu;
    }

    public GPU getGpu() {
        return gpu;
    }

    public int getRamGB() {
        return ramGB;
    }

    public int getAlmacenamientoGB() {
        return almacenamientoGB;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }
}
