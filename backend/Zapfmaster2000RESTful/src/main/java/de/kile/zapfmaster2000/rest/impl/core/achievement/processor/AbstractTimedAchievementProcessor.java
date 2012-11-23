package de.kile.zapfmaster2000.rest.impl.core.achievement.processor;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;

public abstract class AbstractTimedAchievementProcessor extends
		AbstractAchievementProcessor {

	private static final Logger LOG = Logger
			.getLogger(AbstractTimedAchievementProcessor.class);

	public AbstractTimedAchievementProcessor() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, getHour());
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		if (calendar.getTimeInMillis() < System.currentTimeMillis()) {
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		Timer timer = new Timer();
		timer.schedule(createTimerTask(), calendar.getTime(),
				24 * 60 * 60 * 1000);
	}

	@Override
	public final void process(Drawing pDrawing) {
	}

	protected abstract int getHour();

	protected abstract void doProcess();

	private TimerTask createTimerTask() {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					doProcess();		
				} catch (Throwable th) {
					LOG.error("Error while processing timed achievement", th);
				}
			}
		};
	}
}
