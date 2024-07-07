package test4;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DatadrivenInsta {
	WebDriver driver;
	@BeforeTest
	public void bt() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://www.instagram.com/accounts/login/?hl=en");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
	@Test
	public void t1() throws IOException {
	File excel=new File("E:/Instagram.xlsx");
	FileInputStream fis=new FileInputStream(excel);
	XSSFWorkbook wb=new XSSFWorkbook(fis);
	XSSFSheet sht=wb.getSheet("Sheet1");
	int rowcount=sht.getLastRowNum();
	for(int i=0;i<=rowcount;i++) {
		String c1=sht.getRow(i).getCell(0).getStringCellValue();
		String c2=sht.getRow(i).getCell(1).getStringCellValue();
		driver.findElement(By.name("username")).sendKeys(c1);
		driver.findElement(By.name("password")).sendKeys(c2);
		WebElement login = driver.findElement(By.cssSelector("button[type=\"submit\"]"));
		login.click();
	}
	}
	 @AfterTest
	 public void end() {
		 driver.close();
	 }
}
