package input;


public class CollectionObject {
    java.util.ArrayList<java.lang.String> arr = new java.util.ArrayList<java.lang.String>();

    java.util.List<java.lang.String> unmodifiableList = java.util.Collections.unmodifiableList(arr);

    java.util.Map<java.lang.String, java.lang.Integer> chiensMap = new java.util.HashMap<java.lang.String, java.lang.Integer>();

    java.util.List<java.lang.String> unmodifiableListG = com.google.common.collect.ImmutableList.copyOf(arr);

    public CollectionObject() {
    }

    public java.util.ArrayList<java.lang.String> returnArrStr(java.util.ArrayList<java.lang.String> inArr) {
        java.util.ArrayList<java.lang.String> outArr = new java.util.ArrayList<java.lang.String>();
        outArr.add("un");
        outArr.add("deux");
        outArr.add("trois");
        return outArr;
    }
}

