
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


//Esta clase a grande resgos es para crear e inizialisar el arbol de clases en que se usa 
//Como referncia en el Class realm
public class GraphDraw extends JInternalFrame {
    int width;
    int height;

    ArrayList<Node> nodes;
    ArrayList<edge> edges;

    public GraphDraw() { //Metodo Constructor
    	
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	nodes = new ArrayList<Node>();
	edges = new ArrayList<edge>();
	width = 30;
	height = 30;
    }

    public GraphDraw(String name) { //Construct con label
	this.setTitle(name);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	nodes = new ArrayList<Node>();
	edges = new ArrayList<edge>();
	width = 30;
	height = 30;

    }

    //Clase que representa los nodos de nuestro arbol de clase
    class Node {
	int x, y;
	String name;
	
	//Constructo de nodos de arbol de clases
	public Node(String myName, int myX, int myY) {
	    x = myX;
	    y = myY;
	    name = myName;
	}
    }
    
    //Clase que representa las aritas en nuestro arbol de clases
    class edge {
	int i,j;
	
	//Constructor de aristas de arbol de clases
	public edge(int ii, int jj) {
	    i = ii;
	    j = jj;	    
	}
    }
    
    //Metodo para añadir nodos al arbol
    public void addNode(String name, int x, int y) { 
	//add a node at pixel (x,y)
	nodes.add(new Node(name,x,y));
	this.repaint();
    }
    
    //Metodo para añadir aristas al arbol
    public void addEdge(int i, int j) {
	//add an edge between nodes i and j
	edges.add(new edge(i,j));
	this.repaint();
    }
    
    //Metodo que ira dibujando el arbol en una ventana de Graphics
    public void paint(Graphics g) { // draw the nodes and edges
	FontMetrics f = g.getFontMetrics();
	int nodeHeight = Math.max(height, f.getHeight());
	g.setColor(Color.blue);
	for (edge e : edges) {
	    g.drawLine(nodes.get(e.i).x, nodes.get(e.i).y,
		     nodes.get(e.j).x, nodes.get(e.j).y);
	}

	for (Node n : nodes) {
	    int nodeWidth = Math.max(width, f.stringWidth(n.name)+width/2);
	    g.setColor(Color.white);
	    g.fillOval(n.x-nodeWidth/2, n.y-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    g.setColor(Color.blue);
	    g.drawOval(n.x-nodeWidth/2, n.y-nodeHeight/2, 
		       nodeWidth, nodeHeight);
	    g.setColor(Color.black); 
	    g.drawString(n.name, n.x-f.stringWidth(n.name)/2,
			 n.y+f.getHeight()/2);
	}
    }
}

