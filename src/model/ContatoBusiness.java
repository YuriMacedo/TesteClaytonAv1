package model;

import java.util.List;

public class ContatoBusiness {
	
	private List<Contato>contatos;

	public ContatoBusiness(List<Contato> contatos) {
		
		this.contatos = contatos;
	}
	
	//Retorna todos os contatos
	public List<Contato>getTodosContatos() {
		return contatos;
	}
	
	//Retorna o contato pelo Id
	public Contato getContatoById(int id){
		for (Contato contato : contatos) {
				if (id == contato.getId()) 
					return contato;
		}
		return null;
	}
	
	//Adiciona um novo contato na lista
	public boolean addContato(Contato novoContato) {
		Contato contato = getContatoById(novoContato.getId());
		if (contato == null) {
			contatos.add(novoContato);
			return true;
		}
		return false;
		
	}
	//Remove um contato da lista
	public boolean removeContato(int id) {
		for (Contato contato : contatos) {
			if (id ==contato.getId()) {
				contatos.remove(id);
				return true;
			}
		}
		return false;
	}
	
	public void clear() {
		contatos.clear();
	}
}
