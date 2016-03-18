package org.gxz.mydemo.main.tabhost;

import org.gxz.mydemo.DemoApplication;
import org.gxz.mydemo.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Wall_Fragment extends Fragment {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		((DemoApplication) getActivity().getApplication())
				.addActivity(getActivity());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		((DemoApplication) getActivity().getApplication())
				.removeActivity(getActivity());
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.wall, container, false);
	}

}
