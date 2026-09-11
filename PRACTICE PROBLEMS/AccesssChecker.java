import java.util.Scanner;

class MovieTicket {
    protected double ticketPrice;
}

class PremiumMovieTicket extends MovieTicket {

    void accessOwnType() {
        System.out.println("Access through PremiumMovieTicket: "
                + ticketPrice);
    }

    void accessParentType() {

        MovieTicket ticket = this;

        // This represents access through a MovieTicket reference.
        System.out.println("Access through MovieTicket reference: "
                + ticket.ticketPrice);
    }
}

public class AccesssChecker {

    static String classifyAccess(String fieldModifier,
                                 String accessorContext) {

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
                accessorContext.equals("SAME_PACKAGE") ||
                accessorContext.equals(
                    "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {

                return "ALLOWED";
            }
        }

        else if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        return "DENIED";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter field modifier: ");
        String modifier = sc.nextLine();

        System.out.print("Enter accessor context: ");
        String context = sc.nextLine();

        System.out.println("Result: "
                + classifyAccess(modifier, context));

        sc.close();
    }
}