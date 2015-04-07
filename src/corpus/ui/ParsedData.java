package corpus.ui;

import java.awt.BorderLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import corpus.Model;

@SuppressWarnings("serial")
public class ParsedData extends JPanel implements Observer {

	private Model m;
	private JTextArea text;

	public ParsedData(Model m) {
		this.m = m;
		setLayout(new BorderLayout());
		text = new JTextArea();

		JScrollPane jsp = new JScrollPane(text);

		add(jsp, BorderLayout.CENTER);
		update(null, null);
		m.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		text.setText(m.getParsedData());
	}

}
