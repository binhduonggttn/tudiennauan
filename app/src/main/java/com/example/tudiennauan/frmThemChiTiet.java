package com.example.tudiennauan;

import com.Database.Mydatabase;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class frmThemChiTiet extends Activity {

	Mydatabase db = new Mydatabase(this);
	EditText edTen, edTin, EdNoiDung;
	Button btThem;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.them_chi_tiet_ke_hoach);
		Intent intename = getIntent();
		final String s = (String) intename.getSerializableExtra("MaKeHoach1");
		final float tiendutinh = db.TraVeTienDuDinh(s);
		final float tienchi = db.TraVeTienChi(s);
		img = (ImageView) findViewById(R.id.imgThemChiTietKeHoach);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		edTen = (EditText) findViewById(R.id.edTenChiTiet);
		edTin = (EditText) findViewById(R.id.edTienDuDinh);
		EdNoiDung = (EditText) findViewById(R.id.edNoiDungThucHienChiTiet);
		btThem = (Button) findViewById(R.id.btThemChiTiet);
		btThem.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				AlertDialog.Builder al = new AlertDialog.Builder(
						frmThemChiTiet.this);
				al.setTitle("Thông báo");
				if (tienchi > tiendutinh) {
					al.setMessage("Tiền chi dụ tính đã hết");

					al.setNegativeButton("Thoát", new OnClickListener() {

						@Override
						public void onClick(DialogInterface arg0, int arg1) {
							finish();

						}
					});
				} else {
					if ((tienchi + Float.parseFloat(edTin.getText().toString()) > tiendutinh)) {
						al.setMessage("Tiền bạn thêm vượt quá số tiền dự định");

						al.setNegativeButton("Thoát", new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {
								finish();

							}
						});
					} else {
						db.ThemChiTiet(s, edTen.getText().toString(), edTin
								.getText().toString(), EdNoiDung.getText()
								.toString());
						al.setMessage("Thêm thành công");
						al.setNegativeButton("Thoát", new OnClickListener() {

							@Override
							public void onClick(DialogInterface arg0, int arg1) {

								finish();

							}
						});
					}

				}
				al.create().show();

			}
		});

	}

}
