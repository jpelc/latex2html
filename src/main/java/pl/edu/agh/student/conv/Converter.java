package pl.edu.agh.student.conv;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.edu.agh.student.conv.extras.Latex2HTMLListener;

public class Converter {

	public static void main(String[] args) throws FileNotFoundException,
			IOException {
		LatexLexer lexer = new LatexLexer(new ANTLRFileStream(args[0]));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LatexParser parser = new LatexParser(tokens);

		parser.setBuildParseTree(true);
		RuleContext rc = parser.document();
		// rc.inspect(parser);

		ParseTreeWalker walker = new ParseTreeWalker();
		Latex2HTMLListener listener = new Latex2HTMLListener();
		walker.walk(listener, rc);

		BufferedWriter writer = Files.newBufferedWriter(Paths
				.get("output.html"));
		writer.write(listener.getHtml());
		writer.close();

	}

}
