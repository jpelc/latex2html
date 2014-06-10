package pl.edu.agh.student.conv.extras;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;

import pl.edu.agh.student.conv.LatexBaseListener;
import pl.edu.agh.student.conv.LatexParser.AnythingContext;
import pl.edu.agh.student.conv.LatexParser.AuthorinfoContext;
import pl.edu.agh.student.conv.LatexParser.BeginenvContext;
import pl.edu.agh.student.conv.LatexParser.BodyContext;
import pl.edu.agh.student.conv.LatexParser.ClassoptContext;
import pl.edu.agh.student.conv.LatexParser.CommandContext;
import pl.edu.agh.student.conv.LatexParser.DateinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocclassContext;
import pl.edu.agh.student.conv.LatexParser.DocumentContext;
import pl.edu.agh.student.conv.LatexParser.InserttitleContext;
import pl.edu.agh.student.conv.LatexParser.QuoteContext;
import pl.edu.agh.student.conv.LatexParser.SectionContext;
import pl.edu.agh.student.conv.LatexParser.SpecialcharContext;
import pl.edu.agh.student.conv.LatexParser.StringContext;
import pl.edu.agh.student.conv.LatexParser.SubsectionContext;
import pl.edu.agh.student.conv.LatexParser.SubsubsectionContext;
import pl.edu.agh.student.conv.LatexParser.TitleinfoContext;
import pl.edu.agh.student.conv.LatexParser.UnderlineContext;

public class Latex2HTMLListener extends LatexBaseListener {

	private Document document;
	private String author;
	private String date;
	private int fontSize = 10;
	private boolean titleSet;
	private int section;
	private int subsection;
	private int subsubsection;
	private Element content;
	private Element current;

	public String getHtml() {
		return document.html();
	}

	@Override
	public void enterDocument(DocumentContext ctx) {
		document = Document
				.createShell("http://home.agh.edu.pl/~jpi/wiki/dydaktyka:jfik:2014:latex2html");
		document.outputSettings(new Document.OutputSettings().prettyPrint(true));
		document.prependChild(new DocumentType("html", "", "", ""));
		document.head().prepend("<meta />");
		document.head().select("meta").get(0)
				.attr("http-equiv", "Content-Type")
				.attr("content", "text/html").attr("charset", "UTF-8");
	}

	@Override
	public void exitDocclass(DocclassContext ctx) {
		document.head()
				.appendElement("style")
				.text("body {\n" + "font-family: Georgia, serif;\n"
						+ "font-size: " + fontSize + "px;\n" + "margin: 0;\n"
						+ "padding: 0;\n" + "}\n" + "#a4content {\n"
						+ "width: 21cm;\n" + "padding: 2cm;\n"
						+ "margin: 1cm auto;\n" + "}\n" + "#titleBlock {\n"
						+ "width: 21cm;\n" + "padding: 1cm;\n"
						+ "margin: 3cm auto 5cm auto;\n" + "}\n"

				);
	}

	@Override
	public void enterClassopt(ClassoptContext ctx) {
		String content = ctx.getText();
		int position = content.indexOf("pt");
		try {
			fontSize = Integer.parseInt(content.substring(position - 2,
					position));
		} catch (NumberFormatException e) {
			fontSize = Integer.parseInt(content.substring(position - 1,
					position));
		}
	}

	@Override
	public void enterAuthorinfo(AuthorinfoContext ctx) {
		author = ctx.getChild(1).getText();
		author = author.substring(1);
		author = author.substring(0, author.length() - 1).trim();
	}

	@Override
	public void enterDateinfo(DateinfoContext ctx) {
		date = ctx.getChild(1).getText();
		date = date.substring(1);
		date = date.substring(0, date.length() - 1).trim();
	}

	@Override
	public void enterTitleinfo(TitleinfoContext ctx) {
		String title = ctx.getChild(1).getText();
		title = title.substring(1);
		title = title.substring(0, title.length() - 1).trim();
		document.title(title);
		titleSet = true;
	}

	@Override
	public void enterBody(BodyContext ctx) {
		content = document.body().appendElement("div").attr("id", "a4content");
	}

	@Override
	public void enterInserttitle(InserttitleContext ctx) {
		if (titleSet) {
			Element titleBlock = document.body().prependElement("div")
					.attr("id", "titleBlock");
			titleBlock.appendElement("h1").attr("style", "text-align:center;").text(document.head().select("title").text());
			if (author != null)
				titleBlock.appendElement("h2").attr("style", "text-align:center;").text(author);
			if (date != null)
				titleBlock.appendElement("h3").attr("style", "text-align:center;").text(date);
		}
	}

	@Override
	public void enterAnything(AnythingContext ctx) {
		if (content.select("p").size() == 0)
			content.appendElement("p");
	}

	@Override
	public void enterString(StringContext ctx) {
		if (current != null)
			current.appendText(ctx.getText());
	}

	@Override
	public void enterSection(SectionContext ctx) {
		subsection = subsubsection = 0;
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h2").attr("class", "section").text(++section + ". " + sectionName);
		current = content.appendElement("p");
	}

	@Override
	public void enterSubsection(SubsectionContext ctx) {
		subsubsection = 0;
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h3").attr("class", "subsection").text(section + "." + ++subsection + ". " + sectionName);
		current = content.appendElement("p");
	}

	@Override
	public void enterSubsubsection(SubsubsectionContext ctx) {
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h4").attr("class", "subsubsection").text(section + "." + subsection + "." + ++subsubsection + ". " + sectionName);
		current = content.appendElement("p");
	}

	@Override
	public void enterUnderline(UnderlineContext ctx) {
		// TODO Auto-generated method stub
		super.enterUnderline(ctx);
	}

	@Override
	public void enterSpecialchar(SpecialcharContext ctx) {
		// TODO Auto-generated method stub
		super.enterSpecialchar(ctx);
	}

	@Override
	public void enterQuote(QuoteContext ctx) {
		// TODO Auto-generated method stub
		super.enterQuote(ctx);
	}

	@Override
	public void enterBeginenv(BeginenvContext ctx) {
		// TODO Auto-generated method stub
		super.enterBeginenv(ctx);
	}

	@Override
	public void enterCommand(CommandContext ctx) {
		// TODO Auto-generated method stub
		super.enterCommand(ctx);
	}

}
