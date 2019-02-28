package junit;

import junit.framework.TestCase;
import pass.ShiftRightLogical;

public class ShiftRightLogicalTest extends TestCase {
	private ShiftRightLogical shiftRightLogical;

	protected void setUp() throws Exception {
		super.setUp();
		shiftRightLogical = new ShiftRightLogical();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testShiftRightLogical() {
		this.assertEquals(shiftRightLogical.shiftRightLogical(3, 1), 1);
		this.assertEquals(shiftRightLogical.shiftRightLogical(4, 4), 0);
        this.assertEquals(shiftRightLogical.shiftRightLogical(56, 3), 7);
	}
}