package processor;
import spoon.reflect.declaration.CtVariable;

/**
 * to process Set object
 * 
 * @author Chaste , Berenguer
 *
 */
public class SetReplacer implements Replacer {

    public SetReplacer() {
        // empty
    }

    /**
     * process for the type "java.util.Set"
     * 
     * @param element
     */
    public String replace(CtVariable<Object> element) {
        String expression = element.getDefaultExpression().toString().trim();
        System.out.println(expression);
        if (expression.startsWith("new java.util.HashSet")) {
            return processNewHashSet(expression);

        }
        if (expression.startsWith("java.util.Collections.unmodifiableSet")) {

            return processImmutableSet(expression);
        }
        return expression;

    }

    /**
     * create a new String for change the CtExpression in the case where we
     * create an HashSet
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processNewHashSet(String expression) {
        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        // to check if we have a size for the list
        int result = end - start;
        if (result != 1) {
            String size = expression.substring(start + 1, end);
            return "com.google.common.collect.Sets.newHashSetWithExpectedSize("
                    + size + ")";
        } else {
            return "com.google.common.collect.Sets.newHashSet()";
        }

    }

    /**
     * create a new String for change the CtExpression in the case where we
     * create an immutable HashSet
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processImmutableSet(String expression) {

        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        String arg = expression.substring(start + 1, end);
        return "com.google.common.collect.ImmutableSet.copyOf(" + arg + ")";
    }

}
