package br.com.vertx.examples5;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.bridge.PermittedOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.sockjs.BridgeOptions;
import io.vertx.ext.web.handler.sockjs.SockJSHandler;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class SockerJSBridge extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		super.start();
		Router router = Router.router(vertx);

		// Allow outbound traffic to the news-feed address
		BridgeOptions options = new BridgeOptions().addOutboundPermitted(new PermittedOptions());

		router.route("/eventbus/*").handler(SockJSHandler.create(vertx).bridge(options));

		// Serve the static resources
		router.route().handler(StaticHandler.create().setWebRoot("webroot"));

		vertx.createHttpServer().requestHandler(router::accept).listen(8083);

	}

	public static void main(String[] args) {
		ClusterManager mgr = new HazelcastClusterManager();
		VertxOptions vertxOptions = new VertxOptions().setEventLoopPoolSize(1).setWorkerPoolSize(10).setClusterManager(mgr);
		Vertx.clusteredVertx(vertxOptions, r-> {
			Vertx vertx = r.result();
			vertx.deployVerticle(SockerJSBridge.class.getName());
		});

		// VertxOptions vertxOptions = new VertxOptions().setEventLoopPoolSize(1).setWorkerPoolSize(10);
		// Vertx vertx = Vertx.vertx(vertxOptions);
		// vertx.deployVerticle(SockerJSBridge.class.getName());
	}

}