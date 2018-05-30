import io.vertx.core.AbstractVerticle;

public class Receiver extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.eventBus().consumer("news-feed").handler(message -> {
			System.out.println("Received news: " + message.body());
		});
	}
}