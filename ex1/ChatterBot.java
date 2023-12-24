import java.util.*;

/**
 * Base file for the ChatterBot exercise.
 * The bot's replyTo method receives a statement.
 * If it starts with the constant REQUEST_PREFIX, the bot returns
 * whatever is after this prefix. Otherwise, it returns one of
 * a few possible replies as supplied to it via its constructor.
 * In this case, it may also include the statement after
 * the selected reply (coin toss).
 * @author Dan Nirel
 */
class ChatterBot {
	static final String REQUEST_PREFIX = "say ";
	static final String REQUESTED_PHRASE_PLACEHOLDER = "<phrase>";
	static final String ILLEGAL_REQUEST_PLACEHOLDER = "<request>";
	Random rand = new Random();
	String[] repliesToIllegalRequest;
	String[] repliesToLegalRequest;
	String name;

	ChatterBot(String name, String[] repliesToLlegalRequest, String[] repliesToIllegalRequest) {
		this.name = name;
		this.repliesToIllegalRequest = new String[repliesToIllegalRequest.length];
		for(int i = 0 ; i < repliesToIllegalRequest.length ; i = i+1) {
			this.repliesToIllegalRequest[i] = repliesToIllegalRequest[i];
		}
		this.repliesToLegalRequest = new String[repliesToLlegalRequest.length];
		for(int i = 0 ; i < repliesToLlegalRequest.length ; i = i+1) {
			this.repliesToLegalRequest[i] = repliesToLlegalRequest[i];
		}
		
	}
	/*
	String replyTo(String statement) {
		if(statement.startsWith(REQUEST_PREFIX)) {
			//we don’t repeat the request prefix, so delete it from the reply
			return statement.replaceFirst(REQUEST_PREFIX, "");
		}
		return respondToIllegalRequest(statement);
	}
	 */
	String replyTo(String statement) {
		if(statement.startsWith(REQUEST_PREFIX)) {
			return respondToLegalRequest(statement);
		}
		return respondToIllegalRequest(statement);
	}

	String respondToLegalRequest(String statement) {
		String phrase = statement.replaceFirst(REQUEST_PREFIX, "");
		int randomIndex = rand.nextInt(repliesToLegalRequest.length);
		String responsePattern = repliesToLegalRequest[randomIndex];
		String reply = responsePattern.replaceAll(REQUESTED_PHRASE_PLACEHOLDER, phrase);
		return reply;
	}
	/*
	String respondToIllegalRequest(String statement) {
		int randomIndex = rand.nextInt(repliesToIllegalRequest.length);
		String reply = repliesToIllegalRequest[randomIndex];
		if(rand.nextBoolean()) {
			reply = reply + statement;
		}
		return reply;
	}
	*/
	String respondToIllegalRequest(String statement) {
		int randomIndex = rand.nextInt(repliesToIllegalRequest.length);
		//String reply = repliesToIllegalRequest[randomIndex];
		String responsePattern = repliesToIllegalRequest[randomIndex];
		String reply = responsePattern.replaceAll(ILLEGAL_REQUEST_PLACEHOLDER, statement);
		return reply;
	}
	// they want to make a method: "replacePlaceholderInARandomPattern" 
	// and i dont know why

	String getName() {
		return name;
	}
}
