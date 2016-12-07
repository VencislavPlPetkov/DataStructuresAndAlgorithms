package swing;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

public class JavaBrowserHTML extends JFrame implements HyperlinkListener, ActionListener {

	public static void main(String[] args) {

		new JavaBrowserHTML("file:///C:/Users/Venci/Desktop/Java/Other%20Materials/Basic.html");

	}

	String defaultURL;

	JPanel toolPanel = new JPanel();

	JTextField tfTheURL = new JTextField(25);

	JEditorPane htmlPage;

	public JavaBrowserHTML(String defaultURL) {

		JFrame frame = new JFrame("Java Browser");

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.defaultURL = defaultURL;

		tfTheURL.addActionListener(this);

		tfTheURL.setText(defaultURL);

		toolPanel.add(tfTheURL);

		frame.add(toolPanel, BorderLayout.NORTH);

		try {

			htmlPage = new JEditorPane(defaultURL);

			htmlPage.addHyperlinkListener(this);

			htmlPage.setEditable(false);

			JScrollPane scroller = new JScrollPane(htmlPage);

			frame.add(scroller, BorderLayout.CENTER);

		} catch (IOException e) {

			e.printStackTrace();

		}

		frame.setSize(1200, 800);

		frame.setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String pageURL = "";

		if (e.getSource() == tfTheURL) {

			pageURL = tfTheURL.getText();
		} else {
			JOptionPane.showMessageDialog(JavaBrowserHTML.this, "Please enter a web address", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

		try {
			htmlPage.setPage(new URL(pageURL));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("You must always use: http://");
			;
		}

	}

	@Override
	public void hyperlinkUpdate(HyperlinkEvent e) {

		if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {

			try {
				htmlPage.setPage(e.getURL());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			tfTheURL.setText(e.getURL().toExternalForm());
		}

	}

}// End of class
