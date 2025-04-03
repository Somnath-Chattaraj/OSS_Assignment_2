import java.util.Scanner;

public class q6 {

    
    void binary_search(int num, int arr[], int st, int end) {
        int low = st, high = end;
        while (high >= low) { // Fix condition
            int mid = (low + high) / 2;
            if (arr[mid] == num) {
                System.out.println("Number found at index: " + (mid+1));
                return;
            } else if (arr[mid] < num) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the length of the array: ");
        int n = sc.nextInt();
        int arr[] = new int[n];

        System.out.print("Enter " + n + " sorted elements: ");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Enter the number to search: ");
        int num = sc.nextInt();

        sc.close(); 
        int numThreads = 10; 
        int segmentSize = n / numThreads; 

        for (int i = 0; i < numThreads; i++) {
            int start = i * segmentSize;
            int end = (i == numThreads - 1) ? n - 1 : (start + segmentSize - 1);

            Thread t = new Thread(() -> new q6().binary_search(num, arr, start, end));
            t.start();
        }
    }
}
