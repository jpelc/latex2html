package pl.edu.agh.student.conv.extras;

import org.antlr.v4.runtime.ParserRuleContext;

import pl.edu.agh.student.conv.LatexBaseListener;
import pl.edu.agh.student.conv.LatexParser.ArgContext;
import pl.edu.agh.student.conv.LatexParser.AuthorinfoContext;
import pl.edu.agh.student.conv.LatexParser.BegindocContext;
import pl.edu.agh.student.conv.LatexParser.BodyContext;
import pl.edu.agh.student.conv.LatexParser.ClassoptsContext;
import pl.edu.agh.student.conv.LatexParser.ClasstypeContext;
import pl.edu.agh.student.conv.LatexParser.CommandContext;
import pl.edu.agh.student.conv.LatexParser.ContentContext;
import pl.edu.agh.student.conv.LatexParser.DateinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocclassContext;
import pl.edu.agh.student.conv.LatexParser.DocinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocumentContext;
import pl.edu.agh.student.conv.LatexParser.EnddocContext;
import pl.edu.agh.student.conv.LatexParser.EnvironmentContext;
import pl.edu.agh.student.conv.LatexParser.ExprContext;
import pl.edu.agh.student.conv.LatexParser.InserttitleContext;
import pl.edu.agh.student.conv.LatexParser.ItemContext;
import pl.edu.agh.student.conv.LatexParser.OptContext;
import pl.edu.agh.student.conv.LatexParser.PreambleContext;
import pl.edu.agh.student.conv.LatexParser.QuoteContext;
import pl.edu.agh.student.conv.LatexParser.SectionContext;
import pl.edu.agh.student.conv.LatexParser.SpecialcharContext;
import pl.edu.agh.student.conv.LatexParser.SubsectionContext;
import pl.edu.agh.student.conv.LatexParser.SubsubsectionContext;
import pl.edu.agh.student.conv.LatexParser.TextContext;
import pl.edu.agh.student.conv.LatexParser.TitleinfoContext;
import pl.edu.agh.student.conv.LatexParser.UsepkgContext;

public class Latex2HTMLListener extends LatexBaseListener {

	@Override
	public void enterPreamble(PreambleContext ctx) {
		System.out.println(ctx.getText());
		super.enterPreamble(ctx);
	}

	@Override
	public void enterInserttitle(InserttitleContext ctx) {
		System.out.println(ctx.getText());
		super.enterInserttitle(ctx);
	}

	@Override
	public void enterSubsection(SubsectionContext ctx) {
		System.out.println(ctx.getText());
		super.enterSubsection(ctx);
	}

	@Override
	public void enterDocument(DocumentContext ctx) {
		System.out.println(ctx.getText());
		super.enterDocument(ctx);
	}

	@Override
	public void enterClasstype(ClasstypeContext ctx) {
		System.out.println(ctx.getText());
		super.enterClasstype(ctx);
	}

	@Override
	public void enterSpecialchar(SpecialcharContext ctx) {
		System.out.println(ctx.getText());
		super.enterSpecialchar(ctx);
	}

	@Override
	public void enterSection(SectionContext ctx) {
		System.out.println(ctx.getText());
		super.enterSection(ctx);
	}

	@Override
	public void enterBody(BodyContext ctx) {
		System.out.println(ctx.getText());
		super.enterBody(ctx);
	}

	@Override
	public void enterUsepkg(UsepkgContext ctx) {
		System.out.println(ctx.getText());
		super.enterUsepkg(ctx);
	}

	@Override
	public void enterContent(ContentContext ctx) {
		System.out.println(ctx.getText());
		super.enterContent(ctx);
	}

	@Override
	public void enterQuote(QuoteContext ctx) {
		System.out.println(ctx.getText());
		super.enterQuote(ctx);
	}

	@Override
	public void enterArg(ArgContext ctx) {
		System.out.println(ctx.getText());
		super.enterArg(ctx);
	}

	@Override
	public void enterExpr(ExprContext ctx) {
		System.out.println(ctx.getText());
		super.enterExpr(ctx);
	}

	@Override
	public void enterDocinfo(DocinfoContext ctx) {
		System.out.println(ctx.getText());
		super.enterDocinfo(ctx);
	}

	@Override
	public void enterText(TextContext ctx) {
		System.out.println(ctx.getText());
		super.enterText(ctx);
	}

	@Override
	public void enterDocclass(DocclassContext ctx) {
		System.out.println(ctx.getText());
		super.enterDocclass(ctx);
	}

	@Override
	public void enterClassopts(ClassoptsContext ctx) {
		System.out.println(ctx.getText());
		super.enterClassopts(ctx);
	}

	@Override
	public void enterItem(ItemContext ctx) {
		System.out.println(ctx.getText());
		super.enterItem(ctx);
	}

	@Override
	public void enterSubsubsection(SubsubsectionContext ctx) {
		System.out.println(ctx.getText());
		super.enterSubsubsection(ctx);
	}

	@Override
	public void enterBegindoc(BegindocContext ctx) {
		System.out.println(ctx.getText());
		super.enterBegindoc(ctx);
	}

	@Override
	public void enterEnddoc(EnddocContext ctx) {
		System.out.println(ctx.getText());
		super.enterEnddoc(ctx);
	}

	@Override
	public void enterCommand(CommandContext ctx) {
		System.out.println(ctx.getText());
		super.enterCommand(ctx);
	}

	@Override
	public void enterOpt(OptContext ctx) {
		System.out.println(ctx.getText());
		super.enterOpt(ctx);
	}

	@Override
	public void enterEnvironment(EnvironmentContext ctx) {
		System.out.println(ctx.getText());
		super.enterEnvironment(ctx);
	}

	@Override
	public void enterAuthorinfo(AuthorinfoContext ctx) {
		System.out.println(ctx.getText());
		super.enterAuthorinfo(ctx);
	}

	@Override
	public void enterDateinfo(DateinfoContext ctx) {
		System.out.println(ctx.getText());
		super.enterDateinfo(ctx);
	}

	@Override
	public void enterTitleinfo(TitleinfoContext ctx) {
		System.out.println(ctx.getText());
		super.enterTitleinfo(ctx);
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		super.enterEveryRule(ctx);
	}

}
