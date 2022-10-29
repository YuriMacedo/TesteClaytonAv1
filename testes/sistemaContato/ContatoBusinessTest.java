package sistemaContato;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Contato;
import model.ContatoBusiness;

public class ContatoBusinessTest {
	private int idContato01 = 1;
	private int idContato02 = 2;
	private ContatoBusiness contatoService;
	
	@Before
	public void setUp() {
		Contato contato1 = new Contato(idContato01,"Yuri Macedo","yuri_daleTestes@Dale.com",13);
		Contato contato2 = new Contato(idContato02,"Victor Macedo","victor_daleTestes@Dale.com",37);
		
		List<Contato> contatos = new ArrayList();
		contatos.add(contato1);
		contatos.add(contato2);
		
		contatoService = new ContatoBusiness(contatos);
	}
	
	@After
	public void tearDown() {
		contatoService.clear();
	}
	
	//Testando o get com ids existentes
	@Test
	public void getContatoByIdTest() {
		Contato contato = contatoService.getContatoById(idContato01);
		Contato contato1 = contatoService.getContatoById(idContato02);
		
		assertThat(contato.getId(), is(idContato01));
		assertThat(contato1.getId(), is(idContato02));
	}
	@Test
	public void getContatoByIdInexistente() {
		Contato contato = contatoService.getContatoById(10);
		Contato contato2 = contatoService.getContatoById(20);
		
		assertNull(contato);
		assertNull(contato2);		
		assertThat(contatoService.getTodosContatos().size(), is(2));
	}
	
	@Test
	public void addContatoTest() {
		Contato contatoTest1 = new Contato(3,"Cliente certo 1","euSouUmCliente@hotmail.com",34);
		Contato contatoTest2 = new Contato(4,"Cliente certo 2","euSouOMelhorCliente@dale.com",55);
		
		contatoService.addContato(contatoTest1);
		contatoService.addContato(contatoTest2);
		
		Contato testaContato = contatoService.getContatoById(3);
		Contato testaContato2 = contatoService.getContatoById(4);
		
		//Aqui eu estou me certificando pra ver se o contato que foi adicionado comparando com o email dele
		boolean adicionado1 = testaContato.getEmail().equals("euSouUmCliente@hotmail.com");
		boolean adicionado2 = testaContato2.getEmail().equals("euSouOMelhorCliente@dale.com");
		
		assertTrue(adicionado1);
		assertTrue(adicionado2);
		assertThat(contatoService.getTodosContatos().size(),is(4));
		
	}
	//tenta adicionar um contato já existente(Com um id existente)
	@Test 
	public void addContatoErradoTest() {
		Contato contato1 = new Contato(idContato01,"Yuri Macedo Errado","yuri_daleErrado@Dale.com",27);
		
		boolean sucesso =  contatoService.addContato(contato1);
		
		assertFalse(sucesso);
		assertThat(contatoService.getTodosContatos().size(), is(2));
		
	}
	
	//Testando o delete com ids existentes
	@Test
	public void removeContatoTest() {
		boolean contatoRemovido = contatoService.removeContato(idContato01);
		
		assertThat(contatoRemovido, is(true));
		assertThat(contatoService.getTodosContatos().size(), is(1));
		
	}
	
	@Test
	//Testando o delete com id inexistente
	public void removeContatoTestInexistente() {
		boolean contatoRemovido = contatoService.removeContato(10);
		
		assertThat(contatoRemovido, is(false));
		assertThat(contatoService.getTodosContatos().size(), is(2));
	}
}
