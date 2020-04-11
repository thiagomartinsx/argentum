package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;

public class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void maximoNaoDeveSerMenorQueMinimo() {
		CandlestickBuilder builder = new CandlestickBuilder();
		Candlestick candle = builder.comAbertura(20.0).comFechamento(30.0).comMaximo(15.0).comMinimo(50.0)
				.comVolume(300).comData(LocalDateTime.now()).geraCandle();
	}

	@Test
	public void ehAltaSeFechamentoForIgualAbertura() {
		CandlestickBuilder builder = new CandlestickBuilder();
		Candlestick candle = builder.comAbertura(30.0).comFechamento(30.0).comMaximo(35.0).comMinimo(15.0)
				.comVolume(300).comData(LocalDateTime.now()).geraCandle();

		Assert.assertTrue(candle.isAlta());

	}

}
