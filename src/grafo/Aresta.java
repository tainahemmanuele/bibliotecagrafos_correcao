package grafo;

public class Aresta {
    private Integer vertice1;
    private Integer vertice2;
    private double peso;

    public Aresta(Integer vertice1, Integer vertice2, double peso) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = peso;
    }

    public Aresta(Integer vertice1, Integer vertice2) {
        this.vertice1 = vertice1;
        this.vertice2 = vertice2;
        this.peso = 1;
    }

    public Aresta() {
	}

	public Integer getVertice1() {
        return vertice1;
    }

    public Integer getVertice2() {
        return vertice2;
    }

    public double getPeso() {
        return peso;
    }

    public Integer verticeAlvo(Integer atual) {
        if (atual == vertice1)
            return vertice2;
        return vertice1;
    }
}
