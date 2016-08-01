package dk.fitfit.remotetexting.business.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	private LocalDateTime dateTime;
	@ManyToOne
	@JsonBackReference
	private PhoneNumber phoneNumber;
	@ManyToOne
	@JsonBackReference
	private Contact contact;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(final LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(final PhoneNumber phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(final Contact contact) {
		this.contact = contact;
	}
}
