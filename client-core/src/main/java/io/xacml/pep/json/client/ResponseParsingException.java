package io.xacml.pep.json.client;

/**
 * Exception class for Response parsing exceptions.
 * Should be preferred to throwing checked exceptions
 *
 * @author Julio Cesar Villalta III <jvillalta@nvisia.com>
 */
public class ResponseParsingException extends RuntimeException {

    public ResponseParsingException(String message) {
        super(message);
    }

    public ResponseParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseParsingException(Throwable cause) {
        super(cause);
    }
}
