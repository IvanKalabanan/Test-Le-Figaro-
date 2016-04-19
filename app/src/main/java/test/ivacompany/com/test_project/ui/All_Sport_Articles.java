package test.ivacompany.com.test_project.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import test.ivacompany.com.test_project.Adapters.ListAdapter;
import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.LinkList;
import test.ivacompany.com.test_project.entity.List_Articles;
import test.ivacompany.com.test_project.entity.Wrapper;
import test.ivacompany.com.test_project.roboCLass.BaseSpiceActivity;
import test.ivacompany.com.test_project.task.GetAllArticlesDataRequest;

public class All_Sport_Articles extends BaseSpiceActivity {
    private ListAdapter listAdapter;
    ArrayList<List_Articles> Sport_Articles;
    GetArticlesDataRequestListener getArticlesDataRequestListener = new GetArticlesDataRequestListener();
    ListView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.actual_activity, container, false);
        initialization(v);
        getSpiceManager().execute(new GetAllArticlesDataRequest(LinkList.passToAllSportArticles), getArticlesDataRequestListener);

        return v;
    }

    private void initialization(View v) {

        lv = (ListView) v.findViewById(R.id.list_viewMy);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(getActivity(), Article_Activity.class);
                intent.putExtra("id", Sport_Articles.get(position).id);
                intent.putExtra("title", Sport_Articles.get(position).title);
                intent.putExtra("link", Sport_Articles.get(position).link);
                intent.putExtra("subtitle", Sport_Articles.get(position).subtitle);
                startActivity(intent);
            }
        });
    }

    public final class GetArticlesDataRequestListener implements RequestListener<Wrapper> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Request_Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Wrapper result) {
            Sport_Articles = (ArrayList<List_Articles>) result.obj;
            listAdapter = new ListAdapter(getActivity(), R.id.list_viewMy, Sport_Articles);
            lv.setAdapter(listAdapter);
        }
    }
}