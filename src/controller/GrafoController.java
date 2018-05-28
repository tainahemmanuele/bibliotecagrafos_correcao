package controller;

import grafo.Aresta;
import grafo.Grafo;
import grafo.GrafoBase;
import grafo.GrafoPonderado;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class GrafoController {

    public Grafo readGrafo(String path) {

        return (Grafo) createGrafo(path, false);
    }

    public GrafoPonderado readWeightedGrafo(String path){
        return (GrafoPonderado) createGrafo(path, false);
    }

    private GrafoBase createGrafo(String path, boolean ponderado){
        GrafoBase newGraph = null;
        try {
            FileReader fileReader = new FileReader(path);
            BufferedReader buffer = new BufferedReader(fileReader);

            Integer vertices = Integer.parseInt(buffer.readLine());


            if(ponderado){
                newGraph = new GrafoPonderado();
            } else {
                newGraph = new Grafo();
            }

            for (int i = 0; i < vertices ; i++) {
                String aresta = buffer.readLine();
                newGraph.addAresta(aresta);
            }

            fileReader.close();
        } catch (java.io.IOException e) {
            System.err.println("Error opening the file: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return newGraph;

    }

    public String graphRepresentation (GrafoBase graph, String type) {
        return graph.graphRepresentation(type);
    }

    public String BFS(GrafoBase graph, Integer vertex) {
        return graph.BFS(vertex);
    }

    public String DFS(GrafoBase graph, Integer vertex) {
        return graph.DFS(vertex);
    }

    public boolean connected(GrafoBase graph) {
        return graph.connected();
    }

    public String shortestPath(GrafoBase graph, Integer head, Integer tail) {
        return graph.shortestPath(head, tail);
    }

    public String mst(GrafoBase graph) {
        return graph.mst();
    }

    public int getVertexNumber(Grafo Grafo) {
        return Grafo.getNumVertices();
    }

    public int getEdgeNumber(Grafo Grafo) {
        return Grafo.getEdgeNumber();
    }

    public float getMeanEdge(Grafo Grafo) {
        return Grafo.getMeanEdge();
    }

    public String GrafoRepresentation (Grafo Grafo, String type) {
        return "";
    }
}
