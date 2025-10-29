
package Controlador;

import DAO.LoginDAO;
import Modelo.Login_;
import Modelo.Personal;

public class LoginController {

    private LoginDAO loginDAO;

    public LoginController() {
        this.loginDAO = new LoginDAO();
    }

    public Personal login(String email, String contrasena) {
        Personal personal = loginDAO.login(email, contrasena);

        if (personal != null) {
            System.out.println("Inicio de sesión exitoso: " + personal.getEmail());
            Login_.iniciarSesion(personal);
        } else {
            System.out.println("Credenciales incorrectas para el email: " + email);
        }

        return personal;
    }

    public void logout() {
        if (Login_.haySesionActiva()) {
            System.out.println("Cerrando sesión de: " + Login_.getPersonalActual().getEmail());
            Login_.cerrarSesion();
        } else {
            System.out.println("No hay sesión activa para cerrar.");
        }
    }

    public boolean haySesionActiva() {
        return Login_.haySesionActiva();
    }

    public Personal getPersonalActual() {
        return Login_.getPersonalActual();
    }
}
