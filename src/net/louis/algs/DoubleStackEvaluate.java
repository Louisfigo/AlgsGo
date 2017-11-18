package net.louis.algs;

import net.louis.collection.AStack;

import java.util.Arrays;

public class DoubleStackEvaluate {

    private AStack<Double> valStack;
    private AStack<String> opsStack;
        public void calc(String expr[])
    {

        System.out.printf(Arrays.toString(expr));
        for(String e:expr)
        {
            String eItem = e.trim();

            if(eItem.equals("(") );
            else if (eItem.equals("+")) opsStack.push(eItem);
            else if (eItem.equals("-")) opsStack.push(eItem);
            else if (eItem.equals("*")) opsStack.push(eItem);
            else if (eItem.equals("/")) opsStack.push(eItem);
            else if (eItem.equals(")"))
            {
                String ops = opsStack.pop();
                double mv = valStack.pop();
                if (eItem.equals("+")) mv = valStack.pop() + mv;
                else if (eItem.equals("-")) mv = valStack.pop()-mv;
                else if (eItem.equals("*")) mv = valStack.pop()*mv;
                else if (eItem.equals("/")) mv = valStack.pop()/mv;
                valStack.push(mv);
            }
            else
                valStack.push(Double.valueOf(eItem));
        }

        System.out.println(valStack.pop());
    }

    public static void main(String args[])
    {
        String expr = "1 + ( 2 * 3 ) + 4";
        DoubleStackEvaluate doubleStackEvaluate = new DoubleStackEvaluate();
        doubleStackEvaluate.calc(expr.split(" "));
    }
}
