package Structures;

//Define class
public class ToDoItem {
    private String toDoText;
    private boolean complete;

//Define no arg method call
    public void toDoItem(){complete = false;}

//Define method call with String argument
    public void toDoItem(String toDoText){
        complete = false;
        this.toDoText = toDoText;
    }

    //Auto-generate getters and setters for Item Text (toDoText)
    public String getToDoText() {
        return toDoText;
    }
    public void setToDoText(String toDoText) {
        this.toDoText = toDoText;
    }

    //Auto-generate getters and setters for whether item is complete (complete)
    public boolean isComplete() {
        return complete;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
