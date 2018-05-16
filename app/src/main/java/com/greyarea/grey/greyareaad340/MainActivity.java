package com.greyarea.grey.greyareaad340;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.NavigationView;
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
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    protected static final String TAG = "greyarea.greyareaad340";
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mToggle;
    android.support.v4.app.FragmentTransaction fragmentTransaction;
    NavigationView navigationView;
    String mActivityTitle;
    SharedPreferences myPrefs;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        String name = myPrefs.getString("nameKey", "No name");

        TextView label = (TextView) findViewById(R.id.editText1);
        label.setText(name);

        Toolbar mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, new AboutFragment());
        fragmentTransaction.commit();

        navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.nav_about) {
                    Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,
                            "About Clicked",
                            Toast.LENGTH_SHORT).show();
                }

                if (id == R.id.nav_zombies) {
                    Intent intent = new Intent(getApplicationContext(), ShowMovies.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,
                            "Zombie Movies!",
                            Toast.LENGTH_SHORT).show();
                }


                if (id == R.id.nav_cams && isOnline()) {
                    Intent intent = new Intent(getApplicationContext(), WebcamClient.class);
                    startActivity(intent);
                    Toast.makeText(MainActivity.this,
                            "Live Cams",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this,
                            "Sorry, please connect to the Internet and try again",
                            Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
        Log.d(TAG, "onCreate started from Main Activity");

    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void sendButton(View view) {

        //get references to EditText field
        final EditText nameEditText = (EditText) findViewById(R.id.editText1);

        String inputVal = nameEditText.getText().toString();
        if (!inputValidation(inputVal)) {
            Toast.makeText(this, "Field cannot be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        //set up SharedPreferences
        myPrefs = getSharedPreferences("prefID", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = myPrefs.edit();
        editor.putString("nameKey", nameEditText.getText().toString());
        editor.apply();
        Toast.makeText(MainActivity.this,
                "Input Saved!",
                Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, TextBoxMsgSend.class);
        EditText editText = (EditText) findViewById(R.id.editText1);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public boolean inputValidation(String str){
        return !str.isEmpty();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu, menu);
        return true;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (Build.VERSION.SDK_INT > 11) {
            //invalidateOptionsMenu();
            menu.findItem(R.id.about_us).setVisible(true);
            menu.findItem(R.id.action_setting).setVisible(true);
            menu.findItem(R.id.action_search).setVisible(true);
            menu.findItem(R.id.nav_zombies).setVisible(false);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            Toast.makeText(MainActivity.this,
                    "Settings Clicked",
                    Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.about_us) {
            Intent intent = new Intent(this, AboutActivity.class);
            startActivity(intent);
        }
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void movieButton(View view) {
        Intent intent = new Intent(this, ShowMovies.class);
        startActivity(intent);
    }

    public void button7(View view) {
        CharSequence text = "Two";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public void button8(View view) {
        CharSequence text = "Three";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(this, text, duration);
        toast.show();
    }

    public void button9(View view) {
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
