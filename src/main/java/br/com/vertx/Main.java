package br.com.vertx;

import br.com.vertx.examples4.UserRestVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class Main {

	public static void main(String[] args) {
		ClusterManager mgr = new HazelcastClusterManager();
		VertxOptions vertxOptions = new VertxOptions();
		vertxOptions.setEventLoopPoolSize(1)
					.setWorkerPoolSize(10)
					.setClusterManager(mgr)
					// .setBlockedThreadCheckInterval(10000)
					// .setMetricsOptions(new DropwizardMetricsOptions().setEnabled(true))
		;
		Vertx.clusteredVertx(vertxOptions, r -> {
			Vertx vertx = r.result();

			vertx.deployVerticle(UserRestVerticle.class.getName());
		});
	}
}