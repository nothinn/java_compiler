package junit;

import junit.framework.TestCase;
import pass.BitwiseXOR;

public class BitwiseXORTest extends TestCase {
	private BitwiseXOR bitwiseXOR;
	
	protected void setUp() throws Exception {
		super.setUp();
		bitwiseXOR = new BitwiseXOR();
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	
	public void testRemainder() {
		this.assertEquals(bitwiseXOR.bitwiseXOR(3, 1), 2); 
		this.assertEquals(bitwiseXOR.bitwiseXOR(1, 3), 2);
		this.assertEquals(bitwiseXOR.bitwiseXOR(2, 3), 1); 
	}
}
