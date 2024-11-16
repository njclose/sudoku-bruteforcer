public class Node {
    private boolean searchable = false;
    private int value = 0;
    private int[] checkedValues = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    

    public void addCheckedValue(int newValue){
        int i = 0;
        while (checkedValues[i] != 0){
            i++;
        }
        checkedValues[i] = newValue;
    }
    public int[] getCheckedValues(){
        return checkedValues;
    }

    // Methods regarding the searchability of the node
    public boolean isSearchable(){
        return searchable == true;
    }
    public void toggleSearchable(){
        searchable = searchable != true;
    }

    // Methods regrading the value of the node
    public int getValue(){
        return value;
    } 
    public void editValue(int newValue){
        value = newValue;
    }
}
