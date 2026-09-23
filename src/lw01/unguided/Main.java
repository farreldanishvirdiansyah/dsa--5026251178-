package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File file = new File("washes.txt");

        try (Scanner scanner = new Scanner(file)) {
            if (!scanner.hasNextInt()) {
                return;
            }

            int count = scanner.nextInt();
            WashService[] services = new WashService[count];
            int[] unitsArray = new int[count];

            for (int i = 0; i < count; i++) {
                String type = scanner.next();
                String id = scanner.next();
                int days = scanner.nextInt();
                int units = scanner.nextInt();

                unitsArray[i] = units;

                if (type.equalsIgnoreCase("MOTORCYCLE")) {
                    services[i] = new MotorcycleWash(id, days);
                } else if (type.equalsIgnoreCase("CAR")) {
                    services[i] = new CarWash(id, days);
                }
            }

            
            for (int i = 0; i < services.length; i++) {
                WashService s = services[i];
                int totalCharge = s.calculateCharge(unitsArray[i]);
                System.out.println(s.getId() + " | " + s.label() + " | " + totalCharge);
            }

        } catch (FileNotFoundException e) {
            System.err.println("File washes.txt tidak ditemukan: " + e.getMessage());
        }
    }
}