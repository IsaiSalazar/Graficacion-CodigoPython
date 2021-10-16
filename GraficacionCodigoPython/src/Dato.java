
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*; 
class Dato extends JDialog implements ActionListener  { 
  
    /** Panel de texto */ 

    private javax.swing.JPanel f; 
    private javax.swing.JScrollPane scrollPan;
    private JComboBox combo;
    private Grafico grafi;
    
    // JButton 
    private JButton b; 
  
    //Etiqueta para mostrar texto
    private JLabel l; 
  
    // textarea 
    private JTextArea jt; 
  
    // Constructor por default
    Dato(Entorno x) 
    { 
    	super(x,true);
    	ini();
    } 
  
    /** Crea e inicializa el panel y los botones */
    private void ini() {
    	
   	 f = new javax.swing.JPanel(); 
	    f.setLayout(new java.awt.GridBagLayout());
	    scrollPan = new javax.swing.JScrollPane();
	    scrollPan.setViewportView(f);
  // Crea la etiqueta para mostrar el texto
  l = new JLabel("Selecciona el\n texo a mostrar"); 

  // Crea un nuevo butón  
  b = new JButton("Envia"); 

  


  // addActionListener al boton  
  b.addActionListener(this); 

  // Crea el textarea, especifica las filas y columnas
  jt = new JTextArea(10, 10); 

  JPanel p = new JPanel(); 

  // Añade el boton y el textarea al panel

  f.add(l); 


  // Establece el tamaño del panel 
  f.setSize(300, 300); 

  getContentPane().add(f, java.awt.BorderLayout.CENTER);
  
 
  java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
  setBounds((screenSize.width-800), (screenSize.height-550),350,350);
    }

  
    // Si se presiona el botón 
    public void actionPerformed(ActionEvent e) 
    { 
        String s = e.getActionCommand(); 
        if (s.equals("Envia")) { 
            // Establecer el texto de la etiqueta al texto del campo
           // l.setText(jt.getText()); 
        	String itemSeleecionado = (String)combo.getSelectedItem();
        	grafi.settexto(itemSeleecionado);
        	//l.setText(itemSeleecionado); 
            this.dispose();
        } 
    } 
    
    /** Actualiza el textarea */
    public void settexta(JTextArea are)
    {
    	this.jt=are;
    	  combo = new JComboBox();
    	  String ares = jt.getText();
    	  String [] lineas = ares.split("\n");
    	  for(int x=0;x< lineas.length;x++)
    	  combo.addItem(lineas[x]);
    	  f.add(combo);
    	  f.add(b);
    }
    
    /** Actualiza grafico */
    public void setgrafi(Grafico c)    {
    	this.grafi=c;
    }
    
} 