// Copyright 2011 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import static jminusminus.CLConstants.*;

/**
 * The AST node for a throw-statement.
 */

class JThrowStatement
    extends JStatement {

    /** The returned expression. */
    private JExpression expr;

    /**
     * Construct an AST node for a throw-statement given its
     * line number, and the expression that is returned.
     * 
     * @param line
     *                line in which the throw-statement appears
     *                in the source file.
     * @param expr
     *                the throw expression.
     */

    public JThrowStatement(int line, JExpression expr) {
        super(line);
        this.expr = expr;
    }



    public JStatement analyze(Context context) {
    	return this;
    }

    /**
     * Generate code for the return statement. In the case of
     * void method types, generate a simple (void) return. In the
     * case of a return expression, generate code to load that
     * onto the stack and then generate the appropriate return
     * instruction.
     * 
     * @param output
     *                the code emitter (basically an abstraction
     *                for producing the .class file).
     */

    public void codegen(CLEmitter output) {
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        if (expr != null) {
            p.printf("<JThrowStatement line=\"%d\">\n", line());
            p.indentRight(); 
            expr.writeToStdOut(p);
            p.indentLeft();
            p.printf("</JThrowStatement>\n");
        } else {
            p.printf("<JThrowStatement line=\"%d\"/>\n", line());
        }
    }
}
