package com.uniovi.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;

//Ordenamos las pruebas por el nombre del método 
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RedSocialSDITests {

	// En Windows (Debe ser la versión 46.0 y desactivar las actualizacioens
	// automáticas)):
	static String PathFirefox = "C:\\Users\\gemma\\Desktop\\RedSocialSDI\\Firefox46.win\\FirefoxPortable.exe";
	// Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox);
	static String URL = "http://localhost:8090";

	public static WebDriver getDriver(String PathFirefox) {
		// Firefox (Versión 46.0) sin geckodriver para Selenium 2.x.
		System.setProperty("webdriver.firefox.bin", PathFirefox);
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Antes de cada prueba se navega al URL home de la aplicaciónn
	@Before
	public void setUp() {
		driver.navigate().to(URL);
	}

	// Después de cada prueba se borran las cookies del navegador
	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	}

	// Antes de la primera prueba
	@BeforeClass
	static public void begin() {
	} // Al finalizar la última prueba

	@AfterClass
	static public void end() {
		// Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	// PR01. Acceder a la p�gina principal / 
	  // 1.1 Registro de Usuario con datos válidos 
	  @Test 
	  public void RegVal() { 
	    PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary"); 
	    PO_RegisterView.fillForm(driver, "example@example.com", "Josefo", "123456", "123456"); 
	    PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:"); 
	  } 
	 
	  // 1.2 Registro de Usuario con datos inválidos (repetición de contraseña 
	  // inválida) 
	  @Test 
	  public void RegInval() { 
	    PO_HomeView.clickOption(driver, "signup", "class", "btn btn-primary"); 
	    PO_RegisterView.fillForm(driver, "example@example.com", "Josefo", "123456", "1234567"); 
	    PO_View.checkElement(driver, "text", "Los usuarios que actualmente figuran en el sistema son los siguientes:"); 
	  } 
	 
	  // 2.1 Inicio de sesión con datos válidos 
	  @Test 
	  public void InVal() { 
	    PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); 
	    PO_LoginView.fillForm(driver, "email1", "123456"); 
	    PO_View.checkElement(driver, "text", "Usuarios"); 
	  } 
	 
	  // 2.2 Inicio de sesión con datos inválidos (usuario no existente en la 
	  // aplicación) 
	  @Test 
	  public void InInVal() { 
	    PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); 
	    PO_LoginView.fillForm(driver, "noExiste", "123456"); 
	    PO_View.checkElement(driver, "text", "Usuario o contraseña incorrecto"); 
	  } 
	 
	  // 3.1 [LisUsrVal] Acceso al listado de usuarios desde un usuario en sesión 
	  public void LisUsrVal() { 
	    PO_HomeView.clickOption(driver, "login", "class", "btn btn-primary"); 
	    PO_LoginView.fillForm(driver, "email1", "123456"); 
	    PO_NavView.clickOption(driver, "user/listUsuarios", "@href", "btn btn-primary"); 
	    PO_View.checkElement(driver, "text", "Usuarios"); 
	  } 
	   
	  // 3.2 [LisUsrInVal] Intento de acceso con URL desde un usuario no identificado 
	  // al listado de usuarios desde un usuario en sesión. Debe producirse un acceso 
	  // no permitido a vistas privadas. 
	  // 4.1 [BusUsrVal] Realizar una búsqueda valida en el listado de usuarios desde 
	  // un usuario en sesión. 
	  // 4.2 [BusUsrInVal] Intento de acceso con URL a la búsqueda de usuarios desde 
	  // un usuario no 
	  // identificado. Debe producirse un acceso no permitido a vistas privadas. 
	  // 5.1 [InvVal] Enviar una invitación de amistad a un usuario de forma valida. 
	  // 5.2 [InvInVal] Enviar una invitación de amistad a un usuario al que ya le 
	  // habíamos invitado la invitación 
	  // previamente. No debería dejarnos enviar la invitación, se podría ocultar el 
	  // botón de enviar invitación o 
	  // notificar que ya había sido enviada previamente. 
	  // 6.1 [LisInvVal] Listar las invitaciones recibidas por un usuario, realizar la 
	  // comprobación con una lista 
	  // que al menos tenga una invitación recibida. 
	  // 7.1 [AcepInvVal] Aceptar una invitación recibida. 
	  // 8.1 [ListAmiVal] Listar los amigos de un usuario, realizar la comprobación 
	  // con una lista que al menos 
	  // tenga un amigo. 
	  // 9.1 [PubVal] Crear una publicación con datos válidos. 
	  // 10.1 [LisPubVal] Acceso al listado de publicaciones desde un usuario en 
	  // sesión. 
	  // 11.1 [LisPubAmiVal] Listar las publicaciones de un usuario amigo 
	  // 11.2 [LisPubAmiInVal] Utilizando un acceso vía URL tratar de listar las 
	  // publicaciones de un usuario que 
	  // no sea amigo del usuario identificado en sesión. 
	  // 12.1 [PubFot1Val] Crear una publicación con datos válidos y una foto adjunta. 
	  // 12.1 [PubFot2Val] Crear una publicación con datos válidos y sin una foto 
	  // adjunta. 
	  // 13.1 [AdInVal] Inicio de sesión como administrador con datos válidos. 
	  // 13.2 [AdInInVal] Inicio de sesión como administrador con datos inválidos 
	  // (usar los datos de un usuario 
	  // que no tenga perfil administrador). 
	  // 14.1 [AdLisUsrVal] Desde un usuario identificado en sesión como administrador 
	  // listar a todos los 
	  // usuarios de la aplicación. 
	  // 15.1 [AdBorUsrVal] Desde un usuario identificado en sesión como administrador 
	  // eliminar un usuario 
	  // existente en la aplicación. 
	  // 15.2 [AdBorUsrInVal] Intento de acceso vía URL al borrado de un usuario 
	  // existente en la aplicación. 
	  // Debe utilizarse un usuario identificado en sesión pero que no tenga perfil de 
	  // administrador. 

}
