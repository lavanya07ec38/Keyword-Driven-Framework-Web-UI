package com.qa.hs.keyword.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Base {
	
	public WebDriver driver;
	public Properties prop;
	
	
	public WebDriver init_driver(String browserName) {
		
		if(browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:/selenium/chromedriver_new/chromedriver.exe");
			if(prop.getProperty("headless").equals("yes")) {
				ChromeOptions options=new ChromeOptions();
				options.addArguments("headless");
				driver=new ChromeDriver(options);
			}
			else {
				driver=new ChromeDriver();
				
			}
				
		}
		return driver;
		
	}
	
	public Properties init_properties() throws FileNotFoundException {
		
		prop=new Properties();
		FileInputStream fs=new FileInputStream("D:/selenium/selenium-java-3.4.0/com.keywordDrivenDemo/src/main/java/com/qa/hs/keyword/config/config.properties");
		try {
			prop.load(fs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}

}
