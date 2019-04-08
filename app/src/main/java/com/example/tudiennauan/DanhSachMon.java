package com.example.tudiennauan;

import android.app.Activity;
import com.mode.ObjectMonNgon;
import com.parse.ParserTruyen;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

import Adapter.MonNgonAdapter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class DanhSachMon extends Activity {


    ArrayList<Object> lstMonNgon;
    MonNgonAdapter adapterTMG;
    ListView listView;
    static String filexml;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mon_ngon);
        readAccess();
        lstMonNgon=new ArrayList<Object>();
        adapterTMG=new MonNgonAdapter(DanhSachMon.this,R.layout.activity_list_mon_ngon,lstMonNgon);
        listView=(ListView) findViewById(R.id.lvMonNgon);
        listView.setAdapter(adapterTMG);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                try {
                    int chon=Integer.parseInt(((ObjectMonNgon) lstMonNgon.get(arg2)).getId());
                    showDetail(chon);


                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        });


        threadLoadData();
    }


    /**
     * Show dữ liệu chi tiết theo vị trí
     * @param possion
     */
    public void showDetail(int possion) {

        Intent myIntent = new Intent(DanhSachMon.this, DetailActivity.class);


        Bundle extra = new Bundle();
        extra.putInt("mypossion", possion);
        extra.putSerializable("objects", lstMonNgon);
        myIntent.putExtra("extra", extra);

        startActivity(myIntent);

    }

    public  void  onClickBack(View view)
    {
        finish();
    }

    public void readAccess() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getAssets().open(
                    "monan.xml"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mComten = "";
            String mLine = reader.readLine();
            while (mLine != null) {
                // process line
                if (mLine != null) {
                    mComten = mComten + mLine;
                }
                mLine = reader.readLine();

            }
            filexml = mComten;

            //Log.e("Log eeee", ""+mComten);
        } catch (IOException e) {
            // log the exception
            Log.e("Loi", "" + e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // log the exception
                    Log.e("Log e", "" + e);
                }
            }
        }
    }


    /**
     * Tuyến tải dữ liệu
     */
    private void threadLoadData() {

        new Thread(new Runnable() {
            public void run() {
                try {



                    lstMonNgon.addAll(getDefaultData());


                    runOnUiThread(NotifyDataToListView);

                } catch (Exception e) {
                    //Log.e("Mon:", "kk"+e);
                }

            }
        }).start();
    }

    /**
     * Cập nhật lại giao diện sau khi chạy tuyến
     */
    private Runnable NotifyDataToListView = new Runnable() {
        public void run() {


            adapterTMG.setListView(lstMonNgon);
            adapterTMG.notifyDataSetChanged();

        }
    };

    /**
     * Tải dữ liệu từ phân tích xml
     */
    public ArrayList<ObjectMonNgon> getDefaultData() {
        // RssParser myParser = new RssParser();
        ParserTruyen myParser = new ParserTruyen();
        ArrayList<ObjectMonNgon> lst = myParser.getNewList(filexml);




        return lst;
    }


}
