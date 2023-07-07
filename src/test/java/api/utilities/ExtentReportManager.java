package api.utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.sql.Date;
import java.text.SimpleDateFormat;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

//Extent report 5.x

public class ExtentReportManager implements ITestListener{
	
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
    String repName;
    
    public void onStart(ITestContext testContext)
    {
    	
    	String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date(System.currentTimeMillis()));    //time stamp
    	repName="Test-Report-"+timeStamp+".html";
    	
    	sparkreporter=new ExtentSparkReporter (".\\reports\\"+repName);//specify location of the report
    	sparkreporter.config().setDocumentTitle("RestAssuredAutomationProject"); //Title of report
    	sparkreporter.config().setReportName("Pet Store Users API");//Name of the report
    	sparkreporter.config().setTheme(Theme.DARK);
    	
    	extent=new ExtentReports();
    	extent.attachReporter(sparkreporter);
    	extent.setSystemInfo("Application","Pet Store Users API");
    	extent.setSystemInfo("Operatint System",System.getProperty("os.name"));
    	extent.setSystemInfo("User Name",System.getProperty("user.name"));
    	extent.setSystemInfo("Environement","QA");
    	extent.setSystemInfo("user","Mohan");
    	
    }
    
    public void onTestSucess(ITestResult result)
    {
    	test=extent.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
    	test.log(Status.PASS,"Test Passed");
    	
    }
    
    public void onTestFailure(ITestResult result)
    {
    	test=extent.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
    	test.log(Status.FAIL,"Test Failed");
    	test.log(Status.FAIL,result.getThrowable().getMessage());
    	
    	
    }
    
    public void onTestSkipped(ITestResult result)
    {
    	test=extent.createTest(result.getName());
    	test.assignCategory(result.getMethod().getGroups());
    	test.createNode(result.getName());
    	test.log(Status.SKIP,"Test Skipped");
    	test.log(Status.SKIP,result.getThrowable().getMessage());
    	
    	
    }
    
    public void onFinish(ITestContext testContext)
    {
    	extent.flush();
    }
}
