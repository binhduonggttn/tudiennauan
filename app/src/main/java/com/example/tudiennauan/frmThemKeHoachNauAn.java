package com.example.tudiennauan;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.Database.Mydatabase;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class frmThemKeHoachNauAn extends Activity {
	Calendar cal;
	EditText edTenKeHoach;
	EditText edNgayDuDinh, edTienDuDinh;
	private Date dateFinish;
	Button btChon, btThemKeHoach, btThoat;
	ImageView img;
	Mydatabase _data = new Mydatabase(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.them_ke_hoach_nau_an);
		edTenKeHoach = (EditText) findViewById(R.id.edTenKeHoach);
		edNgayDuDinh = (EditText) findViewById(R.id.edNgayThucHienDuDinh);
		edTienDuDinh = (EditText) findViewById(R.id.edSoTienDuDinh);
		btThemKeHoach = (Button) findViewById(R.id.btThemKeHoach);
		btChon = (Button) findViewById(R.id.btLayNgayDuDinh);
		btThoat = (Button) findViewById(R.id.btThoat);
		img = (ImageView) findViewById(R.id.imgThemKeHoach);
		img.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btThoat.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
		btChon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				getDefaultInforkc();
				showDatePickerDialogkc();

			}
		});
		btThemKeHoach.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				if (!(edTenKeHoach.getText().toString().equals(""))) {
					if (!(edNgayDuDinh.getText().toString().equals(""))) {
						if (!(edTienDuDinh.getText().toString().equals(""))) {
							_data.ThemKeHoach(
									edTenKeHoach.getText().toString(),
									edNgayDuDinh.getText().toString(), Float
											.parseFloat(edTienDuDinh.getText()
													.toString()));
							Intent in = new Intent(frmThemKeHoachNauAn.this,
									frmKeHoachNauAn.class);
							startActivity(in);
							finish();
							Toast.makeText(getApplicationContext(),
									"Thêm thành công", Toast.LENGTH_SHORT)
									.show();
						} else {
							Toast.makeText(getApplicationContext(),
									"Bạn chưa nhập tiền dự định",
									Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(getApplicationContext(),
								"Bạn chưa nhập ngày dự định",
								Toast.LENGTH_SHORT).show();
					}

				} else {
					Toast.makeText(getApplicationContext(),
							"Bạn chưa nhập tên kế hoạch", Toast.LENGTH_SHORT)
							.show();
				}

			}
		});
	}

	public void showDatePickerDialogkc() {
		OnDateSetListener callback = new OnDateSetListener() {
			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// Mỗi lần thay đổi ngày tháng năm thì cập nhật lại TextView
				// Date
				edNgayDuDinh.setText((dayOfMonth) + "/" + (monthOfYear + 1)
						+ "/" + year);
				// Lưu vết lại biến ngày hoàn thành
				cal.set(year, monthOfYear, dayOfMonth);
				dateFinish = cal.getTime();
			}
		};
		// các lệnh dưới này xử lý ngày giờ trong DatePickerDialog
		// sẽ giống với trên TextView khi mở nó lên
		String s1 = edNgayDuDinh.getText() + "";
		String strArrtmp1[] = s1.split("/");
		int ngayv = Integer.parseInt(strArrtmp1[0]);
		int thangv = Integer.parseInt(strArrtmp1[1]) - 1;
		int namv = Integer.parseInt(strArrtmp1[2]);
		DatePickerDialog pic1 = new DatePickerDialog(frmThemKeHoachNauAn.this,
				callback, namv, thangv, ngayv);
		pic1.setTitle("Chọn ngày dự định");
		pic1.show();
	}

	public void getDefaultInforkc() {
		// lấy ngày hiện tại của hệ thống
		cal = Calendar.getInstance();
		SimpleDateFormat dft = null;
		// Định dạng ngày / tháng /năm
		dft = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
		String strDate = dft.format(cal.getTime());
		// hiển thị lên giao diện
		edNgayDuDinh.setText(strDate);
		// gán cal.getTime() cho ngày hoàn thành và giờ hoàn thành
		dateFinish = cal.getTime();
	}
}
