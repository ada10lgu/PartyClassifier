package corpus.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import corpus.Model;

@SuppressWarnings("serial")
public class SaveButton extends JButton implements ActionListener {

	private Model m;

	public SaveButton(Model m) {
		super("Save");
		this.m = m;
		addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		m.save();
	}

}
