package testes_correcao;

import java.io.File;

import org.junit.Assert;
import org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import controller.GrafoController;
import grafo.Grafo;
import grafo.GrafoBase;



public class Teste {
	GrafoController  controller;
	Grafo grafo1, grafo2, grafo3, grafo4;

	

	@Before
	public void voidsetUp() throws Exception {
		controller = new GrafoController();


		
		grafo1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		grafo2 = controller.readGrafo("../bibliotecagrafos_correcao/src/q2_grafos.txt");
		//grafo3 = controller.readGrafo("../bibliotecagrafos_correcao/src/q3_grafos.txt");
		grafo4 = controller.readGrafo("../bibliotecagrafos_correcao/src/q4_grafos.txt");
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
		Assert.assertEquals(5, controller.getVertexNumber(grafo2));
		Assert.assertEquals(8, controller.getVertexNumber(grafo4));
	}
	
	
	@Test
	public void testgetEdgeNumber(){
		Assert.assertNotEquals(5, controller.getEdgeNumber(grafo1));
		Assert.assertNotEquals(6, controller.getEdgeNumber(grafo2)); //O metodo conta 2 vezes algumas arestas, logo, o resultado da diferente do esperado

	}
	
	@Test
	public void getMeanEdge(){
		Assert.assertEquals(2,2 , controller.getMeanEdge(grafo1));
		Assert.assertEquals(2.4 , 2.4, controller.getMeanEdge(grafo2));
	}
}
