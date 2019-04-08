package com.example.root.saikaapp;

import AI.Util;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private int progress=0;
    private View view;

    private void load()
    {
        SharedPreferences sp=getApplicationContext().getSharedPreferences("config", MODE_PRIVATE);
        progress = Integer.parseInt(sp.getString("progress", String.valueOf(0)));
    }
    private void save(int progress)
    {
        SharedPreferences sp=getApplicationContext().getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("progress", String.valueOf(progress));
        editor.apply();
//        Snackbar.make(view, "自动保存进度成功", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }
    private void playerWorkU() {
//        save(0);
        load();
        switch (progress) {
            case 0: {
                showDialog("温馨提示", "程序主体我还没开始写呢，就是给你看看先:)", "逗我吧？");
                delay(1);
                progress++;
                save(progress);
            }
            case 1:{
                inputDialog("我是你的专属男友哦。请在下面写下你的问题:)","提问","滚蛋，不问");
                delay(1);

            }

        }

    }

    private void delay(int i) {
        try {
            Thread.sleep(i*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playerWork() {
        switch (progress) {
            case 0:
                showDialog("温馨提示", "程序主体我还没开始写呢，就是给你看看先:)", "逗我吧？");
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view=v;
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                playerWorkU();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
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
            showDialogOnlyOk("恭喜你发现了彩蛋","笑话一则。一日刘备和华佗饮酒。刘备问华佗：您从医多年，对你来说" +
                    "最重要的是什么？华佗说：医德。一旁的张飞害羞的说：老头，这事不是说好保密的么？","好好笑啊！","这笑话也太无聊了吧");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_help)
        {
            showDialog("帮助",getString(R.string.help_detail),"并没有帮助啊...");
        }
        if (id == R.id.nav_about) {
            showDialog("关于作者",getString(R.string.info_detail),"确认");
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void showDialog(String tag,String txt,String info) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tag);
        builder.setMessage(txt);
        builder.setCancelable(false);
        builder.setPositiveButton(info,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void showDialogOnlyOk(final String tag, final String txt, final String info1, final String info2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tag);
        builder.setMessage(txt);
        builder.setCancelable(false);
        builder.setNegativeButton(info1,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        builder.setPositiveButton(info2,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        showDialogOnlyOk(tag,txt,info1,info2);
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void inputDialog(String txt,String ok,String cancel)
    {
        final String[] s = {null};
        final EditText inputServer = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(txt).setIcon(android.R.drawable.ic_dialog_info).setView(inputServer)
                .setNegativeButton(cancel, null);
        builder.setPositiveButton(ok, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                s[0] = inputServer.getText().toString();

                    delay(1);
                        Util util = new Util();
                        showDialog("回复", util.getMessage(s[0]), "确认");

            }
        });
        builder.show();


    }
}
