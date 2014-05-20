package pl.edu.agh.student.jpelc.conv;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;

public class Latex {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		LatexLexer lexer = new LatexLexer(new ANTLRFileStream(args[0]));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LatexParser parser = new LatexParser(tokens);
		parser.setBuildParseTree(true);
		
	}

}
