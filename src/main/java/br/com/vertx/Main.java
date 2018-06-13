package br.com.vertx;

import br.com.vertx.examples4.UserRestVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class Main {

	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx(new VertxOptions().setEventLoopPoolSize(1).setWorkerPoolSize(10).setBlockedThreadCheckInterval(10000));

		vertx.deployVerticle(UserRestVerticle.class.getName());
	}
}