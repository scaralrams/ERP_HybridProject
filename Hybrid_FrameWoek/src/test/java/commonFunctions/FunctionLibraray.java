package commonFunctions;

import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FunctionLibraray 
{
	public static WebDriver driver;
	public static Properties property=new Properties();
	public static WebDriver openBrowser() throws Throwable
	{
		FileInputStream file=new FileInputStream("./Properties/Environment.properties");
		 property.load(file);
		 if (property.getProperty("BROWSER").equalsIgnoreCase("chrome")) 
		 {
			 driver=new ChromeDriver();
			 driver.manage().window().maximize();

		 }else if(property.getProperty("BROWSER").equalsIgnoreCase("firefox")) 
	     {
			 driver=new FirefoxDriver();
			 driver.manage().window().maximize();

		}
		else if(property.getProperty("BROWSER").equalsIgnoreCase("msedge ")) 
	    {
			 driver=new EdgeDriver ();
			 driver.manage().window().maximize();

		}
		else 
		{
			System.out.println("Driver not matched");
		}
		return driver; 
		
	}
	public static void enterURL(WebDriver driver)
	{
		driver.get(property.getProperty("URL"));
		System.out.println("It Opened The URl");
	}
	public static void waitforElement(WebDriver driver,String locatortYpe,String Locatorvlaue,String TestData) throws InterruptedException
	{
		
		WebDriverWait wait= new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(TestData)));
		if (locatortYpe.equalsIgnoreCase("id")) 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(Locatorvlaue)));
		}
		if (locatortYpe.equalsIgnoreCase("name")) 
		{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(Locatorvlaue)));
		}
		if (locatortYpe.equalsIgnoreCase("xpath")) 
		{
			Thread.sleep(10000);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(Locatorvlaue)));
		}
		System.out.println("It Completed The Wait  Actions ");
	}
	public static void  typeAction(WebDriver driver,String locatortYpe,String Locatorvlaue,String TestData)
	{
		if (locatortYpe.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(Locatorvlaue)).clear();
			driver.findElement(By.id(Locatorvlaue)).sendKeys(TestData);

		}
		if (locatortYpe.equalsIgnoreCase("name")) 
		{
			driver.findElement(By.name(Locatorvlaue)).clear();
			driver.findElement(By.name(Locatorvlaue)).sendKeys(TestData);	
		}
		if (locatortYpe.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(Locatorvlaue)).clear();
			driver.findElement(By.xpath(Locatorvlaue)).sendKeys(TestData);		
		}
		System.out.println("It Completed The Type  Actions ");

	}
	public static void  clickAction(WebDriver driver,String locatortYpe,String Locatorvlaue) throws InterruptedException
	{
		if (locatortYpe.equalsIgnoreCase("id")) 
		{
			driver.findElement(By.id(Locatorvlaue)).sendKeys(Keys.ENTER);;

		}
		if (locatortYpe.equalsIgnoreCase("name")) 
		{
			driver.findElement(By.name(Locatorvlaue)).click();

		}
		if (locatortYpe.equalsIgnoreCase("xpath")) 
		{
			driver.findElement(By.xpath(Locatorvlaue)).click();
			Thread.sleep(5000);
			
		
		}
		System.out.println("It Completed The CLiCK  Actions ");

	}
	public static void dataValidation(WebDriver driver, String TestData) 
	{
	    String pageTitle = driver.getTitle().trim();
	    try {
	        Assert.assertEquals(TestData.trim(), pageTitle);
			 

	    }catch (AssertionError e)
	    {
           throw new AssertionError("Title is NOt Matching", e);
		}
		
	        
		
	}
	public static  void closeBrowser(WebDriver driver)
	{
		driver.close();
		System.out.println("It completed the Close Action");
	}
	public static void moveMouse(WebDriver driver,String locatortYpe,String Locatorvlaue) throws Exception 
	{
		Actions a=new Actions(driver);
		if (locatortYpe.equalsIgnoreCase("xpath")) 
		{
			WebElement elemnet=driver.findElement(By.xpath(Locatorvlaue));
			a.moveToElement(elemnet).perform();
		}
		if (locatortYpe.equalsIgnoreCase("name")) 
		{
			WebElement elemnet=driver.findElement(By.name(Locatorvlaue));
			a.moveToElement(elemnet).perform();
		}
		if (locatortYpe.equalsIgnoreCase("id")) 
		{
			WebElement elemnet=driver.findElement(By.id(Locatorvlaue));
			a.moveToElement(elemnet).perform();
		}
		
		
		
	}

    public static void gettingText(WebDriver driver,String Locatorvlaue,String TestData )
    {
    	String textOfTableColoum=driver.findElement(By.xpath(Locatorvlaue)).getText();
    	try {
	        Assert.assertEquals(TestData.trim(), textOfTableColoum.trim());
			 System.out.println("Yes Data Matched");

	    }catch (AssertionError e)
	    {
           throw new AssertionError("Table Data Not Matching", e);
		}
    	
    	
    	
    	
    	
    }
    public static void searchBarDisplay(WebDriver driver,String locatortYpe,String Locatorvlaue)
    {
    	if (locatortYpe.equalsIgnoreCase("id")) 
		{
    		if (!driver.findElement(By.id(Locatorvlaue)).isDisplayed()) 
    		{
				driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon' and @data-caption='Search']")).click();

			}
		}
		if (locatortYpe.equalsIgnoreCase("name")) 
		{
			if (!driver.findElement(By.name(Locatorvlaue)).isDisplayed()) 
    		{
				driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon' and @data-caption='Search']")).click();

			}
		}
		if (locatortYpe.equalsIgnoreCase("xpath")) 
		{
			
			if (!driver.findElement(By.xpath(Locatorvlaue)).isDisplayed()) 
    		{
				driver.findElement(By.xpath("//span[@class='glyphicon glyphicon-search ewIcon' and @data-caption='Search']")).click();

			}
		}
    	
    }
	public static void moveMouseAndClick(WebDriver driver,String locatortYpe,String Locatorvlaue)
	{
		Actions a=new Actions(driver);
		if (locatortYpe.equalsIgnoreCase("xpath")) 
		{
			WebElement elemnet=driver.findElement(By.xpath(Locatorvlaue));
			a.moveToElement(elemnet).click().perform();
		}
		if (locatortYpe.equalsIgnoreCase("name")) 
		{
			WebElement elemnet=driver.findElement(By.name(Locatorvlaue));
			a.moveToElement(elemnet).click().perform();
		}
		if (locatortYpe.equalsIgnoreCase("id")) 
		{
			WebElement elemnet=driver.findElement(By.id(Locatorvlaue));
			a.moveToElement(elemnet).click().perform();
		}
		
		
	}
	public static String getDate() 
	{
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy-HH_mm_ss");
	        String formattedDate = sdf.format(date);
	        return formattedDate;	
	}
	
	


}
