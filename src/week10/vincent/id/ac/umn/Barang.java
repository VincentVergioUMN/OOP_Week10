package week10.vincent.id.ac.umn;

public class Barang {
    protected String id;
    protected double harga;
    protected String nama;
    protected int stok;

    public Barang(String id, String nama, double harga, int stok) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getId() {
        return id;
    }

    public String getNama() {
        return nama;
    }

    public double getHarga() {
        return harga;
    }

    public int getStok() {
        return stok;
    }

    public void minusStok(int jumlah) {
        this.stok -= jumlah;
    }

    public void displayInfo() {
        System.out.println("ID : " + id);
        System.out.println("Nama : " + nama);
        System.out.println("Harga : " + harga);
        System.out.println("Stok : " + stok);
    }
}