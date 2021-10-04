package google.abdallah.qurannn.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.http.SslCertificate;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseFetchfr extends SQLiteAssetHelper {
    public static String DB_Name="quran.fr.db";
    public DatabaseFetchfr(Context context) {
        super(context, DB_Name, null, 1);
    }
}
