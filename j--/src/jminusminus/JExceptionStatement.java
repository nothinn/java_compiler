package jminusminus;

import static jminusminus.CLConstants.*;
import java.util.*;


/**
 * The AST node for an exception-statement.
 */

 class JExceptionStatement extends JStatement{

    /** Main block for code */
    private JBlock mainBlock;

    /** Blocks for the catches */
    private ArrayList<JBlock> catchBlocks;

    /** Parameters for the catches */
    private ArrayList<JFormalParameter> catchParams;

    /** Finally block for code */
    private JBlock finalBlock;




    /**
     * Construct an AST node for an exception-statement given its line number, the block to run,
     * the catch parameters, the catch blocks and the finalBlock
     * 
     * @param line
     *            line in which the if-statement occurs in the source file.
     * @param mainBlock
     *            Main block that has to be executed.
     * @param catchParams
     *            Formal parameters that describe the catches.
     * @param catchBlock
     *            Block to be executed when exception caught.
     * @param finalBlock
     *            Block of code that is always executed at the end.
     */

    public JExceptionStatement(int line, JBlock mainBlock, ArrayList<JFormalParameter> catchParams, ArrayList<JBlock> catchBlocks,JBlock finalBlock){
        super(line);
        this.mainBlock = mainBlock;
        this.catchParams = catchParams;
        this.catchBlocks = catchBlocks;
        this.finalBlock = finalBlock;
        
    }

    public JStatement analyze(Context context) {
        // To be filled out
        return this;
    }


    public void codegen(CLEmitter output) {
        //To be filled out.
    }


    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JExceptionStatement line=\"%d\">\n", line());
        p.indentRight();
        p.printf("<MainBlock>\n");
        p.indentRight();
        mainBlock.writeToStdOut(p);
        p.indentLeft();
        if(catchParams != null){
            ListIterator<JFormalParameter> parIte = catchParams.listIterator();
            ListIterator<JBlock> blockIte = catchBlocks.listIterator();
        
            while(parIte.hasNext()){
                
            p.printf("<CatchParam>\n");
            p.indentRight();
            parIte.next().writeToStdOut(p);
            p.indentLeft();
            p.printf("</CatchParam\n");

            p.printf("<CatchBlock>");
            p.indentRight();
            blockIte.next().writeToStdOut(p);
            p.indentLeft();
            p.printf("</CatchBlock>");
            
            }   
        }   
        p.indentLeft();
        p.printf("<ThenClause>\n");
        p.indentRight();
        //thenPart.writeToStdOut(p);
        p.indentLeft();
        p.printf("</ThenClause>\n");
        //if (elsePart != null) {
            p.printf("<ElseClause>\n");
            p.indentRight();
            //elsePart.writeToStdOut(p);
            p.indentLeft();
            p.printf("</ElseClause>\n");
        //}
        p.indentLeft();
        p.printf("</JIfStatement>\n");
    }

 }