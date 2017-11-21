package net.louis.algs;

import net.louis.collection.AStack;
import net.louis.collection.Queue;
import net.louis.collection.linked.LinkedQueue;
import net.louis.collection.linked.LinkedStack;

import java.util.Arrays;

public class DoubleStackEvaluate {

    private AStack<Double> valStack  =new LinkedStack<Double> ();
    private AStack<String> opsStack = new LinkedStack<>();
    private Queue<String> postFixResult = new LinkedQueue<>();
        public void calc(String expr[])
    {

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
                if (ops.equals("+")) mv = valStack.pop() + mv;
                else if (ops.equals("-")) mv = valStack.pop()-mv;
                else if (ops.equals("*")) mv = valStack.pop()*mv;
                else if (ops.equals("/")) mv = valStack.pop()/mv;
                valStack.push(mv);
            }
            else
                valStack.push(Double.valueOf(eItem));
        }

        System.out.println(valStack.pop());
    }



    public static void main(String args[])
    {
        String expr[] = new String[]{"(","1","+","2",")"};
        DoubleStackEvaluate doubleStackEvaluate = new DoubleStackEvaluate();
        doubleStackEvaluate.calc(expr);
    }
}
