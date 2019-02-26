package ogamebot;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import java.lang.Math;

public class page
{

protected WebDriver driver;
protected WebDriverWait wait;
public boolean isPlanetMere = true;
public boolean ischantierBusy = false;

   public page()
   {
	  //m_labo = new laboratoire();
	  // m_batiment = new batiment();
		// TODO Auto-generated method stub		
   }
   
   public void connexion()
   {
	   System.out.println("Working Directory = " +
	              System.getProperty("user.dir"));
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\src\\ogamebot\\chromedriver.exe");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver,10);
		driver.get("https://fr.ogame.gameforge.com/");
		driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.id("usernameLogin")).sendKeys("nemesism@hotmail.fr");
	    driver.findElement(By.id("passwordLogin")).sendKeys("pencilcho44",Keys.RETURN);
	    wait.until(ExpectedConditions.titleContains("OGame Lobby"));	    
	    driver.findElement(By.xpath("//button[contains(.,'Jouer')]")).click();
	    driver.findElement(By.xpath("//button[contains(.,'Jouer')]")).click();
	    for (String handle1 : driver.getWindowHandles()) 
	    {	 
	         driver.switchTo().window(handle1);	 
	    }	    
   }
    
   public int getMetal(){
       return Integer.parseInt(driver.findElement(By.id("resources_metal")).getText().replace(".",""));
   }

   public int getCrystal(){
       return Integer.parseInt(driver.findElement(By.id("resources_crystal")).getText().replace(".", ""));
   }

   public int getDeuterium(){
        return Integer.parseInt(driver.findElement(By.id("resources_deuterium")).getText().replace(".", ""));
   }

   public int getEnergy(){
       return Integer.parseInt(driver.findElement(By.id("resources_energy")).getText().replace(".",""));
   }
   
   public int Searchlvl(String i_lvl){
	   String res = driver.findElement(By.cssSelector("*[ref='"+i_lvl+"']")).getText();
	   return Integer.parseInt(res.replace(" ",""));
   }
   
   public void ClickBatiment(String i_id) {
	   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("*[ref='"+i_id+"']")));
	   driver.findElement(By.cssSelector("*[ref='"+i_id+"']")).click();
   }
   
   public boolean isPayable(int i_metal,int i_cristal, int i_deut,int i_lvl) {
	   return this.isPayable(i_metal, i_cristal, i_deut, i_lvl, 2);
   }
   
   public boolean isPayable(int i_metal,int i_cristal, int i_deut,int i_lvl,double i_coeff) {
	    int prixmetal = i_metal*(int)Math.pow(i_coeff,i_lvl);
	    int prixcrystal = i_cristal*(int)Math.pow(i_coeff,i_lvl);
	    int prixdeut = i_deut*(int)Math.pow(i_coeff,i_lvl);
	    return (prixmetal <= this.getMetal() && prixcrystal <= this.getCrystal() && prixdeut <= this.getDeuterium());
  }
   
   public WebDriver getDriver() {
	   return driver;
   }
   
   public int goToAnotherPlanet() {
	   driver.findElement(By.linkText("Vue d`ensemble")).click();
	   List<WebElement> listPlanet = driver.findElements(By.className("smallplanet "));
	   JavascriptExecutor jse = (JavascriptExecutor)driver;
	   jse.executeScript("scroll(0, 250)"); // if the element is on bottom.
	   for(int i=0;i<listPlanet.size();i++)
	   {
		   System.out.println(listPlanet.get(i).getText());
		   if(listPlanet.get(i).getText().contains(driver.findElement(By.id("planetNameHeader")).getText()))
		   {
			   if(i+1 < listPlanet.size())
			   {
				   listPlanet.get(i+1).click();
				   this.isPlanetMere = false;
				   break;
			   }
			   else
			   {
				   listPlanet.get(0).click();
				   this.isPlanetMere = true;
				   break;
			   }			   
		   }
	   }
	   
	   return 0;
   }
   

};