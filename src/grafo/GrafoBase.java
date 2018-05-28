package grafo;

import java.util.*;

public abstract class GrafoBase {

    protected Map<Integer, Set<Aresta>> vertices;
    private List<Aresta> arestas = new ArrayList<Aresta>();


    private float meanEdge;

    public GrafoBase() {
        this.vertices = new HashMap<Integer, Set<Aresta>>();

    }

    public String graphRepresentation (String type) {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        if (type.equals("AM")) {
            return getAMString(verticesTemp, getAM(verticesTemp));
        } else if (type.equals("AL")) {
           return getALString(getAL(verticesTemp));
        } else {
            return "Tipo não definido";
        }
    }

    protected String getALString(List<String> al) {
        String res = "";
        for (String s : al)
            res += s + "\n";
        return res;
    }


    public Map<Integer, Set<Aresta>> getVertices() {
        return vertices;
    }

    public void setVertices(Map<Integer, Set<Aresta>> vertices) {
        this.vertices = vertices;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public void setMeanEdge(float meanEdge) {
        this.meanEdge = meanEdge;
    }

    protected List<String> getAL(ArrayList<Integer> verticesOrdenados) {
        ArrayList<String> al = new ArrayList<>();
        for (Integer v : verticesOrdenados) {
            String adj = "";
            adj += v + " - ";
            ArrayList<String> vAdj = new ArrayList<>();
            for (Aresta a : this.vertices.get(v)) {
                if (a.getVertice1() != v)
                    vAdj.add(getALVertice1Model(a));
                else
                    vAdj.add(getALVertice2Model(a));
            }
            Collections.sort(vAdj);
            for (String ver : vAdj)
                adj += ver + " ";
            al.add(adj.trim());
        }
        return al;
    }

    protected abstract String getALVertice1Model(Aresta a);

    protected abstract String getALVertice2Model(Aresta a);

    protected  double[][] getAM(ArrayList<Integer> verticesOrdenados) {
        double am[][] = new double[getNumVertices()][getNumVertices()];

        for (double[] l : am)
            Arrays.fill(l, 0);

        for(int i = 0; i < getNumVertices(); i++) {
            Integer atual = verticesOrdenados.get(i);
            Set<Aresta> arestas = vertices.get(atual);
            for (Aresta a : arestas) {
                Integer targetVertex = a.verticeAlvo(atual);
                am[i][verticesOrdenados.indexOf(targetVertex)] = a.getPeso();
            }
        }
        return am;
    }
    protected String getAMString(List<Integer> orderedVertexes, double[][] adjacencyMatrix) {
        String amString = " ";

        for (int i = 0; i < this.getNumVertices(); i++) {
            amString += orderedVertexes.get(i);
            if (this.getNumVertices() - i > 1)
                amString += " ";
        }
        amString += "\n";

        for(int i = 0; i < this.getNumVertices(); i++) {
            String linha = orderedVertexes.get(i) + " ";
            for(int j = 0; j < this.getNumVertices(); j++) {
                linha += Double.toString(adjacencyMatrix[i][j]);
                if (this.getNumVertices() - j > 1)
                    linha += " ";
            }
            amString += linha + "\n";
        }
        return amString;
    }

    public float getMeanEdge() {
        if (getNumVertices() > 0) {
        	meanEdge = getEdgeNumber() / getVertexNumber();
        }else {
        	meanEdge = 0;
        }
        return meanEdge;
    }

    public String BFS(Integer v) {
        Queue<Integer> fila = new LinkedList<>();
        Map<Integer, Boolean> visitado = new HashMap<>();
        Map<Integer, Integer> predecessor = new HashMap<>();
        Map<Integer, Integer> nivel = new HashMap<>();

        for(Integer vertex : this.vertices.keySet()) {
            visitado.put(vertex, false);
        }
        visitado.put(v, true);
        predecessor.put(v, null);
        nivel.put(v, 0);
        fila.add(v);
        while(!fila.isEmpty()){
            Integer atual = fila.poll();
            for(Integer adjacentVertex : getAdjacentVertexes(atual)){
                if(!visitado.get(adjacentVertex)){
                    visitado.put(adjacentVertex, true);
                    predecessor.put(adjacentVertex, atual);
                    nivel.put(adjacentVertex, nivel.get(atual) + 1);
                    fila.add(adjacentVertex);
                }
            }
        }
        String res = "";
        for(Integer vertice : visitado.keySet()) {
            res += (vertice.toString() + " - " + nivel.get(vertice).toString() + "\n");
//            if(predecessor.get(vertice) == null)
//                res += ("\n");
////            else
////                res += predecessor.get(v).toString() + "\n";
        }
        return res;
    }

    public int getNumVertices() {
        return vertices.keySet().size();
    }

    public Set<Integer> getAdjacentVertexes(Integer v) {
        HashSet<Integer> l = new HashSet<>();
        for (Aresta a: vertices.get(v)) {
            l.add(a.verticeAlvo(v));
        }
        return l;
    }

    public boolean connected() {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        double[][] am = getAM(verticesTemp);
        Queue<Integer> fila = new LinkedList<>();
        int[] visitado = new int[getNumVertices() + 1];
        int i, element;
        Integer source = vertices.keySet().iterator().next();
        visitado[source] = 1;
        fila.add(source);
        while (!fila.isEmpty()) {
            element = fila.remove();
            i = element;
            while (i <= getNumVertices()) {
                if (am[element][i] != 0 && visitado[i] == 0) {
                    fila.add(i);
                    visitado[i] = 1;
                }
                if (am[element][i] != 0 && visitado[i] != 0) {
                    return true;
                }
                i++;
            }
        }
        return false;
    }

    public int getEdgeNumber() {
        Set<Aresta> set = new HashSet<>();
        for (Integer v : vertices.keySet())
            for (Aresta a : vertices.get(v))
                set.add(a);
        return set.size();
    }

    public int getVertexNumber() {
        return vertices.size();
    }

    public abstract void addAresta(String aresta);

    public String DFS(Integer source) {
        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        Collections.sort(verticesTemp);
        double[][] am = getAM(verticesTemp);
        Stack<Integer> pilha = new Stack<>();  // para o DFS usa-se umma pilha como auxiliar
        int[] visitado = new int[getNumVertices() + 1];
        int i, element;
        visitado[source] = 1;
        pilha.push(source);
        String res = "";

        while (!pilha.isEmpty()) {
            element = pilha.peek(); // parte-se do topo da pilha
            i = 0;
            while (i <= getNumVertices()) { // verifica-se se ha adjacentes nao-visitados
                if (am[element][i] != 0 && visitado[i] == 0) {
                    pilha.push(i);
                    visitado[i] = 1;
                    break; //segue-se para proximo vertice em profundidade
                }
                i++;
            }
            if (i == getNumVertices()) // checou todos os possiveis vertices adjacentes e nao restou avanco em profundidade
            	res += pilha.pop() + "\n"; // remove da pilha e registra na saida
        }
        return res;
    }

    int minDistance(int dist[], Boolean sptSet[])
    {
        // Initialize min value
        int min = Integer.MAX_VALUE, min_index=-1;

        for (int v = 0; v < this.getNumVertices(); v++)
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }

        return min_index;
    }


    public String shortestPath(Integer head, Integer tail){
    	if (head == tail)
    	    return head.toString();

        ArrayList<Integer> verticesTemp = new ArrayList<Integer>(this.vertices.keySet());
        double[][] am = getAM(verticesTemp);

        boolean[] visited = new boolean[getNumVertices()];
        for( int i = 0; i < getNumVertices(); i++){
            visited[i] = false; //inicializa o array com nenhum visitado
        }
        visited[head] = true;


        double[] distances = new double[getNumVertices()];
        distances[head] = 0; // distancia do vertice para si mesmo eh 0

        ArrayList<Integer> path = new ArrayList<Integer>();
        path.add(head);

        int predecessor = head;
        while(predecessor != tail){

            for( int i = 0; i < getNumVertices(); i++){
                if (am[predecessor][i] != 0.0){ //quando o peso na matriz de adjacencias nao eh nulo, ha uma aresta
                    distances[i] = am[predecessor][i]; // distancia atual do vertice é somada ao peso da nova aresta
                }
                else
                    distances[i] = Double.MAX_VALUE; // quando nao eh nulo nao ha aresta ligando-os e o peso é tido como infinito
            }

            int closestVertex = predecessor;
            double closestVertexDistance = Double.MIN_VALUE;
            for( int j = 0; j < getNumVertices(); j++){
                if (!visited[j] && distances[j] < closestVertexDistance) {
                    closestVertex = j;
                    closestVertexDistance = distances[j];
                }
            }
            predecessor = closestVertex; // o seguinte vertice escolhido eh decidido por euristica gulosa par ao maisproximo vertice ainda nao visitado
            visited[closestVertex] = true;
            path.add(closestVertex);
        }

    	return path.toString();
    }


    public String mst() {
    	return arvoreGeradoraMinima (this.vertices.keySet().iterator().next());
    }

    private String arvoreGeradoraMinima(Integer origem) {

        if(this.connected() == false) {
            new RuntimeException("Nao eh possivel realizar operacao!\nGrafo n�o � conexo.\n");
        }


        String caminho = "Prim " +origem + ":\n";

        Set<Integer> vertexes = this.vertices.keySet();
        List<Aresta> arestas = getArestas();

        List<Integer> visitados = new ArrayList<Integer>();

        Aresta arestaAtual = new Aresta();
        Integer verticeAtual = origem;
        int custoTotal = 0;


        while (visitados.size() < vertexes.size()){

            visitados.add(verticeAtual);
            arestaAtual= percorreArestas(visitados, verticeAtual);

            if(arestaAtual.getVertice2() == null)
                break;

            caminho += arestaAtual.getVertice1() + " " + arestaAtual.getVertice2() +" "+ arestaAtual.getPeso() + ",\n";  // FORMATAR A SAIDA AQUI

            custoTotal += arestaAtual.getPeso();

            verticeAtual = arestaAtual.getVertice2();
        }

        caminho += custoTotal;

        return caminho + "\n";
    }

    private Aresta percorreArestas(List<Integer> visitados, Integer verticeAtual) {

        Aresta arestaSelecionada = new Aresta();
        int menorCusto = Integer.MAX_VALUE;

        for (Integer visitado : visitados) {
            for (Aresta aresta : this.arestas) {
                if(Integer.toString(aresta.getVertice1()).equals(visitado)){
                    if(!visitados.contains(Integer.toString(aresta.getVertice2()))){
                        if(menorCusto > aresta.getPeso()){
                            menorCusto = (int) aresta.getPeso();
                            arestaSelecionada = aresta;
                        }
                    }
                }
            }
        }
        return arestaSelecionada;
    }
 
}
