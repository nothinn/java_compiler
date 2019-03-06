package junit;

import junit.framework.TestCase;
import pass.UnaryComplement;

public class UnaryComplementTest extends TestCase {
    private UnaryComplement un;

    protected void setUp() throws Exception{
        super.setUp();
        un = new UnaryComplement();

    }
    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testUnaryComplement(){
        this.assertEquals(un.complement(0),1);
        this.assertEquals(un.complement(2),-3);

    }
}


