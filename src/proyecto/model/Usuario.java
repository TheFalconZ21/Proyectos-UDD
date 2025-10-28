package model;

import logic.EvaluadorJuego;

public class Usuario {
    private String nombre;
    private double saldo;
    private PC pc;

    public Usuario(String nombre, double saldo, PC pc) {
        this.nombre = nombre;
        this.saldo = saldo;
        this.pc = pc;
    }

    // Método de evaluación de juego para usuarios no premium
    public void evaluarJuego(Juego juego) {
        // Calcula puntuación total usando el PC del usuario
        double puntuacionTotal = EvaluadorJuego.calcularPuntuacionTotal(
                pc.getCpu(),
                pc.getGpu(),
                pc.getRamGB(),
                juego
        );

        // Solo muestra la categoría, no detalles
        String categoria = EvaluadorJuego.categorizarPuntuacion(puntuacionTotal);

        System.out.println(nombre + " evaluando juego: " + juego.getNombre());
        System.out.println("Rendimiento estimado: " + categoria);
        System.out.println("Para ver detalles completos y el componente limitante, mejore a Premium.");
    }

    public void pagarPremium() {
        System.out.println("Debe mejorar a Premium en la tienda.");
    }

    public String getNombre() {
        return nombre;
    }

    public double getSaldo() {
        return saldo;
    }

    public PC getPc() {
        return pc;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
