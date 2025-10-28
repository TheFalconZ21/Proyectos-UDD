import model.*;
import logic.*;
//import data.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Solicitar nombre del usuario
        System.out.print("Ingrese su nombre: ");
        String nombreUsuario = sc.nextLine();

        // Leer PC desde archivo
        System.out.print("Ingrese la ruta del archivo .txt con los datos de su PC: ");
        String rutaPC = sc.nextLine();
        PC pcUsuario = LectorPC.leerPC(rutaPC);
        if (pcUsuario == null) {
            System.out.println("No se pudo cargar el PC, se usará un PC por defecto.");
            CPU cpuDefault = new CPU("Intel i5", 200, 4, 3.5);
            GPU gpuDefault = new GPU("RTX 3060", 300, 8, true);
            pcUsuario = new PC(cpuDefault, gpuDefault, 16, 512, "Windows 10");
        }

        // Crear usuario con saldo inicial 0
        Usuario usuario = new Usuario(nombreUsuario, 0, pcUsuario);

        // Leer juegos desde archivo
        System.out.print("Ingrese la ruta del archivo .txt con los juegos: ");
        String rutaJuegos = sc.nextLine();
        ListaJuegos listaJuegos = LectorJuegos.leerJuegos(rutaJuegos);

        // Iniciar menú principal
        mostrarMenu(sc, usuario, listaJuegos);
    }

    // ===========================
    // MENÚ PRINCIPAL
    // ===========================
    public static void mostrarMenu(Scanner sc, Usuario usuario, ListaJuegos listaJuegos) {
        Tienda tienda = new Tienda();
        boolean salir = false;

        while (!salir) {
            System.out.println("\n=== Menú Principal ===");
            System.out.println("1. Evaluar Juego");
            System.out.println("2. Ir a Tienda");
            System.out.println("0. Salir");
            System.out.print("Ingrese una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    menuEvaluarJuego(sc, usuario, listaJuegos);
                    break;
                case 2:
                    usuario = menuTienda(sc, usuario, tienda);
                    break;
                case 0:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }

    // ===========================
    // MENÚ TIENDA
    // ===========================
    public static Usuario menuTienda(Scanner sc, Usuario usuario, Tienda tienda) {
    boolean volver = false;

    while (!volver) {
        // Mostrar usuario y saldo
        System.out.println("\n=== Tienda ===");
        System.out.println("Usuario: " + usuario.getNombre() + " | Saldo: " + usuario.getSaldo());
        System.out.println("1. Cargar saldo");
        System.out.println("2. Mejorar a Premium (costo: 5000)");
        System.out.println("0. Volver al menú principal");
        System.out.print("Ingrese una opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {
            case 1:
                System.out.print("Ingrese monto a cargar: ");
                double monto = sc.nextDouble();
                sc.nextLine();
                tienda.cargarSaldo(usuario, monto);
                System.out.println("Saldo actualizado: " + usuario.getSaldo());
                break;

            case 2:
                usuario = tienda.ofrecerPremium(usuario); // se actualiza el usuario si se vuelve premium
                break;

            case 0:
                volver = true;
                break;

            default:
                System.out.println("Opción inválida.");
                break;
        }
    }

    return usuario;
}


    // ===========================
    // MENÚ EVALUAR JUEGO
    // ===========================
    public static void menuEvaluarJuego(Scanner sc, Usuario usuario, ListaJuegos listaJuegos) {
        boolean volver = false;
        while (!volver) {
            System.out.println("\n=== Evaluar Juego ===");
            System.out.println("1. Evaluar un juego");
            System.out.println("2. Evaluar todos los juegos disponibles");
            if (usuario instanceof UsuarioPremium) System.out.println("3. Evaluar un juego temporal");
            System.out.println("0. Volver al menú principal");
            System.out.print("Ingrese una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.println("Lista de juegos disponibles:");
                    for (int i = 0; i < listaJuegos.obtenerTodos().size(); i++) {
                        System.out.println((i + 1) + ". " + listaJuegos.obtenerJuego(i).getNombre());
                    }
                    System.out.print("Seleccione un juego: ");
                    int idx = sc.nextInt() - 1;
                    sc.nextLine();
                    if (idx >= 0 && idx < listaJuegos.obtenerTodos().size()) {
                        usuario.evaluarJuego(listaJuegos.obtenerJuego(idx));
                    } else System.out.println("Opción inválida.");
                    break;

                case 2:
                    for (Juego j : listaJuegos.obtenerTodos()) {
                        usuario.evaluarJuego(j);
                    }
                    break;

                case 3:
                    if (usuario instanceof UsuarioPremium) {
                        UsuarioPremium up = (UsuarioPremium) usuario;
                        Juego juegoTemp = up.crearJuegoPersonalizado(listaJuegos, sc);
                        if (juegoTemp != null) {
                            listaJuegos.agregarJuego(juegoTemp);
                            usuario.evaluarJuego(juegoTemp);
                        }
                    } else {
                        System.out.println("Opción inválida.");
                    }
                    break;

                case 0:
                    volver = true;
                    break;

                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        }
    }
}
