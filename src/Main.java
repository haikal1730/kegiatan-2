import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SistemPerpustakaan sistemPerpustakaan = new SistemPerpustakaan();
        sistemPerpustakaan.jalankan();
    }
}

class SistemPerpustakaan {
    private Buku[] daftarBuku = new Buku[100];
    private Mahasiswa[] daftarMahasiswa = new Mahasiswa[100];
    private int jumlahMahasiswa = 0;
    private int jumlahBuku = 0;

    public void jalankan() {
        isiBukuAwal();

        int pilihan;
        Scanner scanner = new Scanner(System.in);

        do {
            tampilkanMenu();
            System.out.print("Miliho (1-3): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    Admin admin = new Admin(this, scanner);
                    admin.login();
                    break;
                case 2:
                    SistemMahasiswa sistemMahasiswa = new SistemMahasiswa(this, scanner);
                    sistemMahasiswa.login();
                    break;
                case 3:
                    System.out.println("Sayonara");
                    break;
                default:
                    System.out.println("Ngawur");
            }
        } while (pilihan != 3);

        scanner.close();
    }

    private void isiBukuAwal() {
        tambahBuku(new Buku("001", "BUMI", "TERE LIYE", 5));
        tambahBuku(new Buku("002", "BULAN", "TERE LIYE", 5));
        tambahBuku(new Buku("003", "MATAHARI", "TERE LIYE", 5));
        tambahBuku(new Buku("004", "BINTANG", "TERE LIYE", 5));
        tambahBuku(new Buku("005", "CEROS DAN BATOZAR", "TERE LIYE", 5));
        tambahBuku(new Buku("006", "KOMET", "TERE LIYE", 5));
        tambahBuku(new Buku("007", "KOMET MINOR", "TERE LIYE", 5));
        tambahBuku(new Buku("008", "SELENA", "TERE LIYE", 5));
        tambahBuku(new Buku("009", "NEBULA", "TERE LIYE", 5));
        tambahBuku(new Buku("010", "SI PUTIH", "TERE LIYE", 5));
        tambahBuku(new Buku("011", "LUMPU", "TERE LIYE", 5));
        tambahBuku(new Buku("012", "BIBI GIL", "TERE LIYE", 5));
        tambahBuku(new Buku("013", "SAGARAS", "TERE LIYE", 5));
    }

    public void tambahBuku(Buku buku) {
        if (jumlahBuku < daftarBuku.length) {
            daftarBuku[jumlahBuku++] = buku;
        } else {
            System.out.println("Daftar buku sudah penuh. Tidak dapat menambahkan buku lagi.");
        }
    }

    public Buku[] getDaftarBuku() {
        return daftarBuku;
    }

    public void setDaftarBuku(Buku[] daftarBuku) {
        this.daftarBuku = daftarBuku;
    }

    public Mahasiswa[] getDaftarMahasiswa() {
        return daftarMahasiswa;
    }

    public void setDaftarMahasiswa(Mahasiswa[] daftarMahasiswa) {
        this.daftarMahasiswa = daftarMahasiswa;
    }

    public int getJumlahMahasiswa() {
        return jumlahMahasiswa;
    }

    public void setJumlahMahasiswa(int jumlahMahasiswa) {
        this.jumlahMahasiswa = jumlahMahasiswa;
    }

    public int getJumlahBuku() {
        return jumlahBuku;
    }

    public void setJumlahBuku(int jumlahBuku) {
        this.jumlahBuku = jumlahBuku;
    }

    private void tampilkanMenu() {
        System.out.println("===== Sistem Perpustakaan =====");
        System.out.println("1. Login sebagai Admin");
        System.out.println("2. Login sebagai Mahasiswa");
        System.out.println("3. Keluar");
    }
}

class SistemMahasiswa {
    private SistemPerpustakaan sistemPerpustakaan;
    private Scanner scanner;

    public SistemMahasiswa(SistemPerpustakaan sistemPerpustakaan, Scanner scanner) {
        this.sistemPerpustakaan = sistemPerpustakaan;
        this.scanner = scanner;
    }

    public void login() {

        menuMahasiswa();
    }

    private void menuMahasiswa() {
        int pilihan;
        do {
            tampilkanMenuMahasiswa();
            System.out.print("Miliho (1-4): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanDaftarBuku();
                    break;
                case 2:
                    pinjamBuku();
                    break;
                case 3:
                    kembalikanBuku();
                    break;
                case 4:
                    System.out.println("Sayonara");
                    break;
                default:
                    System.out.println("Ngawur");
            }
        } while (pilihan != 4);
    }

    private void tampilkanMenuMahasiswa() {
        System.out.println("\n===== Menu Mahasiswa =====");
        System.out.println("1. Tampilkan Daftar Buku");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Logout");
    }

    private void tampilkanDaftarBuku() {
        Buku[] buku = sistemPerpustakaan.getDaftarBuku();
        System.out.println("\n===== Daftar Buku =====");
        for (int i = 0; i < sistemPerpustakaan.getJumlahBuku(); i++) {
            System.out.println("ID: " + buku[i].getId());
            System.out.println("Judul: " + buku[i].getJudul());
            System.out.println("Penulis: " + buku[i].getPenulis());
            System.out.println("Jumlah: " + buku[i].getJumlah());
            System.out.println();
        }
    }

    private void pinjamBuku() {
        System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
        String idBuku = scanner.next();
        Buku buku = cariBukuById(idBuku);
        if (buku != null && buku.getJumlah() > 0) {
            buku.setJumlah(buku.getJumlah() - 1);
            System.out.println("Buku berhasil dipinjam!");
        } else {
            System.out.println("Stok buku habis atau buku tidak ditemukan.");
        }
    }

    private void kembalikanBuku() {
        System.out.print("Masukkan ID Buku yang ingin dikembalikan: ");
        String idBuku = scanner.next();
        Buku buku = cariBukuById(idBuku);
        if (buku != null) {
            buku.setJumlah(buku.getJumlah() + 1);
            System.out.println("Buku berhasil dikembalikan!");
        } else {
            System.out.println("Buku tidak ditemukan.");
        }
    }

    private Buku cariBukuById(String id) {
        for (int i = 0; i < sistemPerpustakaan.getJumlahBuku(); i++) {
            if (sistemPerpustakaan.getDaftarBuku()[i].getId().equals(id)) {
                return sistemPerpustakaan.getDaftarBuku()[i];
            }
        }
        return null;
    }
}

