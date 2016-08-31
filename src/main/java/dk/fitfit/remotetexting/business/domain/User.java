package dk.fitfit.remotetexting.business.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "users")  // Postgresql already has a table called user, thus we need to call it something else
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String userId;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "user")
	@JsonManagedReference
	private Set<PhoneNumber> phoneNumbers = new HashSet<>();
	private String fcmRegId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(final String userId) {
		this.userId = userId;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public String getFcmRegId() {
		return fcmRegId;
	}

	public void setFcmRegId(final String fcmRegId) {
		this.fcmRegId = fcmRegId;
	}
}
