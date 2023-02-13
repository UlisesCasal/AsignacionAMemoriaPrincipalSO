package org.algoritmos.so.ParticionesFijas;

import org.algoritmos.so.Estado;

public class Particiones {
    private final int inicio;
    private final int fin;
    private Estado estado;
    private int processId = -1;
    private String contenido;

    public Particiones(int inicio, int fin){
        this.estado = Estado.LIBRE;
        this.inicio = inicio;
        this.fin = fin;
    }

    public void setContenido(int pid, String contenido){
        this.processId = pid;
        this.contenido = contenido;
        this.estado = Estado.OCUPADO;
    }
    public int[] getRango(){
        return new int[]{inicio, fin};
    }


    public Estado getEstado() {
        return this.estado;
    }

    public String getContenido() {
        return "inicio: " + this.inicio + " fin: " + this.fin + " estado: " + this.estado + " pid: " + this.processId + " contenido: " + this.contenido;
    }

    public int getPid() {
        return this.processId;
    }

    public void eliminarContenido() {
        this.estado = Estado.LIBRE;
        this.contenido = null;
        this.processId = -1;

    }
}
