import java.util.*;

class LRUCache {
    LinkedHashMap<String, String> cache;
    int cap;

    LRUCache(int cap) {
        this.cap = cap;
        cache = new LinkedHashMap<>(cap, 0.75f, true);
    }

    String get(String key) {
        return cache.getOrDefault(key, null);
    }

    void put(String key, String val) {
        cache.put(key, val);
        if (cache.size() > cap) {
            String first = cache.keySet().iterator().next();
            cache.remove(first);
        }
    }
}

public class MultiLevelCacheSystem {
    LRUCache l1 = new LRUCache(2);
    LRUCache l2 = new LRUCache(5);

    public String get(String key) {
        String val = l1.get(key);
        if (val != null) return "L1 HIT";

        val = l2.get(key);
        if (val != null) {
            l1.put(key, val);
            return "L2 HIT";
        }

        l2.put(key, "DATA");
        return "DB HIT";
    }

    public static void main(String[] args) {
        MultiLevelCacheSystem m = new MultiLevelCacheSystem();

        System.out.println(m.get("video1"));
        System.out.println(m.get("video1"));
    }
}