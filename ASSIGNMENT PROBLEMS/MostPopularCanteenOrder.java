import java.util.*;

public class MostPopularCanteenOrder {
    static void mostPopular(String[] orders) {
        HashMap<String, Integer> map = new HashMap<>();

        for (String item : orders)
            map.put(item, map.getOrDefault(item, 0) + 1);

        String best = orders[0];
        int max = 0;

        for (String item : orders) {
            if (map.get(item) > max) {
                max = map.get(item);
                best = item;
            }
        }

        System.out.println("(" + best + ", " + max + ")");
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "idli", "vada", "dosa",
            "idli", "dosa", "tea"
        };

        mostPopular(orders);
    }
}