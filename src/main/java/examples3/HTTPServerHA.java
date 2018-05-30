import io.vertx.core.AbstractVerticle;

public class HTTPServerHA extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(request -> {
			request.response().end("<body><h1></h1></body>");
		});
	}
}