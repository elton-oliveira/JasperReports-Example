package br.com.fluentcode.jasperreports.dto;

import java.util.ArrayList;
import java.util.Collection;

public class Empresa {
	
	private String nome;
	
	private Collection<Contato> contatos = new ArrayList<Contato>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Collection<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(Collection<Contato> contatos) {
		this.contatos = contatos;
	}
	
}
