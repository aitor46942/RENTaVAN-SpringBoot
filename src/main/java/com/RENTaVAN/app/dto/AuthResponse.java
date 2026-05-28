package com.RENTaVAN.app.dto;

public class AuthResponse {
    private boolean exito;
    private String mensaje;
    private String nombre;

    public AuthResponse(boolean exito, String mensaje) {
        this.exito = exito;
        this.mensaje = mensaje;
    }

    public AuthResponse(boolean exito, String mensaje, String nombre) {
        this.exito = exito;
        this.mensaje = mensaje;
        this.nombre = nombre;
    }

    public boolean isExito() { return exito; }
    public void setExito(boolean exito) { this.exito = exito; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
}