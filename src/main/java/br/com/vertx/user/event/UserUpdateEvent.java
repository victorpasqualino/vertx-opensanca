package br.com.vertx.user.event;

import br.com.vertx.user.User;

public class UserUpdateEvent {

	private User last;
	private User now;

	public UserUpdateEvent(User last, User now) {
		this.last = last;
		this.now = now;
	}

	/**
	 * @return the last
	 */
	public User getLast() {
		return last;
	}

	/**
	 * @return the now
	 */
	public User getNow() {
		return now;
	}
}