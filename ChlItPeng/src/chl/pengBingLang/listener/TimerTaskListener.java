package chl.pengBingLang.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import chl.pengBingLang.utils.DiskCheckTimerTask;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;//定时器类

/**
 * 随Tomcat启动的监听器，用于实现定时任务，不依赖于框架
 * 
 * @author PengBingLang 彭秉浪
 */
public class TimerTaskListener implements ServletContextListener {
	private Timer timer = null;

	// 初始化监听器，在tomcat启动的时,启动监听器，在这里实现定时器功能
	public void contextInitialized(ServletContextEvent event) {
		timer = new Timer(true); // true表示定时器设置为守护线程
		event.getServletContext().log("定时器已创建");// 添加日志，可在tomcat日志中查看到

		Calendar startTime = Calendar.getInstance();
		startTime.add(Calendar.DATE, 1);// 日期加一，明天
		startTime.set(Calendar.HOUR_OF_DAY, 1);
		startTime.set(Calendar.MINUTE, 0);
		startTime.set(Calendar.SECOND, 0);
		startTime.set(Calendar.MILLISECOND, 0);
		// timer.schedule(new DiskCheckTimerTask(event.getServletContext()),
		// startTime.getTime(), 24 * 60 * 60 * 1000);
		timer.schedule(new DiskCheckTimerTask(event.getServletContext()), new Date(), 5 * 60 * 1000);
		event.getServletContext().log("定时任务已启动");
	}

	// 关闭监听器，在这里销毁定时器。
	public void contextDestroyed(ServletContextEvent event) {
		timer.cancel();
		event.getServletContext().log("定时器销毁成功");
	}
}
