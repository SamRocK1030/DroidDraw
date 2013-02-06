package upenn.seas.cis350;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

public class PaintBrushView extends View {
	int x,y;
	//PaletteView pView;
	//Paint p;
	//Bitmap bmp;
	
	List<Dot> dots = new ArrayList<Dot>();
	List<Dpath> paths = new ArrayList<Dpath>();
	Dpath current;

	public PaintBrushView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		//p = new Paint();
		//p.setColor(Color.BLUE);
		//p.setStyle(Style.STROKE);
		//Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
		//bmp = Bitmap.createBitmap(this.getWidth(), this.getHeight(), conf);
	}
	
	 public PaintBrushView(Context c, AttributeSet a) {
	       super(c, a);
	       //p = new Paint();
	       //p.setColor(Color.BLUE);
	      // p.setStrokeWidth(5);
	      // p.setStyle(Style.STROKE);
	       
	      // Bitmap.Config conf = Bitmap.Config.ARGB_8888; // see other conf types
		//	bmp = Bitmap.createBitmap(this.getWidth(), this.getHeight(), conf);
	   }
	
	public void onClear() {
		paths.clear();
		invalidate();
	}
	 
	 protected void onDraw(Canvas canvas) {
		 
		 
		 //canvas.drawBitmap(bmp, 0, 0, p);
		 
		// canvas.setBitmap(bmp);
		 for (int i = 0; i < paths.size(); i ++) {
			 //p.setColor(paths.get(i).Color);
			 //p.setColor(paths.get(i).Width);
			 canvas.drawPath(current, current.pathPaint);
			 canvas.drawPath(paths.get(i),paths.get(i).pathPaint);
		 }
   }
	/*  no longer needed 
	 public void setColor(int c) {
		 p.setColor(c);
	 }
	 
	 
	 public void setWidth(int w) {
		 p.setStrokeWidth(w);
	 }
	*/ 
	 public boolean onTouchEvent (MotionEvent e) {
		 x = (int)e.getX();
		  y = (int)e.getY();
			
		 
		 if (e.getAction() == MotionEvent.ACTION_DOWN) {
		 //current.moveTo(x, y);
			 current = new Dpath(x, y, PaletteView.color, PaletteView.strokeWidth);
			 //current.lineTo(x, y); //added to handle the issue of not drawing single dots -- doesnt work
			 //paths.add(current);
			// dots.add(new Dot(x,y,2,5));

			 invalidate();
			 return true;
		 } else  if (e.getAction() == MotionEvent.ACTION_MOVE) {
			 //dots.add(new Dot(x,y,2,5));
			 current.lineTo(x, y);
			 //current.close();
			 //paths.add(current);
			 //current = new Path(); 
			 //current.moveTo(x,y);
			 
			 invalidate();
			 return true;
		 } else  if (e.getAction() == MotionEvent.ACTION_UP) {
			 
			 paths.add(current);
			// current.reset(); //added to separate each path on finger lift
			 invalidate();
			 return true;
		 }
		 return false;
	 }

 class Dot {
	 int X,Y,Color,Width;
	 
	 Dot(int x, int y, int color, int width) {
		 X = x;
		 Y = y;
		 Color = color;
		 Width = width;
	 }
		 
	 }
 
 //wrapper class for paths
 class Dpath extends Path{
	 int X,Y;
	 Paint pathPaint;
	 
	 Dpath(int x, int y, int color, int width) {
		 super();
		 this.moveTo(x,y);
		 pathPaint = new Paint();
		 pathPaint.setColor(color);
		 pathPaint.setStrokeWidth(width);
		 pathPaint.setStyle(Style.STROKE);
	 }
		 
	 }

}
