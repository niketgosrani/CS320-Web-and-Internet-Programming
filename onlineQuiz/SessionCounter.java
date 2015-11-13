package onlineQuiz;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionCounter implements HttpSessionListener{
	
	private static int activeSession = 0;

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		activeSession++;
				
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		if(activeSession > 0)
			activeSession--;
	}

	public static int getActiveSession() {
		return activeSession;
	}
	
	
	
}
