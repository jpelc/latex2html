package pl.edu.agh.student.conv.html;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class HTMLWriter {

	private int fontSize;
	private String title;
	private List<HTMLElement> elements;

	public HTMLWriter() {
		this.fontSize = 12;
		this.title = "ANTLR4-based LaTeX to HTML converter";
		elements = new LinkedList<HTMLElement>();
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addElement(HTMLElement element) {
		elements.add(element);
	}

	public void saveFile(String filename) throws IOException {
		BufferedWriter writer = Files.newBufferedWriter(Paths.get(filename));
		writer.write(createPage());
		writer.close();
	}

	private String createPage() {
		StringBuilder html = new StringBuilder();
		html.append("<!DOCTYPE HTML>"
				+ "<html>\n"
				+ "<head>\n"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n"
				+ "<title>"
				+ title
				+ "</title>\n"
				+ "<style>\n"
				+ "body {font-family: \"Times New Roman\", Times, serif; font-size: "
				+ fontSize
				+ "px}\n"
				+ ".center {margin-left: auto; margin-right: auto; width: 70%;}\n"
				+ ".right {float: right; width: 300px;}\n"
				+ ".left {float: left; width: 300px;}\n" 
				+ "</style>\n"
				+ "</head>\n" + "<body>\n");
//		if (!title.equals("ANTLR4-based LaTeX to HTML converter")
//				&& !author.equals("")) {
//			html.append("<h1 class=\"center\">" + title + "</h1>");
//			html.append("<h2 class=\"center\">" + author + "</h1>");
//		}
		for (HTMLElement e : elements)
			html.append(e.getHtml());
		html.append("</body\n" + "</html>");
		return html.toString();
	}

}
