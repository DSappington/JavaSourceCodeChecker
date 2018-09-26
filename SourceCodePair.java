import java.io.File;
import java.io.IOException;


import javafx.application.Application;


import javafx.stage.Stage;


public class SourceCodePair extends Application {

	static File file;

	public static void main(String[] args) throws IOException {
		if (args.length == 1) {						//checks if file is passed as argument
			file = new File(args[0]);
		} else if (args.length != 1) {			// argument gets passed to JavafX FileChooser
			System.out.println("No argument was passed. Default to file selection");
			try {
				Application.launch(args);
			} catch (Exception e){
				System.out.println("Still no file was passed. Application exiting.");
			}
		} 
	}
	

	
	public void start(Stage primaryStage) throws Exception {
		SourceCodePanel panel = new SourceCodePanel();
		panel.isMatch(panel.FileOpener().getAbsoluteFile());

	}
}