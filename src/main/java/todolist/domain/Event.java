package todolist.domain;

public class Event {
    private String title;
    private String category;
    private String description;
    public boolean isComplete;

    public Event(String eventTitle, String category, String description) {
        this.title = eventTitle;
        this.category = category;
        this.description = description;
        this.isComplete = false;
    }

    public Event(String eventTitle, String description) {
        this(eventTitle, "" , description);
    }

    public Event(String eventTitle) {
        this(eventTitle, "", "");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean getCompletionStatus() {
        return isComplete;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void completeEvent() {
        isComplete = true;
    }

    @Override
    public String toString() {
        StringBuilder returnedString = new StringBuilder();
        returnedString.append("Title: " + title + "\n");

        if(!category.isEmpty()) {
            returnedString.append("Category: " + category + "\n");
        }

        if(!description.isEmpty()) {
            returnedString.append("Description: " + description + "\n");
        }
        if(isComplete) {
            returnedString.append("Status: Completed");
        } else {
            returnedString.append("Status: In progress...");
        }

        returnedString.append("\n");
        return returnedString.toString();
    }
}
