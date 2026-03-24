import java.util.*;

public class AutocompleteSystem {
    HashMap<String, Integer> freq = new HashMap<>();

    public void add(String q) {
        freq.put(q, freq.getOrDefault(q, 0) + 1);
    }

    public void search(String prefix) {
        List<String> list = new ArrayList<>();

        for (String q : freq.keySet()) {
            if (q.startsWith(prefix)) list.add(q);
        }

        list.sort((a,b)->freq.get(b)-freq.get(a));
        System.out.println(list);
    }

    public static void main(String[] args) {
        AutocompleteSystem a = new AutocompleteSystem();

        a.add("java");
        a.add("javascript");
        a.add("java");
        a.add("jupyter");

        a.search("jav");
    }
}