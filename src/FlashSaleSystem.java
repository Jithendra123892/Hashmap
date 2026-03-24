import java.util.*;

public class FlashSaleSystem {
    private HashMap<String, Integer> stock = new HashMap<>();
    private Queue<Integer> waiting = new LinkedList<>();

    public FlashSaleSystem() {
        stock.put("IPHONE15", 3);
    }

    public synchronized String purchase(String product, int user) {
        int s = stock.getOrDefault(product, 0);

        if (s > 0) {
            stock.put(product, s - 1);
            return "Success, remaining: " + (s - 1);
        } else {
            waiting.add(user);
            return "Waiting #" + waiting.size();
        }
    }

    public static void main(String[] args) {
        FlashSaleSystem f = new FlashSaleSystem();
        for (int i = 1; i <= 5; i++)
            System.out.println(f.purchase("IPHONE15", i));
    }
}