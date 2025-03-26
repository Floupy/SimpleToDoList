package todolist.logic;


import todolist.domain.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ToDoList {
    private List<Event> eventList;
    private List<Event> completedEventList;
    private List<Event> incompleteEventList;
    private List<String> eventCategories;


    public ToDoList() {
        this.eventList = new ArrayList<>();
        this.completedEventList = new ArrayList<>();
        this.incompleteEventList = new ArrayList<>();
        this.eventCategories = new ArrayList<>();
    }

    public void addEvent(Event eventToAdd) {
        String eventCategory = sanitizeString(eventToAdd.getCategory());
        eventList.add(eventToAdd);
        incompleteEventList.add(eventToAdd);

        if(!eventCategories.contains(eventCategory)) {
            eventCategories.add(eventCategory);
        }
    }

    public void deleteEvent(Event eventToDelete) {
        if(eventList.contains(eventToDelete)) {
            eventList.remove(eventToDelete);
        }
        if(incompleteEventList.contains(eventToDelete)) {
            incompleteEventList.remove(eventToDelete);
        }
        if(completedEventList.contains(eventToDelete)) {
            completedEventList.remove(eventToDelete);
        }
        if(!moreThanOneWithCategory(eventToDelete.getCategory())) {
            deleteCategory(eventToDelete.getCategory());
        }
    }

    public void completeEvent(Event event) {
        event.completeEvent();
        if(!completedEventList.contains(event)) {
            completedEventList.add(event);
            incompleteEventList.remove(event);
        } else {
            System.out.println("Event already completed");
        }
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Event> getCompletedEventList() {
        return completedEventList;
    }

    public List<Event> getIncompleteEventList() {
        return incompleteEventList;
    }

    public List<String> getEventCategories() {
        return eventCategories;
    }

    public void printAllEvents() {
        if(eventList.isEmpty()) {
            System.out.println("\nThere are no events.");
            return;
        }

        int index = 1;

        System.out.println("\nAdded events:");
        for(Event event: eventList) {
            System.out.print(index + ") ");
            System.out.println(event);
            index++;
        }
    }

    public void printIncompleteEvents() {
        if(incompleteEventList.isEmpty()) {
            System.out.println("There are no incomplete events.");
            return;
        }

        int index = 1;
        System.out.println("Incomplete events:");
        for(Event event: incompleteEventList) {
            System.out.print(index + ")");
            System.out.println(event);
            index++;
        }
    }

    public void printCompletedEvents() {
        if(completedEventList.isEmpty()) {
            System.out.println("\nThere are no completed events.");
            return;
        }

        int index = 1;

        System.out.println("\nCompleted events:");
        for(Event completedEvent: completedEventList) {
            System.out.print(index + ") ");
            System.out.println(completedEvent);
            index++;
        }
    }

    public void printCategories() {
        if(eventCategories.isEmpty()) {
            System.out.println("There are no event categories.");
            System.out.println("Consider adding an event with a category.");
            return;
        }
        int index = 1;

        for(String category: eventCategories) {
            System.out.println(index + ") " + category);
            index++;
        }
    }

    public void filterEventsByCategory(String category) {
        System.out.println("\nEvents in \"" + category + "\" category: \n");
        eventList.stream().filter(event -> event.getCategory().equals(category)).forEach(System.out::println);
    }

    //#TODO: potentially implement in the database version of this app
//    public void editEvent(Event eventToEdit, String newTitle, String newCategory, String newDescription) {
//        eventToEdit.setTitle(newTitle.trim());
//        eventToEdit.setCategory(sanitizeString(newCategory));
//        eventToEdit.setDescription(newDescription);
//    }


    private boolean eventWithCategoryExists(String category) {
        return eventList.stream().anyMatch(event -> event.getCategory().equals(category));
    }

    public boolean moreThanOneWithCategory(String category) {
        int count = 0;

        for(Event event: eventList) {
            if (event.getCategory().equals(category)) {
                count++;
            }
        }

        return count > 1;
    }

    public boolean incompleteEventExists() {
        return !incompleteEventList.isEmpty();
    }

    private void deleteCategory(String category) {
        this.eventCategories.remove(sanitizeString(category));
    }

    private static String sanitizeString(String stringToSanitize) {
        if(stringToSanitize == null) {
            return "";
        }

        return stringToSanitize.toLowerCase().trim();
    }


}
