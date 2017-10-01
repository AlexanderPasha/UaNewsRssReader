package com.mannydev.rssalluanews;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mannydev.rssalluanews.RSS.RssFeed;
import com.mannydev.rssalluanews.RSS.RssItem;
import com.mannydev.rssalluanews.RSS.RssReader;
import com.mannydev.rssalluanews.model.adapters.MyAdapter;
import com.squareup.picasso.Picasso;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class RSSNews extends AppCompatActivity {
    ArrayList<RssItem>rssNews;
    TextView textView, textTitle;
    ListView lvMain;
    MyAdapter myAdapter;
    Toolbar toolbar;
    String rssUrl, newsTittle,logoUrl;
    ImageView imageLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_lenta);

        //Получаем данные из MainActivity
        Intent intent = getIntent();
        newsTittle = intent.getStringExtra("title");
        rssUrl = intent.getStringExtra("url");
        logoUrl = intent.getStringExtra("logo");


        imageLogo = (ImageView) findViewById(R.id.imageLogo);
        Picasso.with(this).load(logoUrl).fit().centerInside().into(imageLogo);
        rssNews = new ArrayList<>();
        textView = new TextView(this);
        textTitle = (TextView)findViewById(R.id.textTitle);
        textTitle.setText(newsTittle);
        lvMain = (ListView) findViewById(R.id.lvMain);
        toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Добавляем кнопку "Назад"
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back);
        }

        //Загружаем RSS
        RSSAsyncLoad longTask = new RSSAsyncLoad(); // Создаем экземпляр
        longTask.execute();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


    class RSSAsyncLoad extends AsyncTask<Void, Void, ArrayList<RssItem>> {


        private URL url;
        String urlLink;
        private ArrayList<RssItem> rssItems;

        @Override
        protected void onPreExecute(){
            urlLink = rssUrl;

        }

        @Override
        protected ArrayList<RssItem> doInBackground(Void... noargs) {

            try {
                Log.v("Logs", "Пробуем загрузить ленту");
                //url = new URL("http://feeds.bbci.co.uk/news/world/rss.xml");
                url = new URL(urlLink);
                RssFeed feed = RssReader.read(url);
                rssItems = feed.getRssItems();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SAXException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return rssItems;

        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> result) {
            Log.v("myLogs", "Список обновлен!");
            if(result!=null){
                myAdapter = new MyAdapter(RSSNews.this, result);
                lvMain.setAdapter(myAdapter);
            }

        }
    }
}

