package ogamebot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class batiment extends page implements IdBatiment {

    int m_lvlmineMetal = 0;
    int m_lvlmineCrystal = 0;
    int m_lvlmineDeuterium = 0;
    int m_lvlmineEnergy = 0;
    int m_lvlhangarmetal = 0;
    int m_lvlhangarcrystal = 0;
    int m_lvlhangardeuterium = 0;
    int m_capmetal = 0;
    int m_capcrystal = 0;
    int m_capdeut = 0;
    int m_lvlusinerobots = 0;
    int m_lvllabo = 0;
    int m_lvlchantier = 0;
    
    public batiment(WebDriver i_driver, WebDriverWait i_wait,boolean ischantierBusy) {
    	super.driver = i_driver;
    	super.wait = i_wait;
    	super.ischantierBusy = ischantierBusy;
	}
    
    public int construireBatiment()
	{
    	System.out.println("ischanierbusy:"+this.ischantierBusy);
	    this.m_lvlmineMetal = this.Searchlvl(mineMetal);
	    this.m_lvlmineCrystal = this.Searchlvl(mineCrystal);
	    this.m_lvlmineDeuterium = this.Searchlvl(mineDeut);
	    this.m_lvlmineEnergy = this.Searchlvl(mineEnergy);
	    this.m_lvlhangarmetal = this.Searchlvl(hangarMetal);
	    this.m_lvlhangarcrystal = this.Searchlvl(hangarCrystal);
	    this.m_lvlhangardeuterium = this.Searchlvl(hangarDeut);
	    //ArrondiInferieur(2.5 * e ^ (20 * NIVEAU / 33)) * 5000
	    if(this.getEnergy() < 0)
	    {	    	
	    	//212
	    	if(this.isPayable(75, 15, 0, this.m_lvlmineEnergy, 1.5))
	            this.ClickBatiment(mineEnergy);
	    	else if(!this.ischantierBusy)
	    	{
	    		this.ClickBatiment(satellite);
	    		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("costs_wrap")));
	            List<WebElement> pDevo = driver.findElements(By.linkText("Construire"));
	            List<WebElement> psat = driver.findElements(By.className("undermark"));
	            int sat = 0;
	            for(int i=0;i<psat.size();i++)
	            {
	            	if(psat.get(i).getText().contains("(+"))
	            	{
	    	            sat = 1 +  this.getEnergy()/Integer.parseInt(psat.get(i).getText().replace("(+", "").replace(")", ""));
	            		break;
	            	}
	            }
	            driver.findElement(By.id("number")).sendKeys(Integer.toString(sat));
	            for(int i=0;i<pDevo.size();i++)
	            {
	                pDevo.get(i).click();
	            }
	    	}
	    }
	    else if(this.m_lvlhangarmetal < this.m_lvlmineMetal/2 && this.isPayable(1000, 0, 0, this.m_lvlhangarmetal))
	    {
	    	this.ClickBatiment(hangarMetal);
	    }
	    else if(this.m_lvlhangarcrystal < this.m_lvlmineCrystal/2 && this.isPayable(1000, 500, 0, this.m_lvlhangarcrystal))
	    {
	    	this.ClickBatiment(hangarCrystal);
	    }
	    else if(this.m_lvlhangardeuterium < this.m_lvlmineDeuterium/2 && this.isPayable(1000, 1000, 0, this.m_lvlhangardeuterium))
	    {
	    	this.ClickBatiment(hangarDeut);
	    }
	    else if(this.m_lvlmineMetal < this.m_lvlmineCrystal + 4
	    		&& this.isPayable(60, 15, 0, this.m_lvlmineMetal, 1.5))
	    {
	            this.ClickBatiment(mineMetal);
	    }
	    else if(this.m_lvlmineCrystal < this.m_lvlmineDeuterium + 4 
	    		&& this.isPayable(48, 24, 0, this.m_lvlmineCrystal, 1.6))
	    {
	            this.ClickBatiment(mineCrystal);
	    }
	    else if(this.isPayable(225, 75, 0, this.m_lvlmineDeuterium, 1.5))
	    {
	            this.ClickBatiment(mineDeut);
	    }
	    else
	    	return 0;
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("costs_wrap")));
        List<WebElement> pDev = driver.findElements(By.linkText("Développer"));
        for(int i=0;i<pDev.size();i++)
        {
            pDev.get(i).click();
        }
	   
	    //this.goToOnglet("Vue d`ensemble");
	    //a class=build-it_premium : impossible de construire : antimatière
	    //a class=build-it_disabled : impossible de construire
	    return 0;
	}
       
    public int construireInstallations() 
    {
		driver.findElement(By.linkText("Installations")).click();
    	this.m_lvlusinerobots = this.Searchlvl(usineRobots);
    	this.m_lvlchantier = this.Searchlvl(chantier);
    	this.m_lvllabo = this.Searchlvl(laboratoire);
    	int prixdeut = 200 * (int)Math.pow(2, this.m_lvlusinerobots);
	    if(prixdeut <= this.getDeuterium() && this.isPayable(400, 120, 200, this.m_lvlusinerobots))
	    {
	            this.ClickBatiment(usineRobots);
	    }
	   /* else if(this.m_lvllabo < 4 && this.isPayable(200, 400, 200, this.m_lvllabo) && this.isPlanetMere)
	    {
	        	this.ClickBatiment(laboratoire);
	    }*/
	    else if(this.m_lvlchantier < 5 && this.isPayable(400, 200, 100, this.m_lvlchantier))
	    {
	            this.ClickBatiment(chantier);
	    }
	    else
	    	return 0;
	    
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("costs_wrap")));
        List<WebElement> pDev = driver.findElements(By.linkText("Développer"));
        for(int i=0;i<pDev.size();i++)
        {
            pDev.get(i).click();
        }
	    
	    return 0;
    }
}
