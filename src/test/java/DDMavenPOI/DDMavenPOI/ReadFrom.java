package DDMavenPOI.DDMavenPOI;


import java.io.File;
import java.io.FileInputStream;
//import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
//import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ReadFrom {
	
	WebDriver driver;
	WebDriverWait wait;
	XSSFWorkbook wb;
	XSSFSheet sheet;
	XSSFCell cell;
	
	@BeforeTest
	public void browserSetUp() 
	{
	//System.setProperty("webdriver.gecko.driver", System.getProperty("user⁩.dir") + "/BrowserDrivers/geckodriver⁩");
	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/BrowserDrivers/geckodriver");
	//DesiredCapabilities capa = DesiredCapabilities.firefox();
	//capa.setCapability("marionette", true);
	driver=new FirefoxDriver();
	driver.get("https://www.amazon.ca");
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void ReadData() throws IOException {
		System.out.println("into the ReadData method");
		//File src = new File("/Users⁩/⁨anick⁩/⁨eclipse-workspace⁩/⁨DataDrivenFramework⁩/⁨DataDrivenWithPOI/TestData/POI_TestData.xlsx");
		File src = new File (System.getProperty("user.dir") + "/TestData/POI_TestData.xlsx");
		System.out.println("read the xlsx file" +src);
		FileInputStream finput = new FileInputStream (src);
		System.out.println("value of finput" +finput);
		wb=new XSSFWorkbook(finput);
		System.out.println("value of wb" +wb);
		sheet=wb.getSheetAt(0);
		
		for(int i=1;i<=sheet.getLastRowNum();i++) {
			cell=sheet.getRow(i).getCell(1);
		
			//cell.setCellType(Cell.CELL_TYPE_STRING);
			
			driver.findElement(By.id("nav-link-yourAccount")).click();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			driver.findElement(By.id("ap_email")).sendKeys(cell.getStringCellValue());
			//driver.findElement(By.id("ap_email")).sendKeys("shwetaanand.86@gmail.com");
			cell=sheet.getRow(i).getCell(2);
			driver.findElement(By.id("ap_password")).sendKeys(cell.getStringCellValue());
			//driver.findElement(By.id("ap_password")).sendKeys("Shweta@0201");
			//FileOutputStream foutput = new FileOutputStream(src);
			//String msg = "Data imported successfully";
			driver.findElement(By.id("signInSubmit")).click();
			driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
			
			Actions action = new Actions(driver);
			WebElement we = driver.findElement(By.id("nav-link-yourAccount"));
			action.moveToElement(we).moveToElement(driver.findElement(By.xpath("//html/body/div[1]/header/div/div[1]/div[4]/div[6]/div[2]/a[13]/span"))).click().build().perform();
			//driver.findElement(By.id("nav-link-yourAccount")).
			//driver.findElement(By.id("nav-item-signout")).click();
			
		//	/html/body/div[1]/header/div/div[1]/div[4]/div[6]/div[2]/a[13]/span
			
		}
	}

}
