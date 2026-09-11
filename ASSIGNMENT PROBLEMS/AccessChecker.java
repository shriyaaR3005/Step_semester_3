import java.util.Scanner;

public class AccessChecker {

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
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeByModifier(String[][] attempts) {

        String[] modifiers = {
            "private", "default", "protected", "public"
        };

        int[] allowed = new int[4];
        int[] denied = new int[4];

        for (int i = 0; i < attempts.length; i++) {

            String modifier = attempts[i][0];
            String context = attempts[i][1];

            String result =
                classifyAccess(modifier, context);

            for (int j = 0; j < modifiers.length; j++) {

                if (modifier.equals(modifiers[j])) {

                    if (result.equals("ALLOWED")) {
                        allowed[j]++;
                    } else {
                        denied[j]++;
                    }

                    break;
                }
            }
        }

        StringBuilder output = new StringBuilder();

        for (int i = 0; i < modifiers.length; i++) {

            if (i > 0) {
                output.append(" | ");
            }

            output.append(modifiers[i])
                  .append(": ")
                  .append(allowed[i])
                  .append(" allowed / ")
                  .append(denied[i])
                  .append(" denied");
        }

        return output.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter field modifier: ");
        String modifier = sc.nextLine();

        System.out.print("Enter accessor context: ");
        String context = sc.nextLine();

        System.out.println(
            "Access: " + classifyAccess(modifier, context)
        );

        System.out.print("Enter number of attempts: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.println("\nAttempt " + (i + 1));

            System.out.print("Enter modifier: ");
            attempts[i][0] = sc.nextLine();

            System.out.print("Enter context: ");
            attempts[i][1] = sc.nextLine();
        }

        System.out.println("\nSummary:");
        System.out.println(
            summarizeByModifier(attempts)
        );

        sc.close();
    }
}