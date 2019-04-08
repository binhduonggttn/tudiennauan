package com.example.tudiennauan;

import java.util.ArrayList;
import java.util.List;

import Adapter.ChuDeMonAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.Database.Mydatabase;
import com.Entities.ChuDeMon;

public class frmChuDeMon extends Activity {
	ListView lv;
	ImageView iv;
	Animation ani;

	Mydatabase db = new Mydatabase(this);
	ArrayList<ChuDeMon> imageArry = new ArrayList<ChuDeMon>();
	ChuDeMonAdapter adapter;
	String[] item = new String[] { "Please search..." };
	Button btThem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vocabulary);
		btThem = (Button) findViewById(R.id.btThemChuDe);
		iv = (ImageView) findViewById(R.id.imgChuDe);
		lv = (ListView) findViewById(R.id.listKeHoach);
		lv.setDividerHeight(10);
		item = getItemsFromDb();
		List<ChuDeMon> contacts = db.getAllContacts();
		for (ChuDeMon cn : contacts) {
			imageArry.add(cn);
		}
		adapter = new ChuDeMonAdapter(this, R.layout.listview_item, imageArry);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String id1 = db.TraVeID(item[position].toString());
				String valuesString = "";
				valuesString = id1 + "";
				Intent intObj = new Intent(frmChuDeMon.this, frmMonMoi.class);
				intObj.putExtra("giatri", valuesString);
				startActivity(intObj);
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String id1 = db.TraVeID(item[arg2].toString());
				db.XoaChuDe(id1);
				adapter.notifyDataSetChanged();
				imageArry.remove(arg2);
				lv.setAdapter(adapter);

				return false;
			}
		});
		btThem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				final Dialog dl = new Dialog(frmChuDeMon.this);
				dl.setContentView(R.layout.themchude);
				dl.setTitle("Thêm chủ đề");
				dl.show();
				final EditText ed = (EditText) dl.findViewById(R.id.edTenChuDe);
				Button btT = (Button) dl.findViewById(R.id.btThemDl);
				Button bthuy = (Button) dl.findViewById(R.id.btHuyDl);

				btT.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						db.ThemChuDe(ed.getText().toString());
						finish();
					}
				});
				bthuy.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						dl.cancel();
					}
				});

			}
		});
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	public String[] getItemsFromDb() {

		// add items on the array dynamically
		List<ChuDeMon> products = db.TraVeDanhSachChuDeMonList();
		int rowCount = products.size();

		String[] item = new String[rowCount];
		int x = 0;

		for (ChuDeMon record : products) {

			item[x] = record.get_tenChuDe();
			x++;
		}

		return item;
	}

}
