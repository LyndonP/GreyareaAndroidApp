package com.greyarea.grey.greyareaad340;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;

public class TextBoxMsgSend extends AppCompatActivity {

    protected static final String TAG = "greyarea.greyareaad340";

    TextView receive;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_box_msg_send);

        Toolbar sToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(sToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        receive = (TextView)findViewById(R.id.textView1);

        receive.setText(getIntent().getStringExtra("EdiTtEXTvALUE"));

    }
}

