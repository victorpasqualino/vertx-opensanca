package br.com.vertx.examples4;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.vertx.handlers.MetricsHandler;
import br.com.vertx.handlers.MetricsHttpClientHandler;
import br.com.vertx.handlers.MetricsHttpServerHandler;
import br.com.vertx.user.impl.MemoryUserRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.dropwizard.MetricsService;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

public class UserRestVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(UserRestVerticle.class);

	private UserController userController;
	private MetricsService metricsService;
	private HttpServer httpServer;
	private HttpClient httpClient;

    @Override
    public void init(Vertx vertx, Context context) {
		super.init(vertx, context);
        configurarJson();
		this.userController = new UserController(vertx, new MemoryUserRepository());
		this.metricsService = MetricsService.create(vertx);
		this.httpServer = vertx.createHttpServer();
		this.httpClient = vertx.createHttpClient();
    }

    @Override
    public void start() throws Exception {
        httpServer.requestHandler(createRouter()::accept).listen(8081, r-> {
            if (r.succeeded()) {
                log.info("Server started on port " + r.result().actualPort());
            } else {
                log.error(r.cause());
            }
        });
    }

    private Router createRouter() {
		Router router = Router.router(vertx);
		
		router.route().handler(BodyHandler.create());

        router.route(HttpMethod.GET, "/users").produces("application/json").handler(userController::listUsers);

        router.route(HttpMethod.GET, "/users/:id").produces("application/json").handler(userController::findUserById);

        router.route(HttpMethod.POST, "/users").consumes("application/json").produces("application/json").handler(userController::insertUser);

		router.route(HttpMethod.PUT, "/users/:id").consumes("application/json").produces("application/json").handler(userController::updateUser);
		
		router.route(HttpMethod.GET, "/metrics").produces("application/json").handler(MetricsHandler.create(metricsService, vertx));

		router.route(HttpMethod.GET, "/metrics/server").produces("application/json").handler(MetricsHttpServerHandler.create(metricsService, httpServer));

		router.route(HttpMethod.GET, "/metrics/client").produces("application/json").handler(MetricsHttpClientHandler.create(metricsService, httpClient));

        return router;
    }

    private void configurarJson() {
		Json.mapper.registerModule(new JavaTimeModule());
        Json.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

}