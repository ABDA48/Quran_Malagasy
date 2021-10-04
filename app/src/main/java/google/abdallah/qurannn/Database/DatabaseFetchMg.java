package google.abdallah.qurannn.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseFetchMg extends SQLiteAssetHelper {
    public static String DB_Name="koranmalagasy.db";
    public DatabaseFetchMg(Context context) {
        super(context, DB_Name, null, 1);
    }
}
