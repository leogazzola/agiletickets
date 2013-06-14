package br.com.caelum.agiletickets.models;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.junit.Assert;
import org.junit.Test;

public class EspetaculoTest {


	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(5));
	}

	@Test
	public void deveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertTrue(ivete.Vagas(6));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoes() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(1));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(15));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(5, 3));
	}

	@Test
	public void DeveInformarSeEhPossivelReservarAQuantidadeExataDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(3));
		ivete.getSessoes().add(sessaoComIngressosSobrando(4));

		assertTrue(ivete.Vagas(10, 3));
	}

	@Test
	public void DeveInformarSeNaoEhPossivelReservarAQuantidadeDeIngressosDentroDeQualquerDasSessoesComUmMinimoPorSessao() {
		Espetaculo ivete = new Espetaculo();

		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));
		ivete.getSessoes().add(sessaoComIngressosSobrando(2));

		assertFalse(ivete.Vagas(5, 3));
	}

	private Sessao sessaoComIngressosSobrando(int quantidade) {
		Sessao sessao = new Sessao();
		sessao.setTotalIngressos(quantidade * 2);
		sessao.setIngressosReservados(quantidade);

		return sessao;
	}
	
	//não cria nenhuma sessão
	@Test
	public void  naoCriaSessaoSeDataFimMenorQueDataInicio() {
		
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes( new LocalDate(2013, 06, 12),new LocalDate(2013, 06, 11), 
			new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertTrue(sessoes.isEmpty());
		
	}
	@Test
	public void  CriaSessaoUnica() {
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes( new LocalDate(2013, 06, 12),new LocalDate(2013, 06, 12), 
			new LocalTime(), Periodicidade.DIARIA);
		
		Assert.assertEquals(1, sessoes.size());		
		Assert.assertEquals("12/06/13", sessoes.get(0).getDia());
	}
	
	@Test
	public void  CriaDuasSessoesSemanaisComPeriodicidadeSemanal() {
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes( new LocalDate(2013, 06, 12),new LocalDate(2013, 06, 20), 
			new LocalTime(), Periodicidade.SEMANAL);
		
		Assert.assertEquals(2, sessoes.size());
	
		Assert.assertEquals("12/06/13", sessoes.get(0).getDia());
		Assert.assertEquals("19/06/13", sessoes.get(1).getDia());
	}
	
	@Test
	public void  CriaumaSessaoComPeriodicidadeMensal() {
		
		Espetaculo espetaculo = new Espetaculo();
		List<Sessao> sessoes = espetaculo.criaSessoes( new LocalDate(2013, 06, 13),new LocalDate(2013, 07, 11), 
			new LocalTime(), Periodicidade.SEMANAL);
		
		Assert.assertEquals(5, sessoes.size());
	
		Assert.assertEquals("13/06/13", sessoes.get(0).getDia());
		Assert.assertEquals("20/06/13", sessoes.get(1).getDia());
		Assert.assertEquals("27/06/13", sessoes.get(2).getDia());
		Assert.assertEquals("04/07/13", sessoes.get(3).getDia());
	}
	
	
}
