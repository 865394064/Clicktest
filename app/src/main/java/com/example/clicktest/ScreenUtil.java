package com.example.clicktest;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Build;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import java.lang.reflect.Method;

/**
 * 类名称：ScreenUtil 创建人：hjz 创建时间：2020-12-14
 */
public class ScreenUtil {
	public static int sp2px(Context context, int sp) {

		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) (displayMetrics.scaledDensity*sp+0.5);
	}

	public static int dp2px(Context context, int sp) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) (displayMetrics.density*sp+0.5);
	}

	public static int px2sp(Context context, int px) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) (px/displayMetrics.scaledDensity+0.5);
	}

	public static int px2dip(Context context, int px) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return (int) (px/displayMetrics.density+0.5);
	}



	public static int getDpi(Context context){
		int dpi = 0;
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		DisplayMetrics displayMetrics = new DisplayMetrics();
		@SuppressWarnings("rawtypes")
        Class c;
		try {
			c = Class.forName("android.view.Display");
			@SuppressWarnings("unchecked")
            Method method = c.getMethod("getRealMetrics", DisplayMetrics.class);
			method.invoke(display, displayMetrics);
			dpi=displayMetrics.heightPixels;
		}catch(Exception e){
			e.printStackTrace();
		}
		return dpi;
	}

	public static int getScreenHeight(Context context) {
		DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
		return displayMetrics.heightPixels;
	}

	/**
	 * 获取屏幕宽度(px)
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}


	/**
	 * 获取屏幕的statusBar
	 *
	 * @param activity
	 * @return int statusbar高度
	 */
	public static int getStatusBarHeight(Activity activity) {
		int statusHeight = 0;
		Rect localRect = new Rect();
		activity.getWindow().getDecorView()
				.getWindowVisibleDisplayFrame(localRect);
		statusHeight = localRect.top;
		if (0 == statusHeight) {
			Class<?> localClass;
			try {
				localClass = Class.forName("com.android.internal.R$dimen");
				Object localObject = localClass.newInstance();
				int i5 = Integer.parseInt(localClass
						.getField("status_bar_height").get(localObject)
						.toString());
				statusHeight = activity.getResources()
						.getDimensionPixelSize(i5);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
		}
		return statusHeight;
	}

	/**
	 * 获取应用程序的可视高度
	 *
	 * @param activity
	 * @return int 可视高度
	 */
	public static int getRealScreenHeight(Activity activity) {
		View content = activity.getWindow().findViewById(
				Window.ID_ANDROID_CONTENT);
		return content.getHeight();
	}

	/**
	 * 获取设备显示指标
	 *
	 * @param activity
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Activity activity) {
		return activity.getResources().getDisplayMetrics();
	}

	/**
	 * 获取设备显示指标
	 *
	 * @param context
	 *            上下文
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		if (context != null) {
			return context.getResources().getDisplayMetrics();
		}
		return null;
	}

	/**
	 * 设置全屏
	 */
	public static void setFullScreen(Context context) {
		if (!(context instanceof Activity))
			return;
		WindowManager manager = ((Activity) context).getWindowManager();
		Display display = manager.getDefaultDisplay();
		WindowManager.LayoutParams lp = ((Activity) context).getWindow()
				.getAttributes();
		lp.width = display.getWidth();
		((Activity) context).getWindow().setAttributes(lp);
	}

}
