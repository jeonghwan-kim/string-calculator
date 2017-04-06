import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    private Calculator calc;
    @Before
    public void setUp() {
        calc = new Calculator();
    }
    @Test
    public void testCalculate_withComma() {
        int expected = 1 + 2;
        int actural = calc.calculate("1,2");
        assertEquals(expected, actural);
    }
    @Test
    public void testCalculate_withColon() {
        int expected = 1 + 2;
        int actural = calc.calculate("1:2");
        assertEquals(expected, actural);
    }
    @Test
    public void testCalulate_withCommaAndColon() {
        int expected = 1 + 2 + 3;
        int actural = calc.calculate("1:2,3");
        assertEquals(expected, actural);
    }
    @Test
    public void testCalculate_customSeperator() {
        int expected = 1 + 2 + 3;
        int actural = calc.calculate("//;\\n1;2;3");
        assertEquals(expected, actural);
    }
    @Test(expected = RuntimeException.class)
    public void testCalculate_throwErrorOnNegativeInteger() {
        calc.calculate("1,-2");
    }
    @Test(expected = RuntimeException.class)
    public void testCalculate_throwErrorOnNonInteger() {
        calc.calculate("b,a");
    }
}
