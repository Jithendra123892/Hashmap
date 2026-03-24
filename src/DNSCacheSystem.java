import java.util.*;

public class DNSCacheSystem {
    class Entry {
        String ip;
        long expiry;

        Entry(String ip, long ttl) {
            this.ip = ip;
            this.expiry = System.currentTimeMillis() + ttl;
        }
    }

    HashMap<String, Entry> cache = new HashMap<>();
    int hits = 0, misses = 0;

    public String resolve(String domain) {
        long now = System.currentTimeMillis();

        if (cache.containsKey(domain)) {
            Entry e = cache.get(domain);
            if (now < e.expiry) {
                hits++;
                return "HIT: " + e.ip;
            } else cache.remove(domain);
        }

        misses++;
        String ip = "1.1.1.1";
        cache.put(domain, new Entry(ip, 3000));
        return "MISS: " + ip;
    }

    public static void main(String[] args) throws Exception {
        DNSCacheSystem d = new DNSCacheSystem();

        System.out.println(d.resolve("google.com"));
        System.out.println(d.resolve("google.com"));
        Thread.sleep(4000);
        System.out.println(d.resolve("google.com"));
    }
}