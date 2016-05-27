package test.ivacompany.com.test_project.ui;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.text.format.DateFormat;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import test.ivacompany.com.test_project.DBHelper;
import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.Article;
import test.ivacompany.com.test_project.entity.LinkList;
import test.ivacompany.com.test_project.entity.Wrapper;
import test.ivacompany.com.test_project.roboCLass.BaseSpiceActivityNoFragment;
import test.ivacompany.com.test_project.task.GetArticleDataRequest;

public class Article_Activity extends BaseSpiceActivityNoFragment implements View.OnClickListener {
    ArrayList<Article> article;
    GetArticlesDataRequestListener getArticlesDataRequestListener = new GetArticlesDataRequestListener();
    WebView webView;
    TextView authorTextView, dateTextView, titleTextView;
    DBHelper dbHelper;
    FloatingActionButton favoriteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String idArticle = getIntent().getStringExtra("id");
        getSpiceManager().execute(new GetArticleDataRequest(LinkList.GetPassToArticle(idArticle)), getArticlesDataRequestListener);

        initialization();
    }

    private void initialization() {
        setContentView(R.layout.activity_article);
        authorTextView = (TextView) findViewById(R.id.text_Author);
        dateTextView = (TextView) findViewById(R.id.text_Date);
        titleTextView = (TextView) findViewById(R.id.titleText);
        webView = (WebView) findViewById(R.id.WebViewMy);
        favoriteButton = (FloatingActionButton) findViewById(R.id.fab);
        favoriteButton.setOnClickListener(this);
        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View v) {

        if (checkInDatabase()) {
            deleteFromDatabase();
        } else {
            addToDatabase();
        }

    }

    private void setData() {
        String im = "<img src=\"" + getIntent().getStringExtra("link") + "\" \">";
        String subtitle = "<p>" + getIntent().getStringExtra("subtitle") + "</p>";
        String s = im + subtitle + article.get(0).content; // insert into html code tag img for easy scrolling

        titleTextView.setText(getIntent().getStringExtra("title"));
        authorTextView.setText(article.get(0).author);
        dateTextView.setText(getDate(article.get(0).date));
        webView.loadDataWithBaseURL(null, s, "text/html", "en_US", null);
    }

    public final class GetArticlesDataRequestListener implements RequestListener<Wrapper> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(Article_Activity.this, "Request_Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Wrapper result) {
            article = (ArrayList<Article>) result.obj;
            setData();
        }
    }

    private String getDate(long t) {
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(t * 1000L);
        String date = DateFormat.format("dd-MM-yyyy hh:mm", cal).toString();
        return date;
    }

    private boolean checkInDatabase() {
        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor a = db.rawQuery("SELECT id FROM articles WHERE id = ?", new String[]{article.get(0)._id});
        if (!(a.moveToFirst()) || a.getCount() == 0) {
            return false;
        } else {
            return true;
        }
    }


    private void addToDatabase() {
        ContentValues cv = new ContentValues();
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        cv.put("id", article.get(0)._id);
        cv.put("link", getIntent().getStringExtra("link"));
        cv.put("title", getIntent().getStringExtra("title"));
        cv.put("subtitle", getIntent().getStringExtra("subtitle"));
        db.insert(DBHelper.NAME_TABLE, null, cv);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Article add to Favorite", Toast.LENGTH_SHORT);
        toast.show();
    }

    private void deleteFromDatabase() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete(DBHelper.NAME_TABLE, "id = '" + article.get(0)._id + "'", null);
        Toast toast = Toast.makeText(getApplicationContext(),
                "Article delete from Favorite", Toast.LENGTH_SHORT);
        toast.show();
    }
}
