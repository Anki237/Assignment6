package test4;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DragnScreenshot {
	WebDriver driver;
	@BeforeTest
	public void bt() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://v1.training-support.net/selenium/drag-drop");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
}
	@Test
	public void t1() throws IOException {
		TakesScreenshot ss=(TakesScreenshot)driver;
		File picture = ss.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(picture, new File("E:/ss.png"));
}
	@Test(dependsOnMethods = {"t1"},alwaysRun = true)
	public void t2() {
		WebElement ball= driver.findElement(By.id("draggable"));
		WebElement drop1=driver.findElement(By.id("droppable"));
		Actions act= new Actions(driver);
		act.dragAndDrop(ball, drop1).build().perform();;
		
		
	}
	@Test(dependsOnMethods = {"t2"},alwaysRun = true)
	public void t3() throws IOException {
		TakesScreenshot sp=(TakesScreenshot)driver;
		File picture1 = sp.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(picture1,new File("E:/sp.png"));
	}
	 @AfterTest
	 public void end() {
		 driver.close();
	 }
	
}
