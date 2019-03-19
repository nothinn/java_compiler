import java.lang.Integer;
import java.lang.System;

public class ConditionalExpression {
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        System.out.println(x % 2 == 0 ? "even" : "odd");
        boolean y = true;
        boolean z = false;
        boolean test =false;
        test = y||z;
        test = y&&z;
    }
}
