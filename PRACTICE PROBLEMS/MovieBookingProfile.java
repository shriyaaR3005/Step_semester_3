import java.util.Scanner;

public class MovieBookingProfile {

    private String name;
    private boolean confirmed;
    private String otp;

    // No-argument constructor
    public MovieBookingProfile() {
        name = "";
        confirmed = false;
    }

    // Convenience constructor
    public MovieBookingProfile(String name) {
        this();
        this.name = name;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

    // Boolean getter
    public boolean isConfirmed() {
        return confirmed;
    }

    // Setter for confirmed
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    // Write-only OTP
    public void setOtp(String otp) {

        if (otp != null && otp.matches("\\d{4,6}")) {
            this.otp = otp;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        MovieBookingProfile profile =
                new MovieBookingProfile(name);

        System.out.print("Enter confirmation status (true/false): ");
        boolean confirmed = sc.nextBoolean();
        profile.setConfirmed(confirmed);

        sc.nextLine();

        System.out.print("Enter OTP: ");
        String otp = sc.nextLine();

        profile.setOtp(otp);

        System.out.println("Name: " + profile.getName());
        System.out.println("Confirmed: " + profile.isConfirmed());

        // OTP is intentionally never printed or retrieved.

        sc.close();
    }
}