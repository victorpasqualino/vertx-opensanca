package br.com.vertx.user.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import br.com.vertx.user.User;
import br.com.vertx.user.UserRepository;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class MemoryUserRepository implements UserRepository {

    private Map<String, User> users;

    public MemoryUserRepository() {
        users = new HashMap<>();
        users.put("59aca527-92de-4b11-8f81-e0c0b2233286", new User("59aca527-92de-4b11-8f81-e0c0b2233286", "FULANO DA SILVA", "13283941319", LocalDate.parse("2005-02-15", DateTimeFormatter.ISO_DATE)));
        users.put("1ad8b6e7-4f3e-4318-acfa-f4e5ea8d44e1", new User("1ad8b6e7-4f3e-4318-acfa-f4e5ea8d44e1", "BELTRANO DA SILVA SANTOS", "46584784207", LocalDate.parse("1999-07-11", DateTimeFormatter.ISO_DATE)));
        users.put("6c0149d7-41fc-4f9d-b3b6-10f4df28955d", new User("6c0149d7-41fc-4f9d-b3b6-10f4df28955d", "SICRANO GONCALVES", "15913940741", LocalDate.parse("1998-03-23", DateTimeFormatter.ISO_DATE)));
    }

	@Override
	public void listAll(Handler<AsyncResult<Collection<User>>> handler) {
        handler.handle(Future.succeededFuture(users.values()));
	}

	@Override
	public void findById(String id, Handler<AsyncResult<User>> handler) {
        handler.handle(Future.succeededFuture(users.get(id)));
	}

	@Override
	public void insert(User user, Handler<AsyncResult<Void>> handler) {
        Future<Void> result = null;
        if (users.containsKey(user.getId())) {
            result = Future.failedFuture(new IllegalStateException("User with id " + user.getId() + " already exists!"));
        } else {
            users.put(user.getId(), user);
            result = Future.succeededFuture();
        }
        handler.handle(result);
	}

	@Override
	public void update(User user, Handler<AsyncResult<Void>> handler) {
        Future<Void> result = null;
        if (users.containsKey(user.getId())) {
            users.remove(user.getId());
            users.put(user.getId(), user);
            result = Future.succeededFuture();
        } else {
            result = Future.failedFuture(new IllegalStateException("User with id " + user.getId() + " not exists!"));
        }
        handler.handle(result);
	}

}