package com.tddinaction.concurrency.threadstartstop;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class Server {

	private Runnable serverDaemon = new Runnable() {
		public void run() {
			try {
				System.out.println("The server is now supposedly running...");
				Object obj = new Object();
				synchronized (obj) {
					obj.wait();
				}
			} catch (InterruptedException e) {
				System.out.println("The server thread was interrupted");
			}
		}
	};

	private ThreadFactory threadFactory = Executors.defaultThreadFactory();

	private ExecutorService executor;

	public void setThreadFactory(ThreadFactory threadFactory) {
		this.threadFactory = threadFactory;
	}

	public void start() {
		executor = Executors.newSingleThreadExecutor(threadFactory);
		executor.execute(serverDaemon);
	}

	public void stop() {
		executor.shutdownNow();
	}
}
