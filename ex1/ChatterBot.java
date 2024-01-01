import java.util.*;


/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 * 
 * @author Dan Nirel
 */
class ChatterBot {
	static final String REQUEST_PREFIX = "say ";
	static final String REQUESTED_PHRASE_PLACEHOLDER = "<phrase>";
	static final String ILLEGAL_REQUEST_PLACEHOLDER = "<request>";

	private final Random rand = new Random();
	private final String[] repliesToIllegalRequest;
	private final String[] repliesToLegalRequest;
	private final String name;

	ChatterBot(String name, String[] repliesToLegalRequest, String[] repliesToIllegalRequest) {
		this.name = name;
		this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
		this.repliesToLegalRequest = new String[repliesToLegalRequest.length];
		System.arraycopy(repliesToIllegalRequest, 0, this.repliesToIllegalRequest, 0, repliesToIllegalRequest.length);
		System.arraycopy(repliesToLegalRequest, 0, this.repliesToLegalRequest, 0, repliesToLegalRequest.length);
	}

	/*
	 * String replyTo(String statement) {
	 * if(statement.startsWith(REQUEST_PREFIX)) {
	 * //we don’t repeat the request prefix, so delete it from the reply
	 * return statement.replaceFirst(REQUEST_PREFIX, "");
	 * }
	 * return respondToIllegalRequest(statement);
	 * }
	 */
	String replyTo(String statement) {
		if (statement.startsWith(REQUEST_PREFIX)) {
			// return respondToLegalRequest(statement);
			return replacePlaceholderInARandomPattern(statement.replaceFirst(REQUEST_PREFIX, ""),
					repliesToLegalRequest, REQUESTED_PHRASE_PLACEHOLDER);
		}
		return replacePlaceholderInARandomPattern(statement,
				repliesToIllegalRequest, ILLEGAL_REQUEST_PLACEHOLDER);
	}

	String replacePlaceholderInARandomPattern(String statement, String[] replies, String placeholder) {
		int randomIndex = rand.nextInt(replies.length);
		// String reply = repliesToIllegalRequest[randomIndex];
		String responsePattern = replies[randomIndex];
		String reply = responsePattern.replaceAll(placeholder, statement);
		return reply;
	}

	String getName() {
		return this.name;
	}
}
