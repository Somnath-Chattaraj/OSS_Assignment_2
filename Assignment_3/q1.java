public class q1 <T> {
    
    void sort(T[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (((Comparable<T>) arr[j]).compareTo(arr[j + 1]) > 0) {
                    T temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        Integer[] intArray = {5, 2, 9, 1, 5, 6};
        String[] strArray = {"banana", "apple", "orange", "kiwi"};
        q1<Integer> intSorter = new q1<>();
        q1<String> strSorter = new q1<>();
        intSorter.sort(intArray);
        strSorter.sort(strArray);
        System.out.println("Sorted Integer Array: ");
        for (int i : intArray) {
            System.out.print(i + " ");
        }
        System.out.println("\nSorted String Array: ");
        for (String s : strArray) {
            System.out.print(s + " ");
        }
    }
}