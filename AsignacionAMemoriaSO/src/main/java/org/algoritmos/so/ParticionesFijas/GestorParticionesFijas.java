package org.algoritmos.so.ParticionesFijas;

import org.algoritmos.so.Estado;
import org.algoritmos.so.Gestor;

import java.util.Objects;
import java.util.Scanner;

public class GestorParticionesFijas extends Gestor {
    private Scanner sc = new Scanner(System.in);
    private Particiones[] particiones = null;


    public void iniciar(){

        if (particiones == null){
            crearParticiones();
        }
        Gestor.Menu();
        String decision = sc.nextLine();
        while (!Objects.equals(decision, "4")){
            switch (decision) {
                case "1" -> this.cargarProceso();

                case "2" -> this.mostrarMemoria();

                case "3" -> this.bajarProceso();

                default -> System.exit(0);
            }
            Gestor.Menu();
            decision = sc.nextLine();
        }
    }

    private void crearParticiones() {
        particiones = new Particiones[10];
        for (int i = 0; i < 10; i++) {
            particiones[i] = new Particiones(i*10, (i*10) + 10);
        }
    }

    private void cargarProceso() {
        //PIDO DATOS DEL PROCESO A CARGAR
        String pid;
        String contenido;
        int tamanio;
        int bloquesRequeridos;
        int contadorCarga = 0;
        int i = 0;
        int diferencia = 0;
        int bloquesUsados = 0;

        System.out.println("Por favor ingrese el PID: ");
        pid = sc.nextLine();
        System.out.println("Por favor ingrese el contenido: ");
        contenido = sc.nextLine();
        tamanio = contenido.length();

        //VERIFICO SI TENGO ESPACIO DISPONIBLE PARA ALMACENARLO
        bloquesRequeridos = (int) Math.ceilDiv(tamanio, 10);
        if (bloquesRequeridos <= this.getNumeroBloquesLibres()){

            //RECORRE Y CARGA A LAS PARTICIONES EL CONTENIDO:
            while (i < this.particiones.length && bloquesUsados < bloquesRequeridos) {
                if (this.particiones[i].getEstado() == Estado.LIBRE){
                    if ((diferencia + 10) > tamanio) {
                        diferencia = contadorCarga + this.getDiferencia(tamanio);
                    }else{
                        diferencia += 10;
                    }
                    this.particiones[i].setContenido(Integer.parseInt(pid), contenido.substring(contadorCarga, diferencia));
                    bloquesUsados ++;

                    if ((contadorCarga + 10) > tamanio){
                        contadorCarga += this.getDiferencia(tamanio);
                    }
                    else{
                        contadorCarga += 10;
                    }

                }
                i ++;
            }
        }else{
            System.out.println("No hay espacio suficiente");
        }

    }

    private int getDiferencia(int contadorCarga) {
        String numero = String.valueOf(contadorCarga);
        numero = numero.substring(numero.length()-1, numero.length());
        return Integer.parseInt(numero);

    }

    private int getNumeroBloquesLibres() {
        int particionesLibres = 0;

        for (int i = 0; i < this.particiones.length; i++) {
            if (this.particiones[i].getEstado() == Estado.LIBRE){
                particionesLibres ++;
            }
        }
        return particionesLibres;
    }

    private void bajarProceso() {
        int proceso;
        int procesoParticion;
        System.out.println("Por favor ingrese el proceso que quiere eliminar de Memoria Principal: ");
        proceso = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < this.particiones.length; i++) {
            procesoParticion = this.particiones[i].getPid();

            if (proceso == procesoParticion){
                this.particiones[i].eliminarContenido();
            }

        }
    }

    private void mostrarMemoria() {

        System.out.println("MEMORIA PRINCIPAL:");
        for (int i = 0; i < this.particiones.length; i++) {
            System.out.println(this.particiones[i].getContenido());
        }
        System.out.println("FIN DE MEMORIA PRINCIPAL");
    }


}
