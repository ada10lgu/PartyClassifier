package corpus.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import corpus.Model;

@SuppressWarnings("serial")
public class URLSelector extends JTextField implements ActionListener {
	private Model m;

	public URLSelector(Model m) {
		super("http://www.riksdagen.se/sv/Debatter--beslut/Interpellationsdebatter1/Debatt/?did=H210392");
		this.m = m;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.setURL(getText());
	}
}
