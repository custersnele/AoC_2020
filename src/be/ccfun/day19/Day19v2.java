package be.ccfun.day19;

import be.ccfun.day19.gen.Day19Lexer;
import be.ccfun.day19.gen.Day19Parser;
import be.ccfun.day19.gen2.TestLexer;
import be.ccfun.day19.gen2.TestParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day19v2 {

	public static void main(String[] args) throws IOException {


		List<String> lines = Files.readAllLines(Path.of("resources/day19_part2.txt"));
		List<Rule> rules = new ArrayList<>();
		List<String> messages = new ArrayList<>();
		for (String line : lines) {
			if (line.contains(":")) {
				String[] split = line.split(":");
				int ruleNumber = Integer.parseInt(split[0]);
				if (line.contains("\"")) {
					int idx = line.indexOf('\"');
					rules.add(new EndRule(ruleNumber, line.substring(idx + 1, idx + 2)));
				} else {
					rules.add(new SubRule(ruleNumber, split[1]));
				}
			} else {
				if (!line.isBlank()) {
					messages.add(line);
				}
			}
		}
		for (Rule rule : rules) {
			System.out.println(rule.toANTLR());
		}

		int count = 0;
		for (String message : messages) {
			System.out.println(message);
			if (isValid(message)) {
				count++;
				System.out.println("valid");
			} else {
				System.out.println("invalid");
			}
		}
		System.out.println(count);

	}

	public static boolean isValid(String text) {
		CharStream charStream = CharStreams.fromString(text);
		Day19Lexer lexer = new Day19Lexer(charStream);
		// Stream of token
		CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);

		// Create the parser
		Day19Parser parser = new Day19Parser(commonTokenStream);
		parser.removeErrorListeners(); // remove ConsoleErrorListener
		SyntaxError syntaxError = new SyntaxError();
		parser.addErrorListener(syntaxError); // add ours

		Day19Parser.RContext tree = parser.r();
		if (tree.getStop().getStopIndex() != text.length() - 1) {
			return false;
		}

		return !syntaxError.isError();
	}
}
