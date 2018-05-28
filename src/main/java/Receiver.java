
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServerOptions;

public class Receiver extends AbstractVerticle {

    @Override
    public void start() throws Exception {
        vertx.createHttpServer(new HttpServerOptions(context.config())).requestHandler(request -> {
            System.out.println(context.deploymentID());
            request.response().end("<body><h1>Hello Java from Vert.X</h1></body>");
        }).listen(r -> {
            if (r.succeeded()) {
                System.out.println("Listening on port: " + r.result().actualPort());
            } else {
                System.out.println("Error: " + r.cause());
            }
        });
    }

}