package week10.vincent.id.ac.umn;

public class Order {
    private String id;
    private Voucher voucher;
    private Handphone handphone;
    private int jumlah;
    public static int total = 0;

    public Order(String idSuffix, Voucher voucher, int jumlah) {
        this.id = "OV" + idSuffix;
        this.voucher = voucher;
        this.jumlah = jumlah;
        total++;
    }

    public Order(String idSuffix, Handphone handphone, int jumlah) {
        this.id = "OH" + idSuffix;
        this.handphone = handphone;
        this.jumlah = jumlah;
        total++;
    }

    public String getId() {
        return id;
    }

    public Voucher getVoucher() {
        return voucher;
    }

    public Handphone getHandphone() {
        return handphone;
    }

    public int getJumlah() {
        return jumlah;
    }

    public double hitungTotal() {
        if (voucher != null) return voucher.getHargaJual() * jumlah;
        if (handphone != null) return handphone.getHarga() * jumlah;
        return 0;
    }
}