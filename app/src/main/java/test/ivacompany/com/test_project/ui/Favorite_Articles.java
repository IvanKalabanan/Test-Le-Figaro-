package test.ivacompany.com.test_project.ui;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import test.ivacompany.com.test_project.Adapters.ListAdapter;
import test.ivacompany.com.test_project.DBHelper;
import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.List_Articles;

public class Favorite_Articles extends Activity {
    private ListAdapter listAdapter;
    ArrayList<List_Articles> favotite = new ArrayList<>();
    ListView lv;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initialization();
        setData();
        listAdapter = new ListAdapter(this, R.id.list_viewMy, favotite);
        lv.setAdapter(listAdapter);
    }

    private void initialization() {
        setContentView(R.layout.favorite_activity);

        lv = (ListView) findViewById(R.id.list_viewMy);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(Favorite_Articles.this, Article_Activity.class);
                intent.putExtra("id", favotite.get(position).id);
                intent.putExtra("title", favotite.get(position).title);
                intent.putExtra("link", favotite.get(position).link);
                intent.putExtra("subtitle", favotite.get(position).subtitle);
                startActivity(intent);
            }
        });
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