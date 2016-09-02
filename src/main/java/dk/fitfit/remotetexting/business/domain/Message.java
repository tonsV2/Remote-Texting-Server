package dk.fitfit.remotetexting.business.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


@Entity
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JsonBackReference
	private PhoneNumber from;
	@ManyToOne
	@JsonBackReference
	private PhoneNumber to;
	private String content;
	private long timestampProvider;
	private long timestampDelivered;
	private long timestampSent;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public PhoneNumber getFrom() {
		return from;
	}

	public void setFrom(final PhoneNumber from) {
		this.from = from;
	}

	public PhoneNumber getTo() {
		return to;
	}

	public void setTo(final PhoneNumber to) {
		this.to = to;
	}

	public String getContent() {
		return content;
	}

	public void setContent(final String content) {
		this.content = content;
	}

	public long getTimestampProvider() {
		return timestampProvider;
	}

	public void setTimestampProvider(final long timestampProvider) {
		this.timestampProvider = timestampProvider;
	}

	public long getTimestampDelivered() {
		return timestampDelivered;
	}

	public void setTimestampDelivered(final long timestampDelivered) {
		this.timestampDelivered = timestampDelivered;
	}

	public long getTimestampSent() {
		return timestampSent;
	}

	public void setTimestampSent(final long timestampSent) {
		this.timestampSent = timestampSent;
	}
}
