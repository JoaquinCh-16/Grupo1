package Modelo;

import Modelo.Personal;

public class Login_ {
    private static Personal personalActual;

    
    public static void iniciarSesion(Personal personal) {
        personalActual = personal;
    }

    public static Personal getPersonalActual() {
        return personalActual;
    }

    public static void cerrarSesion() {
        personalActual = null;
    }
    
    public static boolean haySesionActiva() {
        return personalActual != null;
    }
}
