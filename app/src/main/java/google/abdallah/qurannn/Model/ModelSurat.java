package google.abdallah.qurannn.Model;

import android.net.Uri;

import java.util.List;

public class ModelSurat {
  private   String numberSurah;
  private   String SurahName;
 private    String SurahCity;
  private   String numberVerse;
 private    String SurahNameAr;
 private    int downorup;
private  boolean isExpanded;
private Uri uri;
private String juz;

    public String getJuz() {
        return juz;
    }

    public void setJuz(String juz) {
        this.juz = juz;
    }

    private List<ModelSearch> modelSearchList;

    public ModelSurat(String surahName, List<ModelSearch> modelSearchList) {
        SurahName = surahName;
        this.modelSearchList = modelSearchList;
        isExpanded=false;
    }

    public Uri getUri() {
        return uri;
    }

    public void setUri(Uri uri) {
        this.uri = uri;
    }

    public ModelSurat(String SurahNameAr) {
        SurahName = SurahNameAr;
    }

    public List<ModelSearch> getModelSearchList() {
        return modelSearchList;
    }

    public void setModelSearchList(List<ModelSearch> modelSearchList) {
        this.modelSearchList = modelSearchList;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public ModelSurat() {
        isExpanded=false;
    }

    public ModelSurat(String numberSurah, String surahName, String surahNameAr) {
        this.numberSurah = numberSurah;
        SurahName = surahName;
        SurahNameAr = surahNameAr;
    }

    public ModelSurat(String numberSurah, String surahName, String surahCity, String numberVerse, String surahNameAr, int downorup) {
        this.numberSurah = numberSurah;
        SurahName = surahName;
        SurahCity = surahCity;
        this.numberVerse = numberVerse;
        SurahNameAr = surahNameAr;
        this.downorup = downorup;
    }

    public String getNumberSurah() {
        return numberSurah;
    }

    public void setNumberSurah(String numberSurah) {
        this.numberSurah = numberSurah;
    }

    public String getSurahName() {
        return SurahName;
    }

    public void setSurahName(String surahName) {
        SurahName = surahName;
    }

    public String getSurahCity() {
        return SurahCity;
    }

    public void setSurahCity(String surahCity) {
        SurahCity = surahCity;
    }

    public String getNumberVerse() {
        return numberVerse;
    }

    public void setNumberVerse(String numberVerse) {
        this.numberVerse = numberVerse;
    }

    public String getSurahNameAr() {
        return SurahNameAr;
    }

    public void setSurahNameAr(String surahNameAr) {
        SurahNameAr = surahNameAr;
    }

    public int getDownorup() {
        return downorup;
    }

    public void setDownorup(int downorup) {
        this.downorup = downorup;
    }
}
