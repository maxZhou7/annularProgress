package com.max.annularpg;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

public class FillCircleView extends View {

	private Paint mPaint;
	private int width, height;
	private int maxProgress = 100;
	private int currentProgress = 30;
	private int circleWidth = 2;
	private RectF rectF;

	public FillCircleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint = new Paint();
		rectF = new RectF();
		mPaint.setAntiAlias(true);
	}

	public void setProgress(int progress) {
		if (progress < 0 || progress > 100) {
			return;
		}
		currentProgress = progress;
		invalidate();
	}

	public int getProgress() {
		return currentProgress;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		width = getWidth();
		height = getHeight();
		int min = Math.min(width, height);
		width = height = min;

		rectF.left = rectF.top = circleWidth / 2;
		rectF.right = rectF.bottom = width - circleWidth / 2;
		int r = (width - circleWidth) / 2;
		mPaint.setColor(Color.RED);
		mPaint.setStyle(Style.STROKE);
		mPaint.setStrokeWidth(circleWidth);
		canvas.drawCircle(min / 2, min / 2, r, mPaint);

		mPaint.setStyle(Style.FILL);
		mPaint.setColor(Color.BLUE);

		float sY = r - (float) 2 * r * currentProgress / maxProgress;
		float angel = (float) Math.acos(sY / r);// 弧度

		canvas.drawArc(rectF, 90 - (float) (180 * angel / Math.PI),
				(float) (360 * angel / Math.PI), true, mPaint);
		Path path = new Path();
		if (sY > 0) {
			mPaint.setColor(Color.WHITE);
			path.moveTo((float) (r - Math.sqrt(r * r - sY * sY)) - circleWidth
					/2, r + sY);
			path.lineTo((float) (r + Math.sqrt(r * r - sY * sY)) + circleWidth
					, r + sY);
			path.lineTo(r + circleWidth/2 , r);
		} else {
			mPaint.setColor(Color.BLUE);
			path.moveTo((float) (r - Math.sqrt(r * r - sY * sY)), r + sY
					+ circleWidth / 2);
			path.lineTo((float) (r + Math.sqrt(r * r - sY * sY)) + circleWidth,
					r + sY + circleWidth / 2);
			path.lineTo(r + circleWidth/2, r + circleWidth );
		}
		canvas.drawPath(path, mPaint);

		mPaint.setColor(Color.GREEN);
		mPaint.setStrokeWidth(1);
		String text = currentProgress + "%";
		int textHeight = height / 5;
		mPaint.setTextSize(textHeight);
		int textWidth = (int) mPaint.measureText(text, 0, text.length());
		mPaint.setStyle(Style.FILL);
		canvas.drawText(text, width / 2 - textWidth / 2, height / 2
				+ textHeight / 2, mPaint);
	}
}
