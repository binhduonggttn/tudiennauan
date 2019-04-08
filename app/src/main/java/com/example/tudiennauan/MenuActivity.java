package com.example.tudiennauan;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuActivity extends Activity {
	GridView gv;
	ArrayList<Logo> ds;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		gv = (GridView) findViewById(R.id.gridView1);
		ds = new ArrayList<Logo>();
		String[] ten = { "Chủ Đề", "Học Online", "Kế Hoạch",
				"Công Thức", "Thống kê", "Exit" };

		int[] hinh = { R.drawable.apple, R.drawable.menu_youtube,
				R.drawable.list_kitchen, R.drawable.ic_launcher, R.drawable.phone,
				R.drawable.exit };

		for (int i = 0; i < ten.length; i++)
			ds.add(new Logo(ten[i], hinh[i]));

		MyAdapter adapter = new MyAdapter(this);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch (arg2) {
				case 0:
					Intent i = new Intent(getApplicationContext(),
							frmChuDeMon.class);
					startActivity(i);

					break;
				case 1:
					Intent youtube = new Intent(
							Intent.ACTION_VIEW,
							Uri.parse("https://www.youtube.com/channel/UC-2EWHDbHnx5JOeyWfLdIxQ/videos"));
					startActivity(youtube);

					break;
				case 2:
					Intent test = new Intent(getApplicationContext(),
							frmKeHoachNauAn.class);
					startActivity(test);

					break;

				case 3:
					Intent kt = new Intent(getApplicationContext(),
							DanhSachMon.class);
					startActivity(kt);
					break;
				case 4:
					Intent About = new Intent(getApplicationContext(),
							frmThongKe.class);
					startActivity(About);
					break;
				case 5:
					AlertDialog.Builder bui = new AlertDialog.Builder(
							MenuActivity.this);
					bui.setTitle("Exit");
					bui.setMessage("Do You Want To Exit?");
					bui.setNegativeButton("Yes",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									finish();
								}
							});
					bui.setPositiveButton("No",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									// TODO Auto-generated method stub
									dialog.cancel();
								}
							});
					bui.show();

					break;

				default:
					break;
				}

			}
		});

	}

	public static class View_Mot_O {
		public ImageView imageview;
		public TextView textview;
	}

	class MyAdapter extends BaseAdapter {
		Context c;

		MyAdapter(Context c) {
			this.c = c;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return ds.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return ds.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			View_Mot_O mot_o;
			LayoutInflater inf = ((Activity) c).getLayoutInflater();
			if (convertView == null) {
				convertView = inf.inflate(R.layout.gridview_mot_o, null);
				mot_o = new View_Mot_O();
				mot_o.imageview = (ImageView) convertView
						.findViewById(R.id.imgChuDeMon);
				mot_o.textview = (TextView) convertView
						.findViewById(R.id.ccccc);
				convertView.setTag(mot_o);

                convertView.setBackgroundResource(R.drawable.select);
//
//                convertView
//                        .setBackgroundColor(Color.argb(200, 222, 102, 52));

//				if (position % 6 == 0) {
//					convertView
//							.setBackgroundColor(Color.argb(200, 216, 32, 30));
//				} else {
//					if (position % 6 == 1) {
//						convertView.setBackgroundColor(Color.argb(200, 255,
//								131, 6));
//					} else {
//						if (position % 6 == 2) {
//							convertView.setBackgroundColor(Color.argb(200, 0,
//									198, 99));
//						} else {
//							if (position % 6 == 3)
//								convertView.setBackgroundColor(Color.argb(200,
//										0, 206, 206));
//							else {
//								if (position % 6 == 4)
//									convertView.setBackgroundColor(Color.argb(
//											200, 26, 156, 139));
//								else
//									convertView.setBackgroundColor(Color.argb(
//											200, 128, 0, 128));
//							}
//						}
//					}
//				}
			} else {
				mot_o = (View_Mot_O) convertView.getTag();
			}

			mot_o.imageview.setImageResource(ds.get(position).hinh);
			mot_o.textview.setText(ds.get(position).ten);

			return convertView;
		}
	}

}
