package com.example.tudiennauan;

import java.util.ArrayList;
import java.util.List;

import Adapter.MonMoiAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.Database.Mydatabase;
import com.Entities.MonAnMoi;

public class frmMonMoi extends Activity {

	ListView lv;
	ImageView iv;
	Animation ani;
	String[] item = new String[] { "Please search..." };

	Mydatabase db = new Mydatabase(this);
	ArrayList<MonAnMoi> imageArry = new ArrayList<MonAnMoi>();
	MonMoiAdapter adapter;
	Button btThem;
	String s = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mon_moi_layout);
		btThem = (Button) findViewById(R.id.btThemMonAn);
		iv = (ImageView) findViewById(R.id.imgMonMoi);
		lv = (ListView) findViewById(R.id.listKeHoach);
		lv.setDividerHeight(10);
		Intent intename = getIntent();
		s = (String) intename.getSerializableExtra("giatri");
		item = getItemsFromDb();

		imageArry.addAll(db.TraVeTuDanhSachMonTheoChuDe(s));

		adapter = new MonMoiAdapter(this, R.layout.layout_item_mon, imageArry);
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				String id1 = db.TraVeIDMon(item[position].toString());

				String valuesString = "";
				valuesString = id1 + "";

				Intent intObj = new Intent(frmMonMoi.this, frmChiTietMon.class);

				intObj.putExtra("MaMon", valuesString);

				startActivity(intObj);
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String id1 = db.TraVeIDMon(item[arg2].toString());
				db.XoaMon(id1);
				imageArry.remove(arg2);
				adapter.notifyDataSetChanged();
				lv.setAdapter(adapter);
				return false;
			}
		});
		iv.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		btThem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String valuesString = "";
				valuesString = s + "";
				Intent in = new Intent(frmMonMoi.this, frmThemMonAn.class);
				in.putExtra("MaChuDe", valuesString);
				startActivity(in);

			}
		});
	}

	public String[] getItemsFromDb() {

		// add items on the array dynamically
		List<MonAnMoi> products = db.TraVeDanhSachMonTheChuDeList();
		int rowCount = products.size();

		String[] item = new String[rowCount];
		int x = 0;

		for (MonAnMoi record : products) {

			item[x] = record.get_tenMon();
			x++;
		}

		return item;
	}

}
