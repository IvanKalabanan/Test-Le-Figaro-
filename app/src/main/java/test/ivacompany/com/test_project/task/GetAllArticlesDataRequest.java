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
        /// very bad code
        String rrr = json_array.substring(28);
        String r = rrr.replace("[", "{ \"List_Articles\" : [");
        String rr = r.replace("]", "] }");
        ///

        JSONObject json_obj = null;
        try {
            json_obj = new JSONObject(rr);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JSONArray jsonMainNode = json_obj.optJSONArray("List_Articles");
        int lengthJsonArr = jsonMainNode.length();

        for (int i = 0; i < lengthJsonArr; i++) {

            JSONObject jsonChildNode = null;
            try {
                jsonChildNode = jsonMainNode.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }


            String id = jsonChildNode.optString("id").toString();
            String internalId = jsonChildNode.optString("internalId").toString();
            String update = jsonChildNode.optString("update").toString();
            String date = jsonChildNode.optString("date").toString();
            Integer ranking = Integer.parseInt(jsonChildNode.optString("ranking"));
            String title = jsonChildNode.optString("title").toString();
            String subtitle = jsonChildNode.optString("subtitle").toString();
            String thumb = jsonChildNode.optString("thumb").toString();
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
