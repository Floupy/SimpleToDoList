//#TODO: Implement exception handling where needed and error handling

package todolist.ui;

import todolist.domain.Event;
import todolist.logic.ToDoList;

import java.util.Scanner;

public class UserInterface {
    private Scanner inputReader;
    private ToDoList toDoList;

    public UserInterface(Scanner scanner, ToDoList toDoList) {
        this.inputReader = scanner;
        this.toDoList = toDoList;
    }

    public void start() {
        printCommands();
    }

    public void printCommands() {

        while(true) {
            System.out.println("=================================");
            System.out.println("||       Your To-Do List       ||");
            System.out.println("||                             ||");
            System.out.println("|| 1)Add event to list.        ||");
            System.out.println("|| 2)Delete event from list.   ||");
            System.out.println("|| 3)Complete event            ||");
            System.out.println("|| 4)Show all events           ||");
            System.out.println("|| 5)Show all completed events ||");
            System.out.println("|| 6)Show all incomplete events||");
            System.out.println("|| 7)Show by category          ||");
            System.out.println("|| 8)Quit                      ||" );
            System.out.println("=================================");
            System.out.println();
            System.out.println("What would you like to do?(number)");
            System.out.print("> ");
            int command = -1;
            try {
                command = Integer.parseInt(inputReader.nextLine());
            } catch (NumberFormatException numEx) {
                System.out.println("Invalid input! Please enter a number!");
                continue;
            }

            processComand(command);
            System.out.println();
        }
    }

    public void processComand(int command) {
        if(command == 1) {
            addEvent();
        } else if(command == 2) {
            deleteEvent();
        } else if(command == 3) {
            completeEvent();
        } else if(command == 4) {
            printAllEvents();
        } else if(command == 5) {
            printAllCompletedEvents();
        } else if(command == 6){
            printIncompleteEvents();
        } else if(command == 7){
            showByCategory();
        } else if(command == 8) {
            quitProgram();
        }else {
            System.out.println("Invalid command!");
        }
    }

    public void addEvent() {
        System.out.println("Enter event details to add");
        System.out.print("Title: ");
        String eventTitle = inputReader.nextLine();
        while (eventTitle.isEmpty()) {
            System.out.println("Title can't be blank!");
            System.out.print("Title: ");
            eventTitle = inputReader.nextLine();
        }

        System.out.print("Category(optional): ");
        String category = sanitizeString(inputReader.nextLine());
        if(category.isEmpty()) {
            category = "none";
        }

        System.out.print("Description(optional): ");
        String description = inputReader.nextLine().trim();
        if(description.isEmpty()) {
            description = "...";
        }

        Event eventToAdd = new Event(eventTitle, category, description);
        toDoList.addEvent(eventToAdd);
    }

    public void deleteEvent() {
        if(toDoList.getEventList().isEmpty()) {
            System.out.println("There are no events.");
            return;
        }
        while(true) {
            printAllEvents();
            System.out.print("Choose event to delete(number)(0 to exit): ");
            int deletedEvent = -1;
            try {
                deletedEvent = Integer.parseInt(inputReader.nextLine());
            } catch (NumberFormatException numFormatEx) {
                System.out.println("Input is not a number! Please enter a number!");
                continue;
            }
            if(deletedEvent == 0) {
                return;
            }

            if(deletedEvent < 1 || deletedEvent > toDoList.getEventList().size()) {
                System.out.println("Invalid event number! Enter a valid event number!");
                continue;
            }
            Event eventToDelete = toDoList.getEventList().get(deletedEvent - 1);
            toDoList.deleteEvent(eventToDelete);
            System.out.println("Event \"" + eventToDelete.getTitle() + "\" successfully deleted");
            break;
        }
    }

    public void completeEvent() {
        if(!toDoList.incompleteEventExists()) {
            System.out.println("There are no incomplete events.");
            return;
        }
        printIncompleteEvents();
        System.out.println("Which event would you like to complete?(number):");
        while (true) {
            int eventNumber;
            try{
                eventNumber = Integer.valueOf(inputReader.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number");
                continue;
            }
            if(eventNumber < 1 || eventNumber > toDoList.getIncompleteEventList().size()) {
                System.out.println("Invalid event number! Enter a valid event number");
                continue;
            }
            //Event eventToComplete = toDoList.getEventList().get(toDoList.getEventList().size() - (eventNumber - 1));
            Event eventToComplete = toDoList.getIncompleteEventList().get(eventNumber - 1);
            System.out.println("Event \"" + eventToComplete.getTitle() + "\" has been completed!");
            toDoList.completeEvent(eventToComplete);
            break;
        }

    }
    public void printAllEvents() {
        toDoList.printAllEvents();
    }

    public void printAllCompletedEvents() {
        toDoList.printCompletedEvents();
    }

    public void printIncompleteEvents() {
        toDoList.printIncompleteEvents();
    }

    public void showByCategory() {
        if(toDoList.getEventCategories().isEmpty()) {
            System.out.println("There are no events.");
            return;
        }

        while(true) {
            System.out.println("\nAvailable categories:");
            toDoList.printCategories();
            System.out.print("\nWhich categories events would you like to see?(0 to exit)");
            System.out.print("> ");
            int categoryNum;

            try {
                categoryNum = Integer.valueOf(inputReader.nextLine());
            } catch (NumberFormatException numFormEx) {
                System.out.println("Invalid input! Please enter a number");
                continue;
            }

            if (categoryNum == 0) {
                break;
            }

            if(categoryNum < 0 || categoryNum > toDoList.getEventCategories().size()) {
                System.out.println("Invalid category number! Please enter a valid number");
                continue;
            }

            toDoList.filterEventsByCategory(toDoList.getEventCategories().get(categoryNum - 1));
        }
    }

    public void quitProgram() {
        System.out.println("Goodbye!");
        System.exit(0);
    }

    private static String sanitizeString(String stringToSanitize) {
        if(stringToSanitize == null) {
            return "";
        }

        return stringToSanitize.toLowerCase().trim();
    }


}
