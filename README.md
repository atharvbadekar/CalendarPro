
# CalendarPro

CalendarPro is a simple Java application that allows users to manage and organize events in a calendar. It provides features such as adding events, viewing events by category, setting reminders,smart scheduling easy viewing.



## Features

- Add events with details like name, date, time, location, notes, and categories.
- View events in day, week, and month views.
- View events by category.
- Set reminders for events.
- Alerts if event is already exist at that time and date.
- Update existing events.


## Prerequisites

- Java Development Kit (JDK) 18.0.1.1
- Git version 2.40.1.windows.1
## Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/atharvbadekar/CalendarPro.git
  
2. Navigate to the project directory:
 ```sh
   cd CalendarPro
```
3. Compile the source code:
```sh
    javac *.java
```
3. Run the application:
```sh
    java CalendarProTest
    java Main
```
    
## Usage

- Launch the application by following the installation instructions.
- Use the provided menu to navigate through different features.
- Follow the prompts to add, view, and manage events.
- Use the various views (day, week, month) to visualize your events.


## Documentation

[CalendarPro_Output.pdf]()


## Roadmap

 ```
  +------------------+      +-------------+     +------------+
  |     Event        |      |   Reminder  |     | CalendarPro |
  +------------------+      +-------------+     +------------+
  | - name: String   |      | - event:     |     | - events:   |
  | - date: Date     |      |   Event     |     |   List<Event> |
  | - location: String|----->| - minutesBefore: int | - reminders: |
  | - notes: String  |      +-------------+     |   List<Reminder> |
  | - category:      |                       |   | + addEvent(...) |
  |   List<String>   |                       |   | + addReminder(...) |
  | + Event(...)     |                       |   | + viewEvents(...) |
  | + getDate()      |                       |   | + parseDate(...) |
  | + getLocation()  |                       |   | + ... |
  | + ...            |                       +------------+
  +------------------+
```

