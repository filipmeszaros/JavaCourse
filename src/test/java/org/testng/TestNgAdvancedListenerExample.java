package org.testng;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.selenium.SeleniumConfiguration;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * This advanced TestNG listener is implemented using ExtentReports framework, that will generate HTML report of test results.
 * It will create test report in ./reports/ directory with current date and time, and exact number and percentages of passed/failed/skipped tests,
 * along with
 */
public class TestNgAdvancedListenerExample extends SeleniumConfiguration implements ITestListener {

    public ExtentSparkReporter reporter;
    public ExtentReports reports;
    public ExtentTest test;
    //Make TestListener thread safe, e.g when running testng.xml with multiple threads (method results won't be scrumbled)
    public ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    final String USER_DIR = System.getProperty("user.dir") + "\\reports\\";
    final String REPORT_SUBDIR = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
    final String REPORT_DIR = USER_DIR + REPORT_SUBDIR;


    @Override
    public void onStart(ITestContext context) {
        reporter = new ExtentSparkReporter(REPORT_DIR);
        reporter.config().setReportName("Web Automation Results");
        reporter.config().setDocumentTitle("Test Results");

        reports = new ExtentReports();
        reports.attachReporter(reporter);

        //You can write any additional details to HTML test summary here
        reports.setSystemInfo("Tester", "Filip Meszaros");
        reports.setSystemInfo("Test details", "Write some details here");

        //Flush report to create whole report directory needed for screenshots
        reports.flush();
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = reports.createTest(result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName());
        extentTest.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long testDuration = result.getEndMillis() - result.getStartMillis();
        extentTest.get().log(Status.PASS, "Test duration: " + TimeUnit.MILLISECONDS.toSeconds(testDuration) + " seconds");
    }

    @SneakyThrows
    @Override
    public void onTestFailure(ITestResult result) {
        long testDuration = result.getEndMillis() - result.getStartMillis();
        extentTest.get().log(Status.FAIL, "Test duration: " + TimeUnit.MILLISECONDS.toSeconds(testDuration) + " seconds");
        extentTest.get().fail(result.getThrowable().getMessage());
        extentTest.get().fail(result.getThrowable());

        //Take screenshot of current screen and append it to report
        File screenshotObject = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotName = result.getTestClass().getRealClass().getSimpleName() + "_" + result.getMethod().getMethodName() + ".png";
        String absoluteScreenshotPath = REPORT_DIR + "\\" + screenshotName;
        FileUtils.copyFile(screenshotObject, new File(absoluteScreenshotPath));

        extentTest.get().addScreenCaptureFromPath(absoluteScreenshotPath); //assign screenshot to failed test
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        long testDuration = result.getEndMillis() - result.getStartMillis();
        extentTest.get().log(Status.FAIL, "Test Failed with timeout");
        extentTest.get().log(Status.FAIL, "Test duration: " + TimeUnit.MILLISECONDS.toSeconds(testDuration) + " seconds");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extentTest.get().skip(result.getThrowable().getCause());
        extentTest.get().skip(result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        long runTimeInSeconds = (context.getEndDate().getTime() - context.getStartDate().getTime()) / 1000L;
        reports.setSystemInfo("Running time", runTimeInSeconds + " seconds");
        reports.flush();
    }
}
