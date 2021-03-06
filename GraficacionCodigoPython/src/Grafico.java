import com.sun.j3d.exp.swing.JCanvas3D;
import com.sun.j3d.loaders.IncorrectFormatException;
import com.sun.j3d.loaders.ParsingErrorException;
import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsConfiguration;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.Timer;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.geometry.Sphere;

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
import javax.swing.JTextArea;
import javax.vecmath.AxisAngle4f;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix3d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;


//Clase importante ya que en ella se ejecutan la mayoria de accion
//de este proyecto en su forma grafica
public class Grafico  extends JInternalFrame {
	private boolean usado=false;
	private int iterado=0;
    private Component comp;
    private String palbra ="Palabra";
    private  JCanvas3D canvas;
    private SimpleUniverse universe;
    private javax.media.j3d.Locale univTotal;
    private SimpleUniverse univ = null;
    private BranchGroup scene ;
    private Button go = new Button("Go");
    private TransformGroup objTrans;
    private float scala = 0.15f;
    private Transform3D trans = new Transform3D();
    private boolean spin = false;
    private 	int cuanta=25;
    private Timer iniciar;
    private Timer iniciarrojo;
    private Timer buscar;
    BranchGroup palabra1Branch2;
    private Timer buscar2;
    private 	Letra cubote = new Letra();
    private 	Letra cubote2 = new Letra();
    private ArrayList<Letra> ceroyunoverd = new ArrayList<Letra>();
    private ArrayList<Letra> ceroyunorojo = new ArrayList<Letra>();
    private ArrayList<Cube> cuboide = new ArrayList<Cube>();
    private	int iterador;//iterado de cubos
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0,0.0),
  	      100.0);
  TextureLoader loader;
    
    //Metodo constructor para clase Grafico
public Grafico() {

	super("Entorno grafico");
    setSize(256, 256);
    setClosable(true);
    Dimension dim = new Dimension(256, 256);
    pack();
 	Canvas3D c = createUniverse();
 	this.add(c, java.awt.BorderLayout.CENTER);
 	fondo();
 	ini(); 		
}


/*
metodo que inicia los cubos del mundo de los objetos y las palabras de las plataformas
*/
public void ini()
{ 	Cube cu = new Cube();
	univ.addBranchGraph(cu.cubo1()); 
	Cube cu2 = new Cube();
	univ.addBranchGraph(cu2.cubo2()); 
	Cube cu3 = new Cube();
	univ.addBranchGraph(cu3.cubo3()); 
	
	Cube insNomPla = new Cube();
	insNomPla.setpalabr("Object Realm");
	BranchGroup palabraClass = new BranchGroup();
	palabraClass = insNomPla.letrasCubo(palabraClass,-0.85f,-0.62f,-0.40f, false);	
	univ.addBranchGraph(palabraClass);
	
	insNomPla.setpalabr("main");
	BranchGroup palabraMain = new BranchGroup();
	palabraMain = insNomPla.letrasCubo(palabraMain,0.6f,-0.62f,-0.20f, false);	
	univ.addBranchGraph(palabraMain);
	
	insNomPla.setpalabr("Class");
	BranchGroup palabraClassAr = new BranchGroup();
	palabraClassAr = insNomPla.letrasCubo(palabraClassAr,0.95f,1.0f,-1.5f, true);	
	univ.addBranchGraph(palabraClassAr);
	
	insNomPla.setpalabr("realm");
	BranchGroup palabraRealArb = new BranchGroup();
	palabraRealArb = insNomPla.letrasCubo(palabraRealArb,0.95f,.8f,-1.5f, true);	
	univ.addBranchGraph(palabraRealArb);
	inicaiacube();
	creaceroverde();
	creacerorojo();
}


/*Metodo para inicialisar todos los cubos que representan los objetos en el mundo de los objetos*/
private void inicaiacube(){
	double x=-.90,y=-.60,z=-.75,x0=0,z0=0;
	for (int i=0;i<16;i++)
	{
		 Cube cu = new Cube(0);	
		 cu.inici();
		if(i<4)
		{
			cu.setcordenadasan(x0, z0);
		 cu.setcord(x, y, z);			
		 z-=.22;
		 x+=.035;
		 z0++;
		}
		else
		{
			if(i==4)
			{
				x0++;
		    	z0=0;
				x=-.65;
				z=-.7;
				cu.setcordenadasan(x0, z0);
				 cu.setcord(x, y, z);			
				 z-=.22;
			}
			else
			{
				if(i<8)
			{	
					z0++;
				 cu.setcord(x, y, z);	
					cu.setcordenadasan(x0, z0);
				 z-=.22;
				 x+=.050;

			}
				else
				{
					if(i==8)
					{
						x0++;
				    	z0=0;
						x=-.4;
						z=-.65;
						 cu.setcord(x, y, z);		
							cu.setcordenadasan(x0, z0);
						 z-=.22;

					}
					else
					{

						if(i<12)
					{		
							z0++;
						 cu.setcord(x, y, z);		
							cu.setcordenadasan(x0, z0);
						 z-=.22;
						 x+=.05;
					}
						else
						{			
							if(i==12)
						{
								x0++;
						    	z0=0;
							x=-.18;
							z=-.60;
							cu.setcordenadasan(x0, z0);
							 cu.setcord(x, y, z);			
							 z-=.22;

						}
							else
							{
								z0++;
								cu.setcord(x, y, z);		
								cu.setcordenadasan(x0, z0);
							 z-=.22;
							 x+=.055;
							}
						}
					}
					
				}
			}
		}
		cuboide.add(cu);
		}

	}




//Este es un metodo solo para fines de probar las animacion, no tiene ningun fin en la simulacion
public void prueba() {
	
	int delay = 160;
	iterador=0;
	  ActionListener taskPerformer = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	    	try{ 
	    	  if(iterador<cuboide.size())
	    	  {
	    		  univ.addBranchGraph(cuboide.get(iterador).anima()); 
	    		  cuboide.get(iterador).inicia();
	    		
	    	  iterador++;
	    	  //finciclovubos();
	    	  } 

	    	 }catch(Exception ex){}
	      }
	  }; 

	  buscar =  new Timer(delay, taskPerformer);
	  buscar.start();
	  iniciaanimacionverde(cuboide.get(15).getx0(),cuboide.get(15).getz0());

}

/*Metodo para pone la imagen de arbol en el panel de class realm*/
public void caratula(BufferedImage im){
	int paratextura = Primitive.GENERATE_NORMALS + Primitive.GENERATE_TEXTURE_COORDS;
	TextureLoader loader=null;
	Texture texture=null;
	Appearance ap=null;
	loader = new TextureLoader(im,"INTENSITY", new Container());
	texture = loader.getTexture();
	ap = new Appearance();
	ap.setTexture(texture);
	Box cabeza=new Box(1.1f,1.1f,1.1f,paratextura,ap);
	univ.addBranchGraph(boxe(cabeza)); 
}


/*Metodo que complementa el metodo que estableca la imagen del class realm, solo apliacando cierta
 * tranformaciones para se vea mejor colocado
 */
private  BranchGroup boxe(Box cabeza){
	BranchGroup objRoot = new BranchGroup();
    TransformGroup objScale = new TransformGroup();
    Transform3D t3d = new Transform3D();
    Transform3D rotate = new Transform3D();
Transform3D tempRotate = new Transform3D();
tempRotate.rotY(Math.PI/2.32d);
Matrix3d n = new Matrix3d();
Vector3d op = new Vector3d(.01,1,1);
tempRotate.setScale(op);
Vector3d op2 = new Vector3d(-.01,0.4,-1.9);
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
  
objTrans.addChild(cabeza);

objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
			 

return objRoot;
 
}

//Metodo que nos ayuda a comprobar dar por terminado cualquier tipo de animacion
private void finciclo()
{
		  if(cuanta==0)
	  {
		  iniciar.stop();
		  cuanta=25;

			int delay = 160;
			  ActionListener taskPerformer = new ActionListener() {
			      public void actionPerformed(ActionEvent evt) {

			    	  if(ceroyunoverd.get(24).acab()==1)
			    	  {
			    		  buscar2.stop();

			    		  System.out.print("acabeverde");	
			    		  	for(int i=0;i<25;i++)
			    		  	{
			    		  		ceroyunoverd.get(i).deten();
			    		  	}
			  			iniciaanimacionrojo();
			    	  }
			      }
			  }; 
			  
			  buscar2 =  new Timer(delay, taskPerformer);
			  buscar2.start();
			

	  }

}


/*Este metodo establece cierta imagen al class realm*/
private void fondo()
{
    BranchGroup objRoot = new BranchGroup();
    BoundingSphere backgroundBounds = new BoundingSphere(new Point3d(0,0,0), 1000);
    TextureLoader myLoader = new  TextureLoader("bin/images/Fondo.jpg",this);
    ImageComponent2D myImage = myLoader.getImage( );
    Background background = new Background();
    background.setImage(myImage);
    background.setImageScaleMode(5);
    background.setApplicationBounds(backgroundBounds);
    objRoot.addChild(background);
    univ.addBranchGraph(objRoot);
}


/*Este metodo ayuda a poder interactuar con el mundo grafico */
private BranchGroup createSceneGraph(boolean isInteractive, boolean isRandom) {
    // Create the root of the branch graph
    BranchGroup objRoot = new BranchGroup();
    TransformGroup objTrans = new TransformGroup();
    Transform3D t3dTrans = new Transform3D();
    t3dTrans.setTranslation(new Vector3d(0, 0, -2));
    objTrans.setTransform(t3dTrans);

    TransformGroup objRot = new TransformGroup();
    objRot.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    objRoot.addChild(objTrans);
    objTrans.addChild(objRot);
    Font3D f3d = new Font3D(new Font("dialog", Font.PLAIN, 1),
		     new FontExtrusion());
    Text3D text = new Text3D(f3d, palbra,
		     new Point3f( -2.3f, -0.5f, -1.5f));

    Shape3D sh = new Shape3D();
Appearance app = new Appearance();
Material mm = new Material();
mm.setLightingEnable(true);
app.setMaterial(mm);
sh.setGeometry(text);
sh.setAppearance(app);

    objRot.addChild( sh );

    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0),
            100.0);
    
// Set up the ambient light
Color3f ambientColor = new Color3f(0.3f, 0.3f, 0.3f);
AmbientLight ambientLightNode = new AmbientLight(ambientColor);
ambientLightNode.setInfluencingBounds(bounds);
objRoot.addChild(ambientLightNode);

// Set up the directional lights
Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
Color3f light2Color = new Color3f(1.0f, 1.0f, 0.9f);
Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);

DirectionalLight light1
    = new DirectionalLight(light1Color, light1Direction);
light1.setInfluencingBounds(bounds);
objRoot.addChild(light1);

DirectionalLight light2
    = new DirectionalLight(light2Color, light2Direction);
light2.setInfluencingBounds(bounds);
objRoot.addChild(light2);


    if (true == isInteractive) {
        MouseRotate mr = new MouseRotate(comp, objRot);
        mr.setSchedulingBounds(bounds);
        mr.setSchedulingInterval(1);
        objRoot.addChild(mr);
    } else {
        // Create a new Behavior object that will perform the
        // desired operation on the specified transform and add
        // it into the scene graph.
        Transform3D yAxis = new Transform3D();

        // rotation speed is randomized a bit so that it does not go at the same speed on every canvases,
        // which will make it more natural and express the differences between every present universes
        Alpha rotationAlpha = null;

        if (true == isRandom) {
            int duration = Math.max(2000, (int) (Math.random() * 8000.));
            rotationAlpha = new Alpha(-1,
                    (int) ((double) duration * Math.random()), 0, duration,
                    0, 0);
        } else {
            rotationAlpha = new Alpha(-1, 4000);
        }

        RotationInterpolator rotator = new RotationInterpolator(rotationAlpha,
                objRot, yAxis, 0.0f, (float) Math.PI * 2.0f);

        rotator.setSchedulingBounds(bounds);
        objRoot.addChild(rotator);
    }
    

    return objRoot;
}

//Este metodo permite que comienze una hebra
public void aniamar(JTextArea areaTexto){

	Runnable r = new Colorea(areaTexto, this);
	Thread t = new Thread(r);
	t.start();

}

//Metodo que ayudaba en versiones anteriores a establecer una palabra como texto grafico
public void settexto(String cam)
{    
	this.palbra=cam;
    universe.cleanup();
    universe=null;
	canvas=null;
	comp=null;
    canvas = new JCanvas3D(new GraphicsConfigTemplate3D());
    comp = canvas;

    Dimension dim = new Dimension( 610, 560 );
    comp.setPreferredSize(dim);
    comp.setSize(dim);
    getContentPane().setLayout(new BorderLayout());
    getContentPane().add(comp, BorderLayout.CENTER);
    pack();    
    scene = createSceneGraph(true, false);
    universe = new SimpleUniverse(canvas.getOffscreenCanvas3D()); //TODO: this is awful and must not be done like that in final version
}


//Metodo para Inisializar el canvas que representa el mundo de los objetos(Este metodo se obtuvo en base a otros codigos
private Canvas3D createUniverse() {
	GraphicsConfiguration config =
	SimpleUniverse.getPreferredConfiguration();

	
	Canvas3D c = new Canvas3D(config);

	
	univ = new SimpleUniverse(c);


    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);

// add mouse behaviors to the ViewingPlatform
ViewingPlatform viewingPlatform = univ.getViewingPlatform();

PlatformGeometry pg = new PlatformGeometry();

// Set up the ambient light
Color3f ambientColor = new Color3f(0.1f, 0.1f, 0.1f);
AmbientLight ambientLightNode = new AmbientLight(ambientColor);
ambientLightNode.setInfluencingBounds(bounds);
pg.addChild(ambientLightNode);

// Set up the directional lights
Color3f light1Color = new Color3f(1.0f, 1.0f, 0.9f);
Vector3f light1Direction  = new Vector3f(1.0f, 1.0f, 1.0f);
Color3f light2Color = new Color3f(1.0f, 1.0f, 1.0f);
Vector3f light2Direction  = new Vector3f(-1.0f, -1.0f, -1.0f);
Color3f light3Color = new Color3f(1.0f, 1.0f, 1.0f);
Vector3f light3Direction  = new Vector3f(0.0f, 0.0f, 0.0f);

DirectionalLight light1
    = new DirectionalLight(light1Color, light1Direction);
light1.setInfluencingBounds(bounds);
pg.addChild(light1);

DirectionalLight light2
    = new DirectionalLight(light2Color, light2Direction);
light2.setInfluencingBounds(bounds);
pg.addChild(light2);

DirectionalLight light3
= new DirectionalLight(light3Color, light3Direction);
light2.setInfluencingBounds(bounds);
pg.addChild(light3);

viewingPlatform.setPlatformGeometry( pg );
  
// This will move the ViewPlatform back a bit so the
// objects in the scene can be viewed.
viewingPlatform.setNominalViewingTransform();
if (!spin) {
    OrbitBehavior orbit = new OrbitBehavior(c,
				    OrbitBehavior.REVERSE_ALL);
    orbit.setSchedulingBounds(bounds);
    viewingPlatform.setViewPlatformBehavior(orbit);	    
}    
     
    
    // Ensure at least 5 msec per frame (i.e., < 200Hz)
univ.getViewer().getView().setMinimumFrameCycleTime(5);

	return c;
  }


//Crea o inisializa numeros binarios verdes en el mundo de los objetos
private void creaceroverde() {
	for(int i=0;i<25;i++)
	{
		int numero = (int) (Math.random() * 2);
	  if(numero ==0)
	  {
		 	Letra cero = new Letra();
cero.setpalabra("0");
cero.asigpal(0);
cero.inici();
cero.setcord(0);
ceroyunoverd.add(cero);
univ.addBranchGraph(cero.animacionLEtra()); 

	  }
	  else
	  {
			Letra cero = new Letra();
			cero.asigpal(1);
			cero.inici();
			cero.setpalabra("1");
			cero.setcord(0);
			ceroyunoverd.add(cero);
			univ.addBranchGraph(cero.animacionLEtra());
	  }
	  }
}

//Crea o inisializa numeros binarios rojos en el mundo de los objetos
private void creacerorojo() {
	for(int i=0;i<25;i++)
	{
		int numero = (int) (Math.random() * 2);
	  if(numero ==0)
	  {
		 	Letra cero = new Letra();
cero.setpalabra("0");
cero.asigpal(2);
cero.inici();
cero.setcord(2);
ceroyunorojo.add(cero);


	  }
	  else
	  {
			Letra cero = new Letra();
			cero.setpalabra("1");
			cero.asigpal(3);
			cero.inici();
			cero.setcord(2);
			ceroyunorojo.add(cero);

	  }
	  }
}

/*Metodo que ayuda a empezar la animiacion de los numero binarios verdes*/
private void iniciaanimacionverde(double x,double z){
	int delay = 180; //milliseconds
	iterado=0;
	  ActionListener taskPerformer = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	          //...Perform a task...
if(iterado<25)
{  
	    	  ceroyunoverd.get(iterado).setcero(x, z);
	    	  ceroyunoverd.get(iterado).inicia();
	    	  iterado++;
	    	  cuanta--;

}
if(iterado==25)
	iniciar.stop();
 iniciar=null;
}
	  }; 
	  
	 iniciar =  new Timer(delay, taskPerformer);
	iniciar.start();
	}

/*Metodo que ayuda a empezar la animiacion de los numero binarios rojos*/
private void iniciaanimacionrojo(){
	int delay = 160; //milliseconds
	iterado=0;
	cuanta=25;
	 System.out.print("inicia rojo");
	  ActionListener taskPerformer2 = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	    	  if(iterado<25)
	    	  { 	  ceroyunorojo.get(iterado).setcord(2);
	    	  univ.addBranchGraph(ceroyunorojo.get(iterado).animacionLEtra()); 
	    	  ceroyunorojo.get(iterado).inicia();
	    	  iterado++;
	    	  cuanta--;}
	    	  if(iterado==25) {
	    		  iniciarrojo.stop();
	    		  iniciarrojo=null;
	    	 }

	      }
	  }; 
	  
	  iniciarrojo =  new Timer(delay, taskPerformer2);
	  iniciarrojo.start();
	}

//Ayuda a establecer el objeto primero que representa el cubo y la palabra, en este caso del codigo el del perro
private void iniciaprrro()
{
	
    iniciaanimacionverde(cuboide.get(0).getx0(),cuboide.get(0).getz0());
    while(  ceroyunoverd.get(24).acab()==0){
  	  System.out.print("");
    } 
	cuboide.get(0).setpalabr("beagle ");
	javax.media.j3d.Locale univTotal = univ.getLocale();//univ variable del tipo simpleUniverse 
    BranchGroup palabra1Branch = new BranchGroup();
    palabra1Branch.setCapability (palabra1Branch.ALLOW_DETACH);

    univ.addBranchGraph(cuboide.get(0).anima()); 
      cuboide.get(0).inicia();  
      while(  cuboide.get(0).acab()==0){
    	 System.out.print("");
      } 
      palabra1Branch = cuboide.get(0).letrasCubo(palabra1Branch);//cubote3 objeto clase letra
      univTotal.addBranchGraph(palabra1Branch);
      ceroyunoverd.clear();
}


//Ayuda a establecer el objeto segundo que representa el cubo y la palabra, en este caso del codigo el de la croqueta
private void iniciaalimprrro(){

    iniciaanimacionverde(cuboide.get(1).getx0(),cuboide.get(1).getz0());
    while(  ceroyunoverd.get(24).acab()==0){
  	  System.out.print("");
    } 
	cuboide.get(1).setpalabr("croqueta");
	univTotal = univ.getLocale();//univ variable del tipo simpleUniverse 
    palabra1Branch2 = new BranchGroup();
    palabra1Branch2.setCapability (BranchGroup.ALLOW_DETACH);

    univ.addBranchGraph(cuboide.get(1).anima()); 
    
      cuboide.get(1).inicia();  
      while(  cuboide.get(1).acab()==0){
    	 System.out.print("");
      } 
    //  cuboide.get(1).deten();  
      palabra1Branch2 = cuboide.get(1).letrasCubo(palabra1Branch2);//cubote3 objeto clase letra
      univTotal.addBranchGraph(palabra1Branch2);
      //univTotal.removeBranchGraph(palabra1Branch2);
      ceroyunoverd.clear();
      ceroyunoverd=null;
      ceroyunoverd = new ArrayList<Letra>();
}

//Hace la animacion del mensaje entre los objetos ya creados previamente
private void inimetodo() {
	
	creaceroverde();
    iniciaanimacionverde(cuboide.get(0).getx0(),cuboide.get(0).getz0());
    while(  ceroyunoverd.get(24).acab()==0){
  	  System.out.print("");
    } 
    
    iniciaanimacionrojo();

    while(  ceroyunorojo.get(24).acab()==0){
    	  System.out.print("");
      }  
    univTotal.removeBranchGraph(palabra1Branch2);
    cuboide.get(1).setacto();
    cuboide.get(1).inicia();  

    
    
}

//Este metodo ayuda a detectar lineas del codigo para poder empezar a hacer una animacion segun los llamados
class Colorea implements Runnable{
	
	public JTextArea areaTexto;
	public Grafico graf;
	public Colorea(JTextArea texbox, Grafico graf) {
		this.areaTexto = texbox;
		this.graf = graf;
	}
	
	public void run() {
		
		int numLetras = 0;
		String[] renglones = areaTexto.getText().split("\n");
		
		for(int i = 0; i<renglones.length; i++) {
			System.out.println(renglones[i]);
			try {
				Highlighter.HighlightPainter redPainter;
				Highlighter.HighlightPainter colorsito;
				redPainter = new DefaultHighlighter.DefaultHighlightPainter(Color.YELLOW);
				areaTexto.getHighlighter().addHighlight(numLetras, numLetras+renglones[i].length(), redPainter);

				Thread.sleep(50);
				
				if(renglones[i].indexOf("= Perro()")!=-1) {
					
					Animate hilo1 = new Animate(this.graf,1);
					hilo1.start();
					try {
						hilo1.join();
						
					}catch(Exception e) {}
					
					Thread.sleep(1000);
				}
				if(renglones[i].indexOf("= AlimentoPerro()")!=-1) {
					creaceroverde();
					iniciaalimprrro();
					if(usado==false)
					{	
					usado=true;			
				     }
					Animate hilo1 = new Animate(this.graf,2);
				//	hilo1.start();
					try {
						hilo1.join();
					//	hilo1.stop();
					}catch(Exception e) {}
					
				Thread.sleep(1000);
				}
					if(renglones[i].indexOf("tenComida(croqueta)")!=-1) {
						inimetodo();
					Animate hilo1 = new Animate(this.graf,3);
					try {
						hilo1.join();
					}catch(Exception e) {}
					
					Thread.sleep(1000);
					
					
				}
				
				areaTexto.getHighlighter().removeAllHighlights();
				numLetras += renglones[i].length()+1;

			}
			catch(Exception ble) {}
			
			
		}
	}
	
}



//Clase interna auxiliar para detectar que tipo de animacion hacer
class Animate extends Thread{
	public Grafico graf;
	boolean ban = true;
	int tipoAnima;
	public Animate(Grafico graf, int nAnimacion) {
		this.graf = graf;
		tipoAnima = nAnimacion;
	}
	public void run() {
		while(ban) {
		switch(tipoAnima){
		
		case 1:
			
			iniciaprrro();

			break;
		case 2:


			break;
		case 3:
			inimetodo();
			break;
		
		}
		}
	}



}}


