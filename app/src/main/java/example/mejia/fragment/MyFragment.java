package example.mejia.fragment;import android.os.Bundle;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import main.ljppff.com.meijia1.R;public class MyFragment extends Fragment{	@Override	public void onCreate(Bundle savedInstanceState) {		super.onCreate(savedInstanceState);					}	@Override	public View onCreateView(final LayoutInflater inflater, ViewGroup container,			Bundle savedInstanceState) {		View view			=  inflater.inflate(R.layout.fmore, container, false);						return view;	}	}