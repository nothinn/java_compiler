package junit;

import junit.framework.TestCase;
import pass.UnaryPlus;

public class UnaryPlusTest extends TestCase {
    private UnaryPlus un;

    protected void setUp() throws Exception{
        super.setUp();
        un = new UnaryPlus();

    }
    protected void tearDown() throws Exception{
        super.tearDown();
    }

    public void testUnaryPlus(){
        this.assertEquals(un.unplus(4),4);
        this.assertEquals(un.unplus(2),2);

    }
}
