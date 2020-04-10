package br.com.alura.argentum.xstream;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import br.com.alura.argentum.modelo.Negociacao;
import br.com.alura.argentum.reader.LeitorXML;

public class LocalDateTimeConverterTest {

    @Test
    public void deveGerarOXmlComADataAdequada() {
    	
    	 String resultadoExperado = "<negociacao>\n" + 
                 "  <preco>40.5</preco>\n" +
                 "  <quantidade>100</quantidade>\n" + 
                 "  <data>\n" + 
                 "    <time>1459479600000</time>\n" + 
                 "    <timezone>America/Sao_Paulo</timezone>\n" + 
                 "  </data>\n" + 
                 "</negociacao>";

        Negociacao negociacao = new Negociacao(40.5, 100, LocalDateTime.of(2016, 04, 01, 00, 00));

        XStream stream = new XStream(new DomDriver());
        stream.alias("negociacao", Negociacao.class);
        stream.registerLocalConverter(Negociacao.class, "data", new LocalDateTimeConverter());
        String resultado = stream.toXML(negociacao);

        assertEquals(resultadoExperado, resultado);
    }
    
    @Test
    public void deveGerarObjetoAdequadoDeUmXml() {

        String xml = "<negociacao>\n" + 
                     "  <preco>40.5</preco>\n" +
                     "  <quantidade>100</quantidade>\n" + 
                     "  <data>\n" + 
                     "    <time>1459479600000</time>\n" + 
                     "    <timezone>America/Sao_Paulo</timezone>\n" + 
                     "  </data>\n" + 
                     "</negociacao>";

        XStream stream = new XStream(new DomDriver());
        stream.alias("negociacao", Negociacao.class);
        stream.registerLocalConverter(Negociacao.class, "data",
                new LocalDateTimeConverter());
        Negociacao negociacaoGerada = (Negociacao) stream.fromXML(xml);

        Negociacao negociacaoEsperada = new Negociacao(40.5, 100,
                LocalDateTime.of(2016, 04, 01, 00, 00));

        assertEquals(negociacaoEsperada, negociacaoGerada);
    }
    
    @Test
    public void carregaXMLComUmaNegociacaoApenas() {

        String xmlDeTeste = "<list>\n" + 
                            "  <negociacao>\n" +
                            "    <preco>40.5</preco>\n" +
                            "    <quantidade>100</quantidade>\n" + 
                            "    <data>\n" +
                            "      <time>1459479600000</time>\n" +
                            "      <timezone>America/Sao_Paulo</timezone>\n" +
                            "    </data>\n" + 
                            "  </negociacao>\n" + 
                            "</list>";

        Negociacao negociacaoEsperada = new Negociacao(40.5, 100,
                LocalDateTime.of(2016, 04, 01, 00, 00));

        LeitorXML leitor = new LeitorXML();

        InputStream xml = new ByteArrayInputStream(xmlDeTeste.getBytes());

        List<Negociacao> negociacoes = leitor.carrega(xml);

        assertEquals(1, negociacoes.size());
        assertEquals(negociacaoEsperada, negociacoes.get(0));
    }
}
