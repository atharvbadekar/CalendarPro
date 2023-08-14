import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class EventInfo {
    String eventname;
    Date eventdate;
    String eventlocation;
    String any_notes;
    List<String> event_category;

    public EventInfo(String name, Date date, String location, String notes, List<String> tags) {
        this.eventname = name;
        this.eventdate = date;
        this.eventlocation = location;
        this.any_notes = notes;
        this.event_category = tags;
    }
}

class ReminderDetails {
    EventInfo event;
    int minutesBefore;

    public ReminderDetails(EventInfo event, int minutesBefore) {
        this.event = event;
        this.minutesBefore = minutesBefore;
    }
}

class CalendarPro {
    List<EventInfo> events;
    List<ReminderDetails> reminders;

    public CalendarPro() {
        events = new ArrayList<>();
        reminders = new ArrayList<>();
    }

    public void addEvent(String name, Date date, String location, String notes, List<String> tags) {
        EventInfo event = new EventInfo(name, date, location, notes, tags);
        events.add(event);
    }

    // taking user input,
    public void addeventinfo(Scanner scanner) {
    System.out.print("Enter event name: ");
    String name = scanner.nextLine();

    System.out.print("Enter event date (yyyy-MM-dd): ");
    String dateStr = scanner.nextLine();
    Date date = parseDate(dateStr);

    System.out.print("Enter event time (HH:mm): ");
    String timeStr = scanner.nextLine();
    Date time = parseTime(timeStr);

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar timeCal = Calendar.getInstance();
    timeCal.setTime(time);
    cal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));

    // check event is there in the list already or not
    for (EventInfo event : events) {
        if (event.eventdate.equals(cal.getTime())) {
            System.out.println("event is already scheduled at the same time:");
            printEventDetails(event);

            System.out.print("Contineu this event (yes/no)? ");
            String proceed = scanner.nextLine();
            if (!proceed.equalsIgnoreCase("yes")) {
                System.out.println("choose different date or time for your event.");
                return;
            } else {
                System.out.println("Updating the existing event...");
                updateextingevent(scanner, event);
                return;
            }
        }
    }

    System.out.print("Enter event location: ");
    String location = scanner.nextLine();

    System.out.print("Enter event notes: ");
    String notes = scanner.nextLine();

    List<String> tags = takecategoryinput(scanner);

    addEvent(name, cal.getTime(), location, notes, tags);

    System.out.print("Set a reminder? (yes/no): ");
    String setReminder = scanner.nextLine();
    if (setReminder.equalsIgnoreCase("yes")) {
        System.out.print("Enter reminder time (in minutes): ");
        int minutesBefore = scanner.nextInt();
        addReminder(events.get(events.size() - 1), minutesBefore);
    }
}



    public void updateextingevent(Scanner scanner, EventInfo eventToUpdate) {
    System.out.print("Enter updated event name: ");
    String name = scanner.nextLine();

    System.out.print("Enter updated event date (yyyy-MM-dd): ");
    String dateStr = scanner.nextLine();
    Date date = parseDate(dateStr);

    System.out.print("Enter updated event time (HH:mm): ");
    String timeStr = scanner.nextLine();
    Date time = parseTime(timeStr);

    Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    Calendar timeCal = Calendar.getInstance();
    timeCal.setTime(time);
    cal.set(Calendar.HOUR_OF_DAY, timeCal.get(Calendar.HOUR_OF_DAY));
    cal.set(Calendar.MINUTE, timeCal.get(Calendar.MINUTE));

    System.out.print("Enter updated event location: ");
    String location = scanner.nextLine();

    System.out.print("Enter updated event notes: ");
    String notes = scanner.nextLine();

    List<String> tags = takecategoryinput(scanner);

    eventToUpdate.eventname = name;
    eventToUpdate.eventdate = cal.getTime();
    eventToUpdate.eventlocation = location;
    eventToUpdate.any_notes = notes;
    eventToUpdate.event_category = tags;

    System.out.println("Event updated successfully.");
    System.out.println("-------------------------");
}


    public void addReminder(EventInfo event, int minutesBefore) {
        ReminderDetails reminder = new ReminderDetails(event, minutesBefore);
        reminders.add(reminder);
    }

    public void viewEvents(Date startDate, Date endDate) {
        boolean found = false;
        for (EventInfo event : events) {
            if (event.eventdate.after(startDate) && event.eventdate.before(endDate)) {
                printEventDetails(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found within the specified date range.");
            System.out.println("-------------------------");
        }
    }

    public void viewbycategory(String category) {
        boolean found = false;
        for (EventInfo event : events) {
            if (event.event_category.contains(category)) {
                printEventDetails(event);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No events found for the selected category.");
            System.out.println("-------------------------");
        }
    }


    public void easyview(int viewType) {
        Calendar calendar = Calendar.getInstance();
        Date currentDate = new Date();
        calendar.setTime(currentDate);

        // viewing day week month wise.....
        switch (viewType) {
            case 1: 
                System.out.println("\nDay View:");
                viewEvents(currentDate, currentDate);
                break;
            case 2: 
                System.out.println("\nUpcoming events in this week:");
                Calendar endCalendar = Calendar.getInstance();
                endCalendar.setTime(currentDate);
                endCalendar.add(Calendar.DAY_OF_MONTH, 7);
                Date endDate = endCalendar.getTime();
                viewEvents(currentDate, endDate);
                break;
            
            case 3: 
                System.out.println("\nMonth View:");
                calendar.set(Calendar.DAY_OF_MONTH, 1);
                Date firstDayOfMonth = calendar.getTime();
                calendar.add(Calendar.MONTH, 1);
                calendar.add(Calendar.DAY_OF_MONTH, -1);
                Date lastDayOfMonth = calendar.getTime();
                viewEvents(firstDayOfMonth, lastDayOfMonth);
                break;
            default:
                System.out.println("Invalid view type.");
        }
    }

    private Date parseDate(String dateStr) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("date is wronng. taking current date.");
            return new Date();
        }
    }

    private Date parseTime(String timeStr) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        try {
            return timeFormat.parse(timeStr);
        } catch (ParseException e) {
            System.out.println("time is wrong. taking current time.");
            return new Date();
        }
    }

    private List<String> takecategoryinput(Scanner scanner) {
        System.out.print("Enter event Category: ");
        String tagsStr = scanner.nextLine();
        return Arrays.asList(tagsStr.split(","));
    }

    private void printEventDetails(EventInfo event) {
        System.out.println("Event: " + event.eventname);
        System.out.println("Date: " + event.eventdate);
        System.out.println("Location: " + event.eventlocation);
        System.out.println("Notes: " + event.any_notes);
        System.out.println("Category: " + event.event_category);
        System.out.println("--------------------");
    }
}







