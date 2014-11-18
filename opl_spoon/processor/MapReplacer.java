package processor;

import spoon.reflect.declaration.CtVariable;

/**
 * to process Map object
 * 
 * @author Chaste , Berenguer
 *
 */
public class MapReplacer implements Replacer {

    public MapReplacer() {
        // empty
    }

    /**
     * Replace type "java.util.Map".
     * 
     * @param element
     */
    public String replace(CtVariable<Object> element) {
        String expression = element.getDefaultExpression().toString().trim();
        if (expression.startsWith("new java.util.HashMap")) {
            return processNewHashMap(expression);

        }
        if (expression.startsWith("java.util.Collections.unmodifiableMap")) {

            return processImmutableMap(expression);
        }
        return expression;
    }

    /**
     * Create a new String for change the CtExpression in the case where we
     * create an HashMap
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processNewHashMap(String expression) {
        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        // to check if we have a size for the list
        int result = end - start;
        if (result == 1) {
            // there is no argument
            return "com.google.common.collect.Maps.newHashMap()";
        } else {
            // there is argument
            String size = expression.substring(start + 1, end);
            return "com.google.common.collect.Maps.newHashMapWithExpectedSize("
                    + size + ")";
        }
    }

    /**
     * create a new String for change the CtExpression in the case where we
     * create an immutable HashMap
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processImmutableMap(String expression) {

        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        String arg = expression.substring(start + 1, end);
        return "com.google.common.collect.ImmutableMap.copyOf(" + arg + ")";
    }

}
