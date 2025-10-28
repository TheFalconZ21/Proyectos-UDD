package model;

import logic.EvaluadorJuego;
import java.util.*;

public class UsuarioPremium extends Usuario {

    public UsuarioPremium(String nombre, double saldo, PC pc) {
        super(nombre, saldo, pc);
    }

    // Sobrescribe evaluarJuego para mostrar resultados detallados
    @Override
    public void evaluarJuego(Juego juego) {
        PC pc = getPc();
        double cpuRatio = EvaluadorJuego.calcularRendimientoCPU(pc.getCpu(), juego.getCpuRequerida());
        double gpuRatio = EvaluadorJuego.calcularRendimientoGPU(pc.getGpu(), juego.getGpuRequerida());
        double ramRatio = EvaluadorJuego.calcularRendimientoRAM(pc.getRamGB(), juego.getRamMinima());
        double puntuacionTotal = EvaluadorJuego.calcularPuntuacionTotal(pc.getCpu(), pc.getGpu(), pc.getRamGB(), juego);
        String categoria = EvaluadorJuego.categorizarPuntuacion(puntuacionTotal);
        String limitante = EvaluadorJuego.componenteLimitante(cpuRatio, gpuRatio, ramRatio);

        System.out.println(getNombre() + " evaluando juego: " + juego.getNombre());
        System.out.printf("Puntuación estimada: %.2f - %s%n", puntuacionTotal, categoria);
        System.out.println("Componente limitante: " + limitante);
    }

    // Crear juegos temporales basados en juegos existentes
    public Juego crearJuegoPersonalizado(ListaJuegos listaJuegos, Scanner sc) {

        System.out.print("Ingrese el nombre del juego temporal: ");
        String nombreJuego = sc.nextLine();

        // ---------------- CPU ----------------
        System.out.println("Seleccione CPU mínima exigida:");
        List<CPU> cpus = new ArrayList<>();
        Set<String> cpuUnicas = new HashSet<>();
        int idx = 1;
        for (Juego j : listaJuegos.obtenerTodos()) {
            CPU cpu = j.getCpuRequerida();
            String key = cpu.getNombre() + "-" + cpu.getNucleos() + "-" + cpu.getFrecuenciaGHz();
            if (!cpuUnicas.contains(key)) {
                cpuUnicas.add(key);
                cpus.add(cpu);
                System.out.printf("%d. %s | Núcleos: %d | Frecuencia: %.2f GHz%n",
                        idx++, cpu.getNombre(), cpu.getNucleos(), cpu.getFrecuenciaGHz());
            }
        }
        System.out.print("Opción CPU: ");
        int opcionCPU = Integer.parseInt(sc.nextLine()) - 1;
        CPU cpuSeleccionada = cpus.get(opcionCPU);

        // ---------------- GPU ----------------
        System.out.println("Seleccione GPU mínima exigida:");
        List<GPU> gpus = new ArrayList<>();
        Set<String> gpuUnicas = new HashSet<>();
        idx = 1;
        for (Juego j : listaJuegos.obtenerTodos()) {
            GPU gpu = j.getGpuRequerida();
            String key = gpu.getNombre() + "-" + gpu.getvRAM() + "-" + gpu.isRayTracing();
            if (!gpuUnicas.contains(key)) {
                gpuUnicas.add(key);
                gpus.add(gpu);
                System.out.printf("%d. %s | VRAM: %d GB | RayTracing: %s%n",
                        idx++, gpu.getNombre(), gpu.getvRAM(), gpu.isRayTracing() ? "Sí" : "No");
            }
        }
        System.out.print("Opción GPU: ");
        int opcionGPU = Integer.parseInt(sc.nextLine()) - 1;
        GPU gpuSeleccionada = gpus.get(opcionGPU);

        // ---------------- RAM ----------------
        int ram = 0;
        while (ram <= 0) {
            System.out.print("Ingrese cantidad de RAM mínima requerida (>0): ");
            ram = Integer.parseInt(sc.nextLine());
        }

        // ---------------- Almacenamiento ----------------
        int storage = 0;
        while (storage <= 0) {
            System.out.print("Ingrese cantidad de almacenamiento mínimo (>0): ");
            storage = Integer.parseInt(sc.nextLine());
        }

        // ---------------- Sistemas Operativos ----------------
        System.out.println("Seleccione sistemas operativos compatibles (separados por coma):");
        List<String> sistemasDisponibles = new ArrayList<>();
        for (Juego j : listaJuegos.obtenerTodos()) {
            for (String so : j.getSistemasOperativos()) {
                if (!sistemasDisponibles.contains(so)) sistemasDisponibles.add(so);
            }
        }
        for (int i = 0; i < sistemasDisponibles.size(); i++) {
            System.out.println((i + 1) + ". " + sistemasDisponibles.get(i));
        }
        System.out.print("Ingrese números separados por coma: ");
        String[] opcionesSO = sc.nextLine().split(",");
        List<String> sistemasSeleccionados = new ArrayList<>();
        for (String op : opcionesSO) {
            int index = Integer.parseInt(op.trim()) - 1;
            if (index >= 0 && index < sistemasDisponibles.size())
                sistemasSeleccionados.add(sistemasDisponibles.get(index));
        }

        // Mantener ponderadores del primer juego base
        Map<String, Double> ponder = listaJuegos.obtenerJuego(0).getPonderadores();

        Juego juegoTemporal = new Juego(
                nombreJuego,
                cpuSeleccionada,
                gpuSeleccionada,
                ram,
                storage,
                sistemasSeleccionados,
                ponder
        );

        System.out.println("Juego temporal creado: " + juegoTemporal.getNombre());;

        return juegoTemporal;
    }
}
