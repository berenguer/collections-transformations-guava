package processor;
import spoon.reflect.declaration.CtVariable;

/**
 * to process SortedMap object
 * 
 * @author Chaste , Berenguer
 *
 */
public class SortedMapReplacer implements Replacer {

    public SortedMapReplacer() {
        // empty
    }

    /**
     * process for the type "java.util.SortedMap"
     * 
     * @param element
     */
    public String replace(CtVariable<Object> element) {
        String expression = element.getDefaultExpression().toString().trim();
        System.out.println(expression);
        if (expression.startsWith("new java.util")) {
            int subChaine = expression.indexOf("<");
            String sub = expression.substring(0, subChaine);
            System.out.println("------------------ICI-------------" + sub);
            int lastPoint = sub.lastIndexOf(".");
            String type = sub.substring(lastPoint + 1);
            return processNewSortedMap(expression, type);

        }
        if (expression.startsWith("java.util.SortedMap unmodifiableSortedMap")) {

            return processImmutableSortedMap(expression);
        }
        return expression;

    }

    /**
     * create a new String for change the CtExpression in the case where we
     * create an SortedMap
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processNewSortedMap(String expression, String type) {
        return "com.google.common.collect.Maps.new" + type + "()";
    }

    /**
     * create a new String for change the CtExpression in the case where we
     * create an Immutable SortedMap
     * 
     * @param expression
     *            the CtExpression which need to changes
     * @return a String which will be our new CtExpression
     */
    public String processImmutableSortedMap(String expression) {

        int start = expression.indexOf("(");
        int end = expression.indexOf(")");
        String arg = expression.substring(start + 1, end);
        return "com.google.common.collect.ImmutableSortedMap.copyOf(" + arg
                + ")";
    }

}
