package com.xinlan.rotatedemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.TextView;

public class RotateFragment extends Fragment{
	private String mImageUrl;
	private Item data;
	private View mainView;
	private ViewGroup mContainer;
	private View foreFrame,backFrame;
	
	private View rotateToBackBtn,rotateToForeBtn;
	private TextView foreTitle,foreContent;
	private TextView backTitle;
	private ImageView forePic,backPic;

	public static RotateFragment newInstance(Item item) {
		final RotateFragment f = new RotateFragment();
		f.data = item;
		return f;
	}

	public RotateFragment() {
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mainView = inflater.inflate(R.layout.rotate_item, container, false);
		mContainer = (ViewGroup)mainView.findViewById(R.id.container);
		
		foreFrame = mainView.findViewById(R.id.frame);
		backFrame = mainView.findViewById(R.id.back_frame);
		
		rotateToBackBtn = mainView.findViewById(R.id.rotate_to_back);
		rotateToForeBtn = mainView.findViewById(R.id.rotate_to_fore);
		rotateToBackBtn.setOnClickListener(new RotateToBack());
		rotateToForeBtn.setOnClickListener(new RotateToFore());
		
		foreTitle = (TextView)mainView.findViewById(R.id.fore_title);
		foreContent = (TextView)mainView.findViewById(R.id.fore_content);
		forePic = (ImageView)mainView.findViewById(R.id.fore_pic);
		
		backTitle = (TextView)mainView.findViewById(R.id.back_content);
		backPic = (ImageView)mainView.findViewById(R.id.back_pic);
		
		return mainView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		if (MainActivity.class.isInstance(getActivity())) {
			foreTitle.setText(data.getTitle());
			foreContent.setText(data.getContent());
			forePic.setImageResource(data.getPicId());
			
			backTitle.setText(data.getBackTitle());
			backPic.setImageResource(data.getBackPicId());
		}
	}
	
	private final class RotateToBack implements OnClickListener{
		@Override
		public void onClick(View v) {
			applyRotation(0,90,R.id.frame);
		}
	}//end inner class
	
	private final class RotateToFore implements OnClickListener{
		@Override
		public void onClick(View v) {
			applyRotation(0,90,R.id.back_frame);
		}
	}//end inner class

	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	private void applyRotation(float start, float end, final int viewId){
		final float centerX = mContainer.getWidth() / 2.0f;
        final float centerY = mContainer.getHeight() / 2.0f;
        Rotate3dAnimation rotation =
            new Rotate3dAnimation(start, end, centerX, centerY, 100f, true);
        rotation.setDuration(500);
        rotation.setInterpolator(new AccelerateInterpolator());
        rotation.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation arg0) {
				mContainer.post(new Runnable() {
					@Override
					public void run() {
						if(viewId == R.id.frame){
							foreFrame.setVisibility(View.GONE);
							backFrame.setVisibility(View.VISIBLE);
						}else if (viewId == R.id.back_frame) {
							backFrame.setVisibility(View.GONE);
							foreFrame.setVisibility(View.VISIBLE);
						}
						Rotate3dAnimation rotatiomAnimation = new Rotate3dAnimation(-90, 0, centerX, centerY, 100.0f, false);
						rotatiomAnimation.setDuration(500);
						rotatiomAnimation.setInterpolator(new DecelerateInterpolator());
						mContainer.startAnimation(rotatiomAnimation);
					}
				});
			
			}
			@Override
			public void onAnimationRepeat(Animation arg0) {
			}

			@Override
			public void onAnimationStart(Animation arg0) {
			}
        });
        mContainer.startAnimation(rotation);
	}
}
