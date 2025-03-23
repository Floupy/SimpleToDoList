package todolist.logic;


import todolist.domain.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoList {
    private List<Event> eventList;
    private List<Event> completedEventList;
    private List<Event> incompleteEventList;
    private List<String> eventCategories;
//    private Map<String, List<Event>> eventsByCategory; // category -- events #TODO: implement somehow


    public ToDoList() {
        this.eventList = new ArrayList<>();
        this.completedEventList = new ArrayList<>();
        this.incompleteEventList = new ArrayList<>();
        this.eventCategories = new ArrayList<>();
//        this.eventsByCategory = new HashMap<>();
    }

    public void addEvent(Event eventToAdd) {
        String eventCategory = sanitizeString(eventToAdd.getCategory());
        eventList.add(eventToAdd);
        incompleteEventList.add(eventToAdd);

        if(!eventCategories.contains(eventCategory)) {
            eventCategories.add(eventCategory);
        }

//        if(eventsByCategory.containsKey(eventCategory)) {
//            eventsByCategory
//        }
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
            System.out.println(index + ") ");
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

    public boolean containsEvent(Event event) {
        return false;
    }


    public boolean incompleteEventExists() {
        if(incompleteEventList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    private static String sanitizeString(String stringToSanitize) {
        if(stringToSanitize == null) {
            return "";
        }

        return stringToSanitize.toLowerCase().trim();
    }


}
