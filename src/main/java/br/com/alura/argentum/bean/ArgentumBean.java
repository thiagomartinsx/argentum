package br.com.alura.argentum.bean;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.LineChartModel;

import br.com.alura.argentum.graficos.GeradorDeModeloGrafico;
import br.com.alura.argentum.indicadores.Indicador;
import br.com.alura.argentum.indicadores.IndicadorFactory;
import br.com.alura.argentum.indicadores.IndicadorFechamento;
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
	private String nomeMedia;
	private String nomeIndicadorBase;

	public ArgentumBean() {
		negociacoes = new ClientWebService().getNegociacoes();
		geraGrafico();
	}

	public void geraGrafico() {
		List<Candlestick> candles = new CandlestickFactory().constroiCandles(negociacoes);
		SerieTemporal serie = new SerieTemporal(candles);
		GeradorDeModeloGrafico geradorModelo = new GeradorDeModeloGrafico(serie, 2, serie.getUltimaPosicao());
		IndicadorFactory factory = new IndicadorFactory(nomeMedia, nomeIndicadorBase);
		geradorModelo.plotaIndicador(factory.defineIndicador());
		this.modeloGrafico = geradorModelo.getModeloGrafico();
	}

	public List<Negociacao> getNegociacoes() {
		return this.negociacoes;
	}

	public LineChartModel getModeloGrafico() {
		return modeloGrafico;
	}

	public String getNomeMedia() {
		return nomeMedia;
	}

	public String getNomeIndicadorBase() {
		return nomeIndicadorBase;
	}

	public void setNomeIndicadorBase(String nomeIndicadorBase) {
		this.nomeIndicadorBase = nomeIndicadorBase;
	}

	public void setNomeMedia(String nomeMedia) {
		this.nomeMedia = nomeMedia;
	}

}
