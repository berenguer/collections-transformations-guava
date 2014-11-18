package processor;
import java.util.ArrayList;
import java.util.List;

import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtCodeSnippetStatement;
import spoon.reflect.code.CtStatement;
import spoon.support.reflect.declaration.CtMethodImpl;
/**
 * Process List object.
 * 
 * @author Chaste , Berenguer
 *
 */
public class PatternProcessor
  extends AbstractProcessor<CtMethodImpl<Object>>
{
  public void process(CtMethodImpl<Object> methode)
  {
    String stat = null;
    CtBlock<Object> body = methode.getBody();
    List<CtStatement> statement = body.getStatements();
    ArrayList<Integer> listNewArray = new ArrayList<Integer>();
    
    boolean finish = false;
    for (int i = 0; i < statement.size(); i++)
    {
      stat = ((CtStatement)statement.get(i)).toString();
      if (stat.contains("new java.util.ArrayList")) {
        listNewArray.add(Integer.valueOf(i));
      }
    }
    for (int j = 0; j < listNewArray.size(); j++)
    {
      int start = 0;
      
      start = ((Integer)listNewArray.get(j)).intValue() + 1;
      if (start < statement.size())
      {
        String name = ((CtStatement)statement.get(start - 1)).getSignature();
        int end = start;
        while ((end < statement.size()) && (!finish))
        {
          System.out.println("on test avec " + ((CtStatement)statement.get(end)).toString());
          if (((CtStatement)statement.get(end)).toString().contains(name + ".add"))
          {
            end++;
          }
          else
          {
            end--;
            finish = true;
          }
        }
        System.out.println(name);
        System.out.println(end - start);
        if (start != end)
        {
          String result = "java.util.ArrayList<String> " + name + "= com.google.common.collect.Lists.newArrayList(";
          
          int startRecup = start;
          while (startRecup < end)
          {
            String temp = ((CtStatement)statement.get(startRecup)).toString().substring(((CtStatement)statement.get(startRecup)).toString().indexOf("(") + 1, ((CtStatement)statement.get(startRecup)).toString().indexOf(")"));
            result = result + temp + ",";
            startRecup++;
          }
          result = result + ((CtStatement)statement.get(startRecup)).toString().substring(((CtStatement)statement.get(end)).toString().indexOf("(") + 1, ((CtStatement)statement.get(end)).toString().indexOf(")"));
          result = result + ")";
          
          int startDelete = start - 1;
          for (int k = startDelete; k <= end; k++) {
            statement.remove(startDelete);
          }
          CtCodeSnippetStatement newStat = getFactory().Code().createCodeSnippetStatement(result);
          statement.add(start - 1, newStat);
          if (j + 1 < listNewArray.size())
          {
            int newPosition = ((Integer)listNewArray.get(j + 1)).intValue() - (end - startDelete);
            listNewArray.set(j + 1, Integer.valueOf(newPosition));
          }
          finish = false;
        }
      }
    }
    methode.getBody().setStatements(statement);
  }
}

