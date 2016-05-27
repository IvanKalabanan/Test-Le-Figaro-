package test.ivacompany.com.test_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
    public static String NAME_TABLE = "articles";

    public DBHelper(Context context) {
        super(context, "list_articles", null, 1);
    }


    public void onCreate(SQLiteDatabase db) {
        Log.d("data", "--- onCreate database ---");
        db.execSQL("create table articles ("
                + "id text ,"
                + "link text,"
                + "title text,"
                + "subtitle text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
