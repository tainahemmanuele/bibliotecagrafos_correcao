package teste;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import controller.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class testGrafoController {
    //teste
    private GrafoController controle;
    private final String grafo1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; // connected graph
    private final String grafo2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; // disconnected graph
    private final String grafo3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; // disconnected graph


    @Before
    public void criandoGrafo() {
        List<String> linhas = Arrays.asList("5", "1 2", "2 5", "5 3", "4 5", "1 5");
        Path arquivo = Paths.get(grafo1);
        try {
            Files.write(arquivo, linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void criandoGrafocomPeso() {
        List<String> linhas = Arrays.asList("5", "1 2 0.1", "2 5 0.2", "5 3 5", "3 9 - 9.5", "4 5 2.3", "1 5 1");
        Path arquivo = Paths.get(grafo2);
        try {
            Files.write(arquivo, linhas, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void createController() {

        controle = new GrafoController();
    }


    @Test
    public void testCreateSimpleGraphFromFile() {
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGets() {
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertEquals(5, controle.getVertexNumber(controle.readGrafo(grafo1)));
    }

    @Test
    public void testGetsfails() {
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertNotEquals(2, controle.getVertexNumber(controle.readGrafo(grafo1)));
        Assert.assertNotEquals(6, controle.getEdgeNumber(controle.readGrafo(grafo1)));
        Assert.assertNotEquals(1.0, controle.getMeanEdge(controle.readGrafo(grafo1)));
    }


    @Test
    public void testConnection() {
        controle = new GrafoController();
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(controle.connected(controle.readGrafo(grafo1)));

    }


    //exemplo 1 de saida  AM e AL
    @Test
    public void testRepresentacao2() {
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        String expected = "1 - 2 5" + System.getProperty("line.separator") +
                "2 - 1 5" + System.getProperty("line.separator") +
                "3 - 5" + System.getProperty("line.separator") +
                "4 - 5" + System.getProperty("line.separator") +
                "5 - 1 2 3 4\n";

        String expectedAM =
                " 1 2 3 4 5\n" +
                        "1 0.0 1.0 0.0 0.0 1.0\n" +
                        "2 1.0 0.0 0.0 0.0 1.0\n" +
                        "3 0.0 0.0 0.0 0.0 1.0\n" +
                        "4 0.0 0.0 0.0 0.0 1.0\n" +
                        "5 1.0 1.0 1.0 1.0 0.0\n";


        try {
            Assert.assertEquals(expectedAM, controle.graphRepresentation(controle.readGrafo(grafo1), "AM"));
            Assert.assertEquals(expected, controle.graphRepresentation(controle.readGrafo(grafo1), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testRepresentacaoErrada() {
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            Assert.fail();
        }
        String expected = "1 - 2 5" + System.getProperty("line.separator") +
                "2 - 1 5" + System.getProperty("line.separator") +
                "3 - 5" + System.getProperty("line.separator") +
                "4 - 5" + System.getProperty("line.separator") +
                "5 - 1 2 3 4";

        String expectedAM =
                "    1   2   3   4   5" + System.getProperty("line.separator") +
                        "1   0   0   0   0   0" + System.getProperty("line.separator") +
                        "2   0   0   0   0   0" + System.getProperty("line.separator") +
                        "3   0   0   0   0   0" + System.getProperty("line.separator") +
                        "4   0   0   0   0   0" + System.getProperty("line.separator") +
                        "5   0   0   0   0   0" + System.getProperty("line.separator");


        try {
            Assert.assertNotEquals(expectedAM, controle.graphRepresentation(controle.readGrafo(grafo1), "AM"));
            Assert.assertNotEquals(expected, controle.graphRepresentation(controle.readGrafo(grafo1), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    //exemplo 2 de saida AM e AL
    @Test
    public void testRepresentacao1() {
        //System.out.println(controle.graphRepresentation(controle.readGrafo(grafo1),"AM"));
        try {
            controle.readGrafo(grafo1);
        } catch (Exception e) {
            Assert.fail();

        }
        //System.out.println(controle.graphRepresentation(controle.readGrafo(grafo1),"AL"));
        String expected = "1 - 2\n" +
                "2 - 1 5\n" +
                "3 - 5 9\n" +
                "4 - 5\n" +
                "5 - 2 3 4\n" +
                "9 - 3\n";

        try {
            Assert.assertEquals(expected, controle.graphRepresentation(controle.readGrafo(grafo3), "AL"));
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @Test
    public void testaBFS(){
        String expected = "1 - 0\n" +
                "2 - 1\n" +
                "3 - 2\n" +
                "4 - 2\n" +
                "5 - 1\n";
        try {
            Assert.assertEquals(expected, controle.BFS(controle.readGrafo(grafo1), 1));
        } catch (Exception e) {
            Assert.fail();
        }
    }
    

}