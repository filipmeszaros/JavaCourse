package org.testng;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

/**
 * This advanced TestNG listener is implemented using ExtentReports framework, that will generate HTML report of test results.
 * It will create test report in ./reports/ directory with current date and time, and exact number and percentages of passed/failed/skipped tests,
 * along with
 */
public class TestNgAdvancedListenerExample implements ITestListener {

    public ExtentSparkReporter reporter;
    public ExtentReports reports;
    public ExtentTest test;

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
        reports.setSystemInfo("Test details 2", "Write some additional details here");
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = reports.createTest(result.getTestClass().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName());
        reports.flush();
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        long testDuration = result.getEndMillis() - result.getStartMillis();
        test.log(Status.PASS, "Test duration: " + TimeUnit.MILLISECONDS.toSeconds(testDuration) + " seconds");
        reports.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        long testDuration = result.getEndMillis() - result.getStartMillis();
        test.log(Status.FAIL, "Test duration: " + TimeUnit.MILLISECONDS.toSeconds(testDuration) + " seconds");
        test.fail(result.getThrowable().getCause());
        test.fail(result.getThrowable());

        //TODO: make a screenshot of failure
        reports.flush();
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        test.log(Status.FAIL, "Test Failed with timeout");
        reports.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.skip(result.getThrowable().getCause());
        test.skip(result.getThrowable());

        reports.flush();
    }

    @Override
    public void onFinish(ITestContext context) {
        reports.flush();
    }
}
