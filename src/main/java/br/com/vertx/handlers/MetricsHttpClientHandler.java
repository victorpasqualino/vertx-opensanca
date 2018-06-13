package br.com.vertx.handlers;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.Handler;
import io.vertx.core.http.HttpClient;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.RoutingContext;

public class MetricsHttpClientHandler implements Handler<RoutingContext> {

	private MetricsService metricsService;
	private HttpClient httpClient;

	private MetricsHttpClientHandler(MetricsService metricsService, HttpClient httpClient) {
		this.metricsService = metricsService;
		this.httpClient = httpClient;
	}

	public static MetricsHttpClientHandler create(MetricsService metricsService, HttpClient httpClient) {
		return new MetricsHttpClientHandler(metricsService, httpClient);
	}

	@Override
	public void handle(RoutingContext routingContext) {
		JsonObject metrics = metricsService.getMetricsSnapshot(httpClient);
		routingContext.response().putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json");
		routingContext.response().end(metrics.toBuffer());
	}

}