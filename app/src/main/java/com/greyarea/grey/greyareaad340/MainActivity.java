package com.greyarea.grey.greyareaad340;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "greyarea.greyareaad340";
    EditText SendValue;
    Button SendEditTextValue;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate started from Main Activity");

        SendEditTextValue = (Button) findViewById(R.id.button1);
        SendValue = (EditText) findViewById(R.id.editText1);

        SendEditTextValue.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                intent = new Intent(getApplicationContext(), TextBoxMsgSend.class);
                intent.putExtra("EdiTtEXTvALUE", SendValue.getText().toString());
                startActivity(intent);

            }
        });
    }

    public void movieButton(View view){
        Intent intent = new Intent(this, ShowMovies.class);
        startActivity(intent);
    }

    public void button7(View view){
        CharSequence text = "Two";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public void button8(View view){
        CharSequence text = "Three";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public void button9(View view){
        CharSequence text = "Four";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }



    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart started from Main Activity");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause started from Main Activity");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop started from Main Activity");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy started from Main Activity");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart started from Main Activity");
    }

}
