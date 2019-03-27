package jminusminus;
import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/** 
 * The AST node for a for-statement
 */

class JForStatement extends JStatement {
    /** Test expression. */
    private ArrayList<JVariableDeclarator> declaration;
    private JExpression condition;
    private ArrayList<JStatement> modif;


    /** The Body */
    private JStatement body;

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

    public JForStatement(int line,ArrayList<JVariableDeclarator> declaration, JExpression condition, ArrayList<JStatement> modif, JStatement body) {
        super(line);
        this.declaration= declaration;
        this.condition = condition;
        this.modif = modif;
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

    public JForStatement analyze(Context context) {
        
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
        p.printf("===========================================\n");
        p.printf("<JForStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<Declaration>\n");
        for(JVariableDeclarator i:declaration){
            i.writeToStdOut(p);
        }
        p.printf("</Declaration>\n");
        p.printf("<Condition>\n");
        condition.writeToStdOut(p);
        p.printf("</Condition>\n");
        p.printf("<ModificationExpression>\n");
        for(JStatement i:modif){
            i.writeToStdOut(p);
        }
        p.printf("</ModificationExpression>\n");
        p.printf("<BodyStatement>\n");
        body.writeToStdOut(p);
        p.printf("</BodyStatement>\n");
        p.printf("</JForStatement>\n");
        p.printf("===========================================\n");
    }

}

