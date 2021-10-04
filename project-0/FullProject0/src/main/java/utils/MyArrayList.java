package utils;

/**
 * The MyArrayList class overrides the abstract methods from the
 * MyArrayListInterface interface. It also contains a field, which is
 * an array of Object items.
 */
public class MyArrayList<E> implements MyArrayListInterface<E> {
    private Object[] arrayList;

    //Boilerplate getter, setter, and constructor.
    public Object[] getArrayList() {
        return arrayList;
    }
    public void setArrayList(Object[] arrayList) {
        this.arrayList = arrayList;
    }
    public MyArrayList() {
        this.arrayList = new Object[10];
    }

    /**
     * The size method returns an int, the length of the underlying array.
     */
    @Override
    public int size() {
        return arrayList.length;
    }

    /**
     * The add method adds an Object parameter to the underlying array. It
     * also re-sizes the underlying array.
     */
    @Override
    public void add(E ele) {
        //get the underlying array
        Object[] arr = getArrayList();

        //instantiate a new, longer array
        Object[] newArray = new Object[(arr.length)+2];

        //assign the Objects in our current array to the new array at the same index
        int i = 0;
        while(i<arr.length-1){
            newArray[i]=arr[i];
            i++;
        }
        //assign the added Object to our new array at the next index
        newArray[i+1]=ele;

        //set our new array as the underlying array, replacing the old array
        setArrayList(newArray);
    }

    /**
     * The overloaded add method takes 2 parameters, an Object and an int. It adds the
     * Object to the underlying array at the specified index. It also re-sizes the underlying
     * array.
     */
    @Override
    public void add (E ele,int index){

        //get the underlying array
        Object[] arrayList = getArrayList();

        if(index>=arrayList.length){

            //if the index is outside the underlying array
            Object[] newList = new Object[index+1];

            // for loop that assigns each object from the old array
            // to the same index in the new array
            for(int i=0;i< arrayList.length-1;i++){
                newList[i] = arrayList[i];
            }
            newList[index] = ele;
        }
        //if the index is inside the underlying array
        else{
            Object[] newList = new Object[arrayList.length+2];

            // for loop that assigns all the Objects in the underlying array with index < "index"
            // to the new array
            for(int i=0;i<index;i++){
                newList[i]=arrayList[i];
            }
            //assigns the object to the new array at index, "index"
            newList[index] = ele;

            // for loop that assigns all the Objects in the underlying array with index > "index"
            // to the new array
            for(int i=index;i< newList.length-2;i++){
                newList[i+1]=arrayList[i];
            }
        }
    }

    /**
     * The get method takes an int "index" as an argument, and returns
     * the Object in the underlying array at that index.
     */
    @Override
    public E get (int index){

        //gets the underlying array
        Object[] arr = getArrayList();

        //returns the Object at index "index"
        return (E) arr[index];
    }

    /**
     * The remove method takes an int "index" as an argument, and removes
     * the Object in the underlying array at that index.
     */
    @Override
    public void remove (int index){

        //gets the underlying array
        Object[] arr = getArrayList();

        //instantiates a new Object array
        Object[] newArray = new Object[arrayList.length];

        //while loop assigns the Objects in the underlying array to the new array
        int i = 0;
        while(i<index){
            newArray[i]=arr[i];
            i++;
        }

        //move past the removed Object
        i+=2;

        //while loop that assigns Objects from the underlying array to the new array
        while(i>index){
            newArray[i-1]=arr[i];
            i++;
        }
    }

    /**
     * The clear method uses the logic of the "remove" method and removes each
     * Object of the underlying array.
     */
    @Override
    public void clear () {

        //get the underlying array
        Object[] arr = getArrayList();

        //loop through the array, and call the remove method at each index
        for(int i=0;i<arr.length-1;i++){
            remove(i);
        }
    }

    /**
     * The find method iterates through the underlying array and checks if any Object at
     * each index of the underlying array is equal to the parameter "ele". If there is an
     * Object that is equal to the argument, it returns the index of the equal Object. Otherwise,
     * it returns -1.
     */
    @Override
    public int find (E ele){

        //get the underlying array
        Object[] arr = getArrayList();

        // while loop that compares the Object parameter "ele" to each Object in the
        // underlying array
        int i =0;
        while(i<arr.length-1){
            if(ele.equals(arr[i])){

                //if the Objects are equal, return the index
                return i;
            }else{
                i++;
            }
        }
        //otherwise, return -1
        return -1;
    }

    /**
     * The contains method uses the logic in the "find" method to return a boolean.
     */
    @Override
    public boolean contains(E ele){
        //use the find method and return 'false' if find(ele) = -1.
        if(find(ele)<0){
            return false;
        }else{
            return true;
        }
    }
}