package google.abdallah.qurannn.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseTrack extends SQLiteAssetHelper {
    public static String DB_Name="QuranAudio.db";
    public DatabaseTrack(Context context) {
        super(context, DB_Name, null, 1);
    }
}
