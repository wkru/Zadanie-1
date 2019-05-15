package proz.calc;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * Class responsible for linking the View and Model portions of the program.
 * 
 * @author wk
 *
 */

public class Controller {

	@FXML
	private Button zero;

	@FXML
	private Button tripleZero;

	@FXML
	private Button decimalSign;

	@FXML
	private Button equals;

	@FXML
	private Button add;

	@FXML
	private Button subtract;

	@FXML
	private Button multiply;

	@FXML
	private Button divide;

	@FXML
	private Button one;

	@FXML
	private Button two;

	@FXML
	private Button three;

	@FXML
	private Button four;

	@FXML
	private Button five;

	@FXML
	private Button six;

	@FXML
	private Button seven;

	@FXML
	private Button eight;

	@FXML
	private Button nine;

	@FXML
	private Button allCancel;

	@FXML
	private Button backspace;

	@FXML
	private Button invertSign;

	@FXML
	private Button sqrt;

	@FXML
	private Button power;

	@FXML
	private Button naturalLogarithm;

	@FXML
	private Button clearEntry;

	@FXML
	private TextFlow display;

	@FXML
	private Text displayText;

	private String calcInput = "";
	private String pendingOperation = "";
	private boolean finalResultShown = false;
	private boolean nonNumericOutputShown = false;
	private boolean usePower = false;
	private Model model = new Model();

	/**
	 * The method called by the <code>FXMLLoader</code> after populating the
	 * interface elements entries, containing actions to be done after clicking the
	 * buttons.
	 */
	@FXML
	public void initialize() {
		zero.setOnAction(e -> {
			digitAppend("0");
		});

		tripleZero.setOnAction(e -> {
			digitAppend("000");
		});

		one.setOnAction(e -> {
			digitAppend("1");
		});

		two.setOnAction(e -> {
			digitAppend("2");
		});

		three.setOnAction(e -> {
			digitAppend("3");
		});

		four.setOnAction(e -> {
			digitAppend("4");
		});

		five.setOnAction(e -> {
			digitAppend("5");
		});

		six.setOnAction(e -> {
			digitAppend("6");
		});

		seven.setOnAction(e -> {
			digitAppend("7");
		});

		eight.setOnAction(e -> {
			digitAppend("8");
		});

		nine.setOnAction(e -> {
			digitAppend("9");
		});

		decimalSign.setOnAction(e -> {
			digitAppend(".");
		});

		add.setOnAction(e -> {
			mathOperationClicked("+");
		});

		subtract.setOnAction(e -> {
			mathOperationClicked("-");
		});

		multiply.setOnAction(e -> {
			mathOperationClicked("*");
		});

		divide.setOnAction(e -> {
			mathOperationClicked("/");
		});

		power.setOnAction(e -> {
			mathOperationClicked("^");
		});

		sqrt.setOnAction(e -> {
			if (nonNumericOutputShown)
				return;
			if (finalResultShown)
				finalResultShown = false;
			String oldCalcInput = calcInput;
			calcInput = "Math.pow(" + displayText.getText() + ", 0.5)";
			calculate();
			calcInput = oldCalcInput;
		});

		naturalLogarithm.setOnAction(e -> {
			if (nonNumericOutputShown)
				return;
			if (finalResultShown)
				finalResultShown = false;
			String oldCalcInput = calcInput;
			calcInput = "Math.log(" + displayText.getText() + ")";
			calculate();
			calcInput = oldCalcInput;
		});

		backspace.setOnAction(e -> {
			if (nonNumericOutputShown)
				return;
			if (displayText.getText().equals("0"))
				return;
			if (finalResultShown)
				return;
			if (displayText.getText().length() == 1) {
				displayText.setText("0");
				return;
			}

			displayText.setText(displayText.getText().substring(0, displayText.getText().length() - 1));
		});

		clearEntry.setOnAction(e -> {
			if (nonNumericOutputShown)
				return;
			if (displayText.getText().equals("0"))
				return;
			if (finalResultShown)
				return;

			displayText.setText("0");
		});

		allCancel.setOnAction(e -> {
			displayText.setText("0");
			finalResultShown = false;
			pendingOperation = "";
			calcInput = "";
			nonNumericOutputShown = false;
			usePower = false;
		});

		invertSign.setOnAction(e -> {
			if (nonNumericOutputShown)
				return;
			if (displayText.getText().equals("0"))
				return;
			if (displayText.getText().startsWith("-"))
				displayText.setText(displayText.getText().substring(1, displayText.getText().length()));
			else
				displayText.setText("-" + displayText.getText());
		});

		equals.setOnAction(e -> {
			if (nonNumericOutputShown)
				return;
			if (!calcInput.isBlank() && calcInput.substring(calcInput.length() - 1, calcInput.length()).equals("/")
					&& displayText.getText().equals("0")) {
				showErrorWindow("Nie mo�na dzieli� przez zero", "Popraw swoje obliczenia.");
				return;
			}
			if (usePower) {
				usePower = false;
				calcInput = calcInput + ", " + displayText.getText() + ")";
			} else
				calcInput = calcInput.concat(displayText.getText());
			calculate();
			finalResultShown = true;
			pendingOperation = "";
		});
	}

	/**
	 * Method responsible for showing an error <code>Alert</code> window, as
	 * requested by other parts of the class.
	 * 
	 * @param header  text containing the header for the <code>Alert</code> window
	 * @param content text containing the main text for the <code>Alert</code>
	 *                window
	 */
	private void showErrorWindow(String header, String content) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("B��d dzia�ania programu");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	/**
	 * Method responsible for showing a warning <code>Alert</code> window, as
	 * requested by other parts of the class.
	 * 
	 * @param header  text containing the header for the <code>Alert</code> window
	 * @param content text containing the main text for the <code>Alert</code>
	 *                window
	 */
	private void showWarningWindow(String header, String content) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Uwaga");
		alert.setHeaderText(header);
		alert.setContentText(content);
		alert.showAndWait();
	}

	/**
	 * Method responsible for calling the JShell calculating engine created in an
	 * instance of the <code>Model</code> part to calculate an expression as
	 * requested by other methods in the <code>calcInput</code> String.
	 */
	private void calculate() {
		String result = model.calculate(calcInput);
		if (!result.isBlank() && result.length() > 2
				&& result.substring(result.length() - 2, result.length()).equals(".0"))
			result = result.substring(0, result.length() - 2);
		displayText.setText(result);
		if (displayText.getText().equals("B��d."))
			nonNumericOutputShown = true;
		if (displayText.getText().equals("Infinity")) {
			showWarningWindow("Wynikiem oblicze� by�a niesko�czono��", "Skasuj wynik oblicze� klawiszem AC.");
			nonNumericOutputShown = true;
		}
		if (displayText.getText().equals("NaN")) {
			showErrorWindow("Wynikiem oblicze� by�a nie-liczba", "Skasuj wynik oblicze� klawiszem AC.");
			nonNumericOutputShown = true;
		}
		calcInput = "";
	}

	/**
	 * Method responsible for the correct insertion of a given digit (or a decimal
	 * sign) at the back of the currently displayed number.
	 * 
	 * @param digit a digit to be inserted in the display
	 */
	private void digitAppend(String digit) {
		if (nonNumericOutputShown)
			return;
		if (finalResultShown) {
			displayText.setText("0");
			finalResultShown = false;
		}
		if (!pendingOperation.isBlank()) {
			if (pendingOperation.equals("^")) {
				calcInput = "Math.pow(" + displayText.getText();
				usePower = true;
			} else if (pendingOperation.equals("/")) {
				if (!displayText.getText().contains("."))
					calcInput = displayText.getText() + "." + pendingOperation;
				else
					calcInput = displayText.getText() + pendingOperation;
			} else
				calcInput = displayText.getText() + pendingOperation;
			pendingOperation = "";
			if (digit.equals("."))
				displayText.setText("0.");
			else
				displayText.setText(digit);
			return;
		}
		if (displayText.getText().length() + digit.length() > 16) {
			showWarningWindow("Przekroczona liczba znak�w", "Maksymalna dozwolona liczba znak�w to 16.");
			return;
		}
		if (digit.equals(".")) {
			if (!displayText.getText().contains(".")) {
				displayText.setText(displayText.getText() + ".");
				return;
			} else
				return;
		}
		if (displayText.getText().equals("0")) {
			if (!(digit.equals("0") || digit.equals("000")))
				displayText.setText(digit);
		} else
			displayText.setText(displayText.getText() + digit);
	}

	/**
	 * Method responsible for handling the operation buttons and preparing the
	 * arguments for appropriate processing by the JShell.
	 * 
	 * @param operation the sign of an operation which has been clicked
	 */
	private void mathOperationClicked(String operation) {
		if (nonNumericOutputShown)
			return;
		if (finalResultShown)
			finalResultShown = false;
		if (!pendingOperation.isBlank()) {
			pendingOperation = operation;
			return;
		}
		if (!calcInput.isBlank() && calcInput.substring(calcInput.length() - 1, calcInput.length()).equals("/")
				&& displayText.getText().equals("0")) {
			showErrorWindow("Nie mo�na dzieli� przez zero", "Popraw swoje obliczenia.");
			return;
		}
		if (usePower) {
			usePower = false;
			calcInput = calcInput + ", " + displayText.getText() + ")";
		} else if (operation.equals("/") && !displayText.getText().contains("."))
			calcInput = calcInput + displayText.getText() + ".";
		else
			calcInput = calcInput.concat(displayText.getText());
		calculate();
		pendingOperation = operation;
	}
}
