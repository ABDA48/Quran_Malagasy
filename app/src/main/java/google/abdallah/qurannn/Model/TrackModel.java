package google.abdallah.qurannn.Model;

import android.net.Uri;

public class TrackModel {
    String ayah;
    String qirah;
    String uri;
    String juz;
    String surah;
    public TrackModel() {
    }

    public TrackModel(String ayah, String qirah, String uri) {
        this.ayah = ayah;
        this.qirah = qirah;
        this.uri = uri;
    }

    public String getJuz() {
        return juz;
    }

    public void setJuz(String juz) {
        this.juz = juz;
    }

    public String getSurah() {
        return surah;
    }

    public void setSurah(String surah) {
        this.surah = surah;
    }

    public String getAyah() {
        return ayah;
    }

    public void setAyah(String ayah) {
        this.ayah = ayah;
    }

    public String getQirah() {
        return qirah;
    }

    public void setQirah(String qirah) {
        this.qirah = qirah;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
