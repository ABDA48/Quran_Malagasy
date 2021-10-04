package google.abdallah.qurannn.Model;

public class loveModel {
    String nameofsurat;
    String verseNumber;
    String numberofjuz;
    String numberofsurat;

    public loveModel(String nameofsurat, String numberofsurat,String verseNumber, String numberofjuz) {
        this.nameofsurat = nameofsurat;
        this.verseNumber = verseNumber;
        this.numberofjuz = numberofjuz;
        this.numberofsurat=numberofsurat;
    }

    public String getNumberofsurat() {
        return numberofsurat;
    }

    public void setNumberofsurat(String numberofsurat) {
        this.numberofsurat = numberofsurat;
    }

    public loveModel() {
    }

    public String getNameofsurat() {
        return nameofsurat;
    }

    public void setNameofsurat(String nameofsurat) {
        this.nameofsurat = nameofsurat;
    }

    public String getVerseNumber() {
        return verseNumber;
    }

    public void setVerseNumber(String verseNumber) {
        this.verseNumber = verseNumber;
    }

    public String getNumberofjuz() {
        return numberofjuz;
    }

    public void setNumberofjuz(String numberofjuz) {
        this.numberofjuz = numberofjuz;
    }
}
