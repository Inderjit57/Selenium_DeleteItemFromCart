package com.automationPractice.retryLogic;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class TransformerForRetry implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		// this methods is just telling which class is maintaining retry count
		annotation.setRetryAnalyzer(RetryFailedTestCases.class);
		
	}

}
