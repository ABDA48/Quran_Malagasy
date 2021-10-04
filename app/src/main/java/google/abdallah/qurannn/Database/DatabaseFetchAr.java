package google.abdallah.qurannn.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseFetchAr extends SQLiteAssetHelper {
    public static String DB_Name="quran.ar.db";
    public DatabaseFetchAr(Context context) {
        super(context, DB_Name, null, 1);
    }
}
