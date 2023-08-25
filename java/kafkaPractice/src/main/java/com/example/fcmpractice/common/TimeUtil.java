package com.example.fcmpractice.common;

import java.time.Clock;
import java.time.LocalDateTime;

public class TimeUtil {
	private static Clock clock = Clock.systemDefaultZone();

	private TimeUtil() {
	}

	public static void useFixedClockAt(LocalDateTime dateTime) {
		clock = Clock.fixed(dateTime.atZone(clock.getZone()).toInstant(), clock.getZone());
	}

	public static void useSystemDefaultClock() {
		clock = Clock.systemDefaultZone();
	}

	public static LocalDateTime now() {
		return LocalDateTime.now(clock);
	}

	public static LocalDateTime startOfThisYear() {
		return LocalDateTime.of(now().getYear(), 1, 1, 0, 0, 0);
	}
}
