import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CalendarProTest {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        testViewEventsByCategory(scanner);

        scanner.close();
    }

    public static void testViewEventsByCategory(Scanner scanner) {
        CalendarPro calendar = new CalendarPro();

        // Giving manually date and time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Date date1 = null;
        Date date2 = null;
        Date date3 = null;
        try {
            date1 = dateFormat.parse("2023-08-16 12:00");
            date2 = dateFormat.parse("2023-08-20 12:00");
            date3 = dateFormat.parse("2023-08-14 15:00");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // Add event information as follows
        calendar.addEvent("amaap", date1, "github", "upload project", Arrays.asList("personal"));
        calendar.addEvent("class", date2, "solapur", "java assignment", Arrays.asList("work"));
        calendar.addEvent("Gym", date3, "bale", "leg workout", Arrays.asList("health"));
        


        // Display all added events
        System.out.println("\nAll Added Events:");
        for (EventInfo event : calendar.events) {
            System.out.println("Event: " + event.eventname);
            System.out.println("Date: " + event.eventdate);
            System.out.println("Location: " + event.eventlocation);
            System.out.println("Notes: " + event.any_notes);
            System.out.println("Tags: " + event.event_category);
            System.out.println("------------------------------------");
        }
        
        System.out.print("Enter a category to view events: ");
        String category = scanner.nextLine();
        calendar.viewbycategory(category);
        
        
    }
}
