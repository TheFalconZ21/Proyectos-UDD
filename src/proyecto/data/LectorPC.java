import model.CPU;
import model.GPU;
import model.PC;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class LectorPC {

    public static PC leerPC(String rutaArchivo) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(
                new FileInputStream(rutaArchivo), StandardCharsets.UTF_8))) {

            String linea = br.readLine();
            if (linea == null) {
                System.out.println("Archivo vacío");
                return null;
            }

            // Separar CPU, GPU y demás
            String[] partes = linea.split(",");
            if (partes.length != 5) {
                System.out.println("Formato de archivo incorrecto: se esperaban 5 partes separadas por comas.");
                return null;
            }

            // --- CPU ---
            String[] cpuDatos = partes[0].split("\\|");
            String nombreCPU = cpuDatos[0].trim();

            // Buscar número dentro del texto
            int nucleosCPU = extraerEntero(cpuDatos[1]);
            double freqCPU = extraerDouble(cpuDatos[2]);
            CPU cpu = new CPU(nombreCPU, 0, nucleosCPU, freqCPU);

            // --- GPU ---
            String[] gpuDatos = partes[1].split("\\|");
            String nombreGPU = gpuDatos[0].trim();
            int vram = extraerEntero(gpuDatos[1]);
            boolean raytracing = gpuDatos[2].toLowerCase().contains("sí") || gpuDatos[2].toLowerCase().contains("true");
            GPU gpu = new GPU(nombreGPU, 0, vram, raytracing);

            // --- RAM ---
            int ramGB = extraerEntero(partes[2]);

            // --- Almacenamiento ---
            int almacenamientoGB = extraerEntero(partes[3]);

            // --- Sistema operativo ---
            String sistemaOperativo = partes[4].trim();

            return new PC(cpu, gpu, ramGB, almacenamientoGB, sistemaOperativo);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
            return null;
        }
    }

    // --- Métodos auxiliares para limpiar texto y extraer números ---
    private static int extraerEntero(String texto) {
        texto = texto.replaceAll("[^0-9]", "").trim();
        return texto.isEmpty() ? 0 : Integer.parseInt(texto);
    }

    private static double extraerDouble(String texto) {
        texto = texto.replaceAll("[^0-9.]", "").trim();
        return texto.isEmpty() ? 0.0 : Double.parseDouble(texto);
    }
}