package ogamebot;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import java.lang.Math;

public class vue_ensemble extends page{

laboratoire m_labo;
batiment m_batiment;
	
	public vue_ensemble()
	{
	}

	public int vueEnsemble()
	{
	    List<WebElement> pTerm = driver.findElements(By.xpath("//span[contains(.,'terminé')]"));
	    List<WebElement> pres = driver.findElements(By.partialLinkText("Aucun bâtiment en construction"));
	    List<WebElement> prech = driver.findElements(By.partialLinkText("Aucune recherche en cours"));
	    List<WebElement> pVais = driver.findElements(By.partialLinkText("Aucun(e) vaisseau"));
	    // Aucun(e) vaisseau
	    
	    this.ischantierBusy = false;
	    if(pVais.size() > 0)
	    {
	    	this.ischantierBusy = true;	    	
	    }

	    if(pTerm.size() > 0)
	    {
	        driver.findElement(By.linkText("Vue d`ensemble")).click();
	    }

	   if(pres.size() > 0)
	   {
	       driver.findElement(By.linkText("Ressources")).click();
	       this.m_batiment = new batiment(driver,wait,ischantierBusy);
		  // this.m_batiment.construireInstallations();
	       //driver.findElement(By.linkText("Ressources")).click();
	       this.m_batiment.construireBatiment();	       
	       driver.findElement(By.linkText("Vue d`ensemble")).click();
	   }
	   if(prech.size() > 0 && this.isPlanetMere)
	   {
	       driver.findElement(By.linkText("Recherche")).click();
	       this.m_labo = new laboratoire(driver,wait,this.isPlanetMere);
	       m_labo.RechercheAstro();
	       this.m_labo.RechercheAutre();
	       driver.findElement(By.linkText("Vue d`ensemble")).click();
	   }
	   this.goToAnotherPlanet();

	    return 0;
	}
}


