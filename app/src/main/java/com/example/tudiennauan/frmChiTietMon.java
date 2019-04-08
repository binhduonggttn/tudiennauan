package com.example.tudiennauan;

import com.Database.Mydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class frmChiTietMon extends Activity {

	TextView tvTenMonAn, tvNoiDungMon;
	Mydatabase db = new Mydatabase(this);
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chi_tiet_mon_layout);
		tvTenMonAn = (TextView) findViewById(R.id.TvTenMonChiTiet);
		tvNoiDungMon = (TextView) findViewById(R.id.tvNoiDungMonAn);
		Intent intename = getIntent();
		String s = (String) intename.getSerializableExtra("MaMon");
		img = (ImageView) findViewById(R.id.imgChiTietMon);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		tvTenMonAn.setText(db.TraVeTenMon(Integer.parseInt(s)));
		tvNoiDungMon.setText(db.TraVeNoiDungMon(Integer.parseInt(s)));

	}

}
