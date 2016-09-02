package dk.fitfit.remotetexting.api.resource;

import org.springframework.hateoas.ResourceSupport;


public class MessageResource extends ResourceSupport {
	private PhoneNumberResource to;
	private PhoneNumberResource from;
	private String content;
	private long timestampProvider;
	private long timestampReceived;

	protected MessageResource() {
	}

	protected MessageResource(final PhoneNumberResource to, final PhoneNumberResource from, final String content, final long timestampProvider, final long timestampReceived) {
		this.to = to;
		this.from = from;
		this.content = content;
		this.timestampProvider = timestampProvider;
		this.timestampReceived = timestampReceived;
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

	public long getTimestampReceived() {
		return timestampReceived;
	}

	public void setTimestampReceived(final long timestampReceived) {
		this.timestampReceived = timestampReceived;
	}
}
