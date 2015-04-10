package com.max.annularpg;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	private AnnularView annularPg;
	private FillCircleView circlePg;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		annularPg = (AnnularView) findViewById(R.id.annular_pb);
		circlePg = (FillCircleView) findViewById(R.id.circle_pb);
	}

	boolean isPlaying;
	boolean isPlaying1;

	public void firstClick(View v) {
		if (!isPlaying) {
			isPlaying = true;
			mHandler.post(runnable);
		}
	}

	public void secondClick(View v) {
		if (!isPlaying1) {
			isPlaying1 = true;
			mHandler.post(runnable1);
		}
	}

	int porgress = 0;
	int porgress1 = 1;
	private Handler mHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 1000) {
				if (msg.arg1 <= 100) {
					annularPg.setProgress(msg.arg1);
					mHandler.postDelayed(runnable, 100);
				} else {
					isPlaying = false;
					porgress = 0;
				}
			} else if (msg.what == 1001) {
				if (msg.arg1 <= 100) {
					circlePg.setProgress(msg.arg1);
					mHandler.postDelayed(runnable1, 100);
				} else {
					isPlaying1 = false;
					porgress1 = 0;
				}
			}
		}
	};

	private Runnable runnable = new Runnable() {

		@Override
		public void run() {
			Message message = mHandler.obtainMessage();
			message.arg1 = porgress;
			message.what = 1000;
			mHandler.handleMessage(message);
			porgress++;
		}
	};
	private Runnable runnable1 = new Runnable() {

		@Override
		public void run() {
			Message message = mHandler.obtainMessage();
			message.arg1 = porgress1;
			message.what = 1001;
			mHandler.handleMessage(message);
			porgress1++;
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
