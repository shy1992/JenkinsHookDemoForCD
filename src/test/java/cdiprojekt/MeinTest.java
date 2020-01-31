package cdiprojekt;

import org.junit.Test;

import junit.framework.Assert;

public class MeinTest {
	@Test
	public void calculator_test_sum() {
		Calculator calc = new Calculator();
		int result = calc.add(32, 32);
		
		Assert.assertEquals(64, result);
	}

}
