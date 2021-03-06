
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.Scene;
import java.io.FileNotFoundException;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.net.MalformedURLException;
import java.net.URL;
import com.sun.j3d.utils.behaviors.vp.*;
import java.io.FileNotFoundException;
import javax.media.j3d.Alpha;
import javax.media.j3d.AmbientLight;
import javax.media.j3d.Appearance;
import javax.media.j3d.Background;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Font3D;
import javax.media.j3d.FontExtrusion;
import javax.media.j3d.GraphicsConfigTemplate3D;
import javax.media.j3d.ImageComponent2D;
import javax.media.j3d.Material;
import javax.media.j3d.RotationInterpolator;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Text3D;
import javax.media.j3d.Texture;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.swing.JInternalFrame;


//Clase que uilizamos para implmentar objetos y cubos en el mundo grafico
public class Cube implements Runnable {

	int num=0;
	private double ax= -.80f;
	private double ay=- 0.4f;
	private double az= -0.65f;	
	private double x0=0,z0=0;
    private boolean spin = false;
    private boolean noTriangulate = false;
    private boolean noStripify = false;
    private double creaseAngle = 60.0;
    private URL filename = null;
	private Thread animacion2 = new Thread(this);//hebra
	private Vector3d tras =new Vector3d(ax,ay,az);//vector inicial del acto 0
	private BranchGroup obj = new BranchGroup();//el objeto a retornar
	private  TransformGroup obtrns = new TransformGroup();	//grupo de transformaciones
	private Transform3D a3dTrans = new Transform3D(); 	//transformaciones en 3D
	private int acto;//determina que animacion tendra que realizar	
	private int avance;//auxiliar que se usara en la hebra
	private int delay=5;
	private int acabe=0;
	private String palabra="";
	TransformGroup objTrans = new TransformGroup();
	
    //Metodo constructor
	public Cube() {
		
		inici();
	}
	//Metodo constructor
	public Cube(int x) {
		
	}
	//Metodo set para establecer coordenadas
	public void setcord(double x,double y,double z){
		ax=x;
		ay=y;
		az=z;
	}
	
	//Metodo set en el que solo ocupamos establecer dos coordenadas
	public void setcordenadasan(double x,double z)
	{
		x0=x;
		z0=z;
	}
	
	//Metodo get para obtener una cierta coordenada de x
	public double getx0()
	{
		return this.x0;
	}
	
	//Metodo get para obtener una cierta coordenada de x
	public double getz0()
	{
		return this.z0;
	}
	
	    //Metodo que ayuda a establecer el nombre del archivo obj del cubo
		public void inici() {
	
        if (filename == null) {
        	//System.err.println(Resources.getResource("./modelo/cube.obj"));
       	 filename = Resources.getResource("./modelo/cube.obj");
           if (filename == null) {
               System.err.println("modelo/cube.obj nots found");
               System.exit(1);
           }
	}
	}
	 
	//Metodo para crear el cubo que representa la plataforma del ObjectRealm
	 public  BranchGroup cubo1(){
			BranchGroup objRoot = new BranchGroup();

	        TransformGroup objScale = new TransformGroup();
	        Transform3D t3d = new Transform3D();
	        Transform3D rotate = new Transform3D();
            Transform3D tempRotate = new Transform3D();
	        	           rotate.rotX(Math.PI/1.0d);
         tempRotate.rotY(Math.PI/1.80d);
         Matrix3d n = new Matrix3d();
         Vector3d op = new Vector3d(.5,.05,.5);
         tempRotate.setScale(op);
         Vector3d op2 = new Vector3d(-.5,.6,1);
         tempRotate.setTranslation(op2);
          rotate.mul(tempRotate);
        TransformGroup objRotate = new TransformGroup(rotate);

         objRoot.addChild(objRotate);
	        
         t3d.mul(rotate);
	        objScale.setTransform(t3d);
	      
	        objRoot.addChild(objScale);

	TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
					
		
		objScale.addChild(objTrans);

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
		  
		objTrans.addChild(s.getSceneGroup());
		
       
     
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

	        Color3f bgColor = new Color3f(0.05f, 0.05f, 0.5f);
	        Background bgNode = new Background(bgColor);
	        bgNode.setApplicationBounds(bounds);
	        objRoot.addChild(bgNode);

		return objRoot;
		 
	 }

	//Metodo para crear el cubo que representa la plataforma del main
	 public  BranchGroup cubo2(){
			BranchGroup objRoot = new BranchGroup();

	        TransformGroup objScale = new TransformGroup();
	        Transform3D t3d = new Transform3D();
	        Transform3D rotate = new Transform3D();
         Transform3D tempRotate = new Transform3D();
	        	           rotate.rotX(Math.PI/1.0d);
      tempRotate.rotY(Math.PI/1.80d);
      Matrix3d n = new Matrix3d();
      Vector3d op = new Vector3d(.2,.05,.2);
      tempRotate.setScale(op);
      Vector3d op2 = new Vector3d(.5,.6,.5);
      tempRotate.setTranslation(op2);
       rotate.mul(tempRotate);
     //  rotate.mul(objScale);
     TransformGroup objRotate = new TransformGroup(rotate);
    
      //objRotate.addChild(new ColorCube(0.4));
      objRoot.addChild(objRotate);
	        
      t3d.mul(rotate);
	        objScale.setTransform(t3d);
	         
	        objRoot.addChild(objScale);

	TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
					
		
		objScale.addChild(objTrans);

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
		  
		objTrans.addChild(s.getSceneGroup());
		
    
  
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);


	      
	        // Set up the background
	        Color3f bgColor = new Color3f(0.05f, 0.05f, 0.5f);
	        Background bgNode = new Background(bgColor);
	        bgNode.setApplicationBounds(bounds);
	        objRoot.addChild(bgNode);

		return objRoot;
		 
	 }
      
	//Metodo para crear el cubo que representa la plataforma del Class Realm
	 public  BranchGroup cubo3(){
			BranchGroup objRoot = new BranchGroup();


	        TransformGroup objScale = new TransformGroup();
	        Transform3D t3d = new Transform3D();

	        Transform3D rotate = new Transform3D();
      Transform3D tempRotate = new Transform3D();
	        	           rotate.rotX(Math.PI/1.0d);
   tempRotate.rotY(Math.PI/1.80d);
   Matrix3d n = new Matrix3d();
   Vector3d op = new Vector3d(.01,1,1);
   tempRotate.setScale(op);
   Vector3d op2 = new Vector3d(-.01,-.5,2);
   tempRotate.setTranslation(op2);
    rotate.mul(tempRotate);
  //  rotate.mul(objScale);
  TransformGroup objRotate = new TransformGroup(rotate);
 
   //objRotate.addChild(new ColorCube(0.4));
   objRoot.addChild(objRotate);
	        
   t3d.mul(rotate);
	        objScale.setTransform(t3d);
	        
	        objRoot.addChild(objScale);

	TransformGroup objTrans = new TransformGroup();
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
					
		
		objScale.addChild(objTrans);

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
		  
		objTrans.addChild(s.getSceneGroup());
		
 

		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);


	      
	        // Set up the background
	        Color3f bgColor = new Color3f(0.05f, 0.05f, 0.5f);
	        Background bgNode = new Background(bgColor);
	        bgNode.setApplicationBounds(bounds);
	        objRoot.addChild(bgNode);

		return objRoot;
		 
	 }
	 
	 //Metodo que da el efecto de animar pero en realidad esta estableciendo nuevas transformacion
	 //en el cubo que se desea animar
	 public  BranchGroup anima(){
		 obtrns.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			tras = new Vector3d(ax,ay,az);
			a3dTrans.rotY(-Math.PI/1.80d);
		     	a3dTrans.setTranslation(tras);
			    System.err.println(a3dTrans);
			    Vector3d op = new Vector3d(.1,.05,.1);
			    a3dTrans.setScale(op);	
			    obtrns.setTransform(a3dTrans);

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

	    //Metodo para iniciar una hebra
		public void inicia(){
	    	animacion2.start();
	    	System.out.print(num);
	    num++;	
	    }
		
		//Metodo para detener una hebra
	    public void deten(){
	    	animacion2.stop();
	    }
	    
	    //Meto get para obtener la varibale acabe
		public int acab()
		{
			return this.acabe;
		}
		
		//Metodo para establece una letra la cual se quiera agregar al mundo grafico
		public void  setpalabr(String pal) {
			this.palabra=pal;
		}
		
        
		public  BranchGroup letrasCubo(BranchGroup objRoot){
	          
          
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ); 
            Transform3D trans = new Transform3D();
            
            trans.rotX(-70);

 

            Vector3d vector =new Vector3d(ax-.1, ay+.06, az);
            
            
            trans.setTranslation(vector);
            System.err.println(trans);
            Vector3d op = new Vector3d(.08,.1,.1);//Tama?o
            trans.setScale(op);    
            
                        
            objTrans.setTransform(trans);
            
            Font3D f3d = new Font3D(new Font("dialog", Font.PLAIN, 1),
       		     new FontExtrusion());
            
            Text3D text = new Text3D(f3d, palabra, //varEm atributo de clase de tipo string
                     new Point3f( 0f, -0.f, 0f));

 

            Shape3D sh = new Shape3D();
            Appearance app = new Appearance();
            Material mm = new Material();
            Color3f color1 = new Color3f (0.0f, 0.0f, 1.0f);
            
            
            mm.setLightingEnable(true);
            mm.setDiffuseColor(color1);
            
            app.setMaterial(mm);
            sh.setGeometry(text);
            sh.setAppearance(app);

 

            objTrans.addChild( sh );
            objRoot.addChild(objTrans);
                                    
            return objRoot;
         
      
      }
	
		//Metodo para a?adir letras o palabras al mundo grafico pero con parametros de coordenadas de posicion
		public  BranchGroup letrasCubo(BranchGroup objRoot, float corX, float corY, float corZ){
	          
            TransformGroup objTrans = new TransformGroup();
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ); 
            Transform3D trans = new Transform3D();
            
            trans.rotX(-70);

 

            Vector3d vector =new Vector3d(corX, corY, corZ);
            
            
            trans.setTranslation(vector);
            System.err.println(trans);
            Vector3d op = new Vector3d(.08,.1,.1);//Tama?o
            trans.setScale(op);    
            
                        
            objTrans.setTransform(trans);
            
            Font3D f3d = new Font3D(new Font("dialog", Font.PLAIN, 1),
       		     new FontExtrusion());
            
            Text3D text = new Text3D(f3d, palabra, //varEm atributo de clase de tipo string
                     new Point3f( 0f, -0.f, 0f));

 

            Shape3D sh = new Shape3D();
            Appearance app = new Appearance();
            Material mm = new Material();
            Color3f color1 = new Color3f (0.0f, 0.0f, 1.0f);
            
            
            mm.setLightingEnable(true);
            mm.setDiffuseColor(color1);
            
            app.setMaterial(mm);
            sh.setGeometry(text);
            sh.setAppearance(app);

 

            objTrans.addChild( sh );
            objRoot.addChild(objTrans);
                                    
            return objRoot;
         
      
      }
		
		//Metodo para a?adir letras o palabras al mundo grafico pero con parametros 
		//de coordenadas de posicion y aparte una bandera para establecer un color distinto y tama?o
		public  BranchGroup letrasCubo(BranchGroup objRoot, float corX, float corY, float corZ, boolean band){
	          
            TransformGroup objTrans = new TransformGroup();
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
            objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ); 
            Transform3D trans = new Transform3D();
            
            if(!band)
            trans.rotX(-70);

 

            Vector3d vector =new Vector3d(corX, corY, corZ);
            
            Vector3d op ;
            trans.setTranslation(vector);
            System.err.println(trans);
            if(!band)
            op = new Vector3d(.08,.1,.1);//Tama?o
            else
            	op = new Vector3d(.2,.2,.2);
            
            trans.setScale(op);    
            
                        
            objTrans.setTransform(trans);
            
            Font3D f3d = new Font3D(new Font("dialog", Font.PLAIN, 1),
       		     new FontExtrusion());
            
            Text3D text = new Text3D(f3d, palabra, //varEm atributo de clase de tipo string
                     new Point3f( 0f, -0.f, 0f));

 

            Shape3D sh = new Shape3D();
            Appearance app = new Appearance();
            Material mm = new Material();
            Color3f color1 = new Color3f (0.0f, 1.0f, 1.0f);
            
            
            mm.setLightingEnable(true);
            mm.setDiffuseColor(color1);
            
            app.setMaterial(mm);
            sh.setGeometry(text);
            sh.setAppearance(app);

 

            objTrans.addChild( sh );
            objRoot.addChild(objTrans);
                                    
            return objRoot;
         
      
      }
		
		//Metodo para establecer un acto y avance
		public void setacto()
		{
			acto=1;
		avance=0;
		}
		
		
		/* este metodo es abstracto contenido en implements sirve
		 * para hacer el uso de hebras dentro de una clase instanciada
		 * aqui se haran las animaciones de cubos
		 */
	 @Override
	public void run() {
		// TODO Auto-generated method stub
			Thread ct = Thread.currentThread();   //captura la hebra en ejecucion
	        try{
	        	while (ct == animacion2) {

	            Vector3d op = new Vector3d(.1,.05,.1);
	        	 switch(acto)
                 {
                 case 0:
                 a3dTrans.rotY(-Math.PI/1.80d);
                 tras = new Vector3d(ax,ay,az);
                 a3dTrans.setTranslation(tras);


    		    a3dTrans.setScale(op);	
         		obtrns.setTransform(a3dTrans);
         		avance++; 

                	 if(avance<=80)
                	 {
                         ay += 0.0010;
                         
                	 }
                	 else{
                		 acabe=1;
                		 }
                	 
                	 break;
                 case 1:
                       a3dTrans.rotY(-Math.PI/1.80d);

                       tras = new Vector3d(ax,ay,az);
                       a3dTrans.setTranslation(tras);


          		    a3dTrans.setScale(op);	
               		obtrns.setTransform(a3dTrans);
               		avance++; 

               	 acabe=0;
                      	 if(avance<=80)
                      	 {
                               ay -= 0.0010;
                               
                      	 }
                      	 else{
                      		 acabe=1;

                      		 }
                      	 
                      	 break;
                 }
                	 
	        	 

               
	                Thread.sleep(delay); //Se espera un tiempo antes de seguir la ejecuci?n
	        	}
	        	} catch (InterruptedException ex) {//Logger.getLogger(Cube3D.class.getName()).log(Level.SEVERE, null, ex);	            }
	        	
	        }
	        
	        }
}
