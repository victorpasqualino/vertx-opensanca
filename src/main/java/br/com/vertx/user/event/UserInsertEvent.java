package br.com.vertx.user.event;

import java.util.Objects;

import br.com.vertx.user.User;

public class UserInsertEvent {

	private User user;

	public UserInsertEvent(User user) {
		Objects.requireNonNull(user);
		this.user = user;
	}
	
	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
}