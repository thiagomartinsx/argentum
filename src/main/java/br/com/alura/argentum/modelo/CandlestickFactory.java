package br.com.alura.argentum.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CandlestickFactory {

	public Candlestick constroiCandleParaData(List<Negociacao> negociacoes, LocalDateTime data) {
		double abertura = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double fechamento = negociacoes.isEmpty() ? 0 : negociacoes.get(negociacoes.size() - 1).getPreco();

		double minimo = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();
		double maxima = negociacoes.isEmpty() ? 0 : negociacoes.get(0).getPreco();

		double volume = 0;
		for (Negociacao negociacao : negociacoes) {
			volume += negociacao.getVolume();

			double preco = negociacao.getPreco();
			if (preco > maxima) {
				maxima = preco;
			} else if (preco < minimo) {
				minimo = preco;
			}
		}
		return new Candlestick(abertura, fechamento, maxima, minimo, volume, data);
	}

	public List<Candlestick> constroiCandles(List<Negociacao> negociacoes) {

		List<Candlestick> candlesticks = new ArrayList<>();

		List<Negociacao> negociacoesDoDia = new ArrayList<>();

		LocalDateTime dataAtual = negociacoes.get(0).getData();

		for (Negociacao negociacao : negociacoes) {
			if (negociacao.isMesmoDia(dataAtual)) {
				negociacoesDoDia.add(negociacao);
			} else {
				geraEAdicionaCandle(candlesticks, negociacoesDoDia, dataAtual);

				negociacoesDoDia = new ArrayList<>();
				dataAtual = negociacao.getData();
				negociacoesDoDia.add(negociacao);
			}
		}
		geraEAdicionaCandle(candlesticks, negociacoesDoDia, dataAtual);

		return candlesticks;
	}

	private void geraEAdicionaCandle(List<Candlestick> candlesticks, List<Negociacao> negociacoesDoDia,
			LocalDateTime dataAtual) {
		Candlestick candle = constroiCandleParaData(negociacoesDoDia, dataAtual);
		candlesticks.add(candle);
	}
}