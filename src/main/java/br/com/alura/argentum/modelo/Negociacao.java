package br.com.alura.argentum.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public final class Negociacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
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

	public boolean isMesmoDia(LocalDateTime dateTime) {
		LocalDate outraData = LocalDate.from(dateTime);
		LocalDate data = LocalDate.from(this.data);

		return data.equals(outraData);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		long temp;
		temp = Double.doubleToLongBits(preco);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + quantidade;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Negociacao other = (Negociacao) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (Double.doubleToLongBits(preco) != Double.doubleToLongBits(other.preco))
			return false;
		if (quantidade != other.quantidade)
			return false;
		return true;
	}
}
