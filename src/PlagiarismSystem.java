import java.util.*;

public class PlagiarismSystem {
    HashMap<String, Set<String>> db = new HashMap<>();

    public void addDoc(String id, String text) {
        String[] w = text.split(" ");
        for (int i = 0; i < w.length - 4; i++) {
            String gram = String.join(" ", Arrays.copyOfRange(w, i, i + 5));
            db.putIfAbsent(gram, new HashSet<>());
            db.get(gram).add(id);
        }
    }

    public void analyze(String text) {
        Map<String, Integer> map = new HashMap<>();
        String[] w = text.split(" ");
        int total = 0;

        for (int i = 0; i < w.length - 4; i++) {
            total++;
            String gram = String.join(" ", Arrays.copyOfRange(w, i, i + 5));

            if (db.containsKey(gram)) {
                for (String doc : db.get(gram)) {
                    map.put(doc, map.getOrDefault(doc, 0) + 1);
                }
            }
        }

        for (String doc : map.keySet()) {
            double sim = (map.get(doc) * 100.0) / total;
            System.out.println(doc + ": " + sim + "%");
        }
    }

    public static void main(String[] args) {
        PlagiarismSystem p = new PlagiarismSystem();

        p.addDoc("doc1", "this is a sample document for testing plagiarism system");
        p.addDoc("doc2", "this is another document");

        p.analyze("this is a sample document for plagiarism");
    }
}