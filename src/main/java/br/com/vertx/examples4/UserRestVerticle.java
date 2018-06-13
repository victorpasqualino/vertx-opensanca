package br.com.vertx.examples4;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.vertx.user.MockUserRepository;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Context;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

public class UserRestVerticle extends AbstractVerticle {

    private static final Logger log = LoggerFactory.getLogger(UserRestVerticle.class);

    private UserController userController;

    @Override
    public void init(Vertx vertx, Context context) {
        super.init(vertx, context);
        configurarJson();
        this.userController = new UserController(new MockUserRepository());
    }

    @Override
    public void start() throws Exception {
        vertx.createHttpServer().requestHandler(createRouter()::accept).listen(8081, r-> {
            if (r.succeeded()) {
                log.info("Server started on port " + r.result().actualPort());
            } else {
                log.error(r.cause());
            }
        });
    }

    private Router createRouter() {
        Router router = Router.router(vertx);

        router.route(HttpMethod.GET, "/users").produces("application/json").handler(userController::listUsers);

        router.route(HttpMethod.GET, "/users/:id").produces("application/json").handler(userController::findUserById);

        router.route(HttpMethod.POST, "/users").consumes("application/json").produces("application/json").handler(userController::insertUser);

        router.route(HttpMethod.PUT, "/users/:id").consumes("application/json").produces("application/json").handler(userController::updateUser);

        return router;
    }

    private void configurarJson() {
		Json.mapper.registerModule(new JavaTimeModule());
        Json.mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
	}

}