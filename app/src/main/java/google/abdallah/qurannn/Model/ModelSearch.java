package google.abdallah.qurannn.Model;

import java.util.List;

public class ModelSearch  {
    String number;
    String ayat;

    public ModelSearch() {
    }

    public ModelSearch(String number, String ayat) {
        this.number = number;
        this.ayat = ayat;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAyat() {
        return ayat;
    }

    public void setAyat(String ayat) {
        this.ayat = ayat;
    }
}
