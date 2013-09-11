Rotate3dDemo
============

3D翻转demo



package com.xinlan.ticketDemo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class ColorProgress extends View{
	private Bitmap mBitmap;
	private Rect srcRect;
	private Rect dstRect;
	
	private int bitWidth,bitHeight;
	
	public ColorProgress(Context context, AttributeSet attrs) {
		super(context, attrs);
		mBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.pass_loadingbar);
		bitWidth = mBitmap.getWidth();
		bitHeight = mBitmap.getHeight();
		srcRect = new Rect(0,0,bitWidth,bitHeight);
		dstRect = new Rect();
	}
	
	public void setProgress(int value){
		int originWidth = bitWidth*value/100;
		int start_x = bitWidth-originWidth;
		srcRect.left = start_x;
		srcRect.right = srcRect.left+originWidth;
		
		int w = getWidth()*value/100;
		dstRect.set(0, 0, w, getHeight());
		invalidate();
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawBitmap(mBitmap, srcRect, dstRect, null);
	}
}//end class
