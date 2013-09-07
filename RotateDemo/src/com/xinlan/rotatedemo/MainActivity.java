package com.xinlan.rotatedemo;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	private ViewPager mGallery;
	private ArrayList<Item> mData;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        init();
    }
    
    private void init(){
    	mGallery = (ViewPager)findViewById(R.id.gallery);
    	mGallery.setAdapter(new ItemAdapter(getSupportFragmentManager()));
    }
    
    private final class ItemAdapter extends FragmentStatePagerAdapter{
		public ItemAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int index) {
			return RotateFragment.newInstance(mData.get(index));
		}

		@Override
		public int getCount() {
			return mData.size();
		}
	}//end inner class
    
    private void initData(){
    	mData = new ArrayList<Item>();
    	Item item1 = new Item();
    	item1.setBackPicId(R.drawable.pic1);
    	item1.setPicId(R.drawable.pic1);
    	item1.setTitle("����");
    	item1.setContent("������һ");
    	item1.setBackTitle("������ ����");
    	
    	Item item2 = new Item();
    	item2.setBackPicId(R.drawable.maolilan1);
    	item2.setPicId(R.drawable.pic1);
    	item2.setTitle("ë����");
    	item2.setContent("������һ");
    	item2.setBackTitle("С��");
    	
    	Item item3 = new Item();
    	item3.setBackPicId(R.drawable.maolilan2);
    	item3.setPicId(R.drawable.pic1);
    	item3.setTitle("ë����");
    	item3.setContent("������һ");
    	item3.setBackTitle("С��");
    	mData.add(item1);
    	mData.add(item2);
    	mData.add(item3);
    }
}//end class
