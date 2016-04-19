package test.ivacompany.com.test_project.entity;

public class LinkList {
    static public String passToAllActualArticles = "http://figaro.service.yagasp.com/article/header/QWN0dWFsaXTDqXNBY3R1YWxpdMOpcw==";

    static public String passToAllEconomicArticles = "http://figaro.service.yagasp.com/article/header/w4ljb25vbWllw4ljb25vbWll";

    static public String passToAllSportArticles = "http://figaro.service.yagasp.com/article/header/U3BvcnRTcG9ydA==";

    static public String passToAllCultureArticles = "http://figaro.service.yagasp.com/article/header/Q3VsdHVyZUN1bHR1cmU=";


    public static String GetPassToArticle(String id)
    {
        return String.format("http://figaro.service.yagasp.com/article/%s", id);
    }
}
