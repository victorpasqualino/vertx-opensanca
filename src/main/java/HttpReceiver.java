
import io.vertx.core.AbstractVerticle;

public class HttpReceiver extends AbstractVerticle {

	private static int port = 8080;
	private static final String BODY = "<body><h1>Hello Java from Vert.X</h1></body>";

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(request -> {
            request.response().end(BODY);
        }).listen(port++, r -> {
            if (r.succeeded()) {
                System.out.println("Listening on port: " + r.result().actualPort());
            } else {
                System.out.println("Error: " + r.cause());
            }
        });
    }

}