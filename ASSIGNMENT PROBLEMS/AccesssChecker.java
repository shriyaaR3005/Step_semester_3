import java.util.Scanner;

public class AccesssChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals(
                        "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts) {

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result =
                classifyAccess(modifier, context);

            if (result.equals("DENIED")) {

                return modifier
                    + " via "
                    + context
                    + " (attempt #"
                    + (i + 1)
                    + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.println("\nAttempt " + (i + 1));

            System.out.print("Enter field modifier: ");
            attempts[i][0] = sc.nextLine();

            System.out.print("Enter accessor context: ");
            attempts[i][1] = sc.nextLine();
        }

        System.out.println("\nFirst Denied Attempt:");

        System.out.println(
            firstDeniedAttempt(attempts)
        );

        sc.close();
    }
}