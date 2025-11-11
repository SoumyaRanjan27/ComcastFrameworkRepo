package com.comcast.crm.Listenerutility;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryListenerImp implements IRetryAnalyzer{

	int count=0;  
	int LimitCount=5;
	@Override
	public boolean retry(ITestResult result) {
		if(count<LimitCount) {
			count++;
			return true;
		}
		return false;
	}

}
