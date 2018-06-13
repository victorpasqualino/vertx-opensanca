package br.com.vertx;

import br.com.vertx.examples4.UserRestVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.ext.dropwizard.DropwizardMetricsOptions;

public class Main {

	public static void main(String[] args) {
		VertxOptions vertxOptions = new VertxOptions();
		vertxOptions.setEventLoopPoolSize(1)
					.setWorkerPoolSize(10)
					.setBlockedThreadCheckInterval(10000)
					.setMetricsOptions(new DropwizardMetricsOptions().setEnabled(true))
		;
		Vertx vertx = Vertx.vertx(vertxOptions);

		vertx.deployVerticle(UserRestVerticle.class.getName());
	}
}