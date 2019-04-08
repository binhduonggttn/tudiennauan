package Adapter;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.Entities.ChuDeMon;
import com.example.tudiennauan.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ChuDeMonAdapter extends ArrayAdapter<ChuDeMon> {
	Context context;
	int layoutResourceId;
	Animation ani;
	// BcardImage data[] = null;
	ArrayList<ChuDeMon> data = new ArrayList<ChuDeMon>();

	public ChuDeMonAdapter(Context context, int layoutResourceId,
			ArrayList<ChuDeMon> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ImageHolder holder = null;
		ani = AnimationUtils.loadAnimation(context, R.anim.scale2);
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);
			holder = new ImageHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.tvTenChuDeMon);
			holder.imgIcon = (ImageView) row.findViewById(R.id.imgChuDeMon);
			row.setTag(holder);
		} else {
			holder = (ImageHolder) row.getTag();
		}
		ChuDeMon picture = data.get(position);
		holder.txtTitle.setText(picture.get_tenChuDe());
		byte[] outImage = picture.get_hinhAnh();
		ByteArrayInputStream imageStream = new ByteArrayInputStream(outImage);
		Bitmap theImage = BitmapFactory.decodeStream(imageStream);
		holder.imgIcon.setImageBitmap(theImage);
		if (position % 4 == 0) {
			row.setBackgroundColor(Color.argb(200, 213, 7, 0));
		} else {
			if (position % 4 == 1) {
				row.setBackgroundColor(Color.argb(200, 255, 131, 6));
			} else {
				if (position % 4 == 2) {
					row.setBackgroundColor(Color.argb(200, 0, 198, 99));
				} else
					row.setBackgroundColor(Color.argb(200, 0, 206, 206));
			}

		}

		row.startAnimation(ani);
		return row;

	}

	static class ImageHolder {
		ImageView imgIcon;
		TextView txtTitle;
	}
}
