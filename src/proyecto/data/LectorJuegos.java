import model.*;
import java.io.*;
import java.util.*;

public class LectorJuegos {

    public static ListaJuegos leerJuegos(String rutaArchivo) {
        ListaJuegos listaJuegos = new ListaJuegos();
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                if (linea.isEmpty()) continue;

                try {
                    // Separar los campos principales
                    // Formato esperado:
                    // nombre, cpuNombre, cpuNucleos, cpuGHz, gpuNombre, gpuVRAM, gpuRayTracing, ramMin, almacenamientoMin, (SO1, SO2, ...)
                    String[] partes = linea.split(",", 10); // 10 campos esperados
                    if (partes.length < 10) {
                        System.out.println("Línea con formato incorrecto: " + linea);
                        continue;
                    }

                    String nombre = partes[0].trim();

                    // CPU
                    String cpuNombre = partes[1].trim();
                    int cpuNucleos = Integer.parseInt(partes[2].trim());
                    double cpuGHz = Double.parseDouble(partes[3].trim());
                    CPU cpu = new CPU(cpuNombre, 0, cpuNucleos, cpuGHz);

                    // GPU
                    String gpuNombre = partes[4].trim();
                    int vramGB = Integer.parseInt(partes[5].trim());
                    boolean rayTracing = Boolean.parseBoolean(partes[6].replace("'", "").trim().toLowerCase());
                    GPU gpu = new GPU(gpuNombre, 0, vramGB, rayTracing);

                    // RAM y almacenamiento
                    int ramMin = Integer.parseInt(partes[7].trim());
                    int almacenamientoMin = Integer.parseInt(partes[8].trim());

                    // Sistemas Operativos (último campo entre paréntesis)
                    String soTexto = partes[9].trim();
                    soTexto = soTexto.replace("(", "").replace(")", "");
                    String[] soArray = soTexto.split(",");
                    List<String> sistemasOperativos = new ArrayList<>();
                    for (String so : soArray) {
                        sistemasOperativos.add(so.trim());
                    }

                    // Ponderadores por defecto
                    Map<String, Double> ponderadores = new HashMap<>();
                    ponderadores.put("CPU", 0.4);
                    ponderadores.put("GPU", 0.4);
                    ponderadores.put("RAM", 0.2);

                    // Crear el objeto Juego y agregarlo a la lista
                    Juego juego = new Juego(nombre, cpu, gpu, ramMin, almacenamientoMin, sistemasOperativos, ponderadores);
                    listaJuegos.agregarJuego(juego);

                } catch (Exception e) {
                    System.out.println("Error al procesar línea: " + linea);
                    System.out.println("→ Detalle: " + e.getMessage());
                }
            }

        } catch (IOException e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }

        return listaJuegos;
    }
}
