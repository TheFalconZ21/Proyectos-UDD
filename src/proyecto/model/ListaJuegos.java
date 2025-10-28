// model/ListaJuegos.java
package model;

import java.util.*;

public class ListaJuegos {
    private List<Juego> juegos = new ArrayList<>();

    public void agregarJuego(Juego juego) {
        juegos.add(juego);
    }

    public Juego obtenerJuego(int indice) {
        return juegos.get(indice);
    }

    public List<Juego> obtenerTodos() {
        return juegos;
    }

    // Método usado por el lector de archivos
    public List<Juego> getJuegos() {
        return juegos;
    }

    // (opcional) método para mostrar todos los juegos
    public void mostrarJuegos() {
        if (juegos.isEmpty()) {
            System.out.println("No hay juegos cargados.");
        } else {
            for (int i = 0; i < juegos.size(); i++) {
                System.out.println((i + 1) + ". " + juegos.get(i).getNombre());
            }
        }
    }
}
