/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;


public class Personal {

    private String personalId;
    private String nombre;
    private String email;
    private String contrasena;
    private Integer telefono;
    private String rol;

    public Personal() {
    }

    public Personal(String personalId, String nombre, String email, String contrasena, Integer telefono, String rol) {
        this.personalId = personalId;
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        this.telefono = telefono;
        this.rol = rol;
    }

    // Getters y Setters
    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Personal{" +
                "personalId='" + personalId + '\'' +
                ", nombre='" + nombre + '\'' +
                ", email='" + email + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", telefono=" + telefono +
                ", rol='" + rol + '\'' +
                '}';
    }
    
    public static boolean validarContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 8) {
           System.out.println("Fallo: Contraseña nula o menor a 8 caracteres.");
           return false;
        }
        boolean hasUppercase = false, hasLowercase = false, hasSpecial = false;
        for (char c : contrasena.toCharArray()) {
            if (Character.isUpperCase(c)) hasUppercase = true;
            else if (Character.isLowerCase(c)) hasLowercase = true;
            else if (!Character.isLetterOrDigit(c)) hasSpecial = true;
        }
        System.out.println("Validación - Mayúscula: " + hasUppercase + ", Minúscula: " + hasLowercase + ", Especial: " + hasSpecial);
        return hasUppercase && hasLowercase && hasSpecial;
    }
}