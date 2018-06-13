package br.com.vertx.handlers;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.RoutingContext;

public class MetricsHandler implements Handler<RoutingContext> {

	private MetricsService metricsService;
	private Vertx vertx;

	private MetricsHandler(MetricsService metricsService, Vertx vertx) {
		this.vertx = vertx;
		this.metricsService = metricsService;
	}

	public static MetricsHandler create(MetricsService metricsService, Vertx vertx) {
		return new MetricsHandler(metricsService, vertx);
	}

	@Override
	public void handle(RoutingContext routingContext) {
		JsonObject metrics = metricsService.getMetricsSnapshot(vertx);
		routingContext.response().putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json");
		routingContext.response().end(metrics.toBuffer());
	}

}