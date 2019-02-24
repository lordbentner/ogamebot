package ogamebot;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public interface IdPage {

	public WebDriver driver = new ChromeDriver();
	public WebDriverWait wait = new WebDriverWait(driver,10);
	public static boolean isPlanetMere = true;
	public boolean ischantierBusy = false;
}
