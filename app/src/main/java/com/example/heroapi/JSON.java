package com.example.heroapi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class JSON {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }

    public static JSONArray getJSONArray(JSONObject json) throws JSONException {
        //JSONObject to JSONArray
        JSONArray jsonArray = json.getJSONArray("results");

        return jsonArray;
    }

    public static ArrayList<com.example.heroapi.Hero> getList(JSONArray jsonArray) throws JSONException {
        ArrayList<com.example.heroapi.Hero> heroList = new ArrayList<com.example.heroapi.Hero>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject json_data = jsonArray.getJSONObject(i);
            JSONObject json_stats = jsonArray.getJSONObject(i)
                    .getJSONObject("powerstats");
            JSONObject json_bio = jsonArray.getJSONObject(i)
                    .getJSONObject("biography");
            JSONObject json_work = jsonArray.getJSONObject(i)
                    .getJSONObject("work");
            //public Hero(int id, String name, String strength, String work, String gender)
            Hero hero = new Hero(
                    json_data.getString("id"),
                    json_data.getString("name"),
                    json_stats.getString("strength"),
                    json_stats.getString("speed"),
                    json_bio.getString("full-name"),
                    json_work.getString("occupation")
            );
            heroList.add(hero);
        }
        return heroList;
    }

    public static ArrayList<com.example.heroapi.Hero> getHeroListByName(ArrayList<com.example.heroapi.Hero> heroList, String name) {
        ArrayList<com.example.heroapi.Hero> heroListByName = new ArrayList<com.example.heroapi.Hero>();
        for (com.example.heroapi.Hero hero : heroList) {
            if (hero.getName().contains(name)) {
                heroListByName.add(hero);
            }
        }
        return heroListByName;
    }

}
