package com.example.clicktest;

import android.app.Activity;
import android.content.Intent;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.os.Debug;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	TextView tvStart;
	TextView tvStop;
	private EditText act_key_board_et;
	private KeyboardView keyboardView;
	private View ky_keyboard_parent;
	private CarKeyBoardUtil carKeyBoardUtil;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tvStart = (TextView) findViewById(R.id.tv_start);
		tvStop = (TextView) findViewById(R.id.tv_stop);
		tvStart.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.d("hjzstart","onStartCommand star click");
//				Intent i = new Intent(MainActivity.this, AutoService.class);
				startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS));

//				Intent i = new Intent(MainActivity.this, AutoClickService.class);
//				startService(i);
			}
		});

		tvStop.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, AutoService.class);
				stopService(i);
				tvStart.setText("开启服务");
			}
		});

//		act_key_board_et = findViewById(R.id.act_key_board_et);
//		keyboardView = findViewById(R.id.ky_keyboard);
//		ky_keyboard_parent = findViewById(R.id.ky_keyboard_parent);
//		carKeyBoardUtil = new CarKeyBoardUtil(ky_keyboard_parent,keyboardView,act_key_board_et);
//		act_key_board_et.setOnTouchListener(new View.OnTouchListener() {
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				if (carKeyBoardUtil == null) {
//					carKeyBoardUtil = new CarKeyBoardUtil(ky_keyboard_parent,keyboardView,act_key_board_et);
//				}
//				carKeyBoardUtil.showKeyboard();
//				return false;
//			}
//		});
//		try{
//			RSAEncrypt.test();
//		}catch (Exception e){
//			Log.d("hjztest","e="+e.getMessage());
//		}
		String str="192.168.0.1";
		String[] firstArray=str.split("\\.");
		String[] secondArray=str.split("\\.",2);
		System.out.println("str的原值为:["+str+"]");
		System.out.print("全部分割的结果:");
		for(String a:firstArray){
			System.out.print("["+a+"]");
		}
		System.out.println();
		System.out.print("分割两次的结果:");
		for(String a:secondArray){
			System.out.print("["+a+"]");
		}
	}


	/**
	 * 打印点击的点的坐标
	 * 
	 * @param event
	 * @return
	 */
	@Override
	public boolean onTouchEvent(MotionEvent event) {

		int x = (int) event.getX();
		int y = (int) event.getY();
		tvStart.setText("X at " + x + ";Y at " + y);
		return true;
	}
//	public void initThread(){
//		try{
//			Thread.sleep(5000);
//		}catch (Exception e){
//
//		}
//	}
}
