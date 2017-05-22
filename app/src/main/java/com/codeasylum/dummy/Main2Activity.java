package com.codeasylum.dummy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ArrayList<YoutubeData> YoutubeDataList =  (ArrayList<YoutubeData>)getIntent().getSerializableExtra("FILES_TO_SEND");
        Log.d("ds",YoutubeDataList.get(0).getVideoId());

    }
}
