package pl.edu.agh.student.conv.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.JTextComponent;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import pl.edu.agh.student.conv.LatexLexer;
import pl.edu.agh.student.conv.LatexParser;
import pl.edu.agh.student.conv.extras.CustomErrorListener;
import pl.edu.agh.student.conv.extras.Latex2HTMLListener;

public class LaTeX2HTML {

	private JFrame frame;
	private JTextArea leftTextArea;
	private JTextArea rightTextArea;
	private JTextArea logTextArea;
	private RuleContext rc;
	private LatexParser parser;
	private CustomErrorListener cel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LaTeX2HTML window = new LaTeX2HTML();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public LaTeX2HTML() {

		try {
			UIManager
					.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		initialize();
		cel = new CustomErrorListener(logTextArea);
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setTitle("LaTeX2HTML");
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(5, 5));

		JPanel headerPanel = new JPanel();
		frame.getContentPane().add(headerPanel, BorderLayout.NORTH);

		JButton btnNew = new JButton("New");
		headerPanel.add(btnNew);

		JButton btnOpen = new JButton("Open");
		headerPanel.add(btnOpen);

		JButton btnSaveInput = new JButton("Save input");
		headerPanel.add(btnSaveInput);

		JButton btnSaveOutput = new JButton("Save output");
		headerPanel.add(btnSaveOutput);

		JButton btnConvert = new JButton("Convert");
		headerPanel.add(btnConvert);

		JButton btnShowParseTree = new JButton("Show parse tree");
		headerPanel.add(btnShowParseTree);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new GridLayout(0, 2, 15, 0));

		JScrollPane leftScrollPane = new JScrollPane();
		mainPanel.add(leftScrollPane);

		leftTextArea = new JTextArea();
		leftTextArea.setMargin(new Insets(10, 10, 10, 10));
		leftTextArea.setLineWrap(true);
		leftTextArea.setTabSize(4);
		leftTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		leftScrollPane.setViewportView(leftTextArea);

		JLabel lblInputTexFile = new JLabel("Input TEX file:");
		lblInputTexFile.setLabelFor(leftTextArea);
		leftScrollPane.setColumnHeaderView(lblInputTexFile);

		JScrollPane rightScrollPane = new JScrollPane();
		mainPanel.add(rightScrollPane);

		rightTextArea = new JTextArea();
		rightTextArea.setMargin(new Insets(10, 10, 10, 10));
		rightTextArea.setLineWrap(true);
		rightTextArea.setTabSize(4);
		rightTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		rightScrollPane.setViewportView(rightTextArea);

		JLabel lblOutputHtmlFile = new JLabel("Output HTML file:");
		lblOutputHtmlFile.setLabelFor(rightTextArea);
		rightScrollPane.setColumnHeaderView(lblOutputHtmlFile);

		JPanel footerPanel = new JPanel();
		frame.getContentPane().add(footerPanel, BorderLayout.SOUTH);
		footerPanel.setLayout(new BorderLayout(5, 5));

		JScrollPane logScrollPane = new JScrollPane();
		footerPanel.add(logScrollPane, BorderLayout.CENTER);

		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		logTextArea.setLineWrap(true);
		logTextArea.setRows(5);
		logTextArea.setTabSize(4);
		logTextArea.setFont(new Font("Courier New", Font.PLAIN, 12));
		logScrollPane.setViewportView(logTextArea);

		// action listeners
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (leftTextArea.getText().length() > 0) {
					int result = JOptionPane
							.showConfirmDialog(
									frame,
									"You should save your input file first, otherwise it will be lost. Do you want to continue?",
									"Caution", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CANCEL_OPTION
							|| result == JOptionPane.NO_OPTION)
						return;
				}

				JFileChooser fileChooser = new JFileChooser(Paths.get("")
						.toAbsolutePath().toString());
				fileChooser.setDialogTitle("Specify an input file to read");
				fileChooser.setAcceptAllFileFilterUsed(false);
				fileChooser.setFileFilter(new FileNameExtensionFilter(
						"TEX files only", "tex"));
				fileChooser.setMultiSelectionEnabled(false);
				if (fileChooser.showOpenDialog(frame) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						BufferedReader reader = Files.newBufferedReader(Paths
								.get(file.getAbsolutePath()));
						String line;
						leftTextArea.setText("");
						while ((line = reader.readLine()) != null) {
							leftTextArea.append(line + "\n");
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame,
								"Reading selected file failed.", "Error",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
			}
		});

		btnSaveInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (leftTextArea.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame,
							"There is no content to save in input area",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				boolean result = saveFile(leftTextArea);
				if (!result)
					JOptionPane.showMessageDialog(frame,
							"Saving input file failed.", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});

		btnSaveOutput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rightTextArea.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame,
							"There is no content to save in output area",
							"Information", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				boolean result = saveFile(rightTextArea);
				if (!result)
					JOptionPane.showMessageDialog(frame,
							"Saving output file failed.", "Error",
							JOptionPane.ERROR_MESSAGE);
			}
		});

		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (leftTextArea.getText().length() > 0
						|| rightTextArea.getText().length() > 0) {
					int result = JOptionPane
							.showConfirmDialog(
									frame,
									"You should save your input and output files first, otherwise it will be lost. Do you want to continue?",
									"Caution", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CANCEL_OPTION
							|| result == JOptionPane.NO_OPTION)
						return;
				}
				leftTextArea.setText("");
				rightTextArea.setText("");
			}
		});

		btnConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logTextArea.setText("");
				if (rightTextArea.getText().length() > 0) {
					int result = JOptionPane
							.showConfirmDialog(
									frame,
									"You should save your output file first, otherwise it will be lost. Do you want to continue?",
									"Caution", JOptionPane.YES_NO_OPTION);
					if (result == JOptionPane.CANCEL_OPTION
							|| result == JOptionPane.NO_OPTION)
						return;
				}
				
				if(leftTextArea.getText().length() == 0) {
					JOptionPane.showMessageDialog(frame, "There is no content to convert in input area", "Information", JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				rightTextArea.setText("");
				
				LatexLexer lexer = new LatexLexer(new ANTLRInputStream(leftTextArea.getText()));
				lexer.addErrorListener(cel);
				CommonTokenStream tokens = new CommonTokenStream(lexer);
				parser = new LatexParser(tokens);
				if(parser == null) {
					JOptionPane.showMessageDialog(frame, "Error occured: could not create a parser", "Conversion error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				parser.addErrorListener(cel);

				parser.setBuildParseTree(true);
				rc = parser.document();

				ParseTreeWalker walker = new ParseTreeWalker();
				Latex2HTMLListener listener = new Latex2HTMLListener();
				walker.walk(listener, rc);
				rightTextArea.setText(listener.getHtml());
			}
		});
		
		btnShowParseTree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rc != null && parser != null)
					try {
						rc.inspect(parser);
					} catch (NullPointerException ex) {}
					
				else {
					JOptionPane.showMessageDialog(frame, "You have to run conversion first.", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
	}

	private boolean saveFile(JTextComponent component) {
		JFileChooser fileChooser = new JFileChooser(Paths.get("")
				.toAbsolutePath().toString());
		fileChooser.setDialogTitle("Specify a file to save");
		int result = fileChooser.showSaveDialog(frame);
		if (result == JFileChooser.CANCEL_OPTION)
			return true;
		File file = fileChooser.getSelectedFile();

		try {
			BufferedWriter writer = Files.newBufferedWriter(Paths.get(file
					.getAbsolutePath()));
			writer.write(component.getText());
			writer.close();
		} catch (IOException e) {
			return false;
		}
		return true;
	}

}
