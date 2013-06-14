package br.com.caelum.agiletickets.integration.driver;

import java.math.BigDecimal;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ReservaDriver {
	
	private static final String BASE_URL = "http://localhost:8080";
	private final WebDriver driver;

	private WebElement form() {
		return driver.findElement(By.tagName("form"));
	}
	public ReservaDriver(WebDriver driver) {
		this.driver = driver;
	}


	public void abreSessao(String numSessao) {
		
			driver.get(BASE_URL + "/sessao/" + numSessao);
		
	}

	public BigDecimal adicionaIngressos(String quantidadeIngressos, String tipoIngresso) {
		WebElement form = form();
		String precoIngresso = driver.findElement(By.id("preco")).getText();
		System.out.println(precoIngresso);
		precoIngresso = precoIngresso.substring(3).replace(",",".");

		BigDecimal precoIngressoDecimal = new BigDecimal(precoIngresso);
		
		
		form.findElement(By.name("quantidade")).sendKeys(quantidadeIngressos);
		form.findElement(By.name("tipoingresso")).sendKeys(tipoIngresso);
		form.submit();
		return precoIngressoDecimal;
		
	}

	public void telaDeveConterMetadeDoPreçoTotal(BigDecimal valorTotal) {
		WebElement message = driver.findElement(By.id("message"));
		Assert.assertTrue(message.getText().contains(valorTotal.toString().replace(".", ",")));
		
		
	}

}
