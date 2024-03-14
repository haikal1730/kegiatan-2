import java.util.Scanner;

public class Admin {
    private SistemPerpustakaan sistemPerpustakaan;
    private Scanner scanner;

    public Admin(SistemPerpustakaan sistemPerpustakaan, Scanner scanner) {
        this.sistemPerpustakaan = sistemPerpustakaan;
        this.scanner = scanner;
    }

    public void login() {
        menuAdmin();
    }

    private void menuAdmin() {
        int pilihan;
        do {
            tampilkanMenuAdmin();
            System.out.print("Pilih (1-3): ");
            pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tambahMahasiswa();
                    break;
                case 2:
                    tampilkanMahasiswa();
                    break;
                case 3:
                    System.out.println("Sayonara");
                    break;
                default:
                    System.out.println("Ngawur");
            }
        } while (pilihan != 3);
    }

    private void tampilkanMenuAdmin() {
        System.out.println("\n===== Menu Admin =====");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Mahasiswa");
        System.out.println("3. Logout");
    }

    private void tambahMahasiswa() {
        scanner.nextLine();
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.nextLine();
        System.out.print("Masukkan Fakultas Mahasiswa: ");
        String fakultas = scanner.nextLine();
        System.out.print("Masukkan Program Studi Mahasiswa: ");
        String programStudi = scanner.nextLine();

        Mahasiswa mahasiswa = new Mahasiswa(nama, nim, fakultas, programStudi);
        sistemPerpustakaan.getDaftarMahasiswa()[sistemPerpustakaan.getJumlahMahasiswa()] = mahasiswa;
        sistemPerpustakaan.setJumlahMahasiswa(sistemPerpustakaan.getJumlahMahasiswa() + 1);

        System.out.println("Mahasiswa berhasil ditambahkan!");
    }

    private void tampilkanMahasiswa() {
        Mahasiswa[] mahasiswa = sistemPerpustakaan.getDaftarMahasiswa();
        System.out.println("\n===== Daftar Mahasiswa =====");
        for (int i = 0; i < sistemPerpustakaan.getJumlahMahasiswa(); i++) {
            System.out.println("Nama: " + mahasiswa[i].getNama());
            System.out.println("NIM: " + mahasiswa[i].getNim());
            System.out.println("Fakultas: " + mahasiswa[i].getFakultas());
            System.out.println("Program Studi: " + mahasiswa[i].getProgramStudi());
            System.out.println();
        }
    }
}