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
    private Type id; //Identifier

    private JForStatement forVersion; //We rewrite the enhanced for loop into a normal for loop 


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
        
        
        
        
        JFormalParameter param = new JFormalParameter(line, name, id);
        
        
        
        param =(JFormalParameter) param.analyze(context);
        
        
        
        //First we check if the expression is an array:
        
       /* if(!exp.type().isArray()){
            JAST.compilationUnit.reportSemanticError(exp.line(),
            "The expression is not an array.");
        }*/
        
        //Check that it is the same type:
        //param.type().mustMatchExpected(line, exp.type().componentType());
        
        //Analyze the array:
        exp = exp.analyze(context);
        
        this.context = new LocalContext(context); 
        
        
                //Take the next offset
                int offset = this.context.nextOffset();

        //Declare a new variable for iteration
        LocalVariableDefn variable = new LocalVariableDefn(param.type().resolve(this.context),offset);
        

        //Add to the current local context
        this.context.addEntry(param.line(), param.name(), variable);

        //We can now declare a new variable, to use for our foor loop:
        String newVar = "_i";

        //We check that the name is not already used and if it is, we expand it
        while(this.context.lookup(newVar) != null){
            newVar += "_i";
        }


        //Now we declare the parts of the for loop:

        //Declare the variable used for iterating the array, set to 0 as default.
        JVariableDeclarator declarator = new JVariableDeclarator(line, newVar, Type.INT, new JLiteralInt(line,"0"));
        
        ArrayList<JVariableDeclarator> decl = new  ArrayList<JVariableDeclarator>();
        decl.add(declarator);

        JVariableDeclaration declaration = new JVariableDeclaration(line, new ArrayList<String> (), decl);

        //Now for the condition:
        JExpression lhs, rhs, condition;

        //The lhs of the condition is the size of the array
        lhs = new JFieldSelection(line, exp, "length");
        
        //The rhs is the new variable
        rhs = new JVariable(line, newVar);

        //And it has to run while the length is greater than the variable:
        condition = new JGreaterThanOp(line, lhs, rhs);



        //The update has to increment by 1 each time:
        lhs = new JVariable(line, newVar);
        rhs = new JLiteralInt(line,"1");
        
        ArrayList<JStatement> modif = new ArrayList<JStatement>();
        modif.add(new JPlusAssignOp(line, lhs, rhs));


        //The body needs to first load the next value from the array:
        lhs = new JVariable(line, param.name());
        rhs = new JArrayExpression(line, exp, new JVariable(line, newVar));
        JStatement assign = new JAssignOp(line, lhs, rhs);


        ArrayList<JStatement> statements = new ArrayList<JStatement>();

        statements.add(assign);
        statements.add(this.body);
        JBlock newBody = new JBlock(line, statements);


        forVersion = new JForStatement(line, declaration, condition, modif, newBody);

        forVersion = forVersion.analyze(this.context);

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


        forVersion.codegen(output);

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

