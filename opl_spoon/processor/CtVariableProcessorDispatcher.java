package processor;

import java.util.ArrayList;
import java.util.Hashtable;

import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtVariable;

/**
 * Process all
 * 
 * @author Chaste Maxime, Berenguer Pierre-Philippe
 *
 */
public class CtVariableProcessorDispatcher extends
        AbstractProcessor<CtVariable<Object>> {

    /**
     * Will contain all our processors for the corresponding string key. The key
     * is the CtVariable type. For example : for "java.util.List" key, we
     * associated ListReplacer as value.
     */
    protected Hashtable<String, Replacer> processorsTable;

    /**
     * 
     */
    protected ArrayList<String> elements = new ArrayList<String>();

    @Override
    /**
     * First, set elements to be processed by adding them in an elements array.
     * Elements come from "spoon.reflect.declaration".
     * isToBeProcessed will authorize elements added.
     * Then instantiate our "replacers" and set them available for process method. 
     * @see spoon.reflect.declaration.CtElement
     */
    public void init() {
        // adding element will authorize it to be processed
        this.elements.add("CtFieldImpl");
        this.elements.add("CtLocalVariableImpl");

        // activate processor for replacement
        this.processorsTable = new Hashtable<String, Replacer>() {
            {
                put("java.util.List", new ListReplacer());
                put("java.util.ArrayList", new ArrayListReplacer());
                put("java.util.Map", new MapReplacer());
                put("java.util.Set", new SetReplacer());
                put("java.util.SortedMap", new SortedMapReplacer());
            }
        };

    }

    @Override
    /**
     * Authorize processing of element set in init().
     * @param candidate an element to parse
     * @return true if candidate is set in the Hashtable
     */
    public boolean isToBeProcessed(CtVariable<Object> candidate) {
        return elements.contains(candidate.getClass().getSimpleName());
    }

    @Override
    public void process(CtVariable<Object> element) {
        // type of the element encountered
        String type = element.getType().getQualifiedName();

        // get the processor corresponding to the type of the input element
        Replacer replacer = this.processorsTable.get(type);

        if (replacer != null) { // when processor exists use it
            element.setDefaultExpression(getFactory().Code()
                    .createCodeSnippetExpression(
                            this.processorsTable.get(type).replace(element)));
        }
    }

}
