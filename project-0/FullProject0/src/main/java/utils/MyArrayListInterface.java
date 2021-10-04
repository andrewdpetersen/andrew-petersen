package utils;

/**
 * Boilerplate interface for MyArrayList class, with abstract methods to be overridden.
 */
public interface MyArrayListInterface<E> {

    //size()
    //returns (the highest index of our ArrayList)+1
    int size();

    //add(E ele)
    // adds an element to the end of our ArrayList
    void add(E ele);

    //addAtIndex(E ele, int index)
    //adds an element to our ArrayList and maps it to "index"
    //needs to move the rest of our elements to index+1
    void add(E ele, int index);

    //get(int index)
    // returns the element in our ArrayList at "index"
    E get(int index);

    //remove(int index)
    // removes the element of our list at "index"
    void remove(int index);

    //clear()
    //references our ArrayList to an empty ArrayList
    void clear();

    //find(E ele)
    //returns the "index" for the given element of our ArrayList
    int find(E ele);

    //contains(E ele)
    //returns a boolean for whether the given element is in the list
    boolean contains(E ele);
}
