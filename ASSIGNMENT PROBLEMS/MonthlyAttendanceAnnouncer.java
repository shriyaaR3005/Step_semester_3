public class MonthlyAttendanceAnnouncer {

    static class GymMember {
        protected String memberId;
        protected int monthlyFee;
        protected int sessionsAttended;

        public GymMember(String memberId, int monthlyFee) {

            if (memberId == null ||
                memberId.trim().isEmpty() ||
                memberId.length() < 4) {

                throw new IllegalArgumentException("Invalid member ID");
            }

            this.memberId = memberId;
            this.monthlyFee = monthlyFee;
        }

        public void attendSession() {
            sessionsAttended++;
        }

        public int getSessionsAttended() {
            return sessionsAttended;
        }

        public String displayInfo() {
            return "Standard | Sessions: "
                    + sessionsAttended;
        }
    }

    static class PremiumMember extends GymMember {

        private String trainerName;

        public PremiumMember(String memberId,
                             int monthlyFee,
                             String trainerName) {

            super(memberId, monthlyFee);
            this.trainerName = trainerName;
        }

        public String getTrainerName() {
            return trainerName;
        }

        @Override
        public String displayInfo() {
            return "Premium | Trainer: "
                    + trainerName
                    + " | Sessions: "
                    + sessionsAttended;
        }
    }

    public static String batchPrint(
            GymMember[] members) {

        StringBuilder announcement =
                new StringBuilder();

        for (GymMember member : members) {

            announcement.append(
                    member.displayInfo()
            );

            if (member instanceof PremiumMember) {

                PremiumMember premium =
                        (PremiumMember) member;

                announcement.append(
                        " [Trainer via downcast: "
                );

                announcement.append(
                        premium.getTrainerName()
                );

                announcement.append("]");
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }

    public static void main(String[] args) {

        GymMember[] members = {

                new GymMember(
                        "MEM6",
                        1000
                ),

                new PremiumMember(
                        "MEM7",
                        2000,
                        "Coach Riya"
                )
        };

        System.out.println(
                batchPrint(members)
        );
    }
}