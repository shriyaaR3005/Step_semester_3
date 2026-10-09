
import java.time.LocalDate;

abstract class Assignment {
    String title;
    LocalDate due;

    Assignment(String title, LocalDate due) {
        this.title = title;
        this.due = due;
    }

    abstract double penalty();
}

class Coding extends Assignment {
    Coding(String t, LocalDate d) { super(t, d); }
    double penalty() { return 0.10; }
}

class Written extends Assignment {
    Written(String t, LocalDate d) { super(t, d); }
    double penalty() { return 0.20; }
}

class Submission {
    String student, status = "Submitted";
    Assignment assignment;
    LocalDate date;

    Submission(String s, Assignment a, LocalDate d) {
        student = s;
        assignment = a;
        date = d;
        long late = Math.max(0,
            java.time.temporal.ChronoUnit.DAYS.between(a.due, d));
        System.out.println(student + "'s submission for '" + a.title
                + "' received (" + (late == 0 ? "on time" : late + " days late") + ").");
    }

    void grade(double marks) {
        if (!status.equals("Submitted")) {
            System.out.println("Cannot grade again.");
            return;
        }

        long late = Math.max(0,
            java.time.temporal.ChronoUnit.DAYS.between(assignment.due, date));
        double finalMarks = marks * (1 - late * assignment.penalty());
        status = "Graded";

        System.out.printf("%s graded: %.0f/50%n", student, finalMarks);
        System.out.println("Status: " + status);
    }

    void resubmit() {
        if (status.equals("Graded"))
            System.out.println("Cannot resubmit: '" + assignment.title
                    + "' has already been graded.");
    }
}

public class AssignmentSubmissionPortal {
    public static void main(String[] args) {
        Assignment a = new Coding("Linked List Lab",
                LocalDate.of(2026, 3, 10));
        Assignment b = new Written("Design Essay",
                LocalDate.of(2026, 3, 12));

        Submission s1 = new Submission("Asha", a,
                LocalDate.of(2026, 3, 10));
        Submission s2 = new Submission("Ravi", b,
                LocalDate.of(2026, 3, 14));

        s1.grade(45);
        s2.grade(40);
        s1.resubmit();
    }
}