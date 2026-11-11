package lw04.Unguided;

import java.util.Scanner;

public class Main {
    static void bubbleSort(int[] arr, int startPass, int endPass) {
        for (int i = startPass; i < endPass; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] < arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(Main.class.getResourceAsStream("parcels.txt"));
        int n = sc.nextInt();
        int[] parcels = new int[n];
        for (int i = 0; i < n; i++) {
            parcels[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        sc.close();

        bubbleSort(parcels, 0, 2);

        int[] battery = new int[n];
        for (int i = 0; i < n; i++) {
            battery[i] = parcels[i];
        }

        bubbleSort(parcels, 2, n - 1);

        System.out.println("==== After 2 Passes ====");
        for (int i = 0; i < n; i++) {
            System.out.print(battery[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("==== Fully Sorted ====");
        for (int i = 0; i < n; i++) {
            System.out.print(parcels[i]);
            if (i < n - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("==== Search Results ====");
        for (int i = 0; i < m; i++) {
            int low = 0;
            int high = n - 1;
            int steps = 0;
            int index = -1;
            while (low <= high && index == -1) {
                int mid = (low + high) / 2;
                steps++;
                if (parcels[mid] == targets[i]) {
                    index = mid;
                } else if (parcels[mid] > targets[i]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            if (index >= 0) {
                System.out.println(targets[i] + ": FOUND at index " + index + " (steps: " + steps + ")");
            } else {
                System.out.println(targets[i] + ": NOT FOUND (steps: " + steps + ")");
            }
        }
    }
}
