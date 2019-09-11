package bootsFaces;

import java.util.function.DoubleBinaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.Stateless;

@Stateless
public class Kalkulator {

	public Kalkulator() {
	}

	public String calculateLine(String line) {
		String patternPa = "\\(([\\d\\.\\+\\-\\*\\/]+)\\)";
		Pattern pattern = Pattern.compile(patternPa);
		Matcher m;
		while ((m = pattern.matcher(line)).find())
			line = line.replaceFirst(patternPa, calculatePart(m.group(1)));
		return calculatePart(line);
	}

	private String calculatePart(String line) {
		String patternMu = "(-?[\\d\\.]+)\\*(-?[\\d\\.]+)";
		String patternDi = "(-?[\\d\\.]+)\\/(-?[\\d\\.]+)";
		String patternAd = "(-?[\\d\\.]+)\\+(-?[\\d\\.]+)";
		String patternSu = "(-?[\\d\\.]+)\\-(-?[\\d\\.]+)";
		String patternPo = "(-?[\\d\\.]+)\\^(-?[\\d\\.]+)";
		line = execute(line, patternPo, (d1, d2) -> Math.pow(d1, d2));
		line = execute(line, patternMu, (d1, d2) -> d1 * d2);
		line = execute(line, patternDi, (d1, d2) -> d1 / d2);
		line = execute(line, patternAd, (d1, d2) -> d1 + d2);
		line = execute(line, patternSu, (d1, d2) -> d1 - d2);
		return line;
	}

	private String execute(String line, String patternString, DoubleBinaryOperator func) {
		Pattern pattern = Pattern.compile(patternString);
		Matcher m;
		while ((m = pattern.matcher(line)).find()) {
			Double result = func.applyAsDouble(Double.valueOf(m.group(1)), Double.valueOf(m.group(2)));
			line = line.replaceFirst(patternString, result.toString());
		}
		return line;
	}

}
