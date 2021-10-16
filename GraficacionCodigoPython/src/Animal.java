import java.awt.Font;
import java.io.FileNotFoundException;
import java.net.URL;

import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.ColorCube;

public class Animal  implements Runnable {

	
	
	private String palabra="";
	
	/**Creación de la hebra */
	private Thread animacion = new Thread(this);
	
	/**cordenadas iniciales de el acto 0 los demas actos dependeran de que acto se trata */
	private double ax= -0.5f;
	private double ay= -0.4f;
	private double az= -0.5f;	
	
	  private Vector3d tras =new Vector3d(ax,ay,az); /**vector inicial del acto 0*/
	  private BranchGroup obj = new BranchGroup();  /**el objeto a retornar*/
	  private  TransformGroup obtrns = new TransformGroup();  /**grupo de transformaciones*/
	  private Transform3D a3dTrans = new Transform3D();  /**transformaciones en 3D*/
	  private int acto;  /**determina que animación tendrá que realizar*/
	  private int avance;  /**auxiliar que se usara en la hebra*/
	  
	  private boolean spin = false;
	    private boolean noTriangulate = false;
	    private boolean noStripify = false;
	    private double creaseAngle = 60.0;
	    private URL filename = null;
	    private int anima=0;
	    private int delay=5;
	    private double ya=.000001;
	    private int acabe=0;
		private String dir="Perro";
		  
		
		public Animal(){
			
		}
		  /** Obtiene el nombre del modelo  */
		  public void inici() {
		        if (filename == null) {
		        	//System.err.println(Resources.getResource("./modelo/cube.obj"));
		       	 filename = Resources.getResource("./modelo/"+dir+".obj");
		           if (filename == null) {
		               System.err.println("modelo/cube.obj not found");
		               System.exit(1);
		           }
		        }
		   }	
		
		 /** Construye e inicializa un nuevo objeto */
		 public  BranchGroup animalreg(){
			 
			 		/** Permite la capacidad de escribir la información de transformación del objeto*/
			    	obtrns.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			    	
					tras = new Vector3d(ax,ay,az);
					a3dTrans.rotY(-Math.PI/1.80d);
				     	a3dTrans.setTranslation(tras);
					    System.err.println(a3dTrans);
					    Vector3d op = new Vector3d(.05,.05,.05);
					    a3dTrans.setScale(op);	
					    obtrns.setTransform(a3dTrans);

				/** Create the transform group node and initialize it to the
				 identity.  Enable the TRANSFORM_WRITE capability so that
				 our behavior code can modify it at runtime.  Add it to the
				 root of the subgraph. */

				int flags = ObjectFile.RESIZE;
				if (!noTriangulate) flags |= ObjectFile.TRIANGULATE;
				if (!noStripify) flags |= ObjectFile.STRIPIFY;
				ObjectFile f = new ObjectFile(flags, 
				  (float)(creaseAngle * Math.PI / 180.0));
				Scene s = null;
				try {
				  s = f.load(filename);
				}
				catch (FileNotFoundException e) {
				  System.err.println(e);
				  System.exit(1);
				}
				catch (ParsingErrorException e) {
				  System.err.println(e);
				  System.exit(1);
				}
				catch (IncorrectFormatException e) {
				  System.err.println(e);
				  System.exit(1);
				}
				  
				obtrns.addChild(s.getSceneGroup());
				obj.addChild(obtrns);
				return obj;
				 
			 }

		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
