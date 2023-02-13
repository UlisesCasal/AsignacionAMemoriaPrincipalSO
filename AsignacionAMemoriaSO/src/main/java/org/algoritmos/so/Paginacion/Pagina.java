class Pagina{
    private int numero;
    private int proceso;
    private int Estado = Estados.LIBRE;
    private Frames frame;

    public Pagina(int numero){
        this.numero = numero;
    }

    public int getProceso() {
        return proceso;
    }

    public void setProceso(int proceso, String contenido){
        int i = 0;
        this.proceso = proceso;
        while (GestorPaginacion.bloqueOcupado(this.getBloque() + i) && i < GestorPaginacion.numeroFrames()){
            i ++;
        }
        if (i >= GestorPaginacion.numeroFrames) {
            System.out.println("No hay bloques/frames disponibles");
        }

    }

    public int getBloque() {
        //SIMULA UN OFFSET
        return this.proceso * 10;
    }



}