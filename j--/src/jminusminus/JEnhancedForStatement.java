package jminusminus;
import java.util.ArrayList;
import static jminusminus.CLConstants.*;

/** 
 * The AST node for a enhanced-for-statement
 */

class JEnhancedForStatement extends JStatement {
    /** Test expression. */
    private String name;
    private JExpression exp;
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

    public JEnhancedForStatement(int line,Type id,String name,JExpression exp,JStatement body) {
        super(line);
        this.id = id;
        this.body = body;
        this.name = name;
        this.exp = exp;


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

        //this.id.analyze(this.context);

        /*
           if(this.id != exp.type()){ 
                JAST.compilationUnit.reportSemanticError(line(), "Type mismatch in For loop");
            } */


        body = (JStatement) body.analyze(this.context);
        
        
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
/*
        //First we run initialization
        for (JVariableDeclaration decl : declaration){
            decl.codegen(output);
        }

        //id.codegen();

        output.addNoArgInstruction(ALOAD_3);
        output.addOneArgInstruction(ASTORE, 4);
        output.addOneArgInstruction(ALOAD, 4);
        output.addNoArgInstruction(ARRAYLENGTH);
        output.addOneArgInstruction(ISTORE, 5);
        output.addNoArgInstruction(ICONST_0);
        output.addOneArgInstruction(ISTORE, 6);



        String test = output.createLabel(); //Test if array still has elements

        String doneLabel = output.createLabel(); //Where to  branch if no more elements

        output.addLabel(test); //Place to go to test loop



        output.addOneArgInstruction(ILOAD, 6);
        output.addOneArgInstruction(ILOAD, 5);
        

        //INSERT BRANCH AWAY HERE
        output.addBranchInstruction(IF_ICMPGE, doneLabel);

        //Load next point off the array
        output.addOneArgInstruction(ALOAD,4);

        //Generate the body of the loop
        body.codegen(output);

        //Increment the counter.
        output.addIINCInstruction(6, 1);

        //Always jump back to test:
        output.addBranchInstruction(GOTO, test);



        //Label of where to go when done.
        output.addLabel(doneLabel);

        */

    }
    
    public void writeToStdOut(PrettyPrinter p){
        p.printf("<JEnhancedForStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<Declaration>\n");
        //name.writeToStdOut(p);
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

