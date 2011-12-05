package de.matt3o.bruchrechner.gui;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DividerPanel extends JPanel {
	private static final long serialVersionUID = 3721590177597941178L;
	
	@Override
	protected void paintComponent( Graphics g ) {
    	super.paintComponent( g );
    	
    	g.drawLine( 5, 10, 5, 55 );
  }
}
