package be.ccfun.day19;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class SyntaxError extends BaseErrorListener {

	private boolean error;

	public SyntaxError() {
		super();
		error = false;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
	                        Object offendingSymbol,
	                        int line, int charPositionInLine,
	                        String msg,
	                        RecognitionException e) {
		this.error = true;
	}

	public boolean isError() {
		return this.error;
	}
}
