package de.matt3o.bruchrechner.gui;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import de.matt3o.bruchrechner.Fraction;

public class ReckonerPanel extends JPanel {
	private static final long serialVersionUID = 5312719316647891733L;
	private JPanel operationPanel;
	private JPanel returnPanel;

	public ReckonerPanel() {
//		setOpaque( false );
		
		setLayout( new BorderLayout() );
		returnPanel = new JPanel();
		operationPanel = new OperationPanel();
		
		reloadReturnPanel();
	}
	
	public void reloadReturnPanel(){
		JPanel calculatePanel = new JPanel();
		JPanel numberatorPanel = new JPanel();
		calculatePanel.setLayout( new BoxLayout( calculatePanel, BoxLayout.X_AXIS ) );
		
		numberatorPanel.add( new CalculatePanel( false ) );
		numberatorPanel.add( operationPanel );
		numberatorPanel.add( new CalculatePanel( false ) );
		
		try{
		calculatePanel.add( numberatorPanel );
		calculatePanel.add( new DividerPanel() );
		calculatePanel.add( new ReturnPanel( new Fraction( new Double( Math.random()*402312 ).intValue(), 23 ) ) );
		} catch( Exception e ){
			e.printStackTrace();
		}
		
		add( BorderLayout.NORTH, calculatePanel );
	}
}
