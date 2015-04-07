package corpus.ui;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;

import corpus.Model;

@SuppressWarnings("serial")
public class TitleShower extends JLabel implements Observer {

	private Model m;

	public TitleShower(Model m) {
		this.m = m;
		m.addObserver(this);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		setText(m.getTitle());
	}
}