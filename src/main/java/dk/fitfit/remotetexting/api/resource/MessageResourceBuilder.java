package dk.fitfit.remotetexting.api.resource;

import org.apache.commons.lang3.builder.Builder;


public class MessageResourceBuilder implements Builder<MessageResource> {
	private PhoneNumberResource to;
	private PhoneNumberResource from;
	private String content;
	private long timestampProvider;
	private long timestampDelivered;
	private long timestampSent;

	public MessageResourceBuilder withTo(final PhoneNumberResource to) {
		this.to = to;
		return this;
	}

	public MessageResourceBuilder withFrom(final PhoneNumberResource from) {
		this.from = from;
		return this;
	}

	public MessageResourceBuilder withContent(final String content) {
		this.content = content;
		return this;
	}

	public MessageResourceBuilder withTimestampProvider(final long timestampProvider) {
		this.timestampProvider = timestampProvider;
		return this;
	}

	public MessageResourceBuilder withTimestampReceived(final long timestampReceived) {
		this.timestampDelivered = timestampReceived;
		return this;
	}

	public MessageResourceBuilder withTimestampSent(final long timestampSent) {
		this.timestampSent = timestampSent;
		return this;
	}

	@Override
	public MessageResource build() {
		return new MessageResource(to, from, content, timestampProvider, timestampDelivered, timestampSent);
	}
}