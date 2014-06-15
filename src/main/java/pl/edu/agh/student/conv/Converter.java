package pl.edu.agh.student.conv;

import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.edu.agh.student.conv.extras.Latex2HTMLListener;
import pl.edu.agh.student.conv.gui.LaTeX2HTML;

public class Converter {

	public static void main(String[] args) {

		String input, output;

		// recognizing arguments
		if (args == null || args.length == 0) {
			System.err
					.println("There is no enough arguments. Run this jar with -h option to see the help.");
			return;
		} else if (args.length == 1 && args[0].equals("-h")) {
			System.out.println("There are some hints how to use this tool:");
			System.out.println("-gui\t\t\t-\trun tool in GUI mode");
			System.out
					.println("tex_file output_file\t-\trun tool in console mode, "
							+ "tex_file is input file to convert, output_file is html output file");
			System.out
					.println("-t tex_file output_file\t-\tas above, but it also shows parse tree");
			System.out.println("-h\t\t\t-\trun help");
			return;
		} else if (args.length == 1 && args[0].equals("-gui")) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new LaTeX2HTML();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		} else if ((args.length == 2 && args[0].charAt(0) != '-' && args[1]
				.charAt(0) != '-')
				|| (args.length == 3 && args[0].equals("-t")
						&& args[1].charAt(0) != '-' && args[2].charAt(0) != '-')) {
			if (args.length == 2) {
				input = args[0];
				output = args[1];
			} else {
				input = args[1];
				output = args[2];
			}

			LatexLexer lexer = null;
			try {
				lexer = new LatexLexer(new ANTLRFileStream(input));
			} catch (IOException e) {
				System.err.println("Error occured: could not open input file.");
				return;
			}
			CommonTokenStream tokens = new CommonTokenStream(lexer);
			LatexParser parser = new LatexParser(tokens);

			parser.setBuildParseTree(true);
			RuleContext rc = parser.document();

			if (args.length == 3)
				rc.inspect(parser);

			ParseTreeWalker walker = new ParseTreeWalker();
			Latex2HTMLListener listener = new Latex2HTMLListener();
			walker.walk(listener, rc);

			BufferedWriter writer;
			try {
				writer = Files.newBufferedWriter(Paths.get(output));
				writer.write(listener.getHtml());
				writer.close();

			} catch (IOException e) {
				System.err.println("Error occured: could not write output file");
				return;
			}
			
		} else {
			System.err.println("This is not the correct way to use this tool. "
					+ "Run this jar with -h option to see the help.");
			return;
		}
	}
}
