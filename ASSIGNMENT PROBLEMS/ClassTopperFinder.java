public class ClassTopperFinder {
    static int[] findTopper(int[][] marks) {
        int best = -1, index = 0;

        for (int i = 0; i < marks.length; i++) {
            int total = 0;
            for (int j = 0; j < marks[i].length; j++)
                total += marks[i][j];

            if (total > best) {
                best = total;
                index = i;
            }
        }
        return new int[]{index, best};
    }

    public static void main(String[] args) {
        int[][] marks = {
            {78, 85, 90},
            {88, 92, 79},
            {65, 70, 95}
        };

        int[] r = findTopper(marks);
        System.out.println("(" + r[0] + ", " + r[1] + ")");
    }
}