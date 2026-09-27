    package lw02.prelab;

import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class BankTransaction {
    public static void main(String[] args) throws Exception {
        LinkedList<String[]> transactionsList = new LinkedList<>();
        LinkedList<String[]> customersList = new LinkedList<>();
        Queue<String[]> queue = new LinkedList<>();
        Stack<String[]> failedStack = new Stack<>();

        
        Scanner scanner = new Scanner(new File("C:\\Users\\Lenovo Legion\\Documents\\ASD\\dsa-[5026251178]\\src\\lw02\\prelab\\transactions.txt"));
        
        while (scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().trim().split(" ");
            if (parts.length < 3) continue; 
            
            transactionsList.add(parts); 
            
            
            boolean exists = false;
            for (String[] c : customersList) {
                if (c[0].equals(parts[0])) exists = true;
            }
            if (!exists) customersList.add(new String[]{parts[0], "0"});
        }
        scanner.close();

        
        queue.addAll(transactionsList);

        while (!queue.isEmpty()) {
            String[] tx = queue.poll();
            
            
            for (String[] c : customersList) {
                if (c[0].equals(tx[0])) {
                    int balance = Integer.parseInt(c[1]);
                    int amount = Integer.parseInt(tx[2]);
                    
                    if (tx[1].equals("DEPOSIT")) {
                        c[1] = String.valueOf(balance + amount);
                    } else if (amount > balance) {
                        failedStack.push(tx); 
                    } else {
                        c[1] = String.valueOf(balance - amount);
                    }
                    break;
                }
            }
        }

        // 5. Cetak Output
        System.out.println("=== Final Balances ===");
        for (String[] c : customersList) System.out.println(c[0] + ": " + c[1]);

        System.out.println("=== Failed Transactions ===");
        while (!failedStack.isEmpty()) {
            String[] f = failedStack.pop();
            System.out.println(f[0] + " " + f[1] + " " + f[2]);
        }
    }
}