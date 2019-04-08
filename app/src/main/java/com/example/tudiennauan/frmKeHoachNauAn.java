package com.example.tudiennauan;

import java.util.ArrayList;
import java.util.List;

import Adapter.KeHoachNauAnAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.Database.Mydatabase;
import com.Entities.KeHoachNauAn;

public class frmKeHoachNauAn extends Activity {

	ListView lv;
	Mydatabase db = new Mydatabase(this);
	ArrayList<KeHoachNauAn> imageArry = new ArrayList<KeHoachNauAn>();
	KeHoachNauAnAdapter adapter;
	String[] item = new String[] { "Please search..." };
	Button btThem;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ke_hoach_nau_an_layout);
		lv = (ListView) findViewById(R.id.listKeHoach);
		img = (ImageView) findViewById(R.id.imgKeHoach);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});

		btThem = (Button) findViewById(R.id.btThemKH);
		btThem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent in = new Intent(frmKeHoachNauAn.this,
						frmThemKeHoachNauAn.class);
				startActivity(in);
			}
		});
		lv.setDividerHeight(10);
		imageArry.addAll(db.TraVeTuDanhSachKeHoach());

		adapter = new KeHoachNauAnAdapter(this, R.layout.ke_hoach_nau_an_list,
				imageArry);
		lv.setAdapter(adapter);
		item = getItemsFromDb();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String id1 = db.TraVeMaKeHoach(item[arg2].toString());
				String valuesString = "";
				valuesString = id1 + "";
				Intent intObj = new Intent(frmKeHoachNauAn.this,
						frmChiTietKeHoach.class);
				intObj.putExtra("MaKeHoach", valuesString);
				startActivity(intObj);

			}
		});

	}

	public String[] getItemsFromDb() {

		// add items on the array dynamically
		List<KeHoachNauAn> products = db.TraVeDanhSachKeHoachList();
		int rowCount = products.size();

		String[] item = new String[rowCount];
		int x = 0;

		for (KeHoachNauAn record : products) {

			item[x] = record.get_tenKeHoach();
			x++;
		}

		return item;
	}

}
