import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCalculatorTest {
    private StringCalculator calc;
    @Before
    public void setUp() {
        calc = new StringCalculator();
    }

    @Test
    public void testAdd_빈문자열은_0을_반환한다() {
        int expected = 0;
        int actural = calc.add("");
        assertEquals(expected, actural);
    }

    @Test
    public void testAdd_쉽표로_구분된_숫자들을_더한다() {
        int expected = 1 + 2;
        int actural = calc.add("1,2");
        assertEquals(expected, actural);
    }

    @Test
    public void testAdd_콜론으로_구분된_숫자들을_더한다() {
        int expected = 1 + 2;
        int actural = calc.add("1:2");
        assertEquals(expected, actural);
    }

    @Test
    public void testCalulate_쉼표나_콜론으로_구분된_숫자들을_더한다() {
        int expected = 1 + 2 + 3;
        int actural = calc.add("1:2,3");
        assertEquals(expected, actural);
    }

    @Test
    public void testAdd_커스텀_구분자를_지정할_수_있다() {
        int expected = 1 + 2 + 3;
        int actural = calc.add("//;\\n1;2;3");
        assertEquals(expected, actural);
    }

    @Test(expected = RuntimeException.class)
    public void testAdd_음수가_포함되면_에러를_던진다() {
        calc.add("1,-2");
    }

    @Test(expected = RuntimeException.class)
    public void testAdd_정수가_포함되면_에러를_던진다() {
        calc.add("b,a");
    }
}
