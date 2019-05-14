package proz.calc;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class Model {
	public String calculate(String expression) {
		JShell jshell = JShell.create();
		String result = "B³¹d.";
		try (jshell) {
			List<SnippetEvent> events = jshell.eval(expression);

			for (SnippetEvent e : events) {
				if(e.causeSnippet() == null) {
					switch (e.status()) {
					case VALID:
						if (e.value() != null) {
							result = e.value();
						}
						break;
					default:
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("B³¹d dzia³ania programu");
						alert.setHeaderText("W trakcie obliczeñ napotkano b³¹d.");
						break;
					}
				}
			}
		}
		return result;
	}
}
