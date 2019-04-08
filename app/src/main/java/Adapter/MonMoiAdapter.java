package Adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.Entities.MonAnMoi;
import com.example.tudiennauan.R;

public class MonMoiAdapter extends ArrayAdapter<MonAnMoi> {
	Context context;
	int layoutResourceId;
	Animation ani;
	// BcardImage data[] = null;
	ArrayList<MonAnMoi> data = new ArrayList<MonAnMoi>();

	public MonMoiAdapter(Context context, int layoutResourceId,
			ArrayList<MonAnMoi> data) {
		super(context, layoutResourceId, data);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.data = data;
	}




	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		ImageHolder holder = null;
		ani =AnimationUtils.loadAnimation(context, R.anim.scale2);
		if (row == null) {
			LayoutInflater inflater = ((Activity) context).getLayoutInflater();
			row = inflater.inflate(layoutResourceId, parent, false);

			holder = new ImageHolder();
			holder.txtTitle = (TextView) row.findViewById(R.id.tvTenMonAn);
			holder.TvNgayLap = (TextView) row.findViewById(R.id.tvNgayLapMonAn);
			row.setTag(holder);
		} else {
			holder = (ImageHolder) row.getTag();
		}

		MonAnMoi picture = data.get(position);
		holder.txtTitle.setText(picture.get_tenMon());

		// convert byte to bitmap take from contact class

		holder.TvNgayLap.setText(picture.get_ngayLap());

		if (position % 4 == 0){
			row.setBackgroundColor(Color.argb(200, 213, 7, 0));
		}
		else
		{
			if (position % 4 == 1){
				row.setBackgroundColor(Color.argb(200, 255, 131, 6));
			}
			else
			{
				if (position % 4 == 2){
					row.setBackgroundColor(Color.argb(200, 0, 198, 99));
				}
				else
					row.setBackgroundColor(Color.argb(200, 0, 206, 206));
			}
		}
		row.startAnimation(ani);
		return row;

	}

	static class ImageHolder {
		TextView TvNgayLap;
		TextView txtTitle;
	}
}
