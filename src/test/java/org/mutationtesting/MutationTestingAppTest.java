package org.mutationtesting;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Mutation testing is a testing principle in which faults (mutations) are automatically added into your code multiple times,
 * and your tests are run. Tests should find out all changes, and they should fail.
 * If your tests fail -> mutation is killed -> your test is well written and will fail if bug is introduced to your code.
 * If your test pass -> mutation lives -> your test is badly written and will pass even if bug is introduced to your code.
 * The quality of your tests can be gauged from the percentage of mutations killed.
 *
 * How to proceed:
 * 1, In IntelliJ idea, install plugin: Files > Settings > Plugins > PIT mutation testing Idea plugin. Restart idea.
 * 2, Build project and right click on package src/java/org/mutationtesting and select "Pitest classes in mutationtesting"
 * 3, Mutators will be added to this package and results of mutation testing will show about 88% line coverage and 50% mutation coverage
 * 4, Investigate test report and see what kind of mutators were not found by our tests
 * 5, Uncomment lines commented out in this test and run step 2 again
 * 6, Mutators will be added to this package and results of mutation testing will show 100% line coverage and 100% mutation coverage
 * 7, This way, mutation testing helped us to improve tests, so that if bugs will be introduced into our app, tests will catch them
 *
 */
public class MutationTestingAppTest {
    MutationTestingApp app = new MutationTestingApp();

    @Test
    public void testPositive() {
        assertTrue(app.isPositive(10));
        assertFalse(app.isPositive(-5));
        //assertTrue(app.isPositive(0));   //uncomment this line to improve mutation testing coverage
    }

    @Test
    public void testPrime() {
        assertTrue(app.isPrime(13));
        //assertFalse(app.isPrime(6));     //uncomment this line to improve mutation testing coverage
        //assertFalse(app.isPrime(1));     //uncomment this line to improve mutation testing coverage
    }

    @Test
    public void testPalindrome() {
        assertTrue(app.isPalindrome("noon"));
        //assertFalse(app.isPalindrome("NetSuite"));   //uncomment this line to improve mutation testing coverage
        //assertFalse(app.isPalindrome("xABCx"));      //uncomment this line to improve mutation testing coverage
    }
}
