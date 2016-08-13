package dk.fitfit.remotetexting.api.resource;

public class MessageResourceBuilder {
	private PhoneNumberResource from;
	private String content;
	private long timestampProvider;
	private long timestampReceived;

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
		this.timestampReceived = timestampReceived;
		return this;
	}

	public MessageResource build() {
		return new MessageResource(from, content, timestampProvider, timestampReceived);
	}
}