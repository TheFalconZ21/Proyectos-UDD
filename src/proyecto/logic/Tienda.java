package logic;

import model.Usuario;
import model.UsuarioPremium;
import model.PC;

public class Tienda {

    public void cargarSaldo(Usuario usuario, double monto) {
        double nuevoSaldo = usuario.getSaldo() + monto;
        usuario.setSaldo(nuevoSaldo);
    }

    public Usuario ofrecerPremium(Usuario usuario) {
        if (usuario instanceof UsuarioPremium) {
            System.out.println("Usuario ya es Premium.");
            return usuario;
        }

        if (usuario.getSaldo() >= 5000) {
            usuario.setSaldo(usuario.getSaldo() - 5000);
            System.out.println(usuario.getNombre() + " ahora es Premium! Saldo restante: " + usuario.getSaldo());

            // Crear nuevo UsuarioPremium con el mismo PC
            PC pc = usuario.getPc();
            return new UsuarioPremium(usuario.getNombre(), usuario.getSaldo(), pc);
        } else {
            System.out.println("Saldo insuficiente para mejorar a Premium.");
            return usuario;
        }
    }
}
