package jminusminus;
import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/** 
 * The AST node for a for-statement
 */

class JEnhancedForStatement extends JStatement {
    /** Test expression. */
    private ArrayList<JStatement> declaration;
    private Type id;



    /** The Body */
    private JStatement body;
    private LocalContext context;

    /**
     * Construct an AST node for a for-statement given its line number, the test
     * expression, and the body.
     *
     * @param line
     *            line in which the for-statement occurs in the source file.
     * @param condition
     *            test expression.
     * @param body
     *            the body
     */

    public JEnhancedForStatement(int line,ArrayList<JStatement> declaration, Type id, JStatement body) {
        super(line);
        this.declaration= declaration;
        this.id = id;
        this.body = body;
    }
    
    /**
     * Analysis involves analyzing the test, checking its type and analyzing the
     * body statement.
     *
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JEnhancedForStatement analyze(Context context) {

        this.context = new LocalContext(context);
        
        for (int i = 0; i < declaration.size(); i++) {
            declaration.set(i, (JStatement) declaration.get(i).analyze(
                    this.context));
        }
        //TODO : Shouldn't we check that the second argument has a similar type as the declared type ? 

        
        return this;
    }

    /**
     * Generate code for the while loop.
     *
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        //TODO: Codegen using Jcc
    }
    
    public void writeToStdOut(PrettyPrinter p){
        p.printf("<JEnhancedForStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<Declaration>\n");
        for(JStatement i: declaration){
            i.writeToStdOut(p);
        }
        p.printf("</Declaration>\n");
        p.printf("<referenceType line=\"%d\" type=\"%s\"/>\n", line(),
                  ((id == null) ? "" : id.toString()));
        p.printf("</referenceType>\n");
        p.printf("<BodyStatement>\n");
        body.writeToStdOut(p);
        p.printf("</BodyStatement>\n");
        p.printf("</JEnhancedForStatement>\n");
    }

}

