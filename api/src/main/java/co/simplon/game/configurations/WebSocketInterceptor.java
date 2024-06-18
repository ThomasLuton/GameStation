package co.simplon.game.configurations;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.ChannelInterceptor;

import co.simplon.game.utils.AuthHelper;

public class WebSocketInterceptor
	implements ChannelInterceptor {

    private final AuthHelper authHelper;

    public WebSocketInterceptor(AuthHelper authHelper) {
	this.authHelper = authHelper;
    }

    @Override
    public Message<?> preSend(Message<?> message,
	    MessageChannel channel) {
	Object nativeHeaders = message.getHeaders()
		.get("nativeHeaders");
	if ((nativeHeaders != null) && nativeHeaders
		.toString().startsWith("{Authorization")) {
	    String token = nativeHeaders.toString()
		    .substring(23, 253);
	    try {
		authHelper.verifyToken(token);
	    } catch (Exception e) {

	    }
	}
	return message;
    }
}
