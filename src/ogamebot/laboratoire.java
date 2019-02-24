package ogamebot;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class laboratoire extends page implements IdRecherche{
	
	int m_lvlastro = 0;
    int m_lvlenergy = 0;
    int m_lvlimpulsion = 0;
    int m_lvlespionage = 0;
    int m_lvlordinateur = 0;
    int m_lvlcombustion = 0;
	
	public laboratoire(WebDriver i_driver, WebDriverWait i_wait, boolean i_isplanetmere) {
		super.driver = i_driver;
		super.wait = i_wait;
		super.isPlanetMere = i_isplanetmere;
	}
	
	private int init() {
	    this.m_lvlastro = this.Searchlvl(astro);
	    this.m_lvlenergy = this.Searchlvl(energie);
	    this.m_lvlimpulsion = this.Searchlvl(impulsion);
	    this.m_lvlespionage = this.Searchlvl(espionnage);
	    this.m_lvlcombustion = this.Searchlvl(combustion);
	    this.m_lvlordinateur = this.Searchlvl(ordinateur);
	    return 0;
	}
	
	public void ClickRecherche(String i_id) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("*[ref='"+i_id+"']")));
			driver.findElement(By.cssSelector("*[ref='"+i_id+"']")).click();
	   }
	
	public int RechercheAstro()
	{
		this.init();
	    if(this.m_lvlimpulsion >= 3 && this.m_lvlespionage >= 4
	            && this.isPayable(4000,8000,4000,this.m_lvlastro))
	    {
	            this.ClickBatiment(astro);
	    }
	    else if(this.m_lvlimpulsion < 3 && this.isPayable(2000,4000,600,this.m_lvlimpulsion))
	    {
	       // if(this.m_lvlenergy > 0)
	        //{
	                this.ClickBatiment(impulsion);
	        //}        
	    }
	    else if(this.isPayable(200,1000,200,this.m_lvlespionage))
	    {
	       this.ClickBatiment(espionnage);
	    }
	    else if(this.isPayable(400,0,600,this.m_lvlcombustion))
	    {
	          this.ClickBatiment(combustion);
	    }
	    else
	    	return 0;

	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("costs_wrap")));
        List<WebElement> pDev = driver.findElements(By.linkText("Rechercher"));
        for(int i=0;i<pDev.size();i++)
        {
            pDev.get(i).click();
        }

	    return 0;
	}
	
	public int RechercheAutre()
	{
		if(this.isPayable(0,400,600,this.m_lvlordinateur))
	    {
	        this.ClickBatiment(ordinateur);
	    }
		else if(this.isPayable(0,800,400,this.m_lvlenergy))
        {
                this.ClickBatiment(energie);
        }
		else 
			return 0;
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("costs_wrap")));
        List<WebElement> pDev = driver.findElements(By.linkText("Rechercher"));
        for(int i=0;i<pDev.size();i++)
        {
            pDev.get(i).click();
        }
		
		return 0;
	}

}
