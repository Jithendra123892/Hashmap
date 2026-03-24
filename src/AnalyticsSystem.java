import java.util.*;

public class AnalyticsSystem {
    HashMap<String, Integer> views = new HashMap<>();
    HashMap<String, Set<String>> users = new HashMap<>();
    HashMap<String, Integer> sources = new HashMap<>();

    public void process(String url, String user, String source) {
        views.put(url, views.getOrDefault(url, 0) + 1);
        users.putIfAbsent(url, new HashSet<>());
        users.get(url).add(user);
        sources.put(source, sources.getOrDefault(source, 0) + 1);
    }

    public void dashboard() {
        views.entrySet().stream()
                .sorted((a,b)->b.getValue()-a.getValue())
                .limit(3)
                .forEach(System.out::println);

        System.out.println("Sources: " + sources);
    }

    public static void main(String[] args) {
        AnalyticsSystem a = new AnalyticsSystem();

        a.process("/news", "u1", "google");
        a.process("/news", "u2", "facebook");
        a.process("/sports", "u3", "direct");

        a.dashboard();
    }
}