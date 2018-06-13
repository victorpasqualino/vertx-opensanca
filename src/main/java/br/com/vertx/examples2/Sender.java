package br.com.vertx.examples2;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import io.vertx.core.AbstractVerticle;

public class Sender extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.setPeriodic(2000, s -> {
			vertx.eventBus().publish("news-feed", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));
		});
	}
}