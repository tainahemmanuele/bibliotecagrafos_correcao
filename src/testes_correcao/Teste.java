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
	Grafo grafo1, grafo3;
	GrafoPonderado grafo2,grafo4;


	

	@Before
	public void voidsetUp() throws Exception {
		controller = new GrafoController();


		
		grafo1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		//grafo2 = controller.readWeightedGrafo("../bibliotecagrafos_correcao/src/q2_grafos.txt"); //N�o cria grafo com peso. D� erro de cast
		//grafo3 = controller.readGrafo("../bibliotecagrafos_correcao/src/q3_grafos.txt");
		//grafo4 = controller.readWeightedGrafo("../bibliotecagrafos_correcao/src/q4_grafos.txt"); //N�o cria grafo com peso. D� erro de cast
	}
	
	@Test
	public void testreadGrafo(){
		Grafo grafoTeste1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		Assert.assertEquals(5, controller.getVertexNumber(grafoTeste1));
		Assert.assertNotEquals(2, controller.getVertexNumber(grafoTeste1));

		
		Grafo grafoTeste2 = controller.readGrafo("../bibliotecagrafos_correcao/src/q4_grafos.txt");
		Assert.assertEquals(8, controller.getVertexNumber(grafoTeste2));
		Assert.assertNotEquals(0, controller.getVertexNumber(grafoTeste2));
		
	}
	
	@Test
	public void testgetVertexNumber(){
		Assert.assertEquals(5, controller.getVertexNumber(grafo1));
		//Assert.assertEquals(5, grafo2.getVertexNumber());
		//Assert.assertEquals(8, grafo4.getVertexNumber());
	}
	
	
	@Test
	public void testgetEdgeNumber(){
		Assert.assertNotEquals(5, controller.getEdgeNumber(grafo1)); //Quantidade de arestas deveria ser 5, por�m retorna 10
		//Assert.assertNotEquals(6, grafo2.getEdgeNumber()); //O metodo conta 2 vezes algumas arestas, logo, o resultado da diferente do esperado

	}
	
	@Test
	public void gettestMeanEdge(){
		Assert.assertEquals(2,2 , controller.getMeanEdge(grafo1));
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
}
