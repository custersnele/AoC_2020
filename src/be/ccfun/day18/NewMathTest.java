package be.ccfun.day18;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class NewMathTest {

	@Test
	public void testEvaluation1() {
		long l = NewMath.evaluateExpression("1 + 2 * 3 + 4 * 5 + 6");
		Assertions.assertEquals(71, l);
	}

	@Test
	public void testEvaluation2() {
		long l = NewMath.evaluateExpression("1 + (2 * 3) + (4 * (5 + 6))");
		Assertions.assertEquals(51, l);
	}

	@Test
	public void testEvaluation3() {
		long l = NewMath.evaluateExpression("5 + (8 * 3 + 9 + 3 * 4 * 3)");
		Assertions.assertEquals(437, l);
	}



}
