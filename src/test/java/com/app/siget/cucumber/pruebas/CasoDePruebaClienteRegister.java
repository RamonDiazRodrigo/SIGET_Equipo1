package com.app.siget.cucumber.pruebas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.app.siget.dominio.Manager;
import com.app.siget.dominio.User;
import com.app.siget.excepciones.DiferentesContrasenasException;
import com.app.siget.persistencia.UserDAO;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CasoDePruebaClienteRegister {
	WebDriver driver = null;
	String testCase = "";
	 List<Map<String, String>> a;

	@Given("^registro")
	public void los_datos(String nombre, String email, String password, String passwordConfirm, String rol)
			throws Throwable {
		Path path = FileSystems.getDefault().getPath("src/test/resources/geckodriver.exe");
		System.setProperty("webdriver.gecko.driver", path.toString());
		FirefoxOptions fo = new FirefoxOptions();
		fo.addArguments("--headless");
		driver = new FirefoxDriver(fo);

		driver.get("http://localhost:8080/");
		
	}

	@When("introducir los datos de registro")
	public void se_introducen_los_datos(DataTable dataTable) throws Throwable{
		
	a = dataTable.asMaps(String.class, String.class);
	driver.manage().window().setSize(new Dimension(1102, 634));
	driver.findElement(By.id("username")).sendKeys(a.get(0).get("nombre"));
	driver.findElement(By.id("email")).sendKeys(a.get(0).get("email"));
	driver.findElement(By.id("pwd1")).sendKeys(a.get(0).get("password"));
	driver.findElement(By.id("pwd2")).sendKeys(a.get(0).get("passwordconfirm"));
	{
		WebElement dropdown = driver.findElement(By.id("rol"));
		dropdown.findElement(By.xpath("//option[. = 'ASISTENTE']")).click();
	}
	driver.findElement(By.id("btnregister")).click();
	driver.findElement(By.id("goToRegister")).click();
	}
	
	
	@Then("le damos a registrar")
	public void le_damos_a_registrar(String nombre) throws Throwable {
		 String expectedUrl = driver.getCurrentUrl();
		    
		    if (testCase.equals("Case1")) {
		        assertEquals("http://localhost:8080/index.html", expectedUrl);
		      } else {
		        assertEquals("http://localhost:8080/register.html", expectedUrl);
		      }
		    driver.close();
	}


	
}