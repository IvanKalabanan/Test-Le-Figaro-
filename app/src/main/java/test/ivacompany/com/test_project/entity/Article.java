package test.ivacompany.com.test_project.entity;

import com.google.gson.annotations.SerializedName;

public class Article {
    @SerializedName("_id")
    public String _id;

    @SerializedName("internalId")
    public String internalId;

    @SerializedName("author")
    public String author;

    @SerializedName("categoryId")
    public String categoryId;

    @SerializedName("content")
    public String content;

    @SerializedName("date")
    public long date;

    public Article(String _id, String internalId, String author, String categoryId, String content, long date) {
        this._id = _id;
        this.internalId = internalId;
        this.author = author;
        this.categoryId = categoryId;
        this.content = content;
        this.date = date;
    }
}
