package br.com.alura.argentum.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;

import br.com.alura.argentum.graficos.GeradorDeModeloGrafico;
import br.com.alura.argentum.indicadores.IndicadorAbertura;
import br.com.alura.argentum.indicadores.IndicadorFechamento;
import br.com.alura.argentum.indicadores.MediaMovelPonderada;
import br.com.alura.argentum.indicadores.MediaMovelSimples;
import br.com.alura.argentum.modelo.Candlestick;
import br.com.alura.argentum.modelo.CandlestickFactory;
import br.com.alura.argentum.modelo.Negociacao;
import br.com.alura.argentum.modelo.SerieTemporal;
import br.com.alura.argentum.ws.ClientWebService;

@ViewScoped
@ManagedBean
public class ArgentumBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Negociacao> negociacoes;
	private LineChartModel modeloGrafico;

	public ArgentumBean() {
		negociacoes = new ClientWebService().getNegociacoes();
		List<Candlestick> candles = new CandlestickFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorDeModeloGrafico geradorModelo = new GeradorDeModeloGrafico(serie, 2, serie.getUltimaPosicao());
		geradorModelo.plotaIndicador(new MediaMovelPonderada());
		geradorModelo.plotaIndicador(new MediaMovelSimples());
		this.modeloGrafico = geradorModelo.getModeloGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}

	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

}
