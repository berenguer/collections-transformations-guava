package processor;


import spoon.reflect.declaration.CtVariable;

/**
 * to process arrayList object
 * 
 * @author Chaste , Berenguer
 *
 */
public class ArrayListReplacer implements Replacer {

    public ArrayListReplacer() {
        // empty
    }

    @Override
    public String replace(CtVariable<Object> element) {
        String expression = element.getDefaultExpression().toString().trim();
        return processNewArrayList(expression);

    }

    /**
     * Create a new String for change the CtExpression
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processNewArrayList(String expression) {
        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        // to check if we have a size for the list
        int result = end - start;
        if (expression.contains("new java.util.ArrayList") && result != 1) {
            String size = expression.substring(start + 1, end);
            return "com.google.common.collect.Lists.newArrayListWithCapacity("
                    + size + ")";
        } else {
            return "com.google.common.collect.Lists.newArrayList()";
        }

    }

}
