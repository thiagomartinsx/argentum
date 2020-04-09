package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;

public final class Candlestick {

	private final double abertura;
	private final double fechamento;
	private final double maxima;
	private final double minimo;
	private final double volume;
	private final LocalDateTime data;

	public Candlestick(double abertura, double fechamento, double maxima, double minimo, double volume,
			LocalDateTime data) {
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.maxima = maxima;
		this.minimo = minimo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMaxima() {
		return maxima;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getVolume() {
		return volume;
	}

	public LocalDateTime getData() {
		return data;
	}

	public boolean isAlta() {
		return this.fechamento > this.abertura;
	}

	public boolean isBaixa() {
		return this.fechamento < this.abertura;
	}

}
