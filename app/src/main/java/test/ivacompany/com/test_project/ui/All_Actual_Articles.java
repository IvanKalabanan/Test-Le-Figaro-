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
import test.ivacompany.com.test_project.R;
import test.ivacompany.com.test_project.entity.LinkList;
import test.ivacompany.com.test_project.entity.List_Articles;
import test.ivacompany.com.test_project.entity.Wrapper;
import test.ivacompany.com.test_project.roboCLass.BaseSpiceActivity;
import test.ivacompany.com.test_project.task.GetAllArticlesDataRequest;

public class All_Actual_Articles extends BaseSpiceActivity {
    ArrayList<List_Articles> Actual_Articles;
    GetArticlesDataRequestListener getArticlesDataRequestListener = new GetArticlesDataRequestListener();
    RecyclerView lv;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.actual_activity, container, false);
        initialization(v);

        getSpiceManager().execute(new GetAllArticlesDataRequest(LinkList.passToAllActualArticles), getArticlesDataRequestListener);



        return v;
    }

    private void initialization(View v) {

        lv = (RecyclerView) v.findViewById(R.id.list_viewMy);

      /*  lv.addOnItemTouchListener(
                new RecyclerItemClickListener(v.getContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Toast toast = Toast.makeText(view.getContext(),
                                "Click", Toast.LENGTH_SHORT);
                        toast.show();

                        Intent intent = new Intent(getActivity(), Article_Activity.class);
                        intent.putExtra("id", Actual_Articles.get(position).id);
                        intent.putExtra("title", Actual_Articles.get(position).title);
                        intent.putExtra("link", Actual_Articles.get(position).link);
                        intent.putExtra("subtitle", Actual_Articles.get(position).subtitle);
                        startActivity(intent);

                    }
                })
        );*/

      /*  lv.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        Intent intent = new Intent(getActivity(), Article_Activity.class);
                        intent.putExtra("id", Actual_Articles.get(position).id);
                        intent.putExtra("title", Actual_Articles.get(position).title);
                        intent.putExtra("link", Actual_Articles.get(position).link);
                        intent.putExtra("subtitle", Actual_Articles.get(position).subtitle);
                        startActivity(intent);


                    }
                })
        );*/

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        lv.setLayoutManager(llm);
    }

    public final class GetArticlesDataRequestListener implements RequestListener<Wrapper> {


        @Override
        public void onRequestFailure(SpiceException spiceException) {
            Toast.makeText(getActivity(), "Request_Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(final Wrapper result) {
            Actual_Articles = (ArrayList<List_Articles>) result.obj;
                        Adap adapter = new Adap(getActivity(), Actual_Articles);
            lv.setAdapter(adapter);
        }
    }
}
