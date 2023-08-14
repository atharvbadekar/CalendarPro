import java.util.*;

public class Main {
    public static void main(String[] args) {
        CalendarPro calendar = new CalendarPro();
        Scanner scanner = new Scanner(System.in);

        int choice;
        System.out.println("----------------------  CalendarPro - App  ----------------------");
        

        do {
            System.out.println("========================  MENU  =======================");
            System.out.println("1. Add Event");
            System.out.println("2. Show Added Events");
            System.out.println("3. Show Events by Category");
            System.out.println("4. Easy View");
            System.out.println("5. Update Event:");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.println("\nEnter event information:");
                    calendar.addeventinfo(scanner);
                    break;
                case 2:
                    System.out.println("\nShowing  Events:");
                    calendar.easyview(2); 
                    break;
                case 3:
                    System.out.println("-------------------------");
                    System.out.println("\nShowing Events by Category:");
                    System.out.print("Enter a category (work/health/personal): ");
                    String category = scanner.nextLine();
                    calendar.viewbycategory(category);
                    System.out.println("-------------------------");
                    break;
                case 4:
                    System.out.println("-------------------------");
                    System.out.println("\nEasy Viewing:");
                    System.out.println("1. Day View");
                    System.out.println("2. Week View");
                    System.out.println("3. Month View");
                    System.out.print("Enter choice: ");
                    int viewType = scanner.nextInt();
                    calendar.easyview(viewType);
                    break;
                case 5:
                    System.out.println("-------------------------");
                    System.out.println("\nUpdate Event:");
                    System.out.print("Enter the name of the event to update: ");
                    String eventName = scanner.nextLine();
                    
                    EventInfo eventToUpdate = null;
                    for (EventInfo event : calendar.events) {
                        if (event.eventname.equalsIgnoreCase(eventName)) {
                            eventToUpdate = event;
                            break;
                        }
                    }
                    
                    if (eventToUpdate != null) {
                        System.out.println("\nEnter updated info for the event:");
                        calendar.updateextingevent(scanner, eventToUpdate);
                    } else {
                        System.out.println("Event not found.");
                    }
                    break;
                case 6:
                    System.out.println("-------------------------");
                    System.out.println("=============  Exit  =============");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }

        } while (choice != 6);

        scanner.close();
    }
}