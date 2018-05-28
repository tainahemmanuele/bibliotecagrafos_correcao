package grafo;

import java.util.HashSet;

public class GrafoPonderado extends GrafoBase {

    public GrafoPonderado() {
        super();
    }

    @Override
    protected String getALVertice1Model(Aresta a) {
        return a.getVertice1() + "(" + a.getPeso() + ")";
    }

    @Override
    protected String getALVertice2Model(Aresta a) {
        return a.getVertice2() + "(" + a.getPeso() + ")";
    }


    @Override
    public void addAresta(String strAresta) {
        String[] arestas = strAresta.split(" ");
        Integer vertice1 = Integer.parseInt(arestas[0]);
        Integer vertice2 = Integer.parseInt(arestas[1]);
        Integer weight = Integer.parseInt(arestas[2]);


        Aresta aresta = new Aresta(vertice1, vertice2, weight);
        Aresta reverseAresta = new Aresta(vertice2, vertice1, weight);

        if(!this.vertices.containsKey(vertice1)){
            this.vertices.put(vertice1, new HashSet<Aresta>());
        }
        if(!this.vertices.containsKey(vertice2)){
            this.vertices.put(vertice2, new HashSet<Aresta>());
        }

        this.vertices.get(vertice1).add(aresta);
        this.vertices.get(vertice2).add(reverseAresta);
    }


}
