package test.ivacompany.com.test_project.roboCLass;

import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;


public abstract class BaseSpiceActivityNoFragment extends AppCompatActivity {
    private SpiceManager spiceManager = new SpiceManager(SampleSpiceService.class);

    @Override
    protected void onStart() {
        spiceManager.start(this);
        super.onStart();
    }

    @Override
    protected void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

}