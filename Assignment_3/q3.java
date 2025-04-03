import java.util.Map;
public class q3<T> {
    public void printDuplicate(T[] arr) {
        Map<T, Integer> map = new java.util.HashMap<>();
        for (T item : arr) {
            map.put(item, map.getOrDefault(item, 0) + 1);
        }
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }
    public static void main(String[] args) {
        Integer[] intArray = {1, 2, 3, 1, 2, 3, 4};
        String[] strArray = {"apple", "banana", "apple", "orange", "banana", "kiwi"};
        q3<Integer> intDuplicateFinder = new q3<>();
        q3<String> strDuplicateFinder = new q3<>();
        System.out.println("Duplicates from Integer Array: ");
        intDuplicateFinder.printDuplicate(intArray);
        System.out.println("Duplicates from String Array: ");
        strDuplicateFinder.printDuplicate(strArray);
    }
}
