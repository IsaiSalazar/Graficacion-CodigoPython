import java.awt.List;
import java.util.ArrayList;

/** Crea los nodos del árbol */
public class Nodo {
	boolean visitado = false;
	public String nombre;
	int tipo;
	int pos;
	public ArrayList<Arista> padres = new ArrayList<Arista>(); /** Arreglo de Aristas de nodos padres */
	public ArrayList<Arista> hijas = new ArrayList<Arista>();  /** Arreglo de Aristas de nodos hijos */
	public Nodo(String nom) {
		this.nombre = nom;
	}
	

}
