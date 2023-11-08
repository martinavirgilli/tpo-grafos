import java.util.*;

public class Grafo {
    private int numVertices;
    private Map<Integer, List<Integer>> listaAdyacencia;

    public Grafo(int numVertices) {
        this.numVertices = numVertices;
        this.listaAdyacencia = new HashMap<>();
        for (int i = 0; i < numVertices; i++) {
            listaAdyacencia.put(i, new LinkedList<>());
        }
    }

    public void agregarArista(int origen, int destino) {
        if (origen >= 0 && origen < numVertices && destino >= 0 && destino < numVertices) {
            listaAdyacencia.get(origen).add(destino);
            listaAdyacencia.get(destino).add(origen); // Si el grafo es no dirigido
        }
    }

    public List<Integer> obtenerVerticesAdyacentes(int vertice) {
        if (vertice >= 0 && vertice < numVertices) {
            return listaAdyacencia.get(vertice);
        }
        return Collections.emptyList();
    }

    public int obtenerNumeroDeVertices() {
        return numVertices;
    }

    public void imprimirGrafo() {
        for (int i = 0; i < numVertices; i++) {
            List<Integer> adyacentes = listaAdyacencia.get(i);
            System.out.print("Vértice " + i + " está conectado a: ");
            for (int j : adyacentes) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
    
    //implementacion Prim
    public List<Arista> algoritmoPrim() {
        List<Arista> arbolExpMinima = new ArrayList<>();
        Set<Integer> verticesVisitados = new HashSet<>();
        PriorityQueue<Arista> colaPrioridad = new PriorityQueue<>(new AristaComparator());

        // Comenzar desde un vértice arbitrario, por ejemplo, el vértice 0
        int verticeInicial = 0;
        verticesVisitados.add(verticeInicial);

        while (verticesVisitados.size() < numVertices) {
            for (int verticeAdyacente : listaAdyacencia.get(verticeInicial)) {
                if (!verticesVisitados.contains(verticeAdyacente)) {
                    // Agregar todas las aristas que conectan el árbol con vértices no visitados
                    colaPrioridad.add(new Arista(verticeInicial, verticeAdyacente, obtenerPesoArista(verticeInicial, verticeAdyacente)));
                }
            }

            Arista aristaMinima = colaPrioridad.poll();
            int nuevoVertice = aristaMinima.vertice2;

            if (!verticesVisitados.contains(nuevoVertice)) {
                verticesVisitados.add(nuevoVertice);
                arbolExpMinima.add(aristaMinima);
                verticeInicial = nuevoVertice;
            }
        }

        return arbolExpMinima;
    }

    private int obtenerPesoArista(int vertice1, int vertice2) {
        // Implementa la lógica para obtener el peso de una arista entre dos vértices.
        // Puede ser un valor almacenado en una matriz de adyacencia, por ejemplo.
        // En este ejemplo, supondremos que el peso de la arista es la diferencia entre los vértices.
        return Math.abs(vertice1 - vertice2);
    }

    private class Arista {
        int vertice1;
        int vertice2;
        int peso;

        public Arista(int vertice1, int vertice2, int peso) {
            this.vertice1 = vertice1;
            this.vertice2 = vertice2;
            this.peso = peso;
        }
    }

    private class AristaComparator implements Comparator<Arista> {
        @Override
        public int compare(Arista arista1, Arista arista2) {
            return Integer.compare(arista1.peso, arista2.peso);
        }
    }

    public static void main(String[] args) {
        Grafo grafo = new Grafo(5);

        grafo.agregarArista(0, 1);
        grafo.agregarArista(0, 2);
        grafo.agregarArista(1, 2);
        grafo.agregarArista(1, 3);
        grafo.agregarArista(2, 4);

        List<Arista> arbolExpMinima = grafo.algoritmoPrim();

        System.out.println("Árbol de Expansión Mínima (Prim):");
        for (Arista arista : arbolExpMinima) {
            System.out.println("Vértice " + arista.vertice1 + " - Vértice " + arista.vertice2 + " Peso: " + arista.peso);
        }
    }
}