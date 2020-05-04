package com.qa.keyword.hs.LoginTest;

import org.testng.annotations.Test;

import com.qa.hs.keyword.engine.KeywordEngine;

public class LoginTest {
	
	public KeywordEngine keywordEngine;
	@Test
	public void LoginTest() {
		
		keywordEngine=new KeywordEngine();
		keywordEngine.startExecution("Scenarios");
		
	}

}
