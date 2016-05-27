package test.ivacompany.com.test_project.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import java.util.ArrayList;

import test.ivacompany.com.test_project.Adapters.Adap;
import test.ivacompany.com.test_project.Adapters.ListAdapter;
import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.LinkList;
import test.ivacompany.com.test_project.entity.List_Articles;
import test.ivacompany.com.test_project.entity.Wrapper;
import test.ivacompany.com.test_project.roboCLass.BaseSpiceActivity;
import test.ivacompany.com.test_project.task.GetAllArticlesDataRequest;

public class All_Economic_Articles extends BaseSpiceActivity {
    private ListAdapter listAdapter;
    ArrayList<List_Articles> Economic_Articles;
    GetArticlesDataRequestListener getArticlesDataRequestListener = new GetArticlesDataRequestListener();
    RecyclerView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.actual_activity, container, false);
        initialization(v);
        getSpiceManager().execute(new GetAllArticlesDataRequest(LinkList.passToAllEconomicArticles), getArticlesDataRequestListener);



        return v;
    }

    private void initialization(View v) {

        lv = (RecyclerView) v.findViewById(R.id.list_viewMy);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(llm);

       /* lv.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), Article_Activity.class);
                intent.putExtra("id", Economic_Articles.get(position).id);
                intent.putExtra("title", Economic_Articles.get(position).title);
                intent.putExtra("link", Economic_Articles.get(position).link);
                intent.putExtra("subtitle", Economic_Articles.get(position).subtitle);
                startActivity(intent);
            }
        }));*/
    }

    public final class GetArticlesDataRequestListener implements RequestListener<Wrapper> {
        /**/
        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Request_Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Wrapper result) {
            Economic_Articles = (ArrayList<List_Articles>) result.obj;
            Adap adapter = new Adap(getActivity(), Economic_Articles);
            lv.setAdapter(adapter);
        }
    }
}
