package de.matt3o.bruchrechner.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

public class Window extends JFrame {
	private static final long serialVersionUID = 0L;
	private JTabbedPane tabbedPane;
	private TrimPanel trimPanel;

	public Window() {
		super ( "Bruchrechner" );
		
		setSize( 350, 200 );
		setLocation( 300, 300 );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JLabel copyright = new JLabel( " (c) Matteo Kloiber - alle Rechte vorhanden." );
		
		trimPanel = new TrimPanel( this );
		tabbedPane = new JTabbedPane();
				
		tabbedPane.addTab( "KŸrzer", trimPanel );
		tabbedPane.addTab( "Rechner!", new ReckonerPanel( this ) );

		
		add( BorderLayout.SOUTH, copyright );
		add( tabbedPane );
		setVisible( true );
	}

}
