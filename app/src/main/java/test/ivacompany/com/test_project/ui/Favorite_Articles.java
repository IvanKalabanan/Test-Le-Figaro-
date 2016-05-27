package test.ivacompany.com.test_project.ui;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import test.ivacompany.com.test_project.Adapters.Adap;
import test.ivacompany.com.test_project.DBHelper;
import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.List_Articles;

public class Favorite_Articles extends Activity {
    ArrayList<List_Articles> favotite = new ArrayList<>();
    RecyclerView lv;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        setData();
        setAdapter();
            }

    private void setAdapter(){
        Adap adapter = new Adap(this, favotite);
        lv.setAdapter(adapter);
    }

    private void initialization() {
        setContentView(R.layout.favorite_activity);

        lv = (RecyclerView) findViewById(R.id.list_viewMy);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        lv.setLayoutManager(llm);


    }

    private void setData() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query(DBHelper.NAME_TABLE, null, null, null, null, null, null);

        if (c.moveToLast()) {

            int id = c.getColumnIndex("id");
            int link = c.getColumnIndex("link");
            int title = c.getColumnIndex("title");
            int subtitle = c.getColumnIndex("subtitle");

            do {
                favotite.add(new List_Articles(c.getString(id), "", "", "", 0, c.getString(title),
                        c.getString(subtitle), c.getString(link)));
            } while (c.moveToPrevious());
        }
        c.close();
    }
}