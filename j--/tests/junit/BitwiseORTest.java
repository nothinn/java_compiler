package junit;

import junit.framework.TestCase;
import pass.BitwiseOR;

public class BitwiseORTest extends TestCase {
	private BitwiseOR bitwiseOR;
	
	protected void setUp() throws Exception {
		super.setUp();
		bitwiseOR = new BitwiseOR();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testRemainder() {
		this.assertEquals(bitwiseOR.bitwiseOR(0, 42), 42); 
		this.assertEquals(bitwiseOR.bitwiseOR(42, 0), 42);
		this.assertEquals(bitwiseOR.bitwiseOR(2, 1), 3); 
	}
}
