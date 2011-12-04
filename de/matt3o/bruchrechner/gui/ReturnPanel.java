package de.matt3o.bruchrechner.gui;

import java.awt.BasicStroke;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import de.matt3o.bruchrechner.Fraction;
import de.matt3o.bruchrechner.FractionException;

public class ReturnPanel extends JPanel {
	private static final long serialVersionUID = -3649604550504974672L;
	private Fraction fraction;
	private boolean hasReturn;

	public ReturnPanel() throws FractionException {		
		//DEBUG:
		this( new Fraction( 20, 30 ) );
		return;
		
		//FOR RUNTIME:
//		hasReturn = false;
	}
	
	@Deprecated
	public ReturnPanel( int denominator, int numerator ) throws FractionException {
		this( new Fraction( numerator, denominator) );
	}
	
	public ReturnPanel( Fraction fraction ){
		this.hasReturn = true;
		this.fraction = fraction;
		
		addComponenten();
	}
	
	private void addComponenten(){
		if ( !hasReturn() ){
			return;
		}
		
		JPanel panel1 = new JPanel();
		JPanel panel2 = new JPanel(){
			private static final long serialVersionUID = 1994054411264417867L;

			@Override
			protected void paintComponent( Graphics g ){
				super.paintComponent( g );
				
				if ( g instanceof Graphics2D ){
					Graphics2D g2 = ( Graphics2D ) g;
					g2.setStroke( new BasicStroke(2) );
					g.drawLine( 0, 26, 500000, 26 );
				} else {
					g.drawLine( 0, 26, 500000, 26 );
				}
			}
		};
		panel2.setLayout( new GridLayout( 2, 1 ) );
						
		JLabel numeratorLabel = new JLabel( "" + fraction.getNumerator() );
		JLabel denominatorLabel = new JLabel( "" + fraction.getDenominator() );
		
		JPanel denominatorPanel = new JPanel();
		JPanel numeratorPanel = new JPanel();
		
		denominatorLabel.setOpaque( false );
		denominatorPanel.setOpaque( false );
		denominatorPanel.setLayout( new FlowLayout() );
		
		numeratorLabel.setOpaque( false );
		numeratorPanel.setOpaque( false );
		numeratorPanel.setLayout( new FlowLayout() );
		
		panel2.setOpaque( false );
		
		numeratorPanel.add( numeratorLabel );
		denominatorPanel.add( denominatorLabel );
		
		panel2.add( numeratorPanel );
		panel2.add( denominatorPanel );

		panel1.add( panel2 );
		add( panel2 );
	}
	
	public boolean hasReturn() {
		return hasReturn;
	}
	
	public void setReturn( boolean hasReturn ){
		this.hasReturn = hasReturn;
	}
	

}
