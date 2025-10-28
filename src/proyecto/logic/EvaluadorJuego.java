package logic;

import model.CPU;
import model.GPU;
import model.Juego;

public class EvaluadorJuego {

    public static double calcularRendimientoCPU(CPU cpuUsuario, CPU cpuJuego) {
        return (double) cpuUsuario.calcularPuntuacion() / cpuJuego.calcularPuntuacion();
    }

    public static double calcularRendimientoGPU(GPU gpuUsuario, GPU gpuJuego) {
        return (double) gpuUsuario.calcularPuntuacion() / gpuJuego.calcularPuntuacion();
    }

    public static double calcularRendimientoRAM(int ramUsuarioGB, int ramJuegoMinGB) {
        return (double) ramUsuarioGB / ramJuegoMinGB;
    }

    public static double calcularPuntuacionTotal(CPU cpuUsuario, GPU gpuUsuario, int ramUsuarioGB, Juego juego) {
        double cpuRatio = calcularRendimientoCPU(cpuUsuario, juego.getCpuRequerida()) * juego.getPonderadores().get("CPU");
        double gpuRatio = calcularRendimientoGPU(gpuUsuario, juego.getGpuRequerida()) * juego.getPonderadores().get("GPU");
        double ramRatio = calcularRendimientoRAM(ramUsuarioGB, juego.getRamMinima()) * juego.getPonderadores().get("RAM");
        return cpuRatio + gpuRatio + ramRatio;
    }

    public static String categorizarPuntuacion(double puntaje) {
        if (puntaje < 0.7) return "No funcionará";
        else if (puntaje < 1.0) return "Funcionará con problemas";
        else if (puntaje < 1.5) return "Funcionará bien";
        else return "Funcionará de manera excepcional";
    }

    public static String componenteLimitante(double cpuRatio, double gpuRatio, double ramRatio) {
        double min = Math.min(cpuRatio, Math.min(gpuRatio, ramRatio));
        if (min == cpuRatio) return "CPU";
        else if (min == gpuRatio) return "GPU";
        else return "RAM";
    }
}
