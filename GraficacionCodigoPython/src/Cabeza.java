import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class Cabeza extends JFrame {

	private JMenuBar menus;
	private int tamaño=17;
	private JTextArea areaTexto;
	private JButton cor,cop,peg,nue,gua,ab,acerca,ayuda;
	private JComboBox tFuente;
	private JToolBar barras;
	private Entorno entorno;
    private Grafico grafi;
    private GraphDraw frame;
	private Font areaFuente;
	
	
	
	int[] posicion;
	int cont = 0;
	
	public Cabeza()
	{
		//Te pasas cardona
	}
	
	/** Creación de los menús  */
	public void menus () {
		 menus = new JMenuBar();
		JMenu archivo= new JMenu("Archivo");
		JMenuItem nue= new JMenuItem("Nuevo", new ImageIcon("bin/images/hoja_6.gif"));
		nue.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				nuevo();
				}
			}
		);
		JMenuItem sal= new JMenuItem("Salir",new ImageIcon("bin/images/salir.gif"));
		sal.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					String d=JOptionPane.showInputDialog("Desea salir y guardar el archivo S/N");
					if (d.equals("s")) {
						guardar();
						System.exit(0);
					}
					else if(d.equals("n")) {
						System.exit(0);
					}
					else {
						JOptionPane.showMessageDialog(null,"Caracter invalido");
					}
				}
			}
		);

		JMenuItem abr= new JMenuItem("Abrir",new ImageIcon("bin/images/libro_1.gif"));
		abr.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					abrir ();
				}
			}
		);
		JMenuItem guar= new JMenuItem("Guardar",new ImageIcon("bin/images/guardar.gif"));
		guar.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					guardar ();
				}
			}
		);
		JMenu editar= new JMenu("Editar");
		JMenuItem cor= new JMenuItem("Cortar", new ImageIcon("bin/images/cut.gif"));
		cor.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cortar ();
				}
			}
		);
		JMenuItem cop= new JMenuItem("Copiar",new ImageIcon("bin/images/copiar_0.gif"));
		cop.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					copiar ();
				}
			}
		);
		JMenuItem peg= new JMenuItem ("Pegar",new ImageIcon("bin/images/paste.gif"));
		peg.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pegar ();
				}
			}
		);

		JMenuItem fuen= new JMenuItem("Tamaño de fuente",new ImageIcon("bin/images/hoja_2.gif"));
		fuen.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					tamaño_f();
				}
			}
		);
		JMenu about= new JMenu("Ayuda");
		JMenuItem ayu= new JMenuItem("Ayuda",new ImageIcon("bin/images/bombillo.gif"));
		ayu.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
				ayuda ();
				}
			}
		);
		JMenuItem acer= new JMenuItem("Acerca de...",new ImageIcon("bin/images/chulo.gif"));
		acer.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					acerca();
				}
			}
		);
		
		
		JMenu Graficos= new JMenu("Graficos");
		JMenuItem pedirdato= new JMenuItem("Obten linea",new ImageIcon("bin/images/chulo.gif")); 
		pedirdato.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pedir();
				}
			}
		);
		
		JMenuItem pruebco= new JMenuItem("Prueba de Codigo",new ImageIcon("bin/images/chulo.gif"));
		pruebco.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cod();
				}
			}
		);
		JMenuItem pruebanima= new JMenuItem("Prueba de Animacion",new ImageIcon("bin/images/chulo.gif"));
		pruebanima.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					anima();
				}
			}
		);
		
		/**Se añaden al menú desplegable de archivo */
		archivo.add(nue);
		archivo.add(sal);
		archivo.addSeparator();
		archivo.add(abr);
		archivo.add(guar);
		/**Se añaden al menun desplegable de editar */
		editar.add(cor);
		editar.add(cop);
		editar.add(peg);
		editar.addSeparator();
		editar.add(fuen);
		/** Se añaden al menú desplegable de Grafico */
		Graficos.add(pedirdato);
		Graficos.add(pruebco);
		Graficos.add(pruebanima);
		/** Se añaden al menu desplegable de about */
		about.add(ayu);	
		about.add(acer);
		
		/** Se añaden a la barra de menús*/
		menus.add(archivo);
		menus.add(editar);
		menus.add(Graficos);
		menus.add(about);
	}

	/** Creación de las barras del menú */
	public void barra () {
	
		barras= new JToolBar();

		nue= new JButton ();
		nue.setIcon(new ImageIcon("bin/images/hoja_6.gif"));
		nue.setMargin(new Insets(3,0,0,0));
		nue.setToolTipText("Nuevo");
		nue.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					nuevo ();
				}
			}
		);
		barras.add(nue);

		ab= new JButton();
		ab.setIcon(new ImageIcon("bin/images/libro_1.gif"));
		ab.setMargin(new Insets(2,0,0,0));
		ab.setToolTipText("Abrir");
		ab.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					abrir ();
				}
			}
		);
		barras.add(ab);

		gua= new JButton();
		gua.setIcon(new ImageIcon("bin/images/guardar.gif"));
		gua.setMargin(new Insets(2,0,0,0));
		gua.setToolTipText("Guardar");
		gua.addActionListener(
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					guardar ();
				}
			}
		);
		barras.add(gua);

		barras.addSeparator();

		cor= new JButton();
		cor.setIcon(new ImageIcon("bin/images/cut.gif"));
		cor.setMargin(new Insets(2,0,0,0));
		cor.setToolTipText("Cortar");
		cor.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					cortar ();
				}
			}
		);
		barras.add(cor);


		cop= new JButton();
		cop.setIcon(new ImageIcon("bin/images/copiar_0.gif"));
		cop.setMargin(new Insets(-3,0,0,0));
		cop.setToolTipText("Copiar");
		cop.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					copiar ();
				}
			}
		);
		barras.add(cop);

		peg= new JButton();
		peg.setIcon(new ImageIcon("bin/images/paste.gif"));
		peg.setMargin(new Insets(2,0,0,0));
		peg.setToolTipText("Pegar");
		peg.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					pegar ();
				}
			}
		);
		barras.add(peg);

		JButton del= new JButton();
		del.setIcon(new ImageIcon("bin/images/borrador.gif"));
		del.setMargin(new Insets(2,0,0,0));
		del.setToolTipText("BORRAR todo el texto");
		del.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					areaTexto.setText("");
				}
			}
		);
		barras.add(del);

		barras.addSeparator();

		ayuda= new JButton();
		ayuda.setIcon(new ImageIcon("bin/images/bombillo.gif"));
		ayuda.setMargin(new Insets(2,0,0,0));
		ayuda.setToolTipText("Ayuda");
		ayuda.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					ayuda ();
				}
			}
		);
		barras.add(ayuda);

		acerca= new JButton();
		acerca.setIcon(new ImageIcon("bin/images/chulo.gif"));
		acerca.setMargin(new Insets(5,2,0,0));
		acerca.setToolTipText("Acerca de...");
		acerca.addActionListener (
			new ActionListener () {
				public void actionPerformed (ActionEvent e) {
					acerca ();
				}
			}
		);
		barras.add(acerca);

		barras.addSeparator();

		tFuente= new JComboBox();
		tFuente.addItem("Tamaño Fuente");
		tFuente.addItem("10");
		tFuente.addItem("20");
		tFuente.addItem("30");
		tFuente.addItem("40");
		tFuente.addItem("50");
		tFuente.addItem("Personalizar");
		tFuente.setToolTipText("Tamaño de fuente");
		tFuente.addItemListener(
			new ItemListener () {
				public void itemStateChanged(ItemEvent e) {

					int elegido;
					elegido=tFuente.getSelectedIndex();
					switch (elegido) {

						case 1:
							areaFuente= new Font("Arial", Font.PLAIN, 10);
							areaTexto.setFont(areaFuente);
							break;

						case 2:
							areaFuente= new Font("Arial", Font.PLAIN, 20);
							areaTexto.setFont(areaFuente);
							break;

						case 3:
							areaFuente= new Font("Arial", Font.PLAIN, 30);
							areaTexto.setFont(areaFuente);
							break;

						case 4:
							areaFuente= new Font("Arial", Font.PLAIN, 40);
							areaTexto.setFont(areaFuente);
							break;

						case 5:
							areaFuente= new Font("Arial", Font.PLAIN, 50);
							areaTexto.setFont(areaFuente);
							break;
						case 6:
							tamaño=Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño de la fuente"));
							areaFuente= new Font("Arial", Font.PLAIN, tamaño);
							areaTexto.setFont(areaFuente);
							break;
					}
				}
			}
		);
		barras.add(tFuente);


		barras.addSeparator();

		//getContentPane().add(barras,BorderLayout.NORTH);
	}


	private void nuevo(){

		System.setProperty("sun.awt.noerasebackground", "true");

		   new Entorno();
		   java.awt.EventQueue.invokeLater(new Runnable()
		   {
		       public void run()
		       {
		           new Entorno().setVisible(true);
		       }
		   });
	}
	
	private void abrir () {
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result= fileChooser.showOpenDialog(this);
		if (result== JFileChooser.CANCEL_OPTION) return;
		File name= fileChooser.getSelectedFile();
		if(name.exists()) {
			if (name.isFile()) {
				try {
					BufferedReader input= new BufferedReader(new FileReader (name));
					StringBuffer buffer= new StringBuffer();
					String text;
					areaTexto.setText("");
					while ((text=input.readLine()) !=null)
						buffer.append(text+ "\n");
					areaTexto.append(buffer.toString());
				}
				catch (IOException ioException) {
					JOptionPane.showMessageDialog(null,"Error en el archivo", "Error",JOptionPane.ERROR_MESSAGE);
				}
			}
			else if (name.isDirectory ()) {
				String directory[] = name.list();
				areaTexto.append("\n\nContenido del directorio:\n");
				for (int i=0;i<directory.length; i++)
					areaTexto.append(directory [i]+"\n");
			}
			else {
				JOptionPane.showMessageDialog(null," No existe "," Error ",JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void guardar () {
		JOptionPane.showMessageDialog(null,"¡Por favor no olvide colocar la extension del archivo (*.txt)!");
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		int result= fileChooser.showSaveDialog(this);
		if (result== JFileChooser.CANCEL_OPTION) return;
			File name= fileChooser.getSelectedFile();
			try {
				PrintWriter output= new PrintWriter(new FileWriter( name));
				output.write(areaTexto.getText());
				output.close();
			}
			catch (IOException ioException) {
				JOptionPane.showMessageDialog(null,"Error en el archivo","Error",JOptionPane.ERROR_MESSAGE);
			}
	}

	private void cortar () {
		areaTexto.cut();
	}

	private void copiar () {
		areaTexto.copy();
	}

	private void pegar () {
		areaTexto.paste();
	}

	private void ayuda () {
		JOptionPane.showMessageDialog(null," Nuevo: Abre una nueva ventana\n Abrir: Abre un documento existente\n Guardar: Guarda el documento\n Salir: Salir del programa\n Cortar: ctrl+x\n Copiar: ctrl+c\n Pegar: ctrl+v\n Salir sin guardar: alt+F4");
	}

	private void tamaño_f () {
		tamaño=Integer.parseInt(JOptionPane.showInputDialog("Digite el tamaño de la fuente"));
		areaFuente= new Font("Arial", Font.PLAIN, tamaño);
		areaTexto.setFont(areaFuente);
	}

	private void acerca () {
		JOptionPane.showMessageDialog(null,"Python y los Objetos\nDesarrollado por: "
				+ " - Acosta Salinas Hector Miguel " + " - Cardona Becerra Luis Eduardo" 
				+ " - Rodriguez Salazar Isai" + " - Martinez Leyva Alma Alicia" + 
				" - Estrada López Ivonne Montserrat");
		JOptionPane.showMessageDialog(null,"Profesor: Dr. Perez Gonzalez Hector Gerardo"
				+ "Materia: Ingenieria de Software B" + "Semestre: 2019-2020/II");
		
	}
	
	/** Obtiene los datos del textarea, entonro y grafico */
	private void pedir()
	{
		Dato iWorld;
		
        iWorld = new Dato(entorno);
        iWorld.settexta(areaTexto);
        iWorld.setgrafi(grafi);
  //      iWorld.setSize( 50, 50 );
    //    iWorld.setLocation(50,50 );
        
		System.setProperty("sun.awt.noerasebackground", "true");
		   java.awt.EventQueue.invokeLater(new Runnable()
		   {
		       public void run()
		       {
		           iWorld.setResizable( false );
		    	   iWorld.setVisible(true);
		       }
		   });
		   System.out.println(areaTexto.getText());
	}
	
	private void cod()
	{
		frame.removeAll();
		Arbol arb = dameEstructura(areaTexto.getText()); //Obtiene el árbol a partir del codigo que tiene el textarea
		asignaPosicion(arb,300,400); //Asiga la posición del árbol en el panel 
		frame.setVisible(true); 
		BufferedImage pp= createImage(frame);
		grafi.caratula(pp);
		grafi.aniamar(areaTexto);
	}
	private void anima()
	{
		/*frame.removeAll();
		Arbol arb = dameEstructura(areaTexto.getText());
		asignaPosicion(arb,300,400);
		frame.setVisible(true);
		BufferedImage pp= createImage(frame);
		grafi.caratula(pp);*/
		grafi.prueba();
	}
	public BufferedImage createImage(GraphDraw panel) 
	{
	 int w = panel.getWidth();
	 int h = panel.getHeight();
	 BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
	 Graphics2D g = bi.createGraphics();
	 panel.paint(g);//Nota: se recomienda el método print
	 g.dispose();
	 return bi;
	 }

	public void setarea(JTextArea nu){ //Actualiza el textarea
		this.areaTexto = nu;
	}
	
	public JMenuBar getmenu(){ //Obtiene el menú
		return this.menus;
	}
	
	public JToolBar getbarra() //Obtiene las barras del menú
	{
		return barras;
	}
	
	public void setento(Entorno x) { //Actualiza el entorno 
		
		this.entorno=x;
	}
	
    public void setgrafi(Grafico c)    { //Actualiza el gráfico 
    	this.grafi=c;
    }
    
   
    public void setArbol(GraphDraw avl) { //Actualiza el árbol
    	this.frame = avl;
    }
    
    public Arbol dameEstructura(String programa) { //Obtiene la estructura del árbol a partir del código
    	boolean bandera = false;
    	boolean nom = true;

    	Arbol arbol = new Arbol(); //Crea el árbol
    	String iterador;
    	String padres = "";
    	String nombre = "";
    	String[] fathers; //Arreglo de strings 
    	int indice, actual = 0;
    	String [] lineas = programa.split("\n"); //Divide el programa en una matriz de subcadenas 
    	for(int i = 0; i<lineas.length; i++) {
    		nombre = "";
    		padres = "";
    		bandera = false;
    		nom = true;
    		indice = lineas[i].indexOf("class");
    		if(indice!=-1) {
    			iterador = lineas[i].replaceAll(" +", " ");
    			iterador = iterador.trim();
    			
    			for(int j = indice+6; j<iterador.length(); j++)
    			{
    				
    				if(iterador.charAt(j) ==')')
    					bandera = false;
    				if(bandera){
    					padres += iterador.substring(j, j+1);
    				}
    				else{
    					
    					if(nom){
    						if(iterador.charAt(j)!=' '&& iterador.charAt(j)!='('){
    							nombre += iterador.substring(j, j+1);
    						}
    						else {
    							nom = false;
    						}
    					}
    				}
    				if(iterador.charAt(j)=='(')
    					bandera = true;
    			}
    			Nodo nuevo = new Nodo(nombre);
    			arbol.add(nuevo);
    		}
    		else
    		{
    			
    		}
    		
    	}
    	actual = 0;
    	for(int i = 0; i<lineas.length; i++) {
    		nombre = "";
    		padres = "";
    		bandera = false;
    		nom = true;
    		indice = lineas[i].indexOf("class");
    		if(indice!=-1) {
    			iterador = lineas[i].replaceAll(" +", " ");
    			iterador = iterador.trim();
    			
    			for(int j = indice+6; j<iterador.length(); j++){
    				
    				if(iterador.charAt(j) ==')')
    					bandera = false;
    				if(bandera){
    					padres += iterador.substring(j, j+1);
    				}
    				else{
    					
    					if(nom){
    						if(iterador.charAt(j)!=' '&& iterador.charAt(j)!='('){
    							nombre += iterador.substring(j, j+1);
    						}
    						else {
    							nom = false;
    						}
    					}
    				}
    				if(iterador.charAt(j)=='(')
    					bandera = true;
    			}
    			
    			padres = padres.replaceAll(" ", "");
    			padres = padres.trim();
    			fathers = padres.split(",");
    			for(int j = 0; j<fathers.length; j++){
    				
    				for(Nodo nod : arbol){
    					
    					if(nod.nombre.equals(fathers[j]) ) {
    						Arista nueva = new Arista();
    						nueva.origen = arbol.get(actual);
    						nueva.destino = nod;
    						arbol.get(actual).padres.add(nueva);
    						
    						Arista inversa = new Arista();
    						inversa.origen = nod;
    						inversa.destino = arbol.get(actual);
    						nod.hijas.add(inversa);
    					}
    				}
    			}
    			actual++;
    		}
    		else
    		{
    			
    		}
    		
    	}
    	return arbol;
    }

    /** Imprime el grafo */
    private void imprimeGrafo(Arbol avl){ 
    	JFrame frame = new JFrame();
    	for(Nodo nod: avl)
    	{
    		JOptionPane.showMessageDialog(frame, "Nodo"+nod.nombre+" tiene de padres: ");
    		for(Arista aris : nod.padres)
    		{
    			JOptionPane.showMessageDialog(frame, "Padre: "+aris.destino.nombre);
    			
    		}
    		for(Arista aris : nod.hijas)
    		{
    			JOptionPane.showMessageDialog(frame, "Hija: "+aris.destino.nombre);
    			
    		}
    	}
    }

    //Asigna la posición del árbol 
     private void asignaPosicion(Arbol arb, int alto, int ancho) {
    	int contraiz = 0;
    	int contHojas = 0;
    	int contInter = 0;
    	int niveles = 0;
    	int profundidad = 0;
    	for(Nodo nod : arb) {
    		if(nod.padres.size()==0) {
    			nod.tipo = 1;
    			contraiz++;
    			int temp = calculaProfundidad(nod,0);
    			limpiaArbol(arb);
    			if(temp>profundidad)
    				profundidad = temp;
    		}
    		else {
    			if(nod.hijas.size()==0) {
    				nod.tipo = 3;
    				contHojas++;
    			}
    			else {
    				nod.tipo = 2;
    				contInter++;
    			}
    		}
    	}
    	posicion = new int[profundidad];
    	int posi = (ancho/2);
    	posicion[0] = (ancho/(contraiz+1))-70;
    	for(int i = 1; i<posicion.length; i++) {
    		posicion[i] = 0;
    	}
    	for(Nodo nod : arb) {
    		if(nod.tipo == 1) {
    			creaArbol(nod, 0, 90);
    		}
    	}
    	limpiaArbol(arb);
    	
    	uneAristas(arb);
    	
    	Nodo padre = new Nodo("Object");
    	padre.pos = arb.size();
    	arb.add(padre);
    	frame.addNode("Object", posi, 15);
    	for(Nodo nod : arb) {
    		if(nod.tipo == 1) {
    			Arista trans = new Arista();
    			trans.origen = nod;
    			trans.destino = padre;
    			nod.padres.add(trans);
    			
    			Arista aris = new Arista();
    			trans.origen = padre;
    			trans.destino = nod;
    			nod.hijas.add(aris);
    			
    			frame.addEdge(nod.pos, padre.pos);
    		}
    	}
    	
    	
    	
    }
     
    /**Calcula la profundidad del árbol */
    private int calculaProfundidad(Nodo aux, int cont) {
    	cont++;
    	int max;
    	for(Arista aris : aux.hijas){
    		if(aris.destino.visitado == false) { //Si el nodo es visitado 
    			aris.destino.visitado = true;
    			max = calculaProfundidad(aris.destino,cont);
    			if(max>=cont)
    				cont = max;
    		}
    		
    	}
    	return cont;
    }
   
    //Limpia árbol
    private void limpiaArbol(Arbol arb) {
    	for(Nodo nod : arb) {
    		nod.visitado = false; //El nodo visitado cambia ahora es falso
    	}
    }

    //Creación del árbol 
    private void creaArbol(Nodo nod, int profundidad, int distPad) {
    	posicion[profundidad] = posicion[profundidad]+90;
    //	frame
    	frame.addNode(nod.nombre, posicion[profundidad],distPad);
    	nod.pos = cont;
    	cont++;
    	profundidad++;
    	distPad += distPad;
    	for(Arista ar : nod.hijas) {
    		if(ar.destino.visitado==false) {
    		ar.destino.visitado = true;
    		creaArbol(ar.destino, profundidad,distPad);
    		}
    	}
    }

    //Une las aristas al nodo 
    private void uneAristas(Arbol arb) {

    	for(Nodo nod : arb) {
    		for(Arista aris : nod.hijas) {
    			frame.addEdge(nod.pos, aris.destino.pos); //Añade la arista conectando al nodo de la posición obtenida con el nodo destino
    		}
    	}
    }
}

