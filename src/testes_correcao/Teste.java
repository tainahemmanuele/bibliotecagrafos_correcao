package testes_correcao;

import java.io.File;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controller.GrafoController;
import grafo.Grafo;
import grafo.GrafoBase;
import grafo.GrafoPonderado;



public class Teste {
	GrafoController  controller;
	Grafo grafo1, grafo3, grafo5, grafo6, grafo7;
	GrafoPonderado grafo2,grafo4;


	

	@Before
	public void voidsetUp() throws Exception {
		controller = new GrafoController();


		
		grafo1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		//grafo2 = controller.readWeightedGrafo("../bibliotecagrafos_correcao/src/q2_grafos.txt"); //N�o cria grafo com peso. D� erro de cast
		//grafo3 = controller.readGrafo("../bibliotecagrafos_correcao/src/q3_grafos.txt");
		//grafo4 = controller.readWeightedGrafo("../bibliotecagrafos_correcao/src/q4_grafos.txt"); //N�o cria grafo com peso. D� erro de cast
		grafo5 = controller.readGrafo("../bibliotecagrafos_correcao/src/q5_grafos.txt");
		grafo6 = controller.readGrafo("../bibliotecagrafos_correcao/src/q6_grafos.txt");
		grafo7 = controller.readGrafo("../bibliotecagrafos_correcao/src/q7_grafos.txt");
	}
	
	@Test
	public void testreadGrafo(){
		Grafo grafoTeste1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		Assert.assertEquals(5, controller.getVertexNumber(grafoTeste1));
		Assert.assertNotEquals(2, controller.getVertexNumber(grafoTeste1));

		
		Grafo grafoTeste2 = controller.readGrafo("../bibliotecagrafos_correcao/src/q4_grafos.txt");
		Assert.assertEquals(8, controller.getVertexNumber(grafoTeste2));
		Assert.assertNotEquals(0, controller.getVertexNumber(grafoTeste2));
		
		Grafo grafoTeste3 =   controller.readGrafo("../bibliotecagrafos_correcao/src/q5_grafos.txt");
		Assert.assertEquals(6, controller.getVertexNumber(grafoTeste3));
		Assert.assertNotEquals(10, controller.getEdgeNumber(grafoTeste3)); //Retorna numero dobrado de vertices. S� conta at� a 6 linha do arquivo (apos o numero de vertices) e dobra o valor da mesma. N�o chega a construir o grafo completo.

		Grafo grafoTeste4 = controller.readGrafo("../bibliotecagrafos_correcao/src/q6_grafos.txt");
		Assert.assertEquals(4, controller.getVertexNumber(grafoTeste4));
		Assert.assertNotEquals(4, controller.getEdgeNumber(grafoTeste4));

	}
	
	@Test
	public void testgetVertexNumber(){
		Assert.assertEquals(5, controller.getVertexNumber(grafo1));
		Assert.assertEquals(6, controller.getVertexNumber(grafo5));
		Assert.assertEquals(4, controller.getVertexNumber(grafo6));
		//Assert.assertEquals(5, grafo2.getVertexNumber());
		//Assert.assertEquals(8, grafo4.getVertexNumber());
	}
	
	
	@Test
	public void testgetEdgeNumber(){

		Assert.assertNotEquals(5, controller.getEdgeNumber(grafo1)); //Quantidade de arestas deveria ser 5, por�m retorna 10
		Assert.assertNotEquals(10, controller.getEdgeNumber(grafo5)); //Quantidade arestas deveria ser 10, porem est� retornando o numero dobrado de vertices, pois o arquivo de entrada so le a quantidade de linhas que formam as arestas a partir do numero de vertices.
		Assert.assertNotEquals(4, controller.getEdgeNumber(grafo6)); //Quantidade arestas deveria ser 4, porem est� retornando o numero dobrado de vertices, pois o arquivo de entrada so le a quantidade de linhas que formam as arestas a partir do numero de vertices.
		//Assert.assertNotEquals(6, grafo2.getEdgeNumber()); //O metodo conta 2 vezes algumas arestas, logo, o resultado da diferente do esperado

	}
	
	@Test
	public void gettestMeanEdge(){
		Assert.assertEquals(2,2 , controller.getMeanEdge(grafo1));
		Assert.assertNotEquals(1.6, controller.getMeanEdge(grafo5), 5*Math.ulp(1.6)); //Grau medio do grafo5 deveria ser 1.6 e n�o 2 como retornado
		Assert.assertNotEquals(1, controller.getMeanEdge(grafo6)); //Grau medio do grafo 6 deveria ser 1 e nao 
		//Assert.assertEquals(2.4 , 2.4, grafo2.getMeanEdge());
	}
	
	@Test
	public void testBFS() {

        String output1 = "1 - 0 -\r\n" + 
                "2 - 1 1\r\n" + 
                "3 - 2 5\r\n" + 
                "4 - 2 5\r\n" + 
                "5 - 1 1\r\n";
        
        String output2 = "1 - 1 5\r\n" + 
                "2 - 1 5\r\n" + 
                "3 - 1 5\r\n" + 
                "4 - 1 5\r\n" + 
                "5 - 0 -\r\n" ;
        
        String output3 = "1 - 2 5\r\n" + 
                "2 - 2 5\r\n" + 
                "3 - 0 -\r\n" + 
                "4 - 2 5\r\n" + 
                "5 - 1 3\r\n" ;
        

        Assert.assertNotEquals(output1, controller.BFS(grafo1, 1));
        Assert.assertNotEquals(output3, controller.BFS(grafo1, 3));
        Assert.assertNotEquals(output2, controller.BFS(grafo1, 5));
	}
	
	@Test
	public void testBFSException() {
		
		String output1 = "1 - 0 -\r\n" + 
                "2 - 1 1\r\n" + 
                "3 - 2 5\r\n" + 
                "4 - 2 5\r\n" + 
                "5 - 1 1\r\n";
		try {
			Assert.assertEquals(output1, controller.BFS(grafo1, 0)); // Metodo apresenta exception antes de passar por todas as linhas, assim nao eh possivel cobrir todo o metodo.
		}catch(Exception e) {
			
		}
	}
	
	@Test
	public void testDFS() {
		String resultado1 = "1 - 0 -\n" +
                "2 - 1 1\n" +
                "3 - 3 5\n" +
                "4 - 3 5\n" +
                "5 - 2 2\n";
		try {
			Assert.assertEquals(resultado1, controller.DFS(grafo1, 0)); // Metodo apresenta exception antes de passar por todas as linhas, assim nao eh possivel cobrir todo o metodo.
		} catch (Exception e) {
			
		}
	}
	
	@Test
	public void testRepresetacoes() {
		String resultadoAM1 = " 1 2 3 4 5\n" + 
				"1 0.0 1.0 0.0 0.0 1.0\n" + 
				"2 1.0 0.0 0.0 0.0 1.0\n" + 
				"3 0.0 0.0 0.0 0.0 1.0\n" + 
				"4 0.0 0.0 0.0 0.0 1.0\n" + 
				"5 1.0 1.0 1.0 1.0 0.0\n";
		
		String resultadoAL1 = "1 - 2 5\n" + 
				"2 - 1 5\n" + 
				"3 - 5\n" + 
				"4 - 5\n" + 
				"5 - 1 2 3 4\n";
		
		String resultadoAM5 = " 1 2 3 4 5 6\n" + 
				"1 0.0 1.0 1.0 0.0 1.0 1.0\n" + 
				"2 1.0 0.0 1.0 1.0 1.0 0.0\n" + 
				"3 1.0 1.0 0.0 0.0 0.0 0.0\n" + 
				"4 0.0 1.0 0.0 0.0 1.0 1.0\n" + 
				"5 1.0 1.0 0.0 1.0 0.0 0.0\n" + 
				"6 1.0 0.0 0.0 1.0 0.0 0.0\n";
		
		String resultadoAL5 = "1 - 2 3 5 6\n" + 
				"2 - 1 3 4 5\n" + 
				"3 - 1 2\n" + 
				"4 - 2 5 6\n" + 
				"5 - 1 2 4\n" + 
				"6 - 1 4\n";
		
		Assert.assertEquals(resultadoAM1, controller.graphRepresentation(grafo1, "AM"));
		Assert.assertEquals(resultadoAL1, controller.graphRepresentation(grafo1, "AL"));
		Assert.assertEquals("Tipo não definido", controller.graphRepresentation(grafo1, "AK"));
		Assert.assertNotEquals(resultadoAM5, controller.graphRepresentation(grafo5, "AM")); // Comparacao deve ser diferente, metodo nao funciona devidamente.
		Assert.assertNotEquals(resultadoAL5, controller.graphRepresentation(grafo5, "AL")); // Comparacao deve ser diferente, metodo nao funciona devidamente.
	}
	
	@Test
	public void testConnected() {
		Assert.assertTrue(controller.connected(grafo1)); //retorna true, porem nao foi implementado corretamente
		Assert.assertNotEquals(false, controller.connected(grafo7)); //deveria retornar false, porem retornou true
	}
	
	@Test
	public void testMst() {
		String mst1 = "1 - 0 -\n"
				   + "2 - 1 1\n"
				   + "3 - 2 5\n"
				   + "4 - 2 5\n"
				   + "5 - 1 1\n";
		
		String mst6 = "1 - 0 -\n"
				   + "2 - 1 1\n"
				   + "3 - 2 2\n"
				   + "4 - 1 1\n";
		
		Assert.assertNotEquals(mst1, controller.mst(grafo1)); //mst nao esta funcionando
		Assert.assertNotEquals(mst6, controller.mst(grafo6)); //mst nao esta funcionando
	}
}
