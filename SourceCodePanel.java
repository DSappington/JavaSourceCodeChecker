import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class SourceCodePanel {


	int lineWithError = 1;
	static char missingCharacter;

	public File FileOpener() {
		//Creates file chooser
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Java Files", "*.java"));
		//shows the open dialog for selected file
		File selectedFile = fileChooser.showOpenDialog(null);
		
		// Passed file information for user. 
		File file = new File("File Name: " + selectedFile.getName() + "\n" + "File Path: " + selectedFile.getPath()
				+ "\n");
		// Prints out file information to the console for clarification and return it to the mine. 
		System.out.println(file);
		return selectedFile;

	}
	
	
	public void isMatch(File selectedFile) throws FileNotFoundException {
		//creates stack to process symbols
		Stack<Character> symbols = new Stack<>();

		//error on line 

		//Creates scanner to create each character.
		try (Scanner input = new Scanner(selectedFile);) {
			//does a loop for each character
			while (input.hasNext()) {
				String line = input.nextLine();
				System.out.println("scanning file. we are on line: "+lineWithError++);

				//pushes (, {, [, symbols onto stack
				for (int i = 0; i < line.length(); i++) {
					char ch = line.charAt(i);

					if (ch == '(' || ch == '{' || ch == '[') {
						symbols.push(ch);
					} 
					//pushes ), }, ], symbols onto stack
					else if (ch == ')' || ch == '}' || ch == ']') {
						processSymbols(symbols, ch);
					}
				}
			}
		}
		//checks if symbol is empty. if not, pass does not 
		System.out.println("[*] The Java source-code " +
			(symbols.isEmpty() ? "has" : "does not have") + " correct pairs.");	
		System.out.println("[*] Error on line: " + lineWithError);
	}
	

	private static void processSymbols(
			Stack<Character> stack, Character ch) {
		// Check if character exist
		if ((stack.peek() == '(' && ch == ')') ||
			 (stack.peek() == '[' && ch == ']') ||
			 (stack.peek() == '{' && ch == '}')) {
			stack.pop();	
		}
		//if not, exits and gets passed back to isMatch to finalize output.
		else if ((stack.peek() != '(' && ch == ')') ||
			 (stack.peek() != '[' && ch == ']') ||
			 (stack.peek() != '{' && ch == '}')) {

			System.exit(1);
		}
	}
}
