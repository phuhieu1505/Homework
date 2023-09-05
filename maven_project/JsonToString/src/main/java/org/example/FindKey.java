import java.util.*;

public class FindKey {
    public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (Objects.equals(value, entry.getValue())) {
                return entry.getKey();
            }
        }
        return null; // Trả về null nếu không tìm thấy key
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap value can tim: ");
        Integer valueToFind = scanner.nextInt();

        String key = getKeyByValue(map, valueToFind);

        if (key != null) {
            System.out.println("Key co value la " + valueToFind + ": " + key);
        } else {
            System.out.println("Value " + valueToFind + " khong ton tai trong ");
        }
    }
}
