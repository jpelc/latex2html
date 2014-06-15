package pl.edu.agh.student.conv.extras;

import javax.swing.JTextArea;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class CustomErrorListener extends BaseErrorListener {

	private JTextArea log;

	public CustomErrorListener(JTextArea log) {
		this.log = log;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer,
			Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		log.append("line " + line + ":" +  charPositionInLine + " " + msg + "\n" );
	}

}
