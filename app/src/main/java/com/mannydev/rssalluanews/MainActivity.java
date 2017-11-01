package com.mannydev.rssalluanews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.MobileAds;
import com.mannydev.rssalluanews.model.Feed;
import com.mannydev.rssalluanews.model.adapters.FeedsAdapter;
import com.mannydev.rssalluanews.model.adapters.SpacesItemDecoration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Feed> feeds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-8078146669032188~3449653135");
        setContentView(R.layout.activity_main_feeds);
        feeds = new ArrayList<>();
        getFeeds();
        RecyclerView rvFeeds = findViewById(R.id.rvFeeds);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        rvFeeds.setLayoutManager(mLayoutManager);

        SpacesItemDecoration decoration = new SpacesItemDecoration(1);
        rvFeeds.addItemDecoration(decoration);

        FeedsAdapter adapter = new FeedsAdapter(feeds);
        rvFeeds.setAdapter(adapter);
    }

    private void getFeeds() {

        Feed censorNet = new Feed();
        censorNet.setName("Цензор.НЕТ");
        censorNet.setDescription("www.censor.net.ua");
        censorNet.setUrlFeed("https://censor.net.ua/includes/news_ru.xml");
        censorNet.setUrlLogo("https://static.censor.net.ua/images/logo/ru/520x520.png");
        feeds.add(censorNet);

        Feed obozrevatel = new Feed();
        obozrevatel.setName("Обозреватель");
        obozrevatel.setDescription("www.obozrevatel.com");
        obozrevatel.setUrlFeed("https://www.obozrevatel.com/rss.xml");
        obozrevatel.setUrlLogo("https://lh3.googleusercontent.com/iTz5sSBr9mWKy0xUpJAqmI1DqWVj5CDfNxVejTImM5dNEK1gAgmOQNptYycMiHAvGA=w300");
        feeds.add(obozrevatel);

        Feed factyUa = new Feed();
        factyUa.setName("Факты UA");
        factyUa.setDescription("www.fakty.ua");
        factyUa.setUrlFeed("http://fakty.ua/rss_feed/ukraina");
        factyUa.setUrlLogo("https://lh3.googleusercontent.com/TvMiGwZFlv88LXZi_ru0Qr6SVK05Nn0RA645vWO0R3lV3uTGuzBE3w5PPEsLaed4aPI=w300");
        feeds.add(factyUa);

        Feed ligaNet = new Feed();
        ligaNet.setName("Лига NET");
        ligaNet.setDescription("www.news.liga.net");
        ligaNet.setUrlFeed("http://news.liga.net/ua/all/rss.xml");
        ligaNet.setUrlLogo("https://images.apester.com/user-images%2F7b%2F7b0bbedfd0aefc8f0c6567050dca4f59.png");
        feeds.add(ligaNet);

        Feed lbUa = new Feed();
        lbUa.setName("LB.UA");
        lbUa.setDescription("www.lb.ua");
        lbUa.setUrlFeed("https://lb.ua/rss/rus/news.xml");
        lbUa.setUrlLogo("https://pbs.twimg.com/profile_images/446957846475710464/JLkf9fGY_400x400.jpeg");
        feeds.add(lbUa);

        Feed rbcUa = new Feed();
        rbcUa.setName("РБК UA");
        rbcUa.setDescription("www.rbc.ua");
        rbcUa.setUrlFeed("https://www.rbc.ua/static/rss/newsline.rus.rss.xml");
        rbcUa.setUrlLogo("https://www.2000.ua/modules/pages/pictures/1000x1000/12216_a408b893102d36bcc7a27db9a6f67c30_4090.png");
        feeds.add(rbcUa);

        Feed stranaUa = new Feed();
        stranaUa.setName("Страна UA");
        stranaUa.setDescription("www.strana.ua");
        stranaUa.setUrlFeed("https://strana.ua/xml/rss.xml");
        stranaUa.setUrlLogo("https://yt3.ggpht.com/-JHvrqVg6uqo/AAAAAAAAAAI/AAAAAAAAAAA/UYdQxD3OtI4/s900-c-k-no-mo-rj-c0xffffff/photo.jpg");
        feeds.add(stranaUa);

        Feed radioSvoboda = new Feed();
        radioSvoboda.setName("Радио Свобода");
        radioSvoboda.setDescription("www.radiosvoboda.org");
        radioSvoboda.setUrlFeed("https://www.radiosvoboda.org/api/zrqiteuuir");
        radioSvoboda.setUrlLogo("https://www.svoboda.org/Content/responsive/RFE/img/top_logo_news.png");
        feeds.add(radioSvoboda);

        Feed unianNet = new Feed();
        unianNet.setName("Униан");
        unianNet.setDescription("www.unian.net");
        unianNet.setUrlFeed("https://rss.unian.net/site/news_rus.rss");
        unianNet.setUrlLogo("https://www.goldmir.net/uploads/tv/channel/296/logo_big.jpg");
        feeds.add(unianNet);


        Feed dwCom = new Feed();
        dwCom.setName("DW.COM");
        dwCom.setDescription("www.dw.com");
        dwCom.setUrlFeed("http://partner.dw.com/xml/rss-ru-news");
        dwCom.setUrlLogo("https://2.bp.blogspot.com/-tx2NsnQyI04/WK-5yy0JzWI/AAAAAAAADBQ/A2Vd94Wt-PQJyMxGuItIWNB601w4XhcpwCK4B/s1600/DW-NEWS.png");
        feeds.add(dwCom);

        Feed kursComUa = new Feed();
        kursComUa.setName("Head News");
        kursComUa.setDescription("www.headnews.org");
        kursComUa.setUrlFeed("https://headnews.org/feed/");
        kursComUa.setUrlLogo("https://headnews.org/wp-content/uploads/2016/04/cropped-fav.png");
        feeds.add(kursComUa);

        Feed rateApp = new Feed();
        rateApp.setName("Оценить приложение");
        rateApp.setDescription("Play Market");
        rateApp.setUrlFeed("https://play.google.com/store/apps/details?id=com.mannydev.rssalluanews");
        rateApp.setUrlLogo("https://cdn4.iconfinder.com/data/icons/small-n-flat/24/star-256.png");
        feeds.add(rateApp);
    }
}
