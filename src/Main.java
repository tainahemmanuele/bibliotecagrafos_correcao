import controller.GrafoController;
import grafo.GrafoBase;

/**
 * Created by jhonatanbds on 18/05/18.
 */
public class Main {

    public static void main(String[] args) {
        GrafoController controller = new GrafoController();
        GrafoBase grafo = controller.readGrafo("grafo.txt");
        System.out.println(grafo.graphRepresentation("AM"));

        //System.out.println(grafo.shortestPath(1, 2));

    }
}