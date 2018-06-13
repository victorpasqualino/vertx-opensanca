package br.com.vertx.examples4;

import br.com.vertx.user.User;
import br.com.vertx.user.UserRepository;
import br.com.vertx.user.UserService;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

public class UserController {

	private UserRepository userRepository;
	private UserService userService;

	public UserController(Vertx vertx, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.userService = new UserService(vertx.eventBus(), userRepository);
	}

	public void listUsers(RoutingContext routingContext) {
		routingContext.response().putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json");
		userRepository.listAll(r -> {
			if (r.succeeded()) {
				routingContext.response().end(Json.encode(r.result()));
			} else {
				routingContext.response().setStatusCode(500).end(r.cause().getMessage());
			}
		});
	}

	public void findUserById(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
		userRepository.findById(id, r -> {
			if (r.succeeded()) {
				if (r.result() != null) {
					routingContext.response().putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json");
					routingContext.response().setStatusCode(200).end(Json.encode(r.result()));
				} else {
					routingContext.response().setStatusCode(404);
				}
			} else {
				routingContext.response().setStatusCode(500).end(r.cause().getMessage());
			}
		});
	}

	public void insertUser(RoutingContext routingContext) {
		InsertUserDTO insertUser = routingContext.getBodyAsJson().mapTo(InsertUserDTO.class);
		User newUser = insertUser.toUser();
		userService.insert(newUser, r -> {
			if (r.succeeded()) {
				routingContext.response().putHeader(HttpHeaderNames.CONTENT_TYPE, "application/json");
				routingContext.response().setStatusCode(201).end(Json.encode(newUser));
			} else {
				routingContext.response().setStatusCode(500).end(r.cause().getMessage());
			}
		});
	}

	public void updateUser(RoutingContext routingContext) {
		String id = routingContext.request().getParam("id");
	}

}