import java.util.ArrayList;
import java.util.List;

public class Grafo {

    private List<Vertice> vertices ;
    private List<Arista> aristas ;

    public Grafo() {
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
    }

    public void agregarVertice(Vertice vertice) {
        vertices.add(vertice);
    }

    public void eliminarVertice(Vertice vertice) {
        vertices.remove(vertice);
        List<Arista> aristasAEliminar = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.getOrigen() == vertice || arista.getDestino() == vertice) {
               aristasAEliminar.add(arista); 
            }
        }
        aristas.removeAll(aristasAEliminar);
    }

    public void agregarArista(Arista arista) {
        aristas.add(arista);
    }

    public void eliminarArista(Arista arista) {
        aristas.remove(arista);
    }

    public Vertice buscarVerticePorID(int id) {
        for (Vertice vertice : vertices) {
            if (vertice.getId() == id) {
                return vertice;
            }
        }
        return null;
    }

    public Arista buscarArista(Vertice origen, Vertice destino) {
        for (Arista arista : aristas) {
            if (arista.getOrigen() == origen && arista.getDestino() == destino) {
                return arista;
            }
        }
        return null;  
    }

}