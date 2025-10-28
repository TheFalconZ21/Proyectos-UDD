package logic;

import java.util.Scanner;

public class InputComprobador {

    public static int leerEnteroSeguro(Scanner sc) {
        while (true) {
            String linea = sc.nextLine();
            try {
                return Integer.parseInt(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingresa un número: ");
            }
        }
    }

    public static double leerDoubleSeguro(Scanner sc) {
        while (true) {
            String linea = sc.nextLine();
            try {
                return Double.parseDouble(linea.trim());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Ingresa un número válido: ");
            }
        }
    }
}
