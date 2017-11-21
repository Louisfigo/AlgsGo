package net.louis.algs;

import net.louis.collection.AStack;
import net.louis.collection.linked.LinkedStack;

public class InfixToPostFix {

    private AStack<String> ops = new LinkedStack<>();
    private String convert(String expr[]){

        String postFixExpr = "";

        for(String e:expr)
        {
            if(e.equals("+"))
                ops.push(e);
            else if(e.equals("*"))
                ops.push(e);
            else if (e.equals(")"))
                postFixExpr= postFixExpr + ops.pop() + " ";
            else if(e.equals("("));
            else
                postFixExpr = postFixExpr + " "+e;

        }

        while(!ops.isEmpty())
            postFixExpr = postFixExpr + " " + ops.pop();

        return  postFixExpr;
    }

    public static void  main(String args[])
    {
        InfixToPostFix infixToPostFix = new InfixToPostFix();
        System.out.printf(infixToPostFix.convert(new String[]{"(","1","+","2",")","*","3"}));
    }


}
