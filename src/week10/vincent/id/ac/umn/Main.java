package week10.vincent.id.ac.umn;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static ArrayList<Barang> listBarang = new ArrayList<>();
    static ArrayList<Order> listOrder = new ArrayList<>();
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        listBarang.add(new Handphone("H001", "iPhone 15", 20000000.0, 5, "Hitam"));
        listBarang.add(new Voucher("V001", "Google Play 50K", 50000.0, 10, 0.11));

        int pilihan;
        do {
            System.out.println("------------Menu Toko Voucher & HP------------");
            System.out.println("1. Pesan Barang");
            System.out.println("2. Lihat Pesanan");
            System.out.println("3. Barang Baru");
            System.out.print("Pilihan : ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    pesanBarang();
                    break;
                case 2:
                    lihatPesanan();
                    break;
                case 3:
                    tambahBarang();
                    break;
                default:
                    System.out.println("Input tidak valid!");
            }
            System.out.println();
        } while (pilihan >= 1 && pilihan <= 3);
    }

    public static void pesanBarang() {
        System.out.println("Daftar Barang Toko Voucher & HP");
        for (Barang b : listBarang) {
            System.out.println("------------------------------");
            b.displayInfo();
            if (b instanceof Handphone) {
                System.out.println("(Tipe: Handphone)");
            } else if (b instanceof Voucher) {
                System.out.println("(Tipe: Voucher)");
            }
        }
        System.out.println("------------------------------");
        System.out.print("Ketik 0 untuk batal\nPesan Barang (ID) : ");
        String id = input.next();
        if (id.equals("0")) return;

        Barang found = cariBarangById(id);
        if (found == null) {
            System.out.println("Pemilihan barang tidak sesuai");
            return;
        }

        System.out.print("Masukkan Jumlah : ");
        int jml = input.nextInt();
        if (jml <= 0 || jml > found.getStok()) {
            System.out.println("Stok tidak mencukupi");
            return;
        }

        double totalHarga;
        if (found instanceof Handphone) {
            Handphone hp = (Handphone) found;
            totalHarga = hp.getHarga() * jml;
            System.out.printf("%d x %s dengan total harga %.2f%n", jml, hp.getNama(), totalHarga);
            System.out.print("Masukkan jumlah uang : ");
            double uang = input.nextDouble();
            if (uang < totalHarga) {
                System.out.println("Jumlah uang tidak mencukupi");
                return;
            }
            hp.minusStok(jml);
            Order o = new Order(String.format("%03d", Order.total + 1), hp, jml);
            listOrder.add(o);
            System.out.println("Berhasil dipesan");
        } else if (found instanceof Voucher) {
            Voucher v = (Voucher) found;
            totalHarga = v.getHargaJual() * jml;
            System.out.printf("%d x %s dengan total harga %.2f%n", jml, v.getNama(), totalHarga);
            System.out.print("Masukkan jumlah uang : ");
            double uang = input.nextDouble();
            if (uang < totalHarga) {
                System.out.println("Jumlah uang tidak mencukupi");
                return;
            }
            v.minusStok(jml);
            Order o = new Order(String.format("%03d", Order.total + 1), v, jml);
            listOrder.add(o);
            System.out.println("Berhasil dipesan");
        } else {
            System.out.println("Tipe barang tidak dikenali");
        }
    }

    public static void lihatPesanan() {
        System.out.println("--------Daftar Pesanan Toko Multiguna--------");
        for (Order o : listOrder) {
            System.out.println("ID : " + o.getId());
            if (o.getHandphone() != null) {
                Handphone h = o.getHandphone();
                System.out.println("Nama : " + h.getNama());
                System.out.println("Jumlah : " + o.getJumlah());
                System.out.println("Total : " + (h.getHarga() * o.getJumlah()));
            } else if (o.getVoucher() != null) {
                Voucher v = o.getVoucher();
                System.out.println("Nama : " + v.getNama());
                System.out.println("Jumlah : " + o.getJumlah());
                System.out.println("Total : " + (v.getHargaJual() * o.getJumlah()));
            }
            System.out.println("---------------------------------------------");
        }
        System.out.println("Total Order: " + Order.total);
    }

    public static void tambahBarang() {
        System.out.print("Voucher / Handphone (V/H): ");
        char tipe = input.next().toLowerCase().charAt(0);

        if (tipe == 'v') {
            input.nextLine();
            System.out.print("Nama : ");
            String nama = input.nextLine();
            System.out.print("Harga : ");
            double harga = input.nextDouble();
            System.out.print("Stok : ");
            int stok = input.nextInt();
            System.out.print("PPN (misal 0.11 untuk 11%) : ");
            double pajak = input.nextDouble();
            String id = "V" + String.format("%03d", countExistingType("V") + 1);
            Voucher v = new Voucher(id, nama, harga, stok, pajak);
            listBarang.add(v);
            System.out.println("Voucher telah berhasil diinput dengan ID: " + id);
        } else if (tipe == 'h') {
            input.nextLine();
            System.out.print("Nama : ");
            String nama = input.nextLine();
            System.out.print("Harga : ");
            double harga = input.nextDouble();
            System.out.print("Stok : ");
            int stok = input.nextInt();
            System.out.print("Warna : ");
            String warna = input.next();
            String id = "H" + String.format("%03d", countExistingType("H") + 1);
            Handphone hp = new Handphone(id, nama, harga, stok, warna);
            listBarang.add(hp);
            System.out.println("Handphone telah berhasil diinput dengan ID: " + id);
        } else {
            System.out.println("Input tidak valid");
        }
    }

    public static Barang cariBarangById(String id) {
        for (Barang b : listBarang) {
            if (b.getId().equalsIgnoreCase(id)) return b;
        }
        return null;
    }

    private static int countExistingType(String prefix) {
        int count = 0;
        for (Barang b : listBarang) {
            if (b.getId() != null && b.getId().toUpperCase().startsWith(prefix.toUpperCase())) {
                count++;
            }
        }
        return count;
    }
}