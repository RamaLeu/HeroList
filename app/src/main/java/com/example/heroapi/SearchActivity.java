package com.example.heroapi;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    public static final String HERO_API = "https://www.superheroapi.com/api.php/3062102603893080/search/A";

    private RecyclerView recyclerView;
    private com.example.heroapi.Adapter adapter;

    private ArrayList<Hero> heroList = new ArrayList<Hero>();

    SearchView searchView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        AsyncFetch asyncFetch = new AsyncFetch();
        asyncFetch.execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search, menu);


        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchManager searchManager = (SearchManager) com.example.heroapi.SearchActivity.this.getSystemService(Context.SEARCH_SERVICE);
        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(com.example.heroapi.SearchActivity.this.getComponentName()));
            searchView.setIconified(false);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onNewIntent(Intent intent) {
        // Get search query
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            if (searchView != null) {
                searchView.clearFocus();
            }


            ArrayList<Hero> heroListByName = JSON.getHeroListByName(heroList, query);

            if (heroListByName.size() == 0) {
                Toast.makeText(this, getResources().getString(R.string.search_no_results) + query, Toast.LENGTH_SHORT).show();
            }


            recyclerView = (RecyclerView) findViewById(R.id.hero_list);
            adapter = new com.example.heroapi.Adapter(com.example.heroapi.SearchActivity.this, heroListByName);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(com.example.heroapi.SearchActivity.this));
        }
    }

    private class AsyncFetch extends AsyncTask<String, String, ArrayList<Hero>> {
        ProgressDialog pdLoading = new ProgressDialog(com.example.heroapi.SearchActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //this method will be running on UI thread
            pdLoading.setMessage(getResources().getString(R.string.search_loading_data));
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected ArrayList<Hero> doInBackground(String... params) {
            try {
                JSONObject jsonObject = JSON.readJsonFromUrl(HERO_API);

                    JSONArray jsonArray = null;
                    heroList = new ArrayList<Hero>();
                    try {
                        jsonArray = JSON.getJSONArray(jsonObject);
                        heroList = JSON.getList(jsonArray);
                    } catch (JSONException e) {
                        Toast.makeText(
                                com.example.heroapi.SearchActivity.this,
                                getResources().getText(R.string.search_error_reading_data) + e.getMessage(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                    return heroList;
            } catch (JSONException | IOException e1) {
                Toast.makeText(
                        com.example.heroapi.SearchActivity.this,
                        getResources().getText(R.string.search_error_reading_data) + e1.getMessage(),
                        Toast.LENGTH_LONG
                ).show();
                return null;
            }
        }// doInBackground

        @Override
        protected void onPostExecute(ArrayList<Hero> heroList) {
            //this method will be running on UI thread
            pdLoading.dismiss();

           if(heroList != null) {
               Toast.makeText(com.example.heroapi.SearchActivity.this, getResources().getString(R.string.search_found_entries_from_api) + heroList.size(), Toast.LENGTH_SHORT).show();
           }
        }//onPostExecute
    }//AsyncFetch class
}
