package google.abdallah.qurannn.Model;

import java.util.List;

public class BookmarkModel  {
    String title;

    String nameofsurat;
    String verseNumber;

    public BookmarkModel() {
    }

    public BookmarkModel(String title, String nameofsurat, String verseNumber) {
        this.title = title;
        this.nameofsurat = nameofsurat;
        this.verseNumber = verseNumber;

    }

    public String getNameofsurat() {
        return nameofsurat;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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




}
