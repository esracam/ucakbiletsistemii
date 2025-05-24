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

