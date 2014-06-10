package pl.edu.agh.student.conv.extras;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import pl.edu.agh.student.conv.LatexParser.EmphContext;
import pl.edu.agh.student.conv.LatexParser.EndenvContext;
import pl.edu.agh.student.conv.LatexParser.InserttitleContext;
import pl.edu.agh.student.conv.LatexParser.ItemContext;
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
				.text("body {" + "font-family: Georgia, serif;" + "font-size: "
						+ fontSize + "px;" + "margin: 0;" + "padding: 0;" + "}"
						+ "#a4content {" + "width: 21cm;" + "padding: 2cm;"
						+ "margin: 1cm auto;" + "}" + "#titleBlock {"
						+ "width: 21cm;" + "padding: 1cm;"
						+ "margin: 3cm auto;" + "}"

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
			titleBlock.appendElement("h1").attr("style", "text-align:center;")
					.text(document.head().select("title").html());
			if (author != null)
				titleBlock.appendElement("h2")
						.attr("style", "text-align:center;").text(author);
			if (date != null)
				titleBlock.appendElement("h3")
						.attr("style", "text-align:center;").text(date);
		}
	}

	@Override
	public void enterAnything(AnythingContext ctx) {
		if (content.select("p").size() == 0)
			current = content.appendElement("p");
	}

	@Override
	public void enterString(StringContext ctx) {
		if (current != null) {
			if (current.html().length() != 0)
				current.appendText(" ");
			current.appendText(ctx.getText().trim());
		}
	}

	@Override
	public void enterSection(SectionContext ctx) {
		subsection = subsubsection = 0;
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h2").attr("class", "section")
				.text(++section + ". " + sectionName);
		current = content.appendElement("p");
	}

	@Override
	public void enterSubsection(SubsectionContext ctx) {
		subsubsection = 0;
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h3").attr("class", "subsection")
				.text(section + "." + ++subsection + ". " + sectionName);
		current = content.appendElement("p");
	}

	@Override
	public void enterSubsubsection(SubsubsectionContext ctx) {
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h4")
				.attr("class", "subsubsection")
				.text(section + "." + subsection + "." + ++subsubsection + ". "
						+ sectionName);
		current = content.appendElement("p");
	}

	@Override
	public void enterUnderline(UnderlineContext ctx) {
		String text = ctx.getChild(1).getText();
		text = text.substring(1);
		text = text.substring(0, text.length() - 1).trim();
		if (current.html().length() != 0)
			current.appendText(" ");
		current.appendElement("span")
				.attr("style", "text-decoration:underline;").text(text);
	}

	@Override
	public void enterEmph(EmphContext ctx) {
		String text = ctx.getChild(1).getText();
		text = text.substring(1);
		text = text.substring(0, text.length() - 1).trim();
		if (current.html().length() != 0)
			current.appendText(" ");
		current.appendElement("em").text(text);
	}

	@Override
	public void enterSpecialchar(SpecialcharContext ctx) {
		String result = String.valueOf(ctx.getText().charAt(1));
		if (current.html().length() != 0)
			current.appendText(" ");
		current.appendText(result);
	}

	@Override
	public void enterQuote(QuoteContext ctx) {
		String result = ctx.getText().substring(2);
		result = result.substring(0, result.length() - 2).trim();
		if (current.html().length() != 0)
			current.appendText(" ");
		current.appendElement("q").text(result);
	}

	@Override
	public void enterCommand(CommandContext ctx) {
		switch (ctx.getText()) {
		case "\\par":
			current = content.appendElement("p");
			break;
		case "\\newline":
			current.appendElement("br");
			break;
		case "\\\\":
			current.appendElement("br");
			break;
		case "\\slash":
			current.appendText("/");
			break;
		case "\\textbackslash":
			current.appendText("\\");
			break;
		case "\\ldots":
			current.append("&hellip;");
			break;
		case "\\today":
			if (current.html().length() != 0)
				current.appendText(" ");
			current.appendText(new SimpleDateFormat("dd.MM.yyyy")
					.format(Calendar.getInstance().getTime()));
			break;
		}
	}

	@Override
	public void enterBeginenv(BeginenvContext ctx) {
		String envName = ctx.getChild(1).getText();
		envName = envName.substring(1);
		envName = envName.substring(0, envName.length() - 1).trim();
		switch (envName) {
		case "itemize":
			if (current.tagName().equals("p"))
				current = current.parent().appendElement("ul");
			else
				current = current.appendElement("ul");
			break;
		case "enumarate":
			if (current.tagName().equals("p"))
				current = current.parent().appendElement("ol");
			else
				current = current.appendElement("ol");
			break;
		case "flushleft":
			current = current.appendElement("span").attr("style",
					"display: block; text-align:left;");
			break;
		case "flushright":
			current = current.appendElement("span").attr("style",
					"display: block; text-align:right;");
			break;
		case "center":
			current = current.appendElement("span").attr("style",
					"display: block; text-align:center;");
			break;
		case "quote":
			if (current.tagName().equals("p"))
				current = current.parent().appendElement("blockquote");
			else
				current = current.appendElement("blockquote");
			break;
		case "verbatim":
			if (current.tagName().equals("p"))
				current = current.parent().appendElement("pre");
			else
				current = current.appendElement("pre");
			break;
		}
	}

	@Override
	public void enterEndenv(EndenvContext ctx) {
		String envName = ctx.getChild(1).getText();
		envName = envName.substring(1);
		envName = envName.substring(0, envName.length() - 1).trim();
		if(envName.equals("flushleft") || envName.equals("flushright") || envName.equals("center"))  {
			while(!current.tagName().equals("span"))
				current = current.parent();
			current = current.parent();
		} else {
			current = content.appendElement("p");
		}
	}

	@Override
	public void enterItem(ItemContext ctx) {
		current = current.appendElement("li");
	}

	@Override
	public void exitItem(ItemContext ctx) {
		while(!current.tagName().equals("ol") && !current.tagName().equals("ul"))
			current = current.parent();
	}

}
