package test.ivacompany.com.test_project.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import test.ivacompany.com.test_project.ui.All_Actual_Articles;
import test.ivacompany.com.test_project.ui.All_Culture_Articles;
import test.ivacompany.com.test_project.ui.All_Economic_Articles;
import test.ivacompany.com.test_project.ui.All_Sport_Articles;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                All_Actual_Articles tab1 = new All_Actual_Articles();
                return tab1;
            case 3:
                All_Culture_Articles tab2 = new All_Culture_Articles();
                return tab2;
            case 1:
                All_Economic_Articles tab3 = new All_Economic_Articles();
                return tab3;
            case 2:
                All_Sport_Articles tab4 = new All_Sport_Articles();
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}