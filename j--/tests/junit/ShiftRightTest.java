package junit;

import junit.framework.TestCase;
import pass.ShiftRight;

public class ShiftRightTest extends TestCase {
	private ShiftRight shiftRight;

	protected void setUp() throws Exception {
		super.setUp();
		shiftRight = new ShiftRight();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testShiftRight() {
		this.assertEquals(shiftRight.shiftRight(-1, 1), -1);
		this.assertEquals(shiftRight.shiftRight(-2, 1), -1);
		this.assertEquals(shiftRight.shiftRight(56, 3), 7);

	}
}
