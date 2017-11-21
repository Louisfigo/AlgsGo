package net.louis.algs;

import net.louis.collection.AStack;
import net.louis.collection.linked.LinkedStack;

import java.util.Arrays;

public class DoubleStackAddLeftBrackets {

    private AStack<String> valStack  =new LinkedStack<String>();
    private AStack<String> opsStack = new LinkedStack<>();
    private AStack<String> finalResult = new LinkedStack<>();

    private String[] opsTable = new String[]{"+","-","*","/"};
    private String[] brackets = new String[]{"(",")"};

    private final static String LEFT_SB = "(";
    private final static String RIGHT_SB = ")";

    private boolean isInputBracLeft(String brac)
    {
        return (!"".equals(brac))&& (LEFT_SB.equals(brac));
    }

    private boolean isInputBracRight(String brac)
    {
        return (!"".equals(brac))&& ( RIGHT_SB.equals(brac));
    }

    private boolean isBrac(String e)
    {
        return Arrays.asList(brackets).contains(e);

    }

    private boolean isOps(String ops)
    {
        return  Arrays.asList(opsTable).contains(ops);
    }

    private boolean isVal(String v)
    {
        return  (!isBrac(v))&&(!isOps(v));
    }

    public void addBrac(String expr[])
    {
        for(String e:expr)
        {
            if(isVal(e))
            {
                valStack.push(e);
            }
            else if(isOps(e))
            {
                opsStack.push(e);
            }
            else if(isInputBracRight(e))
            {
                if(!valStack.isEmpty())
                {
                    String backVal  = valStack.pop();
                    String tempResult = LEFT_SB + valStack.pop() + opsStack.pop() + backVal + RIGHT_SB;
                    finalResult.push(tempResult);
                }
                else
                {
                    String backexpr = finalResult.pop();
                    String tmpResult = LEFT_SB + finalResult.pop() + opsStack.pop() + backexpr + RIGHT_SB;
                    finalResult.push(tmpResult);
                }
            }
        }

    }

    public  String getResult()
    {
        return  finalResult.pop();
    }

    public static void main(String args[])
    {
        DoubleStackAddLeftBrackets doubleStackAddLeftBrackets = new DoubleStackAddLeftBrackets();
        doubleStackAddLeftBrackets.addBrac(new String[]{"1" ,"+" ,"2" , ")","*","3","-","4",")","*","5","-","6",")",")",")"});
        System.out.println(doubleStackAddLeftBrackets.getResult());
    }

}
