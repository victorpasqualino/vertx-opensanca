package br.com.vertx.user;

import br.com.vertx.user.event.UserInsertEvent;
import br.com.vertx.user.event.UserUpdateEvent;
import br.com.vertx.user.impl.MemoryUserRepository;;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.json.Json;

public class UserService {

	public static final String USER_INSERT_EVENT = "user-inserted";
	public static final String USER_UPDATE_EVENT = "user-updated";

	private UserRepository userRepository;
	private EventBus eventBus;

	public UserService(EventBus eventBus, UserRepository userRepository) {
		this.userRepository = userRepository;
		this.eventBus = eventBus;
	}

	public void insert(User user , Handler<AsyncResult<Void>> handler) {
		userRepository.insert(user, r -> {
			if (r.succeeded()) {
				eventBus.publish(USER_INSERT_EVENT, Json.encode(new UserInsertEvent(user)));
				handler.handle(Future.succeededFuture());
			} else {
				handler.handle(Future.failedFuture(r.cause()));
			}
		});
	}

	public void update(User user , Handler<AsyncResult<Void>> handler) {
		userRepository.findById(user.getId(), f -> {
			if (f.succeeded()) {
				userRepository.insert(user, r -> {
					if (r.succeeded()) {
						eventBus.publish(USER_INSERT_EVENT, Json.encode(new UserUpdateEvent(f.result(), user)));
						handler.handle(Future.succeededFuture());
					} else {
						handler.handle(Future.failedFuture(r.cause()));
					}
				});
			} else {
				handler.handle(Future.failedFuture(f.cause()));
			}
		});
	}
	
}