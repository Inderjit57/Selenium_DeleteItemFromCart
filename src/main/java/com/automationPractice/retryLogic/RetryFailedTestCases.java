package com.automationPractice.retryLogic;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import com.automationPractice.BasePackage.TestBase;

public class RetryFailedTestCases extends TestBase implements IRetryAnalyzer {

	int counter = 1; // Intial value
	int maxCount = 3; // maximum retry count

	@Override
	public boolean retry(ITestResult result) {
		// When a Test case will fail, this class will be invoked to retry the test
		logger.info("Trying failed Test Case " + counter + " times");

		if (counter < maxCount) {
			counter++;
			return true;
		}
		return false;
	}

}
