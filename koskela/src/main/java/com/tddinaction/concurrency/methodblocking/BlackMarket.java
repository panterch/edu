package com.tddinaction.concurrency.methodblocking;

import java.util.concurrent.Semaphore;

public class BlackMarket {

	private Semaphore tickets = new Semaphore(0);

	public void buyTicket() throws InterruptedException {
		tickets.acquire();
	}

	public void sellTicket() {
		tickets.release();
	}
}
