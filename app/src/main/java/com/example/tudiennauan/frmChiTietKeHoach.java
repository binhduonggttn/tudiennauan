package com.example.tudiennauan;

import java.util.ArrayList;
import java.util.List;

import Adapter.ChiTietKeHoachAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.Database.Mydatabase;
import com.Entities.ChiTietKeHoachNauAn;

public class frmChiTietKeHoach extends Activity {

	ListView listChiTietKH;
	TextView tvDuDinh, tvDaChi;
	ImageView img;
	Mydatabase db = new Mydatabase(this);
	ArrayList<ChiTietKeHoachNauAn> arr = new ArrayList<ChiTietKeHoachNauAn>();
	ChiTietKeHoachAdapter adapter;
	String[] item = new String[] { "Please search..." };
	Button btThem;
	String s = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chi_tiet_ke_hoach_layout);
		tvDuDinh = (TextView) findViewById(R.id.tvDuDinh);
		tvDaChi = (TextView) findViewById(R.id.tvTienNoiDungChi);
		btThem = (Button) findViewById(R.id.btThemKeHoach);
		img = (ImageView) findViewById(R.id.imgChiTietKeHoach);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Intent intename = getIntent();
		s = (String) intename.getSerializableExtra("MaKeHoach");
		listChiTietKH = (ListView) findViewById(R.id.listChiTietKeHoach);
		item = getItemsFromDb();
		listChiTietKH.setDividerHeight(10);
		arr.addAll(db.TraVeTuDanhSachChiTiet(s));

		adapter = new ChiTietKeHoachAdapter(this,
				R.layout.list_chi_tiet_ke_hoach, arr);
		listChiTietKH.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		tvDuDinh.setText(db.TraVeTienDuDinh(s) + "");
		tvDaChi.setText(db.TraVeTienChi(s) + "");

		listChiTietKH.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				db.XoaChiTiet(db.TraVeMaChiTietkeHoach(item[arg2].toString()));
				arr.remove(arg2);

				tvDaChi.setText(db.TraVeTienChi(s) + "");
				adapter.notifyDataSetChanged();
				return false;
			}
		});

		listChiTietKH.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {

				String id1 = db.TraVeMaChiTietkeHoach(item[arg2].toString());
				String valuesString = "";
				valuesString = id1 + "";
				Intent intObj = new Intent(frmChiTietKeHoach.this,
						frmNoiDungChiTiet.class);
				intObj.putExtra("MaChiTiet", valuesString);
				startActivity(intObj);
			}
		});
		btThem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String valuesString = "";
				valuesString = s + "";
				Intent intObj = new Intent(frmChiTietKeHoach.this,
						frmThemChiTiet.class);
				intObj.putExtra("MaKeHoach1", valuesString);
				startActivity(intObj);
				finish();
			}
		});
	}

	public String[] getItemsFromDb() {
		List<ChiTietKeHoachNauAn> products = db.TraVeDanhSachChiTietList(s);
		int rowCount = products.size();
		String[] item = new String[rowCount];
		int x = 0;
		for (ChiTietKeHoachNauAn record : products) {
			item[x] = record.get_tenChiTiet();
			x++;
		}
		return item;
	}
}
