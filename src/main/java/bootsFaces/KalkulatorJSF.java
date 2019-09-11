package bootsFaces;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class KalkulatorJSF {

	private String equation;
	private String result;

	@Inject
	Kalkulator kalkulator;
	
	public void submit() {
		result = kalkulator.calculateLine(equation);
	}

	public void clear() {
		result = null;
		equation = null;
	}

	public String getEquation() {
		return equation;
	}

	public void setEquation(String equation) {
		this.equation = equation;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
