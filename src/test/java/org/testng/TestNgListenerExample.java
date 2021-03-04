package org.testng;

/**
 * TestNG listener can be applied to tests that are being executed (either in testng.xml or in project configuration: Run > Edit Configurations > Listeners).
 * It listens and executes specific methods after each state, e.g.: test was executed, test failed, test passed, etc.
 * It is useful when you need to automatically execute something on each test success/failure, or test start/end, etc.
 */
public class TestNgListenerExample implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("Listener info: Test was successful");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        System.out.println("Listener info: Test FAILED!!! Test name: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        System.out.println("Listener info: Test was skipped. Test name: " + result.getName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}
