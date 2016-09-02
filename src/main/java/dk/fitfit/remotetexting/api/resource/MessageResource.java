package dk.fitfit.remotetexting.api.resource;

import org.springframework.hateoas.ResourceSupport;


public class MessageResource extends ResourceSupport {
	private PhoneNumberResource to;
	private PhoneNumberResource from;
	private String content;
	private long timestampProvider;
	private long timestampDelivered;
	private long timestampSent;

	public MessageResource() {
	}

	protected MessageResource(final PhoneNumberResource to, final PhoneNumberResource from, final String content, final long timestampProvider, final long timestampDelivered, final long timestampSent) {
		this.to = to;
		this.from = from;
		this.content = content;
		this.timestampProvider = timestampProvider;
		this.timestampDelivered = timestampDelivered;
		this.timestampSent = timestampSent;
	}

	public PhoneNumberResource getTo() {
		return to;
	}

	public void setTo(final PhoneNumberResource to) {
		this.to = to;
	}

	public PhoneNumberResource getFrom() {
		return from;
	}

	public void setFrom(final PhoneNumberResource from) {
		this.from = from;
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
