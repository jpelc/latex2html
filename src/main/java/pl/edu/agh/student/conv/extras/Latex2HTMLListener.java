package pl.edu.agh.student.conv.extras;

import org.antlr.v4.runtime.ParserRuleContext;

import pl.edu.agh.student.conv.LatexBaseListener;

public class Latex2HTMLListener extends LatexBaseListener {

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		System.out.println(ctx.getText());
		super.enterEveryRule(ctx);
	}

}
