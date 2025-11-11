package com.comcast.crm.Listenerutility;

import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.comcast.crm.baseTest.BaseClass;
import com.comcast.crm.generic.webdriverutility.UtilityClassObject;

public class ListnerImpClass implements ITestListener, ISuiteListener {
	public static ExtentReports report;
	public ExtentSparkReporter spark;
	public ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		System.out.println("Report configuration");

		// Spark report config
		String time = new Date().toString().replace(" ","_").replace(":","_");
		ExtentSparkReporter spark = new ExtentSparkReporter("./AdvanceReport/report_"+time+".html");
		spark.config().setDocumentTitle("CRM Test Suite Results");
		spark.config().setReportName("CRM Report");
		spark.config().setTheme(Theme.DARK);

		// add env information and create test
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows-11");
		report.setSystemInfo("BROWSER", "Chrome-10");
	}

	@Override
	public void onFinish(ISuite suite) {

		System.out.println("Report backUp");
		report.flush();
  
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("======" + result.getMethod().getMethodName() + "===Start===");
		test = report.createTest(result.getMethod().getMethodName());
		UtilityClassObject.setTest(test);
		test.log(Status.INFO, result.getMethod().getMethodName() + "==>Started<===");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("======" + result.getMethod().getMethodName() + "===End===");
		test.log(Status.PASS, result.getMethod().getMethodName() + "==>Completed<===");

	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testName = result.getMethod().getMethodName();
		String time = new Date().toString().replace(" ", "_").replace(":", "_");
		TakesScreenshot ts = (TakesScreenshot) BaseClass.sdriver;
		String temp = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(temp, testName + "_" + time);
		test.log(Status.FAIL, result.getMethod().getMethodName() + "==>Failed<===");

	}

}
