package upenn.seas.cis350;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PaletteView extends View {

	protected ShapeDrawable square_1,
							square_2,
							square_3,
							square_4,
							square_5,
							color_select,
							stroke_select;
	
	static int color;
	static int strokeWidth;
	protected Paint p;
	protected String time;
	protected int timer_secs;
	//protected PaintBrushView pBView;

   public PaletteView(Context c) {
       super(c);
       init(); 
       
       
   }
   
   public PaletteView(Context c, AttributeSet a) {
       super(c, a);
       init();
       
  
   }
   
   protected void init() {
	   square_1 = new ShapeDrawable(new RectShape());
	   square_1.setBounds(0, 0, 25, 25);
	   square_1.getPaint().setColor(Color.RED);
	   	
	   square_2 = new ShapeDrawable(new RectShape());
	   square_2.setBounds(25, 0, 50, 25);
	   square_2.getPaint().setColor(Color.YELLOW);
	   
	   square_3 = new ShapeDrawable(new RectShape());
	   square_3.setBounds(50, 0, 75, 25);
	   square_3.getPaint().setColor(Color.GREEN);
	   	
	   square_4 = new ShapeDrawable(new RectShape());
	   square_4.setBounds(75, 0, 100, 25);
	   square_4.getPaint().setColor(Color.BLUE);
	   	
	   square_5 = new ShapeDrawable(new RectShape());
	   square_5.setBounds(100, 0, 125, 25);
	   square_5.getPaint().setColor(Color.BLACK);
	   
	   color_select = new ShapeDrawable(new RectShape());
	   color_select.setBounds(0, 0, 25, 25);
	   color_select.getPaint().setStyle(Style.STROKE);
	   color_select.getPaint().setStrokeWidth(5);
	   color_select.getPaint().setColor(Color.GRAY);
	   
	   stroke_select = new ShapeDrawable(new RectShape());
	   stroke_select.setBounds(150, 20, 190, 50);
	   stroke_select.getPaint().setStyle(Style.STROKE);
	   stroke_select.getPaint().setStrokeWidth(5);
	   stroke_select.getPaint().setColor(Color.GRAY);
	   
	   p = new Paint();
	   time = "0:00";
	   timer_secs = 0;
	   color = square_1.getPaint().getColor();
	   strokeWidth = 2;
	   
	   new TimerTask().execute(timer_secs);


   }
   
   protected void onDraw(Canvas canvas) {
	     square_1.draw(canvas);
	     square_2.draw(canvas);
	     square_3.draw(canvas);
	     square_4.draw(canvas);
	     square_5.draw(canvas);
	     
	     color_select.draw(canvas);
	     stroke_select.draw(canvas);
	     
	       // set its color
	     p.setColor(Color.BLACK);
	       // set the alignment
	     p.setTextAlign(Paint.Align.LEFT);
	       // set the typeface (font)
	     p.setTypeface(Typeface.SANS_SERIF);
	       // set the size
	     p.setTextSize(20);
	    
	     canvas.drawText("thin", 150, 40, p);
	     canvas.drawText("THICK", 200, 40, p);
	     canvas.drawText(time, 270, 40, p);

	    // pBView.setColor(color);
	    // pBView.setWidth(strokeWidth);
   }
   
public boolean onTouchEvent(MotionEvent e) {
	
	if (e.getAction() == MotionEvent.ACTION_DOWN) {
		int x = (int)e.getX();
		if (x <= 25) {
			color = square_1.getPaint().getColor();
			color_select.setBounds(square_1.copyBounds());
			invalidate();
			return true;
		}
		else if (x > 25 && x <= 50) {
			color = square_2.getPaint().getColor();
			color_select.setBounds(square_2.copyBounds());
			invalidate();
			return true;
		}
		else if (x > 50 && x <= 75) {
			color = square_3.getPaint().getColor();
			color_select.setBounds(square_3.copyBounds());
			invalidate();
			return true;
		}
		else if (x > 75 && x <= 100) {
			color = square_4.getPaint().getColor();
			color_select.setBounds(square_4.copyBounds());
			invalidate();
			return true;
		}
		else if (x > 100 && x <= 125) {
			color = square_5.getPaint().getColor();
			color_select.setBounds(square_5.copyBounds());
			invalidate();
			return true;
		}
		else if (x > 150 && x <= 190) {
			strokeWidth = 2;
			stroke_select.setBounds(150, 20, 190, 50);
			invalidate();
			return true;
		}
		
		else if (x > 200 && x <= 240) {
			strokeWidth = 6;
			stroke_select.setBounds(200, 20, 260, 50);
			invalidate();
			return true;
		}
		
	}
	return false;
	
}

public int getColor() {
	return this.color;
}

public int getStrokeWidth() {
	return this.strokeWidth;
}

public void updateTimer(Integer t) {
	
	int mins, secs;
	timer_secs = t;
	
	mins = timer_secs/60;
	secs = timer_secs - (60*mins);
	if (secs < 10) {
		time = (mins + ":0" + secs);
	}
	else {
		time = (mins + ":" + secs); 
	}
	invalidate();
	
	new TimerTask().execute(timer_secs);
}

class TimerTask extends AsyncTask<Integer, Void, Integer> {
	
    protected Integer doInBackground(Integer... inputs) {
	     int reply = inputs[0] + 1;
	     SystemClock.sleep(1000);
       return reply; 
    }

    protected void onPostExecute(Integer result) {
  	     // update Views in the UI 
        PaletteView pView = (PaletteView)findViewById(R.id.palette);
        pView.updateTimer(result);
    }


}
   

}
