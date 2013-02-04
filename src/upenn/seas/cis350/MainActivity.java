package upenn.seas.cis350;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	//protected int color;
	//protected int stroke;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void onClearButtonClick (View view) {
    	PaintBrushView pView = (PaintBrushView)findViewById(R.id.paintbrush);
    	pView.onClear();
    }
  /*  
    public void setColorVar(int c) {
    	color = c;
    	
    	PaintBrushView pView = (PaintBrushView)findViewById(R.id.paintbrush);
    	pView.setColor(color);
    
    }
    
    public void setStrokeVar(int w) {
    	stroke = w;
    	
    	PaintBrushView pView = (PaintBrushView)findViewById(R.id.paintbrush);
    	pView.setWidth(stroke);
        
    }
    
    public void changeColorWidth (View view) {
    	Toast.makeText(getApplicationContext(), "HI",Toast.LENGTH_LONG).show();
    	PaletteView palView = (PaletteView)findViewById(R.id.palette);
    	color = palView.getColor();	
    	stroke = palView.getStrokeWidth();
    	PaintBrushView pView = (PaintBrushView)findViewById(R.id.paintbrush);
    	pView.setColor(color);
    	pView.setWidth(stroke);
    	
    }
    */
}
