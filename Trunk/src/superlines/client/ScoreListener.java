package superlines.client;

import java.util.List;

import superlines.ws.ScoreData;

public interface ScoreListener {
	public void updateData(final List<ScoreData> data);
}
