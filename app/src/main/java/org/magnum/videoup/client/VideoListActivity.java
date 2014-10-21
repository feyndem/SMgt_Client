package org.magnum.videoup.client;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class VideoListActivity extends Activity {

	@InjectView(R.id.videoList)
	protected ListView videoList_;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video_list);

		ButterKnife.inject(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		refreshVideos();
	}

	private void refreshVideos() {
		final VideoSvcApi svc = VideoSvc.getOrShowLogin(this);

		if (svc != null) {
			CallableTask.invoke(new Callable<Collection<Patient>>() {

				@Override
				public Collection<Patient> call() throws Exception {
					return svc.getPatients();
				}
			}, new TaskCallback<Collection<Patient>>() {

				@Override
				public void success(Collection<Patient> result) {
					List<String> names = new ArrayList<String>();
					for (Patient v : result) {
						names.add(v.getPatient());
					}
					videoList_.setAdapter(new ArrayAdapter<String>(
							VideoListActivity.this,
							android.R.layout.simple_list_item_1, names));
				}

				@Override
				public void error(Exception e) {
					Toast.makeText(
							VideoListActivity.this,
							"Unable to fetch the video list, please login again.",
							Toast.LENGTH_SHORT).show();

					startActivity(new Intent(VideoListActivity.this,
							LoginScreenActivity.class));
				}
			});
		}
	}

}
