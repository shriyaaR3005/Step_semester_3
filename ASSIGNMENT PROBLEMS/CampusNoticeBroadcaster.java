
import java.util.*;

interface NotificationChannel {
    void send(String student, String title);
}

class EmailChannel implements NotificationChannel {
    public void send(String s, String t) {
        System.out.println("[Email → " + s + "] " + t);
    }
}

class SmsChannel implements NotificationChannel {
    public void send(String s, String t) {
        System.out.println("[SMS → " + s + "] " + t);
    }
}

class AppChannel implements NotificationChannel {
    public void send(String s, String t) {
        System.out.println("[App → " + s + "] " + t);
    }
}

class Student {
    String name, department;
    ArrayList<NotificationChannel> channels = new ArrayList<>();

    Student(String name, String department) {
        this.name = name;
        this.department = department;
    }

    void addChannel(NotificationChannel c) {
        channels.add(c);
    }
}

class Notice {
    String title;
    Set<String> departments;

    Notice(String title, String... departments) {
        this.title = title;
        this.departments = new HashSet<>(Arrays.asList(departments));
    }
}

class NoticeBoard {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(Student s) {
        students.add(s);
    }

    void post(Notice n) {
        if (n.title.trim().isEmpty() || n.departments.isEmpty()) {
            System.out.println(
                "Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.println("Notice '" + n.title + "' posted to "
                + String.join(", ", n.departments) + ".");

        for (Student s : students) {
            if (n.departments.contains(s.department)) {
                for (NotificationChannel c : s.channels) {
                    c.send(s.name, n.title);
                }
            }
        }
    }
}

public class CampusNoticeBroadcaster {
    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student a = new Student("Asha", "CSE");
        a.addChannel(new EmailChannel());
        a.addChannel(new AppChannel());

        Student r = new Student("Ravi", "ECE");
        r.addChannel(new SmsChannel());

        board.addStudent(a);
        board.addStudent(r);

        board.post(new Notice("Lab Closed Tomorrow", "CSE"));
        board.post(new Notice("Fee Deadline Extended", "CSE", "ECE"));
        board.post(new Notice("Sports Day"));
    }
}