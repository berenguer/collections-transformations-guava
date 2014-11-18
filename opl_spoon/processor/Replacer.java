package processor;

import spoon.reflect.declaration.CtVariable;
/**
 * Migrator of JDK Collection to corresponding Guava.
 * @author chaste
 *
 */
public interface Replacer {
    
    /**
     * Transform an object from Collection in standard JDK by the appropriate one in Guava.
     * CtVariable correspond to a method declaration.
     * @param element object from Collection in JDK
     * @return declaration in guava
     */
    public String replace(CtVariable<Object> element);

}
