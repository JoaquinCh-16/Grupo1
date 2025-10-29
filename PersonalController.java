/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import DAO.PersonalDAO;
import Modelo.Personal;
import Modelo.Login_;
import java.util.List;


public class PersonalController {
    private PersonalDAO personalDAO;

    public PersonalController() {
        this.personalDAO = new PersonalDAO();
    }

    public boolean agregarPersonal(String personalId, String nombre, String email, String contrasena, Integer telefono, String rol) {
            System.out.println("Validando contraseña: '" + contrasena + "'");
            if (!Personal.validarContrasena(contrasena)) {
                System.out.println("Validación falló para: '" + contrasena + "'");
                return false;
            }
            Personal personal = new Personal(personalId, nombre, email, contrasena, telefono, rol);
            boolean resultado = personalDAO.agregarPersonal(personal);
            System.out.println("Guardado en DB: " + resultado);
            return resultado;
        }

    public boolean editarPersonal(String personalId, String nombre, String email, String contrasena, Integer telefono, String rol) {
                System.out.println("Editando en controlador - ID: " + personalId + ", Contraseña: " + (contrasena != null ? "'"+contrasena+"'" : "No cambiada"));
            if (contrasena != null && !contrasena.isEmpty() && !Personal.validarContrasena(contrasena)) {
                System.out.println("Validación falló para: '" + contrasena + "'");
                return false;
            }
            // Obtener el personal existente para conservar la contraseña si no se cambia
            Personal existingPersonal = null;
            for (Personal p : listarPersonal()) {
                if (p.getPersonalId().equals(personalId)) {
                    existingPersonal = p;
                    break;
                }
            }
            String finalContrasena = (contrasena != null && !contrasena.isEmpty()) ? contrasena : (existingPersonal != null ? existingPersonal.getContrasena() : "");
            Integer finalTelefono = (telefono != null) ? telefono : (existingPersonal != null ? existingPersonal.getTelefono() : 0);
            Personal personal = new Personal(personalId, nombre, email, finalContrasena, finalTelefono, rol);
            boolean resultado = personalDAO.editarPersonal(personal);
            System.out.println("Resultado de edición en DB: " + resultado);
            return resultado;
    }

    public boolean eliminarPersonal(String personalId) {
        return personalDAO.eliminarPersonal(personalId);
    }

    public List<Personal> listarPersonal() {
        return personalDAO.listarPersonal();
    }
    
    public String obtenerUltimoPersonalId() {
        return personalDAO.obtenerUltimoPersonalId();
    }   
    
}
