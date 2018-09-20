import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.*;

import touchscreenAnalyzer.AnalyzerController;

/**
 * The Main class acts as a placeholder for information sent from a user interface into the analyzer package.
 * @author Carlos May
 *
 */
public class Main extends Frame implements ActionListener, WindowListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Label lblCount;    // Declare a Label component 
	private Label resultPathLabel;
	   private TextField tfCount; // Declare a TextField component 
	   private TextField resultText;
	   private Button btnCount;   // Declare a Button component
	   private int count = 0;     // Counter's value
	
	public Main() {
	   this.setLayout(new FlowLayout());
	         // "super" Frame, which is a Container, sets its layout to FlowLayout to arrange
	         // the components from left-to-right, and flow to next row from top-to-bottom.
	 
	      this.lblCount = new Label("Enter path of folder containing raw data.");  // construct the Label component
	      this.add(lblCount);                    // "super" Frame container adds Label component
	 
	      this.tfCount = new TextField("C:\\Users\\Carlos May\\eclipse-workspace\\Touchscreen-Analyser\\Example_Data", 10); // construct the TextField component with initial text
	      this.tfCount.setEditable(true);       // set to read-only
	      this.add(tfCount);                     // "super" Frame container adds TextField component
	      
	      this.resultPathLabel = new Label("Enter path and folder name for results");
	      this.add(resultPathLabel);
	      
	      
	      this.resultText = new TextField("C:\\Users\\Carlos May\\eclipse-workspace\\Touchscreen-Analyser\\results.csv", 10);
	      this.resultText.setEditable(true);
	      this.add(resultText);
	 
	      this.btnCount = new Button("Analyze!");   // construct the Button component
	      this.add(btnCount);                    // "super" Frame container adds Button component
	 
	      this.btnCount.addActionListener(this);
	      
	      this.addWindowListener(this);
	         // "btnCount" is the source object that fires an ActionEvent when clicked.
	         // The source add "this" instance as an ActionEvent listener, which provides
	         //   an ActionEvent handler called actionPerformed().
	         // Clicking "btnCount" invokes actionPerformed().
	 
	      this.setTitle("Touchscreen Analyzer GUI");  // "super" Frame sets its title
	      this.setSize(500, 200);        // "super" Frame sets its initial window size
	 
	      // For inspecting the Container/Components objects
	      // System.out.println(this);
	      // System.out.println(lblCount);
	      // System.out.println(tfCount);
	      // System.out.println(btnCount);
	 
	      this.setVisible(true);         // "super" Frame shows
	 
	      // System.out.println(this);
	      // System.out.println(lblCount);
	      // System.out.println(tfCount);
	      // System.out.println(btnCount);
	      

	}

	public static void main(String[] args) {
		
		Main app = new Main();

		
		//DEBUG: for now args are replaces by assignment
		args = new String[2];
		args[0] = "C:\\Users\\Carlos May\\eclipse-workspace\\Touchscreen-Analyser\\Example_Data";
		args[1] = "CPT";
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		
		//send information into the Analyzer Package
		AnalyzerController.analyze(tfCount.getText(), this.resultText.getText());
		
		
		
	}
	
	
	@Override
	   public void windowClosing(WindowEvent evt) {
	      System.exit(0);  // Terminate the program
	   }

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
