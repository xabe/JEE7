package com.xabe.tutorial.service.timer;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xabe.tutorial.service.user.UserEJB;

@Singleton
@Startup
public class TimedTaskManager {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TimedTaskManager.class);

	@Resource
	private SessionContext ctx;

	@EJB
	private UserEJB userEJB;

	@Schedule(hour = "*", minute = "*", second = "*/5", info = "Every 5 second timer")
	public void runTask1() {
		LOGGER.debug("---------------------------------------------------------------------------->Tarea ejecutada");
		LOGGER.debug(new Date().toString());
		Collection<Timer> timers = ctx.getTimerService().getAllTimers();
		for (Timer t : timers) {
			LOGGER.debug("Timer info: " + t.getInfo());
		}
	}
}
