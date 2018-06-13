package br.com.vertx.user;

import java.util.Collection;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public interface UserRepository {

    void listAll(Handler<AsyncResult<Collection<User>>> result);

    void findById(String id, Handler<AsyncResult<User>> result);

    void insert(User user, Handler<AsyncResult<Void>> result);

    void update(User user, Handler<AsyncResult<Void>> result);
}