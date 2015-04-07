package corpus.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import corpus.Model;

@SuppressWarnings("serial")
public class CorpusUI extends JFrame {

	
	public CorpusUI(){
		super("Data fetcher");
		setSize(500, 300);
	
		
		Model m = new Model();
		
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(2, 1));
		top.add(new URLSelector(m));
		top.add(new TitleShower(m));
		
		JPanel center = new JPanel();
		center.setLayout(new GridLayout(1, 2));
		center.add(new SourceCode(m));
		center.add(new ParsedData(m));
		
		JPanel bottom = new JPanel();
		bottom.add(new SaveButton(m));
		
		add(center,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
		add(bottom,BorderLayout.SOUTH);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		
		
		
		new CorpusUI();
	}
	
	
	
}
