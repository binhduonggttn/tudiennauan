package com.example.tudiennauan;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.Database.Mydatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class frmThemMonAn extends Activity {
	EditText edTemMon, edCongThuc;
	Button btThem;
	String s = "";
	Mydatabase _data = new Mydatabase(this);
	Calendar cal;
	ImageView img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.them_mon_an_layout);
		Intent intename = getIntent();
		s = (String) intename.getSerializableExtra("MaChuDe");
		edTemMon = (EditText) findViewById(R.id.edTenMonAnMoi);
		img = (ImageView) findViewById(R.id.imgThemMonAN);
		edCongThuc = (EditText) findViewById(R.id.edCongThuc);
		btThem = (Button) findViewById(R.id.btThemMonMoi);
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String date = df.format(Calendar.getInstance().getTime());
		Toast.makeText(getApplicationContext(), date, Toast.LENGTH_SHORT)
				.show();
		btThem.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!(edTemMon.getText().toString().equals(""))) {
					if (!(edCongThuc.getText().toString().equals(""))) {
						SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
						String date = df.format(Calendar.getInstance()
								.getTime());
						_data.ThemMonMoi(edTemMon.getText().toString(), date,
								edCongThuc.getText().toString(),
								Integer.parseInt(s));
						Intent in = new Intent(frmThemMonAn.this,
								frmChuDeMon.class);
						startActivity(in);
						finish();

						Toast.makeText(getApplicationContext(),
								"Thêm thành công", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getApplicationContext(),
								"Bạn chưa nhập công thức", Toast.LENGTH_SHORT)
								.show();
					}
				} else {
					Toast.makeText(getApplicationContext(),
							"Bạn chưa nhập tên món", Toast.LENGTH_SHORT).show();

				}

			}
		});
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {

				finish();

			}
		});
	}
}
