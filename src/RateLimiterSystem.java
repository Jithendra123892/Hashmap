import java.util.*;

public class RateLimiterSystem {
    class Bucket {
        double tokens = 5;
        long last = System.currentTimeMillis();
    }

    HashMap<String, Bucket> map = new HashMap<>();

    public String check(String id) {
        Bucket b = map.getOrDefault(id, new Bucket());
        long now = System.currentTimeMillis();

        double refill = (now - b.last) / 1000.0;
        b.tokens = Math.min(5, b.tokens + refill);
        b.last = now;

        if (b.tokens >= 1) {
            b.tokens--;
            map.put(id, b);
            return "Allowed";
        }
        return "Denied";
    }

    public static void main(String[] args) {
        RateLimiterSystem r = new RateLimiterSystem();

        for (int i = 0; i < 10; i++)
            System.out.println(r.check("user"));
    }
}