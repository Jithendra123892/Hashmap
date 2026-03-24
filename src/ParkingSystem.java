public class ParkingSystem {
    String[] slots;

    ParkingSystem(int size) {
        slots = new String[size];
    }

    int hash(String plate) {
        int sum = 0;
        for (char c : plate.toCharArray()) sum += c;
        return sum % slots.length;
    }

    public void park(String plate) {
        int idx = hash(plate);

        while (slots[idx] != null) {
            idx = (idx + 1) % slots.length;
        }

        slots[idx] = plate;
        System.out.println("Parked at " + idx);
    }

    public static void main(String[] args) {
        ParkingSystem p = new ParkingSystem(5);

        p.park("ABC123");
        p.park("XYZ999");
    }
}