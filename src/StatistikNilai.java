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

        // ===== PUTARAN PERTAMA: total, tertinggi, terendah =====
        // Tertinggi DAN terendah sama-sama dimulai dari NILAI PERTAMA di daftar (daftar.get(0)), bukan dari 0.
        // Kalau terendah dimulai dari 0, hasilnya selalu 0: tidak ada nilai ujian yang lebih kecil dari 0,
        // jadi "x < terendah" tidak pernah benar. Untuk tertinggi, mulai dari 0 kebetulan masih benar,
        // tapi nilai pertama aman untuk keduanya dan pasti berasal dari data yang ada.
        // get(0) aman di sini karena daftar sudah dipastikan tidak kosong.
        int total = 0;
        int tertinggi = daftar.get(0);
        int terendah = daftar.get(0);

        for (int x : daftar) {
            total += x;
            if (x > tertinggi) {
                tertinggi = x;
            }
            if (x < terendah) {
                terendah = x;
            }
        }

        // (double) dulu supaya pembagian tidak dibulatkan seperti pembagian int
        double rataRata = (double) total / daftar.size();

        // String.format mengikuti locale laptop; locale Indonesia memakai koma (75,50)
        System.out.println("Rata-rata       : " + String.format("%.2f", rataRata));
        System.out.println("Tertinggi       : " + tertinggi);
        System.out.println("Terendah        : " + terendah);

        // ===== PUTARAN KEDUA: jumlah mahasiswa di atas rata-rata =====
        // Harus putaran kedua, tidak bisa dihitung di putaran pertama: rata-rata baru diketahui setelah
        // SEMUA nilai dijumlahkan (putaran pertama selesai). Saat nilai pertama (misalnya 85) dibaca,
        // nilai sesudahnya belum terlihat, jadi rata-ratanya belum ada dan 85 belum bisa dibandingkan.
        int diAtasRataRata = 0;
        for (int x : daftar) {
            if (x > rataRata) {
                diAtasRataRata++;
            }
        }
        System.out.println("Di atas rata2   : " + diAtasRataRata + " orang");
    }
}g