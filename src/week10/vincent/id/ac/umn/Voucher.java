package week10.vincent.id.ac.umn;

public class Voucher extends Barang {
    private double pajak;
    public static int total = 0;

    public Voucher(String id, String nama, double harga, int stok, double pajak) {
        super(id, nama, harga, stok);
        this.pajak = pajak;
        total++;
    }

    public double getPajak() {
        return pajak;
    }

    public double getHargaJual() {
        return harga + (harga * pajak);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Pajak : " + pajak);
        System.out.println("Harga Jual : " + getHargaJual());
    }
}