package com.mannydev.rssalluanews;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mannydev.rssalluanews.RSS.RssFeed;
import com.mannydev.rssalluanews.RSS.RssItem;
import com.mannydev.rssalluanews.RSS.RssReader;
import com.mannydev.rssalluanews.model.adapters.MyAdapter;
import com.squareup.picasso.Picasso;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class RSSNews extends AppCompatActivity {
    private ListView lvMain;
    private String rssUrl;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rss_lenta);

        //Получаем данные из MainActivity
        Intent intent = getIntent();
        String newsTittle = intent.getStringExtra("title");
        rssUrl = intent.getStringExtra("url");
        String logoUrl = intent.getStringExtra("logo");

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        ImageView imageLogo = (ImageView) findViewById(R.id.imageLogo);
        Picasso.with(this).load(logoUrl).fit().centerInside().into(imageLogo);
        ArrayList<RssItem> rssNews = new ArrayList<>();
        TextView textView = new TextView(this);
        TextView textTitle = (TextView) findViewById(R.id.textTitle);
        textTitle.setText(newsTittle);
        lvMain = (ListView) findViewById(R.id.lvMain);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        // Добавляем кнопку "Назад"
        if (getSupportActionBar() != null) {
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


        String urlLink;
        private URL url;
        private ArrayList<RssItem> rssItems;

        @Override
        protected void onPreExecute() {
            urlLink = rssUrl;
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected ArrayList<RssItem> doInBackground(Void... noargs) {

            try {
                Log.v("Logs", "Пробуем загрузить ленту");
                url = new URL(urlLink);
                RssFeed feed = RssReader.read(url);
                rssItems = feed.getRssItems();
            } catch (SAXException | IOException e) {
                e.printStackTrace();
            }

            return rssItems;

        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> result) {
            Log.v("myLogs", "Список обновлен!");
            progressBar.setVisibility(View.INVISIBLE);
            if (result != null) {
                MyAdapter myAdapter = new MyAdapter(RSSNews.this, result);
                lvMain.setAdapter(myAdapter);
            }

        }
    }
}

