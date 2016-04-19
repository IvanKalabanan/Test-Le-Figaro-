package test.ivacompany.com.test_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

import test.ivacompany.com.test_project.Adapters.TabsAdapter;
import test.ivacompany.com.test_project.DBHelper;
import test.ivacompany.com.test_project.R;

public class MainActivity extends AppCompatActivity {

    private TabHost mTabHost;
    private ViewPager mViewPager;
    private TabsAdapter mTabsAdapter;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.general_activity);
        dbHelper = new DBHelper(this);

        mTabHost = (TabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup();

        mViewPager = (ViewPager) findViewById(R.id.pager);
        mTabsAdapter = new TabsAdapter(this, mTabHost, mViewPager);
        mTabsAdapter.addTab(mTabHost.newTabSpec("Actualités").setIndicator("Actualités"), All_Actual_Articles.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("Économie").setIndicator("Économie"), All_Economic_Articles.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("Sport").setIndicator("Sport"), All_Sport_Articles.class, null);
        mTabsAdapter.addTab(mTabHost.newTabSpec("Culture").setIndicator("Culture"), All_Culture_Articles.class, null);

        for (int i = 0; i < mTabHost.getTabWidget().getChildCount(); i++) {
            mTabHost.getTabWidget().getChildAt(i).setPadding(10, 10, 10, 10);
        }

        if (savedInstanceState != null) {
            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab"));
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainactivity, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.showMenuItem:
                Intent intent = new Intent(MainActivity.this, Favorite_Articles.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}