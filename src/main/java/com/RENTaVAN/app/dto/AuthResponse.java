package com.RENTaVAN.app.dto;

public class AuthResponse {
    private boolean exito;
    private String mensaje;
    private String nombre; // Opcional: para saludar al usuario al entrar

    // Constructor para mensajes rápidos (el que usas en tu captura)
    public AuthResponse(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    // Constructor completo
    public AuthResponse(boolean exito, String mensaje, String nombre) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.nombre = nombre;
    }

    // Getters y Setters (Necesarios para que Jackson convierta esto a JSON)
    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}