package be.ccfun.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdvancedMathTest {

	@Test
	public void testEvaluation1() {
		long l = AdvancedMath.evaluateExpression("1 + 2 * 3 + 4 * 5 + 6");
		Assertions.assertEquals(231, l);
	}

	@Test
	public void testEvaluation2() {
		long l = AdvancedMath.evaluateExpression("1 + (2 * 3) + (4 * (5 + 6))");
		Assertions.assertEquals(51, l);
	}

	@Test
	public void testEvaluation3() {
		long l = AdvancedMath.evaluateExpression("5 + (8 * 3 + 9 + 3 * 4 * 3)");
		Assertions.assertEquals(1445, l);
	}

	@Test
	public void testEvaluation4() {
		long l = AdvancedMath.evaluateExpression("2 * 3 + (4 * 5)");
		Assertions.assertEquals(46, l);
	}

	@Test
	public void testEvaluation5() {
		long l = AdvancedMath.evaluateExpression("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
		Assertions.assertEquals(669060, l);
	}

	@Test
	public void testEvaluation6() {
		long l = AdvancedMath.evaluateExpression("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
		Assertions.assertEquals(23340, l);
	}

	@Test
	public void testEvaluation7() {
		long l = AdvancedMath.evaluateExpression("(9 * 8 * 4 * 8 * (4 + 8 + 7 + 7 * 5) + 3) * 5");
		Assertions.assertEquals(1532160, l);
	}

}
