package testes_correcao;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

import controller.GrafoController;
import grafo.Grafo;
import grafo.GrafoBase;
import junit.framework.Assert;


public class Teste {
	GrafoController  controller;
	Grafo grafo1, grafo2, grafo3, grafo4;

	

	@Before
	public void voidsetUp() throws Exception {
		controller = new GrafoController();


		
		grafo1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		grafo2 = controller.readGrafo("../bibliotecagrafos_correcao/src/q2_grafos.txt");
		grafo3 = controller.readGrafo("../bibliotecagrafos_correcao/src/q3_grafos.txt");
		grafo4 = controller.readGrafo("../bibliotecagrafos_correcao/src/q4_grafos.txt");
	}
	
	@Test
	public void testreadGrafo(){
		Grafo grafoTeste1 = controller.readGrafo("../bibliotecagrafos_correcao/src/q1_grafos.txt");
		Assert.assertEquals(5, controller.getVertexNumber(grafoTeste1));
	}
}
