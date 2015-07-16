package example.mejia.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import example.mejia.util.Context;
import main.ljppff.com.meijia1.R;

public class ChooseCity extends Activity{
	private ListView mLvArea;
    private List<String> area1List =new ArrayList<String>();
	private AreaAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		setContentView(R.layout.localcity);
		mLvArea =(ListView)this.findViewById(R.id.mLvArea);
		area1List.clear();
		area1List.add(getResources().getString(R.string.b1));
        area1List.add(getResources().getString(R.string.b2));
        area1List.add(getResources().getString(R.string.b3));
        area1List.add(getResources().getString(R.string.b4));
        area1List.add(getResources().getString(R.string.b5));
		mLvArea.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
			/*	Intent sendIntent=new Intent(ChooseCity.this, MJMain.class);
				Bundle b =new Bundle();
				b.putString("CITY", area1List.get(position));
				setResult(1, sendIntent);
				finish();*/
			Context.city =area1List.get(position);
			finish();
			}
		});
		adapter = new AreaAdapter();
		mLvArea.setAdapter(adapter);

	}
	
	class Holder {
		TextView area2name;
		
	}

	
	private class AreaAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return area1List.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return area1List.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getApplicationContext())
						.inflate(R.layout.item_area, null);
				holder = new Holder();
				holder.area2name = (TextView) convertView
						.findViewById(R.id.area2name);
			//	holder.cb = (CheckBox) convertView.findViewById(R.id.area2cb2);
				convertView.setTag(holder);

			} else {
				holder = (Holder) convertView.getTag();
			}
			holder.area2name.setText(area1List.get(position));
			return convertView;
		}

	}

	
	
}
