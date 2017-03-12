package com.sports.filip.widget.clip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;

import java.io.IOException;

/**
 * http://blog.csdn.net/lmj623565791/article/details/39761281
 * @author zhy
 *
 */
public class ClipImageLayout extends RelativeLayout
{

	private ClipZoomImageView mZoomImageView;
	private ClipImageBorderView mClipImageView;

//	private ImageLoader imageLoader;

	/**
	 * 这里测试，直接写死了大小，真正使用过程中，可以提取为自定义属性
	 */
	private int mHorizontalPadding = 20;

	public ClipImageLayout(Context context, AttributeSet attrs)
	{
		super(context, attrs);

		mZoomImageView = new ClipZoomImageView(context);
		mClipImageView = new ClipImageBorderView(context);

		android.view.ViewGroup.LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);

//		imageLoader = ImageLoader.getInstance();

		/**
		 * 这里测试，直接写死了图片，真正使用过程中，可以提取为自定义属性
		 */
//		mZoomImageView.setImageDrawable(getResources().getDrawable(
//				R.drawable.a));

		this.addView(mZoomImageView, lp);
		this.addView(mClipImageView, lp);
		
		
		// 计算padding的px
		mHorizontalPadding = (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP, mHorizontalPadding, getResources()
						.getDisplayMetrics());
		mZoomImageView.setHorizontalPadding(mHorizontalPadding);
		mClipImageView.setHorizontalPadding(mHorizontalPadding);
	}

	public void setImage(String filePath){
			int degree = 0;
			try {
				ExifInterface exifInterface = new ExifInterface(filePath);
				int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
				switch (orientation) {
					case ExifInterface.ORIENTATION_ROTATE_90:
						degree = 90;
						break;
					case ExifInterface.ORIENTATION_ROTATE_180:
						degree = 180;
						break;
					case ExifInterface.ORIENTATION_ROTATE_270:
						degree = 270;
						break;
				}
			} catch (IOException e) {
				e.printStackTrace();
//				return degree;
			}
//			return degree;
//		}


//		Bitmap bmp = BitmapFactory.decodeFile(filePath);

//		Bitmap bmp = imageLoader.loadImageSync("file://" + filePath);
		
//		Glide.with(getContext()).load(filePath).asBitmap().toBytes().

//		Glide.with(getContext()).
		
		Bitmap bmp = getBitmapFromFile(filePath);
		
		if (degree == 0 || null == bmp) {
				mZoomImageView.setImageBitmap(bmp);
		}else{

			Matrix matrix = new Matrix();
			matrix.setRotate(degree, bmp.getWidth() / 2, bmp.getHeight() / 2);
			Bitmap bitmap = Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(),
					bmp.getHeight(), matrix, true);
			if (null != bmp) {
				bmp.recycle();
			}

			mZoomImageView.setImageBitmap(bitmap);
		}



			//imageLoader.displayImage("file://"+filePath,mZoomImageView);
	}


	/**
	 * 读取路径中的图片，然后将其转化为缩放后的bitmap 
	 *
	 * @param path
	 */
	public Bitmap getBitmapFromFile(String path) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
// 获取这个图片的宽和高  
		Bitmap bitmap = BitmapFactory.decodeFile(path, options); // 此时返回bm为空  
		options.inJustDecodeBounds = false;
// 计算缩放比  
		int be = (int)(options.outHeight / (float)200);
		if (be <= 0)
			be = 1;
		options.inSampleSize = 2; // 图片长宽各缩小二分之一  
// 重新读入图片，注意这次要把options.inJustDecodeBounds 设为 false哦  
		bitmap = BitmapFactory.decodeFile(path, options);
//		int w = bitmap.getWidth();
//		int h = bitmap.getHeight();
//		System.out.println(w + " " + h);
		return bitmap;
	}



	/**
	 * 对外公布设置边距的方法,单位为dp
	 * 
	 * @param mHorizontalPadding
	 */
	public void setHorizontalPadding(int mHorizontalPadding)
	{
		this.mHorizontalPadding = mHorizontalPadding;
	}

	/**
	 * 裁切图片
	 * 
	 * @return
	 */
	public Bitmap clip()
	{
		return mZoomImageView.clip();
	}

}
