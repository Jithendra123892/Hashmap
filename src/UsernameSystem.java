import java.util.*;

public class UsernameSystem {
    private HashMap<String, Integer> users = new HashMap<>();
    private HashMap<String, Integer> attempts = new HashMap<>();

    public boolean checkAvailability(String username) {
        attempts.put(username, attempts.getOrDefault(username, 0) + 1);
        return !users.containsKey(username);
    }

    public boolean register(String username, int id) {
        if (checkAvailability(username)) {
            users.put(username, id);
            return true;
        }
        return false;
    }

    public List<String> suggest(String username) {
        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 3; i++) list.add(username + i);
        list.add(username.replace("_", "."));
        return list;
    }

    public String mostAttempted() {
        return Collections.max(attempts.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    public static void main(String[] args) {
        UsernameSystem u = new UsernameSystem();
        u.register("john_doe", 1);

        System.out.println(u.checkAvailability("john_doe"));
        System.out.println(u.suggest("john_doe"));
        System.out.println(u.mostAttempted());
    }
}