package example.meijia;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import example.mejia.ui.MJMain;
import main.ljppff.com.meijia1.R;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		startActivity(new Intent(getApplicationContext(), MJMain.class));
		
	}
}
