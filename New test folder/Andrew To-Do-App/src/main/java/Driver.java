import Structures.ToDoItem;
import Utils.PrintOut;

import java.util.LinkedList;
import java.util.List;

public class Driver {
    public static void main(String[] args){

        //Creates a list instance to hold toDoItems
        List<ToDoItem> toDoItemList = new LinkedList<>();

        //Creates 3 new toDoItems
        ToDoItem toDoItem1 = new ToDoItem();
        ToDoItem toDoItem2 = new ToDoItem();
        ToDoItem toDoItem3 = new ToDoItem();

        //Adds our items to the list
        toDoItemList.add(toDoItem1);
        toDoItemList.add(toDoItem2);
        toDoItemList.add(toDoItem3);

        //Sets the text in items 1 and 2, sets item 3 to true with no text
        toDoItem1.setToDoText("item 1");
        toDoItem2.setToDoText("item 2-complete? no!");
        toDoItem3.setComplete(true);

//        Prints out the items to the console
//        PrintOut.printItem(toDoItem1);
//        PrintOut.printItem(toDoItem2);
//        PrintOut.printItem(toDoItem3);

        //Prints out the full list to the console
        PrintOut.printList((LinkedList<ToDoItem>) toDoItemList);
    }
}
