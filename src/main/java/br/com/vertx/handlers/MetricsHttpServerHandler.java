package br.com.vertx.handlers;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.RoutingContext;

public class MetricsHttpServerHandler implements Handler<RoutingContext> {

	private MetricsService metricsService;
	private HttpServer httpServer;

	private MetricsHttpServerHandler(MetricsService metricsService, HttpServer httpServer) {
		this.metricsService = metricsService;
		this.httpServer = httpServer;
	}

	public static MetricsHttpServerHandler create(MetricsService metricsService, HttpServer httpServer) {
		return new MetricsHttpServerHandler(metricsService, httpServer);
	}

	@Override
	public void handle(RoutingContext routingContext) {
		JsonObject metrics = metricsService.getMetricsSnapshot(httpServer);
		routingContext.response().putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json");
		routingContext.response().end(metrics.toBuffer());
	}

}