
import java.util.Map;

public class q2<T> {
    public void countOccurrences(T[] arr) {
        Map<T, Integer> map = new java.util.HashMap<>();
        for (T item : arr) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public static void main(String[] args) {
        q2<String> stringCounter = new q2<>();
        String[] strArray = {"apple", "banana", "apple", "orange", "banana", "kiwi"};
        stringCounter.countOccurrences(strArray);
        
        q2<Integer> intCounter = new q2<>();
        Integer[] intArray = {1, 2, 3, 1, 2, 3, 4};
        intCounter.countOccurrences(intArray);
    }
    
}
