package input;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableList;



public class CollectionObject {
	
	ArrayList<String> arr = new ArrayList<String>();
	List<String> unmodifiableList = Collections.unmodifiableList(arr);
	Map<String, Integer> chiensMap = new HashMap<String, Integer>();
	List<String> unmodifiableListG = ImmutableList.copyOf(arr);
	
	public CollectionObject() {
		// nothing
	}
	
	public ArrayList<String> returnArrStr(ArrayList<String> inArr) {
		ArrayList<String> outArr = new ArrayList<String>();
		outArr.add("un");
		outArr.add("deux");
		outArr.add("trois");
		
		return outArr;
	}
	
	

}