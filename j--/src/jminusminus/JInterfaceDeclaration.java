package jminusminus;

import java.util.ArrayList;
import static jminusminus.CLConstants.*;

class JInterfaceDeclaration extends JAST implements JTypeDecl{
	
	
	/** Interface modifiers. (only public or empty is allowed)*/
	 private ArrayList<String> mods;

    /** Interface name. */
    private String name;
    
    /** Class block. (Not to sure about the block. my understanding is that is just all inside the curly bracket of the interface/class declaration)*/
    private ArrayList<JMember> interfaceBlock;

    /** Super interface type. */
    private Type superType;

    /** This interface type. */
    private Type thisType;

    /** Context for this interface. (I guess is has that as well. has to be implemented)*/   // TOD: implement - change to interface context
    private ClassContext context; 

    /** Whether this class has an explicit constructor. 
    i guess the interface itself never has a constructor
    private boolean hasExplicitConstructor;
    */

    /** Instance fields of this interface. 
    private ArrayList<JFieldDeclaration> instanceFieldInitializations;
    as far as i understand there is instance field of interfaces is always static.. not sure though
    https://crunchify.com/java-tips-never-make-an-instance-fields-of-class-public/
    https://stackoverflow.com/questions/2430756/why-are-interface-variables-static-and-final-by-default/2430787
    	*/
    
    

    /** Static (interface) fields of this interface. */
    private ArrayList<JFieldDeclaration> staticFieldInitializations;
   
    
    /**
     * Construct an AST node for a interface declaration given the line number, interface
     * modifier, name of the interface, its super interface type, and the
     * class block.
     * 
     * @param line
     *            line in which the interface declaration occurs in the source file.
     * @param mods
     *            interface modifier.
     * @param name
     *            interface name.
     * @param superType
     *            super interface type.
     * @param interfaceBlock
     *            interface block.
     */
    
    public JInterfaceDeclaration(int line, ArrayList<String> mods, String name,
            Type superType, ArrayList<JMember> interfaceBlock) { // is this right??
        super(line);
        this.mods = mods;
        this.name = name;
        this.superType = superType;
        this.interfaceBlock = interfaceBlock;
        //instanceFieldInitializations = new ArrayList<JFieldDeclaration>();
        staticFieldInitializations = new ArrayList<JFieldDeclaration>();
    }
    
    /**
     * Return the interface name.
     * 
     * @return the interface name.
     */

    public String name() {
        return name;
    }

    /**
     * Return the interface' super interface type.
     * 
     * @return the super interface type.
     */

    public Type superType() {
        return superType;
    }

    /**
     * Return the type that this interface declaration defines.
     * 
     * @return the defined type.
     */

    public Type thisType() {
        return thisType;
    }

    
    /**
     * Declare this interface in the parent (compilation unit) context.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */
    
    //not sure what this method does... or if this is what it should contrain
    public void declareThisType(Context context) {    } // Implement later
    
    
    /**
     * Pre-analyze the members of this declaration in the parent context.
     * Pre-analysis extends to the member headers (including method headers) but
     * not into the bodies.
     * 
     * @param context
     *            the parent (compilation unit) context.
     */

    public void preAnalyze(Context context) {    } //implment later

    
    /**
     * Perform semantic analysis on the interface and all of its members within the
     * given context. 
     * 
     * @param context
     *            the parent (compilation unit) context. Ignored here.
     * @return the analyzed (and possibly rewritten) AST subtree.
     */
    
    
    public JAST analyze(Context context) { return this ; }	
    
    
    /**
     * Generate code for the class declaration.
     * 
     * @param output
     *            the code emitter (basically an abstraction for producing the
     *            .class file).
     */

    public void codegen(CLEmitter output) { }
    
    public void writeToStdOut(PrettyPrinter p) {
        p.printf("<JInterfaceDeclaration line=\"%d\" name=\"%s\""
                + " super=\"%s\">\n", line(), name, superType.toString());
        p.indentRight();
        if (context != null) {
            context.writeToStdOut(p);
        }
        if (mods != null) {
            p.println("<Modifiers>");
            p.indentRight();
            for (String mod : mods) {
                p.printf("<Modifier name=\"%s\"/>\n", mod);
            }
            p.indentLeft();
            p.println("</Modifiers>");
        }
        if (interfaceBlock != null) {
            p.println("<InterfaceBlock>");
            for (JMember member : interfaceBlock) {
                ((JAST) member).writeToStdOut(p);
            }
            p.println("</InterfaceBlock>");
        }
        p.indentLeft();
        p.println("</JInterfaceDeclaration>");
    }
}


