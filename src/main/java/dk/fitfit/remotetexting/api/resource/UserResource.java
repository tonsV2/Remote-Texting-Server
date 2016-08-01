package dk.fitfit.remotetexting.api.resource;


public class UserResource {
	private Long id;
	private String email;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}
}
