package br.com.caelum.agiletickets.integration;

import java.math.BigDecimal;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.caelum.agiletickets.integration.driver.EstabelecimentosDriver;
import br.com.caelum.agiletickets.integration.driver.ReservaDriver;


public class ReservaTest {

	public static String BASE_URL = "http://localhost:8080";
	private static WebDriver browser;
	private ReservaDriver reservas;

	@BeforeClass
	public static void abreBrowser() {
		browser = new FirefoxDriver();
	}

	@Before
	public void setUp() throws Exception {
		reservas = new ReservaDriver(browser);
	}

	@AfterClass
	public static void teardown() {
		browser.close();
	}

	@Test
	public void aoSelecionarASessaoEDepoisDoisIngressosDeEstudanteDeveMostrarOPrecode70porcento() throws Exception {
		
		
		reservas.abreSessao("39");

		BigDecimal valorIngresso = reservas.adicionaIngressos("2", "Meia");
		
		BigDecimal valorTotal = valorIngresso.divide(new BigDecimal("2")).multiply(new BigDecimal("2"));

		reservas.telaDeveConterMetadeDoPreçoTotal(valorTotal);
	}


	/*@Test
	public void mostraQueNaoHaEstacionamentoQuandoCadastramosQueNao() throws Exception {
		estabelecimentos.abreListagem();

		estabelecimentos.adicioneEstabelecimentoComEstacionamento(false);

		estabelecimentos.ultimaLinhaDeveTerEstacionamento(false);
	}
	*/
}
