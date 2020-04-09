package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;

public final class Negociacao {

	private final double preco;
	private final int quantidade;
	private final LocalDateTime data;

	public Negociacao(double preco, int quantidade, LocalDateTime data) {
		if (data == null) {
			throw new IllegalArgumentException("data não pode ser nula");
		}

		if (preco < 0) {
			throw new IllegalArgumentException("preco não pode ser negativo");
		}

		if (quantidade <= 0) {
			throw new IllegalArgumentException("quantidade deve ser pelo menos 1");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	public double getPreco() {
		return preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDateTime getData() {
		return data;
	}

	public double getVolume() {
		return this.quantidade * this.preco;
	}

}
