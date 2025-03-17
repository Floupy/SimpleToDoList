package todolist.logic;


import todolist.domain.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ToDoList {
    private List<Event> eventList;
    private List<Event> completedEvents;
    private List<String> eventCategories;
//    private Map<String, List<Event>> eventsByCategory; // category -- events #TODO: implement somehow


    public ToDoList() {
        this.eventList = new ArrayList<>();
        this.completedEvents = new ArrayList<>();
        this.eventCategories = new ArrayList<>();
//        this.eventsByCategory = new HashMap<>();
    }

    public void addEvent(Event eventToAdd) {
        String eventCategory = sanitizeString(eventToAdd.getCategory());
        this.eventList.add(eventToAdd);
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
        if(completedEvents.contains(eventToDelete)) {
            eventList.remove(eventToDelete);
        }
    }

    public void completeEvent(Event event) {
        event.completeEvent();
        if(!completedEvents.contains(event)) {
            completedEvents.add(event);
        } else {
            System.out.println("Event already completed");
        }
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public List<Event> getCompletedEvents() {
        return completedEvents;
    }

    public List<String> getEventCategories() {
        return eventCategories;
    }

    public void printAllEvents() {
        if(eventList.isEmpty()) {
            System.out.println("There are no events.");
            return;
        }

        int index = 1;

        for(Event event: eventList) {
            System.out.print(index + ") ");
            System.out.println(event);
            index++;
        }
    }

    public void printCompletedEvents() {
        if(completedEvents.isEmpty()) {
            System.out.println("There are no completed events.");
            return;
        }

        int index = 1;

        for(Event completedEvent: completedEvents) {
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

    private static String sanitizeString(String stringToSanitize) {
        if(stringToSanitize == null) {
            return "";
        }

        return stringToSanitize.toLowerCase().trim();
    }


}
