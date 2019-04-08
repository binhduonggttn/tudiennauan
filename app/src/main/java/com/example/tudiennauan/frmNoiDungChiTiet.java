package com.example.tudiennauan;

import com.Database.Mydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class frmNoiDungChiTiet extends Activity {
	TextView tvTen, tvTien, tvNoiDung;
	Mydatabase db = new Mydatabase(this);
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.noi_dung_chi_tiet_layout);
		tvTen = (TextView) findViewById(R.id.tvTenMonChiTiet);
		tvTien = (TextView) findViewById(R.id.tvTienNoiDungChi);
		tvNoiDung = (TextView) findViewById(R.id.tvNoiDungChi);
		img = (ImageView) findViewById(R.id.imgNoiDungChiTiet);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		Intent intename = getIntent();
		final String s = (String) intename.getSerializableExtra("MaChiTiet");
		tvTen.setText(db.TraVeTenChiTiet(s));
		tvTien.setText(db.TraVeTienChiTiet(s) + "");
		tvNoiDung.setText(db.TraVeNoiDungChi(s));

	}

}
