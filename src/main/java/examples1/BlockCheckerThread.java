import io.vertx.core.AbstractVerticle;

public class BlockCheckerThread extends AbstractVerticle {

	@Override
	public void start() throws Exception {
		vertx.createHttpServer().requestHandler(request -> {
			sleep(5000);
			request.response().end("<body><h1>Success Ended</h1></body>");
		}).listen(8080);

		//Create non blocking code
	}

	public void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (Exception e) {
			//do nothing (Do not do this)
		}
	}
}