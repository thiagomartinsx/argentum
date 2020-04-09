package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;

import org.junit.Test;

public class NegociacaoTest {
	
	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComDataNula() {
		new Negociacao(10, 5, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComPrecoNegativo() {
		LocalDateTime hoje = LocalDateTime.now();
		new Negociacao(-10, 5, hoje);
	}

	@Test(expected = IllegalArgumentException.class)
	public void naoCriaNegociacaoComQuantidadeZero() {
		LocalDateTime hoje = LocalDateTime.now();
		new Negociacao(10, 0, hoje);
	}

}
