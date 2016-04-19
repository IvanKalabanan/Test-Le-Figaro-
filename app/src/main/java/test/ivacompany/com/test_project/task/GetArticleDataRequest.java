package test.ivacompany.com.test_project.task;


import com.google.gson.Gson;
import com.octo.android.robospice.request.SpiceRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.CharEncoding;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import test.ivacompany.com.test_project.entity.Article;
import test.ivacompany.com.test_project.entity.Wrapper;

public class GetArticleDataRequest extends SpiceRequest<Wrapper> {

    String url;

    public GetArticleDataRequest(String url) {
        super(Wrapper.class);
        this.url = url;
    }

    @Override
    public Wrapper loadDataFromNetwork() throws Exception {

        Wrapper wrapper = new Wrapper();
        wrapper.obj = Parser(IOUtils.toString(new InputStreamReader(new URL(url).openStream(), CharEncoding.UTF_8)));

        return wrapper;
    }


    private ArrayList<Article> Parser(String json_array) {
        ArrayList<Article> arr = new ArrayList<>();

        Gson gson = new Gson();
        arr.add(gson.fromJson(json_array, Article.class));

        return arr;
    }
}
