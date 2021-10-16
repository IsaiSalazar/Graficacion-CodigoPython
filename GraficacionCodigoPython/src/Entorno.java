import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.Label;

import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;

import com.sun.j3d.utils.applet.MainFrame;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.universe.SimpleUniverse;


import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Entorno extends javax.swing.JFrame implements java.awt.event.ActionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; // Pregunta para que sirve 
	private int tamaño=17;
	private JTextArea areaTexto;
	private Font areaFuente;
	private JScrollPane scroll;

    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JPanel panel;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JSplitPane splitPane;
	private Cabeza her;
	private NumeroLinea nume;
	
	/** Constructor*/
	public Entorno()
	{
		
		super("Python y los Objetos");
		her= new Cabeza();

		Iniciocom();

	}
	
	/** Creación de los elementos dl entorno */
	private void Iniciocom()
	{
	    
	    splitPane = new javax.swing.JSplitPane(); // Crea un elemento splitpanel
	    scrollPane = new javax.swing.JScrollPane();

	    panel = new javax.swing.JPanel();
	    desktopPane = new javax.swing.JDesktopPane();

	    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    splitPane.setDividerLocation(323);
	    splitPane.setDividerSize(8);
	    splitPane.setContinuousLayout(true);
	    splitPane.setOneTouchExpandable(true);
	    panel.setLayout(new java.awt.GridBagLayout());

		areaFuente= new Font("Arial", Font.PLAIN, tamaño); //SE CREA EL TIPO DE FUENTE
		areaTexto= new JTextArea(27, 21); //SE CREA EL AREA PARA EL TEXTO
		areaTexto.setFont(areaFuente); //ASIGNA EL TIPO DE FUENTE
		her.setarea(this.areaTexto); //ASIGNA EL AREA PARA EFECTO DEL MENU EN LA CLASE CABEZA
        her.setento(this);	
        
		her.menus(); //SE ACTIVA EL METODO QUE SE ENCARGA DE ASIGNAR LOS METODOS QUE INTERACTURAN CON EL AREA TEXTO
		JMenuBar menus = her.getmenu(); // SE CREA UNA VARIABLE TIPO MENU Y SE ASIGNA LOS MENUS PREVIMENTE CREADOS
		setJMenuBar(menus); //SE ASIGNA AL CUADRO DE TRABAJO
	    nume = new NumeroLinea(areaTexto);
		scroll= new JScrollPane(areaTexto);
		scroll.setRowHeaderView(nume);
		scroll.setLocation(0,0);
		panel.add(scroll);
	    scrollPane.setViewportView(panel);

	    splitPane.setTopComponent(scrollPane);
	    splitPane.setEnabled( false );
	    desktopPane.setBackground(new java.awt.Color(255, 255, 255));
	    desktopPane.setPreferredSize(new java.awt.Dimension(300, 300));
	    splitPane.setRightComponent(desktopPane);
	    getContentPane().add(splitPane, java.awt.BorderLayout.CENTER);
	    
	    her.barra(); // Manda llamar a la método barra para que cree una barra mas interactiva con el usuario
		getContentPane().add(her.getbarra(),BorderLayout.NORTH); // Agrega la barra al entorno de trabajo
		//getContentPane().add(scroll,BorderLayout.CENTER);
		
		/** Asignacion de tamaño de ventana */
	    java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
	    setBounds(0, (screenSize.height-733)/2,1350, 650); 
	    Grafico iWorld;
        iWorld = new Grafico( );
        iWorld.setSize( 610, 560 );
        iWorld.setLocation(0,0 );
        iWorld.setResizable( false );
        desktopPane.add( iWorld );
        iWorld.setVisible(true);	
        her.setgrafi(iWorld);
        
        GraphDraw frame = new GraphDraw("Arbol de herencia");

		frame.setSize(600,800);
		frame.setLocation(611, 100);
		frame.setVisible(false);
		desktopPane.add( frame );
		her.setArbol(frame);
		
	}	
	
	public static void main( String[] args ) 
	{
		System.setProperty("sun.awt.noerasebackground", "true");

		 Entorno nuevo =  new Entorno();
		   java.awt.EventQueue.invokeLater(new Runnable()
		   {
		       public void run()
		       {
		    	   nuevo.setResizable(false);
		    	   nuevo.setVisible(true);
		       }
		   });
		   
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
