package junit;

import junit.framework.TestCase;
import pass.ShiftLeft;

public class ShiftLeftTest extends TestCase {
	private ShiftLeft shiftLeft;

	protected void setUp() throws Exception {
		super.setUp();
		shiftLeft = new ShiftLeft();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testShiftLeft() {
		this.assertEquals(shiftLeft.shiftLeft(1, 2), 4);
		this.assertEquals(shiftLeft.shiftLeft(0, 4), 0);
		this.assertEquals(shiftLeft.shiftLeft(3, 5), 96);
		this.assertEquals(shiftLeft.shiftLeft(-1, 1), -2);
	}
}