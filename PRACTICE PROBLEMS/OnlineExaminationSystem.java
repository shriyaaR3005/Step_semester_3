
abstract class Question {
    String text, answer;
    int marks;

    Question(String text, String answer, int marks) {
        this.text = text;
        this.answer = answer;
        this.marks = marks;
    }

    abstract boolean check(String response);
}

class MCQ extends Question {
    MCQ(String q, String a, int m) { super(q, a, m); }
    boolean check(String r) { return answer.equalsIgnoreCase(r); }
}

class TrueFalse extends Question {
    TrueFalse(String q, String a, int m) { super(q, a, m); }
    boolean check(String r) { return answer.equalsIgnoreCase(r); }
}

class Attempt {
    boolean submitted = false;
    int score = 0;

    void answer(Question q, String response) {
        if (submitted) {
            System.out.println("Cannot change submitted answers.");
            return;
        }
        if (q.check(response)) score += q.marks;
        System.out.println("Answer recorded for " + q.text);
    }

    void submit() {
        submitted = true;
        System.out.println("Exam submitted. Score: " + score + "/10");
    }
}

public class OnlineExaminationSystem {
    public static void main(String[] args) {
        Attempt a = new Attempt();
        Question q1 = new MCQ("Question 1", "C", 5);
        Question q2 = new TrueFalse("Question 2", "False", 5);

        System.out.println("Exam started");
        a.answer(q1, "C");
        a.answer(q2, "True");
        a.submit();
        a.answer(q1, "A");
    }
}