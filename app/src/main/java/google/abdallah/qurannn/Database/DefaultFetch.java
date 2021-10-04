package google.abdallah.qurannn.Database;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DefaultFetch extends SQLiteAssetHelper {
    public static String DB_Name="android.db";
    public DefaultFetch(Context context) {
        super(context, DB_Name, null, 1);
    }
}
