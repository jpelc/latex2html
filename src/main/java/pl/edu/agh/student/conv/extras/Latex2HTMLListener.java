package pl.edu.agh.student.conv.extras;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import pl.edu.agh.student.conv.LatexBaseListener;
import pl.edu.agh.student.conv.LatexParser.AnythingContext;
import pl.edu.agh.student.conv.LatexParser.ArgContext;
import pl.edu.agh.student.conv.LatexParser.AuthorinfoContext;
import pl.edu.agh.student.conv.LatexParser.BegindocContext;
import pl.edu.agh.student.conv.LatexParser.BeginenvContext;
import pl.edu.agh.student.conv.LatexParser.BodyContext;
import pl.edu.agh.student.conv.LatexParser.CommandContext;
import pl.edu.agh.student.conv.LatexParser.ContentContext;
import pl.edu.agh.student.conv.LatexParser.DateinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocclassContext;
import pl.edu.agh.student.conv.LatexParser.DocinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocumentContext;
import pl.edu.agh.student.conv.LatexParser.EmphContext;
import pl.edu.agh.student.conv.LatexParser.EnddocContext;
import pl.edu.agh.student.conv.LatexParser.EndenvContext;
import pl.edu.agh.student.conv.LatexParser.EnvcontentContext;
import pl.edu.agh.student.conv.LatexParser.EnvironmentContext;
import pl.edu.agh.student.conv.LatexParser.ExprContext;
import pl.edu.agh.student.conv.LatexParser.InserttitleContext;
import pl.edu.agh.student.conv.LatexParser.ItemContext;
import pl.edu.agh.student.conv.LatexParser.OptContext;
import pl.edu.agh.student.conv.LatexParser.PreambleContext;
import pl.edu.agh.student.conv.LatexParser.QuoteContext;
import pl.edu.agh.student.conv.LatexParser.SectionContext;
import pl.edu.agh.student.conv.LatexParser.SpecialcharContext;
import pl.edu.agh.student.conv.LatexParser.StringContext;
import pl.edu.agh.student.conv.LatexParser.SubsectionContext;
import pl.edu.agh.student.conv.LatexParser.SubsubsectionContext;
import pl.edu.agh.student.conv.LatexParser.TextContext;
import pl.edu.agh.student.conv.LatexParser.TitleinfoContext;
import pl.edu.agh.student.conv.LatexParser.UnderlineContext;
import pl.edu.agh.student.conv.LatexParser.UsepkgContext;

public class Latex2HTMLListener extends LatexBaseListener {


	public Latex2HTMLListener() {
	}

	@Override
	public void enterPreamble(PreambleContext ctx) {
		// TODO Auto-generated method stub
		super.enterPreamble(ctx);
	}

	@Override
	public void exitPreamble(PreambleContext ctx) {
		// TODO Auto-generated method stub
		super.exitPreamble(ctx);
	}

	@Override
	public void enterInserttitle(InserttitleContext ctx) {
		// TODO Auto-generated method stub
		super.enterInserttitle(ctx);
	}

	@Override
	public void exitInserttitle(InserttitleContext ctx) {
		// TODO Auto-generated method stub
		super.exitInserttitle(ctx);
	}

	@Override
	public void enterSubsection(SubsectionContext ctx) {
		// TODO Auto-generated method stub
		super.enterSubsection(ctx);
	}

	@Override
	public void exitSubsection(SubsectionContext ctx) {
		// TODO Auto-generated method stub
		super.exitSubsection(ctx);
	}

	@Override
	public void enterString(StringContext ctx) {
		// TODO Auto-generated method stub
		super.enterString(ctx);
	}

	@Override
	public void exitString(StringContext ctx) {
		// TODO Auto-generated method stub
		super.exitString(ctx);
	}

	@Override
	public void enterUnderline(UnderlineContext ctx) {
		// TODO Auto-generated method stub
		super.enterUnderline(ctx);
	}

	@Override
	public void exitUnderline(UnderlineContext ctx) {
		// TODO Auto-generated method stub
		super.exitUnderline(ctx);
	}

	@Override
	public void enterDocument(DocumentContext ctx) {
		// TODO Auto-generated method stub
		super.enterDocument(ctx);
	}

	@Override
	public void exitDocument(DocumentContext ctx) {
		// TODO Auto-generated method stub
		super.exitDocument(ctx);
	}

	@Override
	public void enterSpecialchar(SpecialcharContext ctx) {
		// TODO Auto-generated method stub
		super.enterSpecialchar(ctx);
	}

	@Override
	public void exitSpecialchar(SpecialcharContext ctx) {
		// TODO Auto-generated method stub
		super.exitSpecialchar(ctx);
	}

	@Override
	public void enterSection(SectionContext ctx) {
		// TODO Auto-generated method stub
		super.enterSection(ctx);
	}

	@Override
	public void exitSection(SectionContext ctx) {
		// TODO Auto-generated method stub
		super.exitSection(ctx);
	}

	@Override
	public void enterBody(BodyContext ctx) {
		// TODO Auto-generated method stub
		super.enterBody(ctx);
	}

	@Override
	public void exitBody(BodyContext ctx) {
		// TODO Auto-generated method stub
		super.exitBody(ctx);
	}

	@Override
	public void enterUsepkg(UsepkgContext ctx) {
		// TODO Auto-generated method stub
		super.enterUsepkg(ctx);
	}

	@Override
	public void exitUsepkg(UsepkgContext ctx) {
		// TODO Auto-generated method stub
		super.exitUsepkg(ctx);
	}

	@Override
	public void enterContent(ContentContext ctx) {
		// TODO Auto-generated method stub
		super.enterContent(ctx);
	}

	@Override
	public void exitContent(ContentContext ctx) {
		// TODO Auto-generated method stub
		super.exitContent(ctx);
	}

	@Override
	public void enterAnything(AnythingContext ctx) {
		// TODO Auto-generated method stub
		super.enterAnything(ctx);
	}

	@Override
	public void exitAnything(AnythingContext ctx) {
		// TODO Auto-generated method stub
		super.exitAnything(ctx);
	}

	@Override
	public void enterEmph(EmphContext ctx) {
		// TODO Auto-generated method stub
		super.enterEmph(ctx);
	}

	@Override
	public void exitEmph(EmphContext ctx) {
		// TODO Auto-generated method stub
		super.exitEmph(ctx);
	}

	@Override
	public void enterEnvcontent(EnvcontentContext ctx) {
		// TODO Auto-generated method stub
		super.enterEnvcontent(ctx);
	}

	@Override
	public void exitEnvcontent(EnvcontentContext ctx) {
		// TODO Auto-generated method stub
		super.exitEnvcontent(ctx);
	}

	@Override
	public void enterQuote(QuoteContext ctx) {
		// TODO Auto-generated method stub
		super.enterQuote(ctx);
	}

	@Override
	public void exitQuote(QuoteContext ctx) {
		// TODO Auto-generated method stub
		super.exitQuote(ctx);
	}

	@Override
	public void enterArg(ArgContext ctx) {
		// TODO Auto-generated method stub
		super.enterArg(ctx);
	}

	@Override
	public void exitArg(ArgContext ctx) {
		// TODO Auto-generated method stub
		super.exitArg(ctx);
	}

	@Override
	public void enterExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		super.enterExpr(ctx);
	}

	@Override
	public void exitExpr(ExprContext ctx) {
		// TODO Auto-generated method stub
		super.exitExpr(ctx);
	}

	@Override
	public void enterDocinfo(DocinfoContext ctx) {
		// TODO Auto-generated method stub
		super.enterDocinfo(ctx);
	}

	@Override
	public void exitDocinfo(DocinfoContext ctx) {
		// TODO Auto-generated method stub
		super.exitDocinfo(ctx);
	}

	@Override
	public void enterText(TextContext ctx) {
		// TODO Auto-generated method stub
		super.enterText(ctx);
	}

	@Override
	public void exitText(TextContext ctx) {
		// TODO Auto-generated method stub
		super.exitText(ctx);
	}

	@Override
	public void enterDocclass(DocclassContext ctx) {
		// TODO Auto-generated method stub
		super.enterDocclass(ctx);
	}

	@Override
	public void exitDocclass(DocclassContext ctx) {
		// TODO Auto-generated method stub
		super.exitDocclass(ctx);
	}

	@Override
	public void enterBeginenv(BeginenvContext ctx) {
		// TODO Auto-generated method stub
		super.enterBeginenv(ctx);
	}

	@Override
	public void exitBeginenv(BeginenvContext ctx) {
		// TODO Auto-generated method stub
		super.exitBeginenv(ctx);
	}

	@Override
	public void enterItem(ItemContext ctx) {
		// TODO Auto-generated method stub
		super.enterItem(ctx);
	}

	@Override
	public void exitItem(ItemContext ctx) {
		// TODO Auto-generated method stub
		super.exitItem(ctx);
	}

	@Override
	public void enterEndenv(EndenvContext ctx) {
		// TODO Auto-generated method stub
		super.enterEndenv(ctx);
	}

	@Override
	public void exitEndenv(EndenvContext ctx) {
		// TODO Auto-generated method stub
		super.exitEndenv(ctx);
	}

	@Override
	public void enterSubsubsection(SubsubsectionContext ctx) {
		// TODO Auto-generated method stub
		super.enterSubsubsection(ctx);
	}

	@Override
	public void exitSubsubsection(SubsubsectionContext ctx) {
		// TODO Auto-generated method stub
		super.exitSubsubsection(ctx);
	}

	@Override
	public void enterBegindoc(BegindocContext ctx) {
		// TODO Auto-generated method stub
		super.enterBegindoc(ctx);
	}

	@Override
	public void exitBegindoc(BegindocContext ctx) {
		// TODO Auto-generated method stub
		super.exitBegindoc(ctx);
	}

	@Override
	public void enterEnddoc(EnddocContext ctx) {
		// TODO Auto-generated method stub
		super.enterEnddoc(ctx);
	}

	@Override
	public void exitEnddoc(EnddocContext ctx) {
		// TODO Auto-generated method stub
		super.exitEnddoc(ctx);
	}

	@Override
	public void enterCommand(CommandContext ctx) {
		// TODO Auto-generated method stub
		super.enterCommand(ctx);
	}

	@Override
	public void exitCommand(CommandContext ctx) {
		// TODO Auto-generated method stub
		super.exitCommand(ctx);
	}

	@Override
	public void enterOpt(OptContext ctx) {
		// TODO Auto-generated method stub
		super.enterOpt(ctx);
	}

	@Override
	public void exitOpt(OptContext ctx) {
		// TODO Auto-generated method stub
		super.exitOpt(ctx);
	}

	@Override
	public void enterEnvironment(EnvironmentContext ctx) {
		// TODO Auto-generated method stub
		super.enterEnvironment(ctx);
	}

	@Override
	public void exitEnvironment(EnvironmentContext ctx) {
		// TODO Auto-generated method stub
		super.exitEnvironment(ctx);
	}

	@Override
	public void enterAuthorinfo(AuthorinfoContext ctx) {
		
	}

	@Override
	public void exitAuthorinfo(AuthorinfoContext ctx) {
		// TODO Auto-generated method stub
		super.exitAuthorinfo(ctx);
	}

	@Override
	public void enterDateinfo(DateinfoContext ctx) {
		// TODO Auto-generated method stub
		super.enterDateinfo(ctx);
	}

	@Override
	public void exitDateinfo(DateinfoContext ctx) {
		// TODO Auto-generated method stub
		super.exitDateinfo(ctx);
	}

	@Override
	public void enterTitleinfo(TitleinfoContext ctx) {
		// TODO Auto-generated method stub
		super.enterTitleinfo(ctx);
	}

	@Override
	public void exitTitleinfo(TitleinfoContext ctx) {
		// TODO Auto-generated method stub
		super.exitTitleinfo(ctx);
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		super.enterEveryRule(ctx);
		System.out.println(ctx.getText());
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.exitEveryRule(ctx);
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		super.visitTerminal(node);
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		super.visitErrorNode(node);
	}

}
