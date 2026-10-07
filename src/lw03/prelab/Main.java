package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // ==========================================
        // Problem 1: Playlist Lagu (List)
        // ==========================================
        System.out.println("===== Problem 1 =====");
        List<String> playlist = new ArrayList<>();
        File file1 = new File("playlist.txt");
        Scanner sc1 = new Scanner(file1);

        while (sc1.hasNextLine()) {
            String baris = sc1.nextLine().trim();
            if (baris.isEmpty()) {
                continue;
            }

            String[] kata = baris.split(" ");
            String perintah = kata[0];

            if (perintah.equals("ADD")) {
                // Judul lagu berada setelah teks "ADD "
                String judulLagu = baris.substring(4);
                playlist.add(judulLagu);
            } else if (perintah.equals("INSERT")) {
                int indeks = Integer.parseInt(kata[1]);
                // Judul lagu berada setelah teks "INSERT <indeks> "
                String judulLagu = baris.substring(kata[0].length() + 1 + kata[1].length() + 1);
                playlist.add(indeks, judulLagu);
            } else if (perintah.equals("REMOVE")) {
                // Judul lagu berada setelah teks "REMOVE "
                String judulLagu = baris.substring(7);
                playlist.remove(judulLagu);
            }
        }
        sc1.close();

        System.out.println("Total songs: " + playlist.size());
        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }

        // ==========================================
        // Problem 2: Peserta Workshop (Set)
        // ==========================================
        System.out.println("===== Problem 2 =====");
        Set<String> peserta = new LinkedHashSet<>();
        int duplikat = 0;

        File file2 = new File("participants.txt");
        Scanner sc2 = new Scanner(file2);

        while (sc2.hasNextLine()) {
            String nama = sc2.nextLine().trim();
            if (nama.isEmpty()) {
                continue;
            }

            if (peserta.contains(nama)) {
                duplikat++;
            } else {
                peserta.add(nama);
            }
        }
        sc2.close();

        System.out.println("Unique participants: " + peserta.size());
        int nomor = 1;
        for (String nama : peserta) {
            System.out.println(nomor + ". " + nama);
            nomor++;
        }
        System.out.println("Duplicate registrations: " + duplikat);

        // ==========================================
        // Problem 3: Inventaris Produk (Map)
        // ==========================================
        System.out.println("===== Problem 3 =====");
        Map<String, Integer> stok = new LinkedHashMap<>();
        int penjualanGagal = 0;

        File file3 = new File("inventory.txt");
        Scanner sc3 = new Scanner(file3);

        while (sc3.hasNextLine()) {
            String baris = sc3.nextLine().trim();
            if (baris.isEmpty()) {
                continue;
            }

            String[] kata = baris.split(" ");
            String tipe = kata[0];
            String namaProduk = kata[1];
            int jumlah = Integer.parseInt(kata[2]);

            if (tipe.equals("ADD")) {
                if (stok.containsKey(namaProduk)) {
                    int stokLama = stok.get(namaProduk);
                    stok.put(namaProduk, stokLama + jumlah);
                } else {
                    stok.put(namaProduk, jumlah);
                }
            } else if (tipe.equals("SELL")) {
                if (stok.containsKey(namaProduk)) {
                    int stokSekarang = stok.get(namaProduk);
                    if (stokSekarang >= jumlah) {
                        stok.put(namaProduk, stokSekarang - jumlah);
                    } else {
                        penjualanGagal++;
                    }
                } else {
                    penjualanGagal++;
                }
            }
        }
        sc3.close();

        for (String namaProduk : stok.keySet()) {
            System.out.println(namaProduk + ": " + stok.get(namaProduk));
        }
        System.out.println("Failed sales: " + penjualanGagal);
    }
}   