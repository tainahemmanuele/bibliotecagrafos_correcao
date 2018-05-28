package grafo;

import java.util.*;

public class Grafo extends GrafoBase {

    public Grafo() {
        super();
    }

    @Override
    protected String getALVertice1Model(Aresta a) {
        return a.getVertice1().toString();
    }

    @Override
    protected String getALVertice2Model(Aresta a) {
        return a.getVertice2().toString();
    }


    @Override
    public void addAresta(String strAresta) {
        String[] arestas = strAresta.split(" ");
        Integer vertice1 = Integer.parseInt(arestas[0]);
        Integer vertice2 = Integer.parseInt(arestas[1]);

        int defaultWight = 1;

        Aresta aresta = new Aresta(vertice1, vertice2, defaultWight);
        Aresta reverseAresta = new Aresta(vertice2, vertice1, defaultWight);

        if(!this.vertices.containsKey(vertice1)){
            this.vertices.put(vertice1, new HashSet<Aresta>());
        }
        if(!this.vertices.containsKey(vertice2)){
            this.vertices.put(vertice2, new HashSet<Aresta>());
        }

        this.vertices.get(vertice1).add(aresta);
        this.vertices.get(vertice2).add(reverseAresta);
    }



    @Override
    public String toString() {
        return "Grafo{" +
                "vertices=" + vertices +
                '}';
    }
}
