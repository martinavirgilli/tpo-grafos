public class Vertice {
    private int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getDatos() {
        return datos;
    }

    public void setDatos(Object datos) {
        this.datos = datos;
    }

    private Object datos;

    public Vertice(int id, Object datos) {
        this.id = id;
        this.datos = datos;
    }


    
}
