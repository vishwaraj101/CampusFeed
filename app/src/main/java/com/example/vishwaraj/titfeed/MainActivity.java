package com.example.vishwaraj.titfeed;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import android.R.*;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //this is the code i have written
    String url;
    OkHttpClient client=new OkHttpClient();
    Request request;
    String title,jsondata,body;
    ListView list;
    ArrayAdapter adapter;
    ArrayList<String> items;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list=(ListView)findViewById(R.id.listView);



        items=new ArrayList<String>();
        adapter=new ArrayAdapter(this,R.layout.activity_main,R.id.parsedtext,items);
        list.setAdapter(adapter);












        //this is the code i have written


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



  void makecall(final Request request) {
      client.newCall(request).enqueue(new Callback() {
          @Override
          public void onFailure(Call call, IOException e) {
              Log.v("failure", e.getLocalizedMessage());
          }

          @Override
          public void onResponse(Call call, Response response) throws IOException {
              if (!response.isSuccessful()) {
                  Log.v("Panuati", "nothing");
              } else {
                  jsondata = response.body().string();
                  try {
                      JSONObject jsonObject = new JSONObject(jsondata);

for (int i=0;i<jsonObject.length();i++){


    title= jsonObject.getString("title");
    body = jsonObject.getString("body");
    Log.i("title", title);
    Log.i("body", body);
    items.add(title);

}













                  } catch (JSONException e) {
                      e.printStackTrace();
                  }


              }

          }
      });
  }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_cs) {

           url="http://titfeed.16mb.com/titfeed/api/api.php?id=1";
            request=new Request.Builder().url(url).build();
            makecall(request);


        } else if (id == R.id.nav_mech) {
            url="http://titfeed.16mb.com/titfeed/api/api.php?id=2";
            request=new Request.Builder().url(url).build();
            makecall(request);


        } else if (id==R.id.nav_Elec){
            url="http://titfeed.16mb.com/titfeed/api/api.php?id=3";
            request=new Request.Builder().url(url).build();
            makecall(request);
        }
        else if (id==R.id.nav_ph){
            url="http://titfeed.16mb.com/titfeed/api/api.php?id=4";
            makecall(request);

        }else if (id==R.id.nav_mba){
url="http://titfeed.16mb.com/titfeed/api/api.php?id=5";
            makecall(request);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
