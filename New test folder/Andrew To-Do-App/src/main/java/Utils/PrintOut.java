package Utils;

import Structures.ToDoItem;

//Defined a class that prints strings to the console
public class PrintOut {
    public void printOutString(String str) {
        System.out.println(str);
    }
    //Overwrite our method when it takes an instance of the ToDoItem class
    public static void printItem(ToDoItem toDoItem){
        System.out.print("[");
        if (toDoItem.isComplete()){
            System.out.print("*");
        }else{
            System.out.print(" ");
        }
        System.out.print("]");
        System.out.println(toDoItem.getToDoText());
    }
}
