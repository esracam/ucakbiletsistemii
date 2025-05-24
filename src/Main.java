public class Ucak {
    String model;
    String marka;
    String seriNo;
    int koltukKapasitesi;

    public Ucak(String model, String marka, String seriNo, int koltukKapasitesi) {
        this.model = model;
        this.marka = marka;
        this.seriNo = seriNo;
        this.koltukKapasitesi = koltukKapasitesi;
    }

    public String toString() {
        return marka + " " + model + " [" + seriNo + "] Kapasite: " + koltukKapasitesi;
    }
}
public class Lokasyon {
    String ulke;
    String sehir;
    String havaalani;
    boolean aktif;

    public Lokasyon(String ulke, String sehir, String havaalani, boolean aktif) {
        this.ulke = ulke;
        this.sehir = sehir;
        this.havaalani = havaalani;
        this.aktif = aktif;
    }

    public String toString() {
        return sehir + ", " + ulke + " - " + havaalani + (aktif ? " (Aktif)" : " (Pasif)");
    }
}
import java.time.LocalDateTime;

public class Ucus {
    Lokasyon lokasyon;
    LocalDateTime saat;
    Ucak ucak;
    int doluKoltuk;

    public Ucus(Lokasyon lokasyon, LocalDateTime saat, Ucak ucak) {
        this.lokasyon = lokasyon;
        this.saat = saat;
        this.ucak = ucak;
        this.doluKoltuk = 0;
    }

    public boolean rezervasyonYap() {
        if (doluKoltuk < ucak.koltukKapasitesi) {
            doluKoltuk++;
            return true;
        }
        return false;
    }

    public String toString() {
        return lokasyon + " | Tarih: " + saat + " | Uçak: " + ucak;
    }
}
public class Rezervasyon {
    Ucus ucus;
    String ad;
    String soyad;
    int yas;

    public Rezervasyon(Ucus ucus, String ad, String soyad, int yas) {
        this.ucus = ucus;
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
    }

    public String toString() {
        return "Ad: " + ad + " " + soyad + " | Yaş: " + yas + " | Uçuş: " + ucus.toString();
    }

    public String toCSV() {
        return ad + "," + soyad + "," + yas + "," + ucus.lokasyon.sehir + "," + ucus.saat;
    }
}
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Ucus> ucuslar = new ArrayList<>();
        ArrayList<Rezervasyon> rezervasyonlar = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        // Uçak ve Lokasyon örnekleri oluştur
        Ucak ucak1 = new Ucak("A320", "Airbus", "SN123", 3);
        Lokasyon lokasyon1 = new Lokasyon("Türkiye", "İstanbul", "İstanbul Havalimanı", true);
        Ucus ucus1 = new Ucus(lokasyon1, LocalDateTime.of(2025, 6, 1, 12, 30), ucak1);

        ucuslar.add(ucus1);

        System.out.println("=== Mevcut Uçuşlar ===");
        for (int i = 0; i < ucuslar.size(); i++) {
            System.out.println((i + 1) + ". " + ucuslar.get(i));
        }

        System.out.print("Lütfen rezervasyon yapmak istediğiniz uçuş numarasını seçin: ");
        int secim = scanner.nextInt();
        scanner.nextLine(); // dummy read

        if (secim < 1 || secim > ucuslar.size()) {
            System.out.println("Geçersiz seçim.");
            return;
        }

        Ucus secilenUcus = ucuslar.get(secim - 1);

        if (secilenUcus.rezervasyonYap()) {
            System.out.print("Adınız: ");
            String ad = scanner.nextLine();
            System.out.print("Soyadınız: ");
            String soyad = scanner.nextLine();
            System.out.print("Yaşınız: ");
            int yas = scanner.nextInt();

            Rezervasyon rez = new Rezervasyon(secilenUcus, ad, soyad, yas);
            rezervasyonlar.add(rez);

            System.out.println("Rezervasyon başarıyla yapıldı!");
            System.out.println(rez);

            // CSV'ye yaz
            try (FileWriter writer = new FileWriter("rezervasyonlar.csv", true)) {
                writer.write(rez.toCSV() + "\n");
            } catch (IOException e) {
                System.out.println("Dosyaya yazma hatası: " + e.getMessage());
            }

        } else {
            System.out.println("Üzgünüz, uçakta boş koltuk kalmamış.");
        }
    }
}

