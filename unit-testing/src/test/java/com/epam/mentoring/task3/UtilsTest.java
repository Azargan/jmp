package com.epam.mentoring.task3;

import static com.epam.mentoring.task3.Utils.computeFactorial;
import static com.epam.mentoring.task3.Utils.concatenateWords;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
import org.junit.Ignore;

/**
 *
 * @author Aliaksei Vihuro <aliaksei_vihuro@epam.com>
 */
public class UtilsTest {

    @BeforeClass
    public static void setUpClass() {
        System.out.println("Setup test class");
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Clean up test class");
    }

    @Before
    public void setUp() {
        System.out.println("Setup for before test");
    }

    @After
    public void tearDown() {
        System.out.println("Clean up after test");
    }

    /**
     * Test of concatenateWords method, of class Utils.
     */
    @Test
    public void testConcatenateWords() {
        
        System.out.println("Test \"concatenateWords\" method");
        
        assertThat(concatenateWords("Wo", "rd")).isEqualTo("Word");
        assertThat(concatenateWords("Word", null)).isEqualTo("Word");
        assertThat(concatenateWords(null, "Word")).isEqualTo("Word");
        assertThat(concatenateWords("Word", "")).isEqualTo("Word");
        assertThat(concatenateWords("", "Word")).isEqualTo("Word");
        assertThat(concatenateWords(null, null)).isEqualTo(null);
        assertThat(concatenateWords("", "")).isEqualTo("");
    }

    /**
     * Test of computeFactorial method, of class Utils.
     */
    @Test
    public void testComputeFactorial() {
        
        System.out.println("Test \"computeFactorial\" method");
        
        assertThat(computeFactorial(0).longValue()).isEqualTo(1L);
        assertThat(computeFactorial(1).longValue()).isEqualTo(1L);
        assertThat(computeFactorial(10).longValue()).isEqualTo(3628800L);
        
        assertThatThrownBy(()->computeFactorial(null))
                .hasMessage("Argument can't be null or less than zero")
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(()->computeFactorial(-5))
                .hasMessage("Argument can't be null or less than zero")
                .isInstanceOf(IllegalArgumentException.class);
    }
    
    @Test(timeout = 3000L)
    public void testComputeFactorialWithTimeout() {
        
        System.out.println("Test \"computeFactorial\" method with timeout");
        
        assertThat(computeFactorial(500000)).isNotNull();
    }

    /**
     * Test of normalizeWord method, of class Utils.
     */
    @Test
    @Ignore
    public void testNormalizeWord() {
        System.out.println("Test \"normalizeWord\" method. You'll not see this message because ignoring test");
    }

}