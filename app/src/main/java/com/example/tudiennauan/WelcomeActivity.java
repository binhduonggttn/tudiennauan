package com.example.tudiennauan;

import java.io.IOException;

import com.Database.Mydatabase;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

public class WelcomeActivity extends Activity {
	TextView tv;

	Mydatabase data = new Mydatabase(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		data.KiemTraFile();
		isCreateDB();
		tv = (TextView) findViewById(R.id.ccccc);
		Typeface Bl = Typeface.createFromAsset(getAssets(), "Blazed.ttf");
		tv.setTypeface(Bl);
		CountDownTimer cdt = new CountDownTimer(2000, 1000) {

			@Override
			public void onTick(long millisUntilFinished) {
				// TODO Auto-generated method stub




					tv.setTextColor(Color.argb(200, 216, 32, 30));





			}

			@Override
			public void onFinish() {
				// TODO Auto-generated method stub

				Intent i = new Intent(getApplicationContext(),
						MenuActivity.class);
				startActivity(i);
				finish();
			}
		}.start();
	}

	private boolean isCreateDB() {

		try {
			return data.isCreatedDatabase();
		} catch (IOException e) {

			e.printStackTrace();
			return false;
		}

	}

}
