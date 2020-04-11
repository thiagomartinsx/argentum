package br.com.alura.argentum.modelo;

import java.util.List;

public class SerieTemporal {

	private final List<Candlestick> candles;

	public SerieTemporal(List<Candlestick> candles) {
		this.candles = candles;
	}

	public Candlestick getCandle(int posicao) {
		return candles.get(posicao);
	}

	public int getUltimaPosicao() {
		return candles.size() - 1;
	}
}
