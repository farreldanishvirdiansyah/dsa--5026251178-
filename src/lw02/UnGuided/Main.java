package lw02.UnGuided;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        LinkedList<String[]> orders = new LinkedList<>();

        InputStream inputStream = Main.class.getResourceAsStream("orders.txt");
        if (inputStream == null) {
            inputStream = Main.class.getResourceAsStream("/lw02/UnGuided/orders.txt");
        }
        if (inputStream == null) {
            File file = new File("orders.txt");
            if (!file.exists()) file = new File("src/lw02/UnGuided/orders.txt");
            if (file.exists()) {
                try {
                    inputStream = new FileInputStream(file);
                } catch (Exception ignored) {}
            }
        }

        Scanner scanner = new Scanner(inputStream != null ? inputStream : System.in);
        while (scanner.hasNext()) {
            orders.add(new String[]{scanner.next(), scanner.next(), scanner.next(), scanner.next()});
        }
        scanner.close();

        LinkedList<String[]> foods = new LinkedList<>();
        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        LinkedList<String[]> drinks = new LinkedList<>();
        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        Queue<String[]> queue = new LinkedList<>(orders);
        Stack<String[]> failed = new Stack<>();
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        while (!queue.isEmpty()) {
            String[] order = queue.poll();
            String sideDish = order[1];
            String drink = order[2];

            String[] foodItem = null;
            if (!sideDish.equals("-")) {
                for (String[] f : foods) {
                    if (f[0].equals(sideDish)) {
                        foodItem = f;
                        break;
                    }
                }
            }
            boolean foodAvailable = sideDish.equals("-") || (foodItem != null && Integer.parseInt(foodItem[1]) > 0);

            String[] drinkItem = null;
            if (!drink.equals("-")) {
                for (String[] d : drinks) {
                    if (d[0].equals(drink)) {
                        drinkItem = d;
                        break;
                    }
                }
            }
            boolean drinkAvailable = drink.equals("-") || (drinkItem != null && Integer.parseInt(drinkItem[1]) > 0);

            if (foodAvailable && drinkAvailable) {
                if (foodItem != null) {
                    foodItem[1] = String.valueOf(Integer.parseInt(foodItem[1]) - 1);
                }
                if (drinkItem != null) {
                    drinkItem[1] = String.valueOf(Integer.parseInt(drinkItem[1]) - 1);
                }
                successfulOrders.add(order);
            } else {
                failed.push(order);
            }
        }

        System.out.println("=== Successfully Processed Orders ===");
        for (String[] order : successfulOrders) {
            System.out.println(String.join(" ", order));
        }

        System.out.println("\n=== Remaining Food Stock ===");
        for (String[] food : foods) {
            System.out.println(food[0] + " : " + food[1]);
        }

        System.out.println("\n=== Remaining Drink Stock ===");
        for (String[] drink : drinks) {
            System.out.println(drink[0] + " : " + drink[1]);
        }

        System.out.println("\n=== Failed Orders ===");
        while (!failed.isEmpty()) {
            System.out.println(String.join(" ", failed.pop()));
        }
    }
}