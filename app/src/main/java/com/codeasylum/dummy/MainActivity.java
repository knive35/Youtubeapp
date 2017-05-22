package com.codeasylum.dummy;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText searchQuery;
    String string1 = "https://www.googleapis.com/youtube/v3/search?part=snippet%20&q=";
    String string2;
    String string3 = "%20&key=AIzaSyD2S40FRiTjXWvdixMri_Zaru3RqHWe_m4";
    String urlJsonObj;
    Button btnmakeJsonObjectReq,searchButton;
    ProgressDialog pDialog;
   // TextView textResponse;
    String jsonResponse;
    ArrayList<String> youtubeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnmakeJsonObjectReq = (Button) findViewById(R.id.btnObjRequest);
        searchQuery= (EditText) findViewById(R.id.query);
      //  textResponse = (TextView) findViewById(R.id.txtResponse);
        pDialog = new ProgressDialog(this);
        searchButton= (Button) findViewById(R.id.search);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        btnmakeJsonObjectReq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string2=searchQuery.getText().toString();
                 urlJsonObj =string1 + "" + string2 + "" + string3; /*"https://www.googleapis.com/youtube/v3/search?part=snippet%20&q=soccer%20&key=AIzaSyD2S40FRiTjXWvdixMri_Zaru3RqHWe_m4";*/
                //Log.d("STRING",urlJsonObj);

                makeJsonObjectReq();

            }
        });
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                intent.putExtra("FILES_TO_SEND", (Serializable) YoutubeDataList);
                startActivity(intent);
            }
        });

    }
    List<YoutubeData> YoutubeDataList;

    private void makeJsonObjectReq() {
        Log.d("STRING",urlJsonObj);
        showpDialog();
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                urlJsonObj, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Log.d("sdf", jsonObject.toString());

                try {
                    YoutubeDataList= new ArrayList<>();
                    for(int i=0;i<6;i+=2) {
                        YoutubeData youtubeData = new YoutubeData();

                        // Parsing json object response
                        // response will be a json object

                        youtubeData.setFull_title(jsonObject.getString("kind"));
                        youtubeData.setHeader_image_url(jsonObject.getString("etag"));
                        JSONObject itemObject= (JSONObject) jsonObject.getJSONArray("items").get(i);
                        youtubeData.setVideoId(itemObject.getJSONObject("id").getString("videoId"));

                       Log.d("TEST",youtubeData.getVideoId());
                        // youtubeData.setVideoId(jsonObject.getString("videoId"));
                        //JSONObject phone = jsonObject.getJSONObject("phone");
                        //String home = phone.getString("home");
                        //String mobile = phone.getString("mobile");

                        //jsonResponse = "";
                        //jsonResponse += "Name: " + name + "\n\n";
                        //jsonResponse += "Email: " + email + "\n\n";
                        // jsonResponse += "Home: " + home + "\n\n";
                        //jsonResponse += "Mobile: " + mobile + "\n\n";
                        YoutubeDataList.add(youtubeData);
                        Log.d("asd",YoutubeDataList.get(i/2).getVideoId());
                    }
                  //  textResponse.setText(jsonResponse);

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                }
                hidepDialog();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("as", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                // hide the progress dialog
                hidepDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();

    }
}
