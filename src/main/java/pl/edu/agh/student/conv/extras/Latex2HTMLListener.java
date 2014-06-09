package pl.edu.agh.student.conv.extras;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;

import pl.edu.agh.student.conv.LatexBaseListener;
import pl.edu.agh.student.conv.LatexParser.AuthorinfoContext;
import pl.edu.agh.student.conv.LatexParser.ClassoptContext;
import pl.edu.agh.student.conv.LatexParser.DateinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocclassContext;
import pl.edu.agh.student.conv.LatexParser.DocumentContext;
import pl.edu.agh.student.conv.LatexParser.InserttitleContext;
import pl.edu.agh.student.conv.LatexParser.TitleinfoContext;

public class Latex2HTMLListener extends LatexBaseListener {

	private Document document;
	private String author;
	private String date;
	private int fontSize = 10;
	private boolean titleSet;

	public String getHtml() {
		return document.html();
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
	public void enterInserttitle(InserttitleContext ctx) {
		if (titleSet) {
			document.body().getElementById("a4content").append(
					"<h1 style=\"text-align:center;\">"
							+ document.head().select("title").text() + "</h1>");
			if (author != null)
				document.body().getElementById("a4content").append(
						"<h2 style=\"text-align:center;\">" + author + "</h2>");
			if (date != null)
				document.body().getElementById("a4content").append(
						"<h3 style=\"text-align:center;\">" + date + "</h3>");
		}
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
						+ "margin: 1cm auto;\n" + "}\n"

				);
		document.body().appendElement("div").attr("id", "a4content");
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

}
