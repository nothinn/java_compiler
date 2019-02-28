package junit;

import junit.framework.TestCase;
import pass.BitwiseAND;

public class BitwiseANDTest extends TestCase {
	private BitwiseAND bitwiseAND;
	
	protected void setUp() throws Exception {
		super.setUp();
		bitwiseAND = new BitwiseAND();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testRemainder() {
		this.assertEquals(bitwiseAND.bitwiseAND(0, 42), 0); 
		this.assertEquals(bitwiseAND.bitwiseAND(42, 0), 0);
		this.assertEquals(bitwiseAND.bitwiseAND(2, 3), 2); 
	}
}
