package pl.edu.agh.student.conv.extras;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.DocumentType;
import org.jsoup.nodes.Element;

import pl.edu.agh.student.conv.LatexBaseListener;
import pl.edu.agh.student.conv.LatexParser.AuthorinfoContext;
import pl.edu.agh.student.conv.LatexParser.BeginenvContext;
import pl.edu.agh.student.conv.LatexParser.BodyContext;
import pl.edu.agh.student.conv.LatexParser.BoldContext;
import pl.edu.agh.student.conv.LatexParser.CellContext;
import pl.edu.agh.student.conv.LatexParser.ClassoptContext;
import pl.edu.agh.student.conv.LatexParser.CommandContext;
import pl.edu.agh.student.conv.LatexParser.DateinfoContext;
import pl.edu.agh.student.conv.LatexParser.DocumentContext;
import pl.edu.agh.student.conv.LatexParser.EmphContext;
import pl.edu.agh.student.conv.LatexParser.EndenvContext;
import pl.edu.agh.student.conv.LatexParser.InserttitleContext;
import pl.edu.agh.student.conv.LatexParser.ItemContext;
import pl.edu.agh.student.conv.LatexParser.QuoteContext;
import pl.edu.agh.student.conv.LatexParser.RowContext;
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

	private Document document;
	private String author;
	private String date;
	private int fontSize = 10;
	private double marginSize = 2;
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
		document.head().select("meta").get(0).attr("charset", "UTF-8");
	}

	@Override
	public void enterUsepkg(UsepkgContext ctx) {
		if (ctx.getChildCount() == 3) {
			String pkg = ctx.getChild(2).getText();
			pkg = pkg.substring(1);
			pkg = pkg.substring(0, pkg.length() - 1).trim();
			if (pkg.equals("geometry")) {
				String margin = ctx.getChild(1).getText();
				margin = margin.substring(1);
				margin = margin.substring(0, margin.length() - 1).trim();
				int eq = margin.indexOf("=");
				if (!margin.substring(0, eq).equals("margin"))
					return;
				try {
					marginSize = Double.parseDouble(margin.substring(eq + 1,
							margin.lastIndexOf("in")));
				} catch (NumberFormatException e) {
					return;
				}
			}
		}
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
		if (fontSize < 10 || fontSize > 12)
			fontSize = 10;
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
		document.head()
				.appendElement("style")
				.text("body {" + "background-color: #E6E6E6;"
						+ "font-family: Georgia, serif;" + "font-size: "
						+ fontSize
						+ "pt;"
						+ "margin: 0;"
						+ "padding: 0;"
						+ "line-height:150%"
						+ "}"
						+ "#a4content {"
						+ "background-color: #FFFFFF;"
						+ "width: "
						+ (21 - 2 * marginSize * 2.54)
						+ "cm;"
						+ "padding: 0 "
						+ marginSize
						+ "in "
						+ " 0 "
						+ marginSize
						+ "in;"
						+ "margin: 0 auto; margin-bottom: 3in;"
						+ "}"
						+ "#titleBlock {"
						+ "width: "
						+ (21 - 2 * marginSize * 2.54)
						+ "cm;"
						+ "padding: 0;"
						+ "padding-top: "
						+ (marginSize + 0.3)
						+ "in;"
						+ "padding-bottom: 0.05in;"
						+ "margin: 0 auto;"
						+ "}"
						+ "p {margin:0;}"
						+ "table,th,td {border:1px solid black; float:center; border-collapse:collapse;}"

				);
		content = document.body().appendElement("div").attr("id", "a4content");
	}

	@Override
	public void enterInserttitle(InserttitleContext ctx) {
		if (titleSet) {
			Element titleBlock = document.body().getElementById("a4content")
					.prependElement("div").attr("id", "titleBlock")
					.attr("style", "background-color: #FFFFFF;");
			titleBlock.appendElement("h2")
					.attr("style", "font-size: 1.75em; text-align:center;")
					.text(document.head().select("title").html());
			if (author != null)
				titleBlock.appendElement("h3")
						.attr("style", "text-align:center;").text(author);
			if (date != null)
				titleBlock.appendElement("h3")
						.attr("style", "text-align:center;").text(date);
		}
	}

	@Override
	public void enterText(TextContext ctx) {
		if (content.select("p").size() == 0)
			current = content.appendElement("p").attr("style",
					"text-align: justify;");
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
				.text(++section + "\t" + sectionName);
		current = content.appendElement("p").attr("style",
				"text-align: justify;");
	}

	@Override
	public void enterSubsection(SubsectionContext ctx) {
		subsubsection = 0;
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h3").attr("class", "subsection")
				.text(section + "." + ++subsection + "\t" + sectionName);
		current = content.appendElement("p").attr("style",
				"text-align: justify;");
	}

	@Override
	public void enterSubsubsection(SubsubsectionContext ctx) {
		String sectionName = ctx.getChild(1).getText().substring(1);
		sectionName = sectionName.substring(0, sectionName.length() - 1).trim();
		content.appendElement("h4")
				.attr("class", "subsubsection")
				.text(section + "." + subsection + "." + ++subsubsection + "\t"
						+ sectionName);
		current = content.appendElement("p").attr("style",
				"text-align: justify;");
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
	public void enterBold(BoldContext ctx) {
		String text = ctx.getChild(1).getText();
		text = text.substring(1);
		text = text.substring(0, text.length() - 1).trim();
		if (current.html().length() != 0)
			current.appendText(" ");
		current.appendElement("b").text(text);
	}

	@Override
	public void enterSpecialchar(SpecialcharContext ctx) {
		if (current.html().length() != 0)
			current.appendText(" ");
		switch (ctx.getText()) {
		case "-":
			current.appendText("-");
			break;
		case "--":
			current.appendText("\u2013");
			break;
		case "---":
			current.appendText("\u2014");
			break;
		default:
			current.appendText(String.valueOf(ctx.getText().charAt(1)));
		}
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
		switch (ctx.getChild(0).getText()) {
		case "\\par":
			current = content.appendElement("p").attr("style",
					"text-align: justify; text-indent: 20pt;");
			break;
		case "\\newline":
			current.appendElement("br");
			break;
		case "\\slash{}":
			current.appendText("/");
			break;
		case "\\textbackslash{}":
			current.appendText("\\");
			break;
		case "\\today{}":
			if (current.html().length() != 0)
				current.appendText(" ");
			current.appendText(new SimpleDateFormat("dd.MM.yyyy")
					.format(Calendar.getInstance().getTime()));
			break;
		case "\\ldots{}":
			current.append("&hellip;");
			break;
		case "\\texteuro{}":
			current.append(" \u20ac");
			break;
		case "\\textcelsius{}":
			current.append("\u2103");
			break;
		case "\\textGamma{}":
			current.appendText(" \u1d26");
			break;
		case "\\textDelta{}":
			current.append(" \u0394");
			break;
		case "\\textTheta{}":
			current.appendText(" \u0398");
			break;
		case "\\textLambda{}":
			current.appendText(" \u039b");
			break;
		case "\\textPi{}":
			current.appendText(" \u03a0");
			break;
		case "\\textSigma{}":
			current.appendText(" \u03a3");
			break;
		case "\\textPhi{}":
			current.appendText(" \u03a6");
			break;
		case "\\textPsi{}":
			current.appendText(" \u03a8");
			break;
		case "\\textOmega{}":
			current.appendText(" \u03a9");
			break;
		case "\\textalpha{}":
			current.appendText(" \u03b1");
			break;
		case "\\textbeta{}":
			current.appendText(" \u03b2");
			break;
		case "\\textgamma{}":
			current.appendText(" \u03b3");
			break;
		case "\\textdelta{}":
			current.appendText(" \u03b4");
			break;
		case "\\textepsilon{}":
			current.appendText(" \u03b5");
			break;
		case "\\texteta{}":
			current.appendText(" \u03b7");
			break;
		case "\\texttheta{}":
			current.appendText(" \u03b8");
			break;
		case "\\textiota{}":
			current.appendText(" \u03b9");
			break;
		case "\\textkappa{}":
			current.appendText(" \u03ba");
			break;
		case "\\textlambda{}":
			current.appendText(" \u03bb");
			break;
		case "\\textmugreek{}":
			current.appendText(" \u03bc");
			break;
		case "\\textnu{}":
			current.appendText(" \u03bd");
			break;
		case "\\textxi{}":
			current.appendText(" \u03be");
			break;
		case "\\textpi{}":
			current.appendText(" \u03c0");
			break;
		case "\\textrho{}":
			current.appendText(" \u03c1");
			break;
		case "\\textsigma{}":
			current.appendText(" \u03c3");
			break;
		case "\\texttau{}":
			current.appendText(" \u03c4");
			break;
		case "\\textupsilon{}":
			current.appendText(" \u03c5");
			break;
		case "\\textphi{}":
			current.appendText(" \u03c6");
			break;
		case "\\textchi{}":
			current.appendText(" \u03c7");
			break;
		case "\\textpsi{}":
			current.appendText(" \u03c8");
			break;
		case "\\textomega{}":
			current.appendText(" \u03c9");
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
		case "enumerate":
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
		case "tabular":
			if (current.tagName().equals("p"))
				current = current.parent().appendElement("table");
			else
				current = current.appendElement("table");
			break;
		}
	}

	@Override
	public void enterEndenv(EndenvContext ctx) {
		String envName = ctx.getChild(1).getText();
		envName = envName.substring(1);
		envName = envName.substring(0, envName.length() - 1).trim();
		if (envName.equals("flushleft") || envName.equals("flushright")
				|| envName.equals("center")) {
			while (!current.tagName().equals("span"))
				current = current.parent();
			current = current.parent();
		} else {
			current = content.appendElement("p").attr("style",
					"text-align: justify;");
		}
	}

	@Override
	public void enterItem(ItemContext ctx) {
		current = current.appendElement("li");
	}

	@Override
	public void exitItem(ItemContext ctx) {
		while (!current.tagName().equals("ol")
				&& !current.tagName().equals("ul"))
			current = current.parent();
	}

	@Override
	public void enterRow(RowContext ctx) {
		current = current.appendElement("tr");
	}

	@Override
	public void exitRow(RowContext ctx) {
		while(!current.tagName().equals("table"))
			current = current.parent();
	}

	@Override
	public void enterCell(CellContext ctx) {
		current = current.appendElement("td");
	}

	@Override
	public void exitCell(CellContext ctx) {
		while(!current.tagName().equals("tr"))
			current = current.parent();
	}

}
