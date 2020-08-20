package com.jmhd.wml;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DBConnection {
    private String jsonString;
    ArrayList<DiaryData> diaryDataList;

    public void connectDB() {
        GetData task = new GetData();
        task.execute("http://10.0.2.15/db_connection.php");
    }

    private class GetData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            if (s != null) {
                jsonString = s;
                diaryDataList = doParse();
                Log.d("MyLog", diaryDataList.get(0).getTitle());
            }
        }

        private ArrayList<DiaryData> doParse() {
            ArrayList<DiaryData> tempDiaryArray = new ArrayList<DiaryData>();
            try {
                JSONObject jsonObject = new JSONObject(jsonString);
                JSONArray jsonArray = jsonObject.getJSONArray("result");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);

                    String date = item.getString("date");
                    String title = item.getString("title");
                    String content = item.getString("content");

                    DiaryData diaryData = new DiaryData();

                    diaryData.setDate(date);
                    diaryData.setTitle(title);
                    diaryData.setContent(content);

                    tempDiaryArray.add(diaryData);
                }
            } catch (JSONException e) {
                Log.d("MyLog", "showResult: ", e);
            }
            return tempDiaryArray;
        }

        @Override
        protected String doInBackground(String... strings) {
            String url = strings[0];
            try {
                URL serverURL = new URL(url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) serverURL.openConnection();

                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.connect();

                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream is;
                if (responseStatusCode == HttpURLConnection.HTTP_OK) {
                    is = httpURLConnection.getInputStream();
                } else {
                    is = httpURLConnection.getErrorStream();
                }

                InputStreamReader sr = new InputStreamReader(is, "UTF-8");
                BufferedReader br = new BufferedReader(sr);

                StringBuffer sb = new StringBuffer();
                String line;

                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }

                br.close();
                Log.d("MyLog", sb.toString().trim());

                return sb.toString().trim();
            } catch (Exception e) {
                Log.d("MyLog", "InsertData: Error", e);
                String errorString = e.toString();
                return null;
            }


        }
    }
}
