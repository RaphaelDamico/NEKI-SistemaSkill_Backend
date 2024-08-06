package br.com.neki.sistema_skill.exceptions;

public class EntityNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String EntityName, String msg, Integer id) {
		super(msg + EntityName + " by id: " + id);
	}

	public EntityNotFoundException(String msg) {
		super(msg);
	}
}
