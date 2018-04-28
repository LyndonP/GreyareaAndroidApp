package com.greyarea.grey.greyareaad340;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "greyarea.greyareaad340";
    EditText SendValue;
    Button SendEditTextValue;
    Intent intent;
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Greyarea");
        mToolbar.setLogo(R.drawable.ic_priority_high_black_24dp);
        mToolbar.setNavigationIcon(R.drawable.ic_menu_black_24dp);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Log.d(TAG, "onCreate started from Main Activity");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater mMenuInflater = getMenuInflater();
        mMenuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_setting){
            Toast.makeText(MainActivity.this,
                    "Settings Clicked",
                    Toast.LENGTH_SHORT).show();
        }
        if(item.getItemId() == R.id.about_us){
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    public void sendButton(View view){
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
