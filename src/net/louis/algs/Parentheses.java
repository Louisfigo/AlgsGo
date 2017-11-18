package net.louis.algs;

import net.louis.collection.AStack;
import net.louis.collection.linked.LinkedStack;

public class Parentheses {

    private final static String LEFT_SB = "(";
    private final static String RIGHT_SB = ")";
    private final static String LEFT_MB = "[";
    private final static String RIGHT_MB = "]";
    private final static String LEFT_LB = "{";
    private final static String RIGHT_LB = "}";

    private AStack<String> leftB = new LinkedStack<>();

    private boolean isInputBracLeft(String brac)
    {
        return (!"".equals(brac))&& (LEFT_LB.equals(brac) || LEFT_MB.equals(brac) || LEFT_SB.equals(brac));
    }

    private boolean isInputBracRight(String brac)
    {
        return (!"".equals(brac))&& (RIGHT_LB.equals(brac) || RIGHT_MB.equals(brac) || RIGHT_SB.equals(brac));
    }


    public boolean isBracPairValid(String lb,String rb)
    {
        return (lb.equals(LEFT_LB) && rb.equals(RIGHT_LB))
                ||(lb.equals(LEFT_MB) && rb.equals(RIGHT_MB))
                ||(lb.equals(LEFT_SB) && rb.equals(RIGHT_SB));

    }

    public   boolean parentValidate(String[] expr)
    {
        for(String s:expr)
        {
            if(isInputBracLeft(s))
                leftB.push(s);
            else if(isInputBracRight(s))
            {

                if(isBracPairValid(leftB.peek(),s))
                    leftB.pop();

            }

        }

        return leftB.isEmpty();
    }

    public static void main(String args[])
    {
        Parentheses parentheses = new Parentheses();
        System.out.println(parentheses.parentValidate(new String[]{"[","(",")","(","{","}"}));
    }
}
