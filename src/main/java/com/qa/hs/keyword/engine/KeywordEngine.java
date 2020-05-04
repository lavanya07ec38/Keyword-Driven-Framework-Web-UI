package com.qa.hs.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.hs.keyword.base.Base;

public class KeywordEngine {
	
	public WebDriver driver;
	public Properties prop;
	public static Workbook book;
	public static Sheet sheet;
	public static Base base;
	public static WebElement element;
	
	public final String sheet_path="D:\\selenium\\selenium-java-3.4.0\\com.keywordDrivenDemo\\src\\main\\java\\com\\qa\\hs\\keyword\\scenarios\\Keyword_scenarios.xlsx";
	
	public void startExecution(String sheetName) {
		
		String locatorName=null;
		String locatorValue=null;
		
		
		FileInputStream file=null;
		try {
			file=new FileInputStream(sheet_path);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book=WorkbookFactory.create(file);
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sheet=book.getSheet(sheetName);
		int col=0;
		for(int row=0;row<sheet.getLastRowNum();row++) {
			try {
			String locatorColValue=sheet.getRow(row+1).getCell(col+1).toString().trim();
			
			if(!locatorColValue.equals("NA")) {
					
			locatorName=locatorColValue.split("=")[0].trim();
			locatorValue=locatorColValue.split("=")[1].trim();								
			}
			
			String action=sheet.getRow(row+1).getCell(col+2).toString().trim();
			String value=sheet.getRow(row+1).getCell(col+3).toString().trim();
			
			switch(action) {
			case "open browser":
				
				base=new Base();
				prop=base.init_properties();
				
								
				if(value.isEmpty()||(value.equals("sNA"))) {
					driver=base.init_driver(prop.getProperty("browser"));
				}
				else {
					driver=base.init_driver(value);
				}
				break;
				
			case "enter url":
				if(value.isEmpty()||(value.equals("NA"))) {
					driver.get(prop.getProperty("url"));
				}
				else {
					
					driver.get(value);
					driver.manage().window().maximize();
					driver.manage().deleteAllCookies();
					driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
				}
				break;
			case "quit":
				driver.quit();		
				break;
							
			default:
				break;
			
			}
			
		switch(locatorName) {
		
		case "id":
			
			element=driver.findElement(By.id(locatorValue));
			if(action.equalsIgnoreCase("sendkeys")) {
				element.clear();
				element.sendKeys(value);
			}else if(action.equalsIgnoreCase("click")){
				element.click();
			}
			break;
			
		case "linkText":
			
			element=driver.findElement(By.linkText(locatorValue));
			element.click();
			break;
		default:
			break;		
			
		
		}
			}
			catch(Exception e){
				
			}
			
			
			
			
		}
		
		
	}

}
 