package com.roslab.app.tomatogtd.model;

import android.app.Service;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

import com.roslab.app.tomatogtd.R;

public class MediaModel {

	private MediaPlayer ticTac;
	private MediaPlayer chime;

	private Service service;

	public MediaModel(Service service) {
		this.service = service;
	}

	/***
	 * 播放计时声音
	 */
	public void playTicTac() {
		stopPlayTicTac();
		ticTac = MediaPlayer.create(service, R.raw.tictac);
		ticTac.setLooping(true);
		ticTac.start();
	}

	/***
	 * 停止播放计时声音
	 */
	public void stopPlayTicTac() {
		if (ticTac != null) {
			ticTac.stop();
			ticTac.release();
			ticTac = null;
		}
	}

	/***
	 * 播放计时完成声音
	 */
	public void playChime() {
		chime = MediaPlayer.create(service, R.raw.chime);
		chime.setLooping(false);
		chime.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});
		chime.start();
	}
}
