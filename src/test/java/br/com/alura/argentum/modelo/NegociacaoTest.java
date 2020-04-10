package br.com.alura.argentum.modelo;

import static org.junit.Assert.*;

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

	@Test
	public void mesmoSegundoEhDoMesmoDia() {

		LocalDateTime agora = LocalDateTime.now();
		LocalDateTime mesmoMomento = agora;

		Negociacao negociacao = new Negociacao(100.0, 20, agora);

		assertTrue(negociacao.isMesmoDia(mesmoMomento));
	}

	@Test
	public void mesmoDiaMasMesesDiferentesNaoSaoDoMesmoDia() {

		LocalDateTime manha = LocalDateTime.of(2016, 02, 25, 8, 30);
		LocalDateTime tarde = LocalDateTime.of(2016, 03, 25, 8, 30);
		
		Negociacao negociacao = new Negociacao(100.0, 20, manha);

		assertFalse(negociacao.isMesmoDia(tarde));
	}

	@Test
	public void anosDiferentesNaoEhMesmoDia() {

		LocalDateTime manha = LocalDateTime.of(2016, 02, 25, 8, 30);
		LocalDateTime tarde = LocalDateTime.of(2017, 02, 25, 8, 30);

		Negociacao negociacao = new Negociacao(100.0, 20, manha);

		assertFalse(negociacao.isMesmoDia(tarde));
	}

}
