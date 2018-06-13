package br.com.vertx.examples1;

import io.vertx.core.AbstractVerticle;

public class HelloWorld extends AbstractVerticle {
	
	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(request -> {
			request.response().end("<body><h1>Hello from JavaScript Vert.X</h1></body>");
		}).listen(8081);
	}
}