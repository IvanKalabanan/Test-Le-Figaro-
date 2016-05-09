package test.ivacompany.com.test_project.task;

import com.octo.android.robospice.request.SpiceRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import test.ivacompany.com.test_project.entity.List_Articles;
import test.ivacompany.com.test_project.entity.Wrapper;
public class GetAllArticlesDataRequest extends SpiceRequest<Wrapper> {

    String url;

    public GetAllArticlesDataRequest(String url) {
        super(Wrapper.class);
        this.url = url;
    }

    @Override
    public Wrapper loadDataFromNetwork() throws Exception {

        Wrapper wrapper = new Wrapper();
        wrapper.obj = Parser(IOUtils.toString(new InputStreamReader(new URL(url).openStream(), CharEncoding.UTF_8)));

        return wrapper;
    }


    private ArrayList<List_Articles> Parser(String json_array) {
        ArrayList<List_Articles> arr = new ArrayList<List_Articles>();

        JSONArray jsonMainNode, jsonArticle = null;
        try {
            jsonMainNode = new JSONArray(json_array);
            jsonArticle = jsonMainNode.getJSONArray(1);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        int lengthJsonArr = jsonArticle.length();

        for (int i = 0; i < lengthJsonArr; i++) {

            JSONObject item = null;
            try {
                item = jsonArticle.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            String id = item.optString("id");
            String internalId = item.optString("internalId");
            String update = item.optString("update");
            String date = item.optString("date");
            Integer ranking = Integer.parseInt(item.optString("ranking"));
            String title = item.optString("title");
            String subtitle = item.optString("subtitle");
            String thumb = item.optString("thumb");
            JSONObject jObj = null;
            String link = "";
            try {
                jObj = new JSONObject(thumb);
                link = jObj.getString("link").replace("%dx%d", "300x300");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            List_Articles a = new List_Articles(id, internalId, update, date, ranking, title, subtitle, link);
            arr.add(a);
        }
        return arr;
    }
}
