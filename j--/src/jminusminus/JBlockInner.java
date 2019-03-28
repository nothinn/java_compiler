// Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

package jminusminus;

import java.util.ArrayList;

/**
 * The AST node for a block, which delimits a nested level of scope.
 */

class JBlockInner extends JBlock implements JMember {

    /** List of statements forming the block body. */
    private ArrayList<JStatement> statements;

    /**
     * The new context (built in analyze()) represented by this block.
     */
    private LocalContext context;

    /** Class modifiers. */
    private ArrayList<String> mods;

    /** Static (class) fields of this class. */
    private ArrayList<JFieldDeclaration> staticFieldInitializations;

    /** Is method static. */
    protected boolean isStatic;

    /**
     * Construct an AST node for a block given its line number, and the list of
     * statements forming the block body.
     * 
     * @param line
     *            line in which the block occurs in the source file.
     * @param mods
     *            class modifiers.
     * @param classBlock
     *            class block.
     */

    public JBlockInner(int line, ArrayList<String> mods, JBlock body) {
        super(line, body.statements());
        this.statements = body.statements();
        this.mods = mods;
        staticFieldInitializations = new ArrayList<JFieldDeclaration>();
        this.isStatic = mods.contains("static");
    }

    /**
     * Return the list of statements comprising the block.
     * 
     * @return list of statements.
     */

    public ArrayList<JStatement> statements() {
        return statements;
    }

    /**
     * Return the list of modifiers.
     * 
     * @return list of modifiers.
     */

    public ArrayList<String> mods() {
        return mods;
    }

    /**
     * NOT IMPLEMENTED YET!!!!!!!!
     * 
     * Declare this constructor in the parent (class) context.
     * 
     * @param context
     *            the parent (class) context.
     * @param partial
     *            the code emitter (basically an abstraction for producing the
     *            partial class).
     */

    public void preAnalyze(Context context, CLEmitter partial) {
        if (isStatic) {
            JAST.compilationUnit.reportSemanticError(line(),
                    "Constructor cannot be declared static");
        }
    }

    /**
     * NOT IMPLEMENTED YET!!!!!!!!
     * 
     * Analyzing a block consists of creating a new nested context for that
     * block and analyzing each of its statements within that context.
     * 
     * @param context
     *            context in which names are resolved.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */

    public JBlockInner analyze(Context context) {
        // { ... } defines a new level of scope.
        this.context = new LocalContext(context);

        for (int i = 0; i < statements.size(); i++) {
            statements.set(i, (JStatement) statements.get(i).analyze(
                    this.context));
        }
        return this;
    }

    /**
     * NOT IMPLEMENTED YET!!!!!!!!
     * 
     * Generating code for a block consists of generating code for each of its
     * statements.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) {
        for (JStatement statement : statements) {
            statement.codegen(output);
        }
    }

    /**
     * @inheritDoc
     */

    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JBlockInner line=\"%d\">\n", line());
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (context != null) {
            p.indentRight();
            context.writeToStdOut(p);
            p.indentLeft();
        }
        for (JStatement statement : statements) {
            p.indentRight();
            statement.writeToStdOut(p);
            p.indentLeft();
        }
        p.printf("</JBlockInner>\n");
    }

}