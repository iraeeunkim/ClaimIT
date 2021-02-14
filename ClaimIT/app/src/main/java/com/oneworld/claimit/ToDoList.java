package com.oneworld.claimit;

public class ToDoList {
    private final String toDoItem;
    private final int points;
    private boolean isChecked = false;

    public ToDoList(String toDoItem) {
        this(toDoItem, 1);
    }
    public ToDoList(String toDoItem, int points) {
        this.toDoItem = toDoItem;
        this.points = points;
    }

    public String getToDoItem() {
        return toDoItem;
    }

    public int getPoints() {
        return points;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
