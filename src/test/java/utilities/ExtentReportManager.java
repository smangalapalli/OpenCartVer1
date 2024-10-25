package utilities;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;


public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//time stamp
		repName="Test-Report-"+timeStamp+".html";
		//System.out.println("C:\\Users\\sansr\\maven-workspace\\PetStoreAutomation\\reports\\"+ repName);
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+ repName); //specify location of the report
		System.out.println("REppName: " + repName);
		sparkReporter.config().setDocumentTitle("OpenCart Automation Report"); //Title of Report
		sparkReporter.config().setReportName("OpenCart Functional Testing");//name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customers");
		extent.setSystemInfo("User Name",  System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		String os = testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operating System",  os);
		
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser",  browser);
		
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups",  includedGroups.toString());
		}
	}
	
	public void onTestSuccess(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); //to display groups in report
		test.log(Status.PASS, result.getName() + " got successfully executed");		
	}
	
	public void onTestFailure(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL, result.getName() + " got Failed");
		test.log(Status.INFO, result.getThrowable().getMessage());	
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch(IOException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName() + " got Skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());		
	}
	
	public void onFinish(ITestContext testContext)
	{
		extent.flush();
		
		//open the report automatically after report has been generated. Need not open report manually.
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\" + repName;
		File extentReport = new File(pathOfExtentReport);
		System.out.println("repName: " + repName);
		System.out.println("extentReport: " + extentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI()); //opens report in a browser automatically
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		//Send report via email
		/*
		 * try { URL url = new URL("file:///" + System.getProperty("user.dir") +
		 * "reports\\" +repName);
		 * 
		 * //create the email message ImageHtmlEmail email = new ImageHtmlEmail();
		 * ImageHtmlEmail email = new ImageHtmlEmail(); email.setDataSourceResolver(new
		 * DataSourceUrlResolver(url)); email.setHostName("smtp.googlemail.com");
		 * email.setSmtpPort(465); email.setAuthenticator(new
		 * DefaultAuthenticator("sansree95@gmail.com", "gmail password"));
		 * email.setSSLOnConnect(true); email.setFrom("sansree95@gmail.com"); //sender
		 * email.setSubject("Test Results");
		 * email.setMsg("Please find the attached Report...");
		 * email.addTo("sansree95@gmail.com"); //receiver email.attach(url,
		 * "extent report", "please check report"); email.send(); //send the email
		 * 
		 * } catch (Exception e) { e.printStackTrace(); }
		 */
		
	}
	

}
