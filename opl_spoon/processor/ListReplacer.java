package processor;

import spoon.reflect.declaration.CtVariable;

/**
 * Process List object.
 * 
 * @author Chaste , Berenguer
 *
 */
public class ListReplacer implements Replacer {

    public ListReplacer() {
        // empty
    }

    @Override
    public String replace(CtVariable<Object> element) {
        String expression = element.getDefaultExpression().toString().trim();
        if (expression.contains("java.util.Collections.unmodifiableList")) {
            return replaceUnmodifiableList(expression);

        }
        return expression;
    }

    /**
     * Create a new String for changing the CtExpression.
     * 
     * @param expression
     *            The CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String replaceUnmodifiableList(String expression) {

        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        String arg = expression.substring(start + 1, end);
        String exp = "com.google.common.collect.ImmutableList.copyOf(" + arg
                + ")";
        return exp;

    }
}
