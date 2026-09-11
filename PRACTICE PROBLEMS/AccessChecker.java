import java.util.Scanner;

class MovieTicket {
    private String seatNumber;
    String screenId;              // default
    protected double ticketPrice;
    public String movieTitle;
}

public class AccessChecker {

    static String classifyAccess(String fieldModifier, String accessorContext) {

        if (fieldModifier.equals("private")) {
            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }
        }

        else if (fieldModifier.equals("default")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
        }

        else if (fieldModifier.equals("protected")) {
            if (accessorContext.equals("SAME_CLASS") ||
                accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }
        }

        else if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts) {

        int allowed = 0;
        int denied = 0;

        for (int i = 0; i < attempts.length; i++) {

            String result = classifyAccess(
                attempts[i][0],
                attempts[i][1]
            );

            if (result.equals("ALLOWED")) {
                allowed++;
            } else {
                denied++;
            }
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter field modifier: ");
        String modifier = sc.nextLine();

        System.out.print("Enter accessor context: ");
        String context = sc.nextLine();

        System.out.println("Result: " +
                classifyAccess(modifier, context));

        System.out.print("Enter number of batch attempts: ");
        int n = sc.nextInt();
        sc.nextLine();

        String[][] attempts = new String[n][2];

        for (int i = 0; i < n; i++) {

            System.out.print("Enter modifier for attempt " + (i + 1) + ": ");
            attempts[i][0] = sc.nextLine();

            System.out.print("Enter context for attempt " + (i + 1) + ": ");
            attempts[i][1] = sc.nextLine();
        }

        System.out.println(summarizeBatch(attempts));

        sc.close();
    }
}