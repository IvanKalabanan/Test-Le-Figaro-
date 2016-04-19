package test.ivacompany.com.test_project.roboCLass;

import android.support.v4.app.Fragment;

import com.octo.android.robospice.SpiceManager;


public abstract class BaseSpiceActivity extends Fragment {
    private SpiceManager spiceManager = new SpiceManager(SampleSpiceService.class);

    @Override
    public void onStart() {
        spiceManager.start(getActivity());
        super.onStart();
    }

    @Override
    public void onStop() {
        spiceManager.shouldStop();
        super.onStop();
    }

    protected SpiceManager getSpiceManager() {
        return spiceManager;
    }

}