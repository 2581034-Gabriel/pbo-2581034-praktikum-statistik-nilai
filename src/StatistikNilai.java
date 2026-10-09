import java.util.ArrayList;
import java.util.Scanner;

public class StatistikNilai {

    // Penanda "sudah selesai" (sentinel), sama seperti RekapNilai minggu lalu
    static final int SELESAI = -1;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Nilai DISIMPAN di ArrayList (tidak langsung dijumlahkan) supaya bisa ditanya lebih dari sekali.
        // Tipe di dalam <...> harus Integer, bukan int.
        ArrayList<Integer> daftar = new ArrayList<>();

        System.out.println("===== STATISTIK NILAI KELAS =====");
        System.out.println("Ketik " + SELESAI + " kalau sudah selesai.");

        int nilai;
        do {
            // Nomor prompt diambil dari size() + 1, bukan dari variabel pencacah terpisah
            System.out.print("Nilai ke-" + (daftar.size() + 1) + " : ");
            nilai = input.nextInt();

            // -1 hanya penanda selesai, bukan nilai: jangan ditolak dan jangan disimpan.
            // continue melompat ke pengecekan while di bawah, yang sekarang false, jadi loop berhenti.
            if (nilai == SELESAI) {
                continue;
            }

            // Nilai di luar 0-100 ditolak. continue melompat ke while TANPA add,
            // jadi size() tidak berubah dan nomor yang diminta tetap sama.
            if (nilai < 0 || nilai > 100) {
                System.out.println("  Ditolak, harus 0-100");
                continue;
            }

            daftar.add(nilai);
        } while (nilai != SELESAI);

        System.out.println();

        // Kasus tidak ada nilai sah sama sekali (langsung diketik -1): cetak pesan, bukan error
        if (daftar.isEmpty()) {
            System.out.println("Tidak ada nilai sah yang dimasukkan, jadi tidak ada statistik yang bisa dihitung.");
            return;
        }

        System.out.println("Nilai tersimpan : " + daftar);
        System.out.println("Jumlah          : " + daftar.size());
    }
}