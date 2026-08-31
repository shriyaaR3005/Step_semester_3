import java.util.Scanner;

class Course {
    String code;
    String title;
    int credits;
    int labCredits;

    public Course(String code, String title, int credits, int labCredits) {
        this.code = code;
        this.title = title;
        this.credits = credits;
        this.labCredits = labCredits;
    }

    public Course(String code, String title, int credits) {
        this(code, title, credits, 0);
    }

    public int totalCredits() {
        return credits + labCredits;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter theory-only course details:");

        System.out.print("Enter course code: ");
        String code1 = sc.nextLine();

        System.out.print("Enter course title: ");
        String title1 = sc.nextLine();

        System.out.print("Enter credits: ");
        int credits1 = sc.nextInt();
        sc.nextLine();

        Course theory = new Course(code1, title1, credits1);

        System.out.println("\nEnter lab course details:");

        System.out.print("Enter course code: ");
        String code2 = sc.nextLine();

        System.out.print("Enter course title: ");
        String title2 = sc.nextLine();

        System.out.print("Enter credits: ");
        int credits2 = sc.nextInt();

        System.out.print("Enter lab credits: ");
        int labCredits = sc.nextInt();

        Course lab = new Course(code2, title2, credits2, labCredits);

        System.out.println("\nOutput:");
        System.out.println(theory.code + " total credits: " + theory.totalCredits());
        System.out.println(lab.code + " total credits: " + lab.totalCredits());

        sc.close();
    }
}