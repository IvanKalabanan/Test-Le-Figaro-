package test.ivacompany.com.test_project.entity;

import com.google.gson.annotations.SerializedName;

public class List_Articles {
    @SerializedName("id")
    public String id;

    @SerializedName("internalId")
    public String internalId;

    @SerializedName("update")
    public String update;

    @SerializedName("date")
    public String date;

    @SerializedName("ranking")
    public Integer ranking;

    @SerializedName("title")
    public String title;

    @SerializedName("subtitle")
    public String subtitle;

    @SerializedName("link")
    public String link;

    public List_Articles(String id, String internalId, String update, String date, Integer ranking, String title, String subtitle, String link)
    {
        this.id=id;
        this.internalId=internalId;
        this.update=update;
        this.date=date;
        this.ranking = ranking;
        this.title = title;
        this.subtitle = subtitle;
        this.link = link;
    }
}
