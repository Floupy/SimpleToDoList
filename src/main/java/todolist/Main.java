package todolist;

import todolist.ui.UserInterface;
import todolist.logic.ToDoList;
import todolist.domain.Event;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        ToDoList toDoList = new ToDoList();
        UserInterface ui = new UserInterface(reader, toDoList);

        ui.start();
    }
}
