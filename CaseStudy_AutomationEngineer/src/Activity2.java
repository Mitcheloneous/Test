/*'###############################################################################
'-------------------------------------------------------------------------------
' Case Study: Selenium Case Study - Activity 2
' Script Name: TS_BookFlight for 2 Passengers
' Brief Functional Description: Book a return flight for 2 passengers
' Created On: 25-Jan-2017
' Created By: katherine.a.ciudad
' Comments/Remark: 
'-------------------------------------------------------------------------------
'###############################################################################
*/
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.FileInputStream;
import java.io.IOException;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Activity2 {  	
	public static void main(String[] args) throws BiffException, IOException {
					
		WebDriver driver = new FirefoxDriver(); 		         //Launch the Flight Reservation (Web)	
		driver.get("http://newtours.demoaut.com");
		driver.manage().window().maximize();
		assertTrue(isElementPresent(By.xpath("//input[@name='userName']")));
		
		//Read test data from excel file		  
	  String FilePath = "C:\\Users\\katherine.a.ciudad\\workspace\\CaseStudy_AutomationEngineer\\myDataSheet\\MyDataSheet.xls";
		  FileInputStream fs = new FileInputStream(FilePath);
		  Workbook wb = Workbook.getWorkbook(fs);
		  
		 		  
		  Sheet sh = wb.getSheet(0); // this is to get the access to Sheet1. 
		  Sheet sh1 = wb.getSheet(1);// this is to get the access to Sheet2. 
		  Sheet sh2 = wb.getSheet(2);// this is to get the access to Sheet3. 
		  Sheet sh3 = wb.getSheet(3);// this is to get the access to Sheet4. 
		
		  
	 		  
		driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(sh.getCell(0, 0).getContents());
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys(sh.getCell(1, 0).getContents());
		
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		
		driver.findElement(By.xpath("//input[@name='login']")).click();//Login to the application
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		
		assertTrue(isElementPresent(By.xpath("//img[@src='/images/masts/mast_flightfinder.gif']")));	  //Verify that application navigates to "Flight Finder" 	  
		driver.findElement(By.xpath("//input[@value='roundtrip']")).click();
		Select passenger = new Select(driver.findElement(By.xpath("//select[@name='passCount']")));
		passenger.selectByVisibleText("2");
		
		//Get count of dropdown values & print 
		  Select dropdown = new Select(driver.findElement(By.xpath("//select[@name='fromPort']")));      //Get all options     
		  List<WebElement> dd = dropdown.getOptions();   
		  //Get the length     
		  System.out.println(dd.size());      // Loop to print one by one     
		  for (int j = 0; j < dd.size(); j++) {         
			  
			  System.out.println(dd.get(j).getText());      }
		  
		  //Provide flight details
		Select departfrom = new Select(driver.findElement(By.xpath("//select[@name='fromPort']")));
		departfrom.selectByVisibleText(sh1.getCell(0, 3).getContents());
		Select departmonth = new Select(driver.findElement(By.xpath("//select[@name='fromMonth']")));
		departmonth.selectByVisibleText(sh3.getCell(0, 4).getContents());
		Select departday = new Select(driver.findElement(By.xpath("//select[@name='fromDay']")));
		departday.selectByVisibleText(sh2.getCell(0, 0).getContents());
		Select arrivein = new Select(driver.findElement(By.xpath("//select[@name='toPort']")));
		arrivein.selectByVisibleText(sh1.getCell(0, 5).getContents());
		Select returnmonth = new Select(driver.findElement(By.xpath("//select[@name='toMonth']")));
		returnmonth.selectByVisibleText(sh3.getCell(0, 4).getContents());
		Select returnday = new Select(driver.findElement(By.xpath("//select[@name='toDay']")));
		returnday.selectByVisibleText(sh2.getCell(0, 22).getContents());
		driver.findElement(By.xpath("//input[@value='Coach']")).click();
		
		driver.findElement(By.xpath("//input[@name='findFlights']")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		
		//Select Depart and Return flights
		driver.findElement(By.xpath("//input[@value='Blue Skies Airlines$360$270$5:03']")).click();
		driver.findElement(By.xpath("//input[@value='Blue Skies Airlines$630$270$12:23']")).click();
		
		driver.findElement(By.xpath("//input[@name='reserveFlights']")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		
		
		//Capture and display cost details
		WebElement DepartPrice = driver.findElement(By.xpath("//tr[3]/td[3]/font"));
		System.out.println("Departure Trip Cost: " + DepartPrice.getText());
		
		WebElement ArrivePrice = driver.findElement(By.xpath("//tr[6]/td[3]/font"));
		System.out.println("Arival Trip Cost: " + ArrivePrice.getText());
		
		WebElement NoPassengers = driver.findElement(By.xpath("//tr[2]/td/table/tbody/tr[7]/td[2]"));
		System.out.println("Number of passengers: " + NoPassengers.getText());
		
		WebElement Taxes = driver.findElement(By.xpath("//tr[8]/td[2]/font"));
		System.out.println("Taxes: " + Taxes.getText());
	
		WebElement Total = driver.findElement(By.xpath("//td[2]/font/b"));
		System.out.println("Total: " + Total.getText());
		
		//Verify correct calculation of cost
		String x = DepartPrice.getText();
		String y = DepartPrice.getText();
		String z = NoPassengers.getText();
		String k = Taxes.getText();
		String h = Total.getText();
				
		String a = z.replace("$","");
		String t = k.replace("$","");
		String e = h.replace("$","");
		
		int n1 = Integer.parseInt(x);
		int n2 = Integer.parseInt(y);
		int n3 = Integer.parseInt(a);
		int n4 = Integer.parseInt(t);
		int n5 = Integer.parseInt(e);
		
		int answer = ((n1 + n2)* n3) + n4;
		System.out.println("Calculated Total:"  + "$" + answer);
				
		if( answer == n5 ){
			System.out.println ("PASSED");
		   }else{
			 System.out.println ("FAILED");
		   };
		
		 //Fill all the required details
		driver.findElement(By.xpath("//input[@name='passFirst0']")).sendKeys("First");
		driver.findElement(By.xpath("//input[@name='passLast0']")).sendKeys("Last");
		driver.findElement(By.xpath("//input[@name='passFirst1']")).sendKeys("Name");
		driver.findElement(By.xpath("//input[@name='passLast1']")).sendKeys("Name");
		driver.findElement(By.xpath("//input[@name='creditnumber']")).sendKeys("999999999");
		driver.findElement(By.xpath("//input[@name='buyFlights']")).click();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);	
		
		assertTrue(isElementPresent(By.xpath("//font[contains(., 'Your itinerary has been booked!')]")));
		driver.findElement(By.xpath("//img[@src='/images/forms/Logout.gif']")).click();

	
		// Take screenshot and store as a file format
		File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 // now copy the  screenshot to desired location using copyFile //method
		FileUtils.copyFile(src, new File("C:/Users/katherine.a.ciudad/workspace/CaseStudy_AutomationEngineer/Result/Activity2/output2.png"));
		
		driver.close();
		// TODO Auto-generated method stub  	}  }
	}

	private static int multiply(String x, String z) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static int sum(String x, String y) {
		// TODO Auto-generated method stub
		return 0;
	}

	private static void assertTrue(Object elementPresent) {
		// TODO Auto-generated method stub
		
	}

	private static Object isElementPresent(By by) {
		// TODO Auto-generated method stub
		return null;
	}
}

