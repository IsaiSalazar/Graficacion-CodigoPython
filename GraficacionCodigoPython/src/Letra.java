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
/*
 * clase Letra en esta clase se encargara de la creacion de animaciones de los cero y un as u bes 
 * la animacion de los mensajes dee cubos*/
public  class Letra implements Runnable {
	
	private String palabra="";//Cadena para mostrar de forma grafica
	private Thread animacion = new Thread(this);//hebra
    BranchGroup group = new BranchGroup();//Variable auxiliar de modo grafico
    ColorCube cube = new ColorCube(0.3);
    TransformGroup GT = new TransformGroup();//variable para agrupar varias transformaciones
    Transform3D transform = new Transform3D();//variable para declarar una transformacion
    double Y = 0;
    Thread hilo1 = new Thread(this); //Se declara el hilo
	//cordenadas iniciales de el acto 0 los demas actos dependeran de que acto se trata
	private double ax= 0.5f;
	private double ay= -0.54f;
	private double az= -0.5f;	
	private Vector3d tras =new Vector3d(ax,ay,az);//vector inicial del acto 0
	private BranchGroup obj = new BranchGroup();//el objeto a retornar
	private  TransformGroup obtrns = new TransformGroup();	//grupo de transformaciones
	private Transform3D a3dTrans = new Transform3D(); 	//transformaciones en 3D
	private  TransformGroup obtrns2 = new TransformGroup();	//grupo de transformaciones
	private Transform3D a3dTrans2 = new Transform3D(); 	//transformaciones en 3D
	private int acto;//determina que animacion tendra que realizar	
	private int avance;//auxiliar que se usara en la hebra
	private boolean noTriangulate = false;//Variable de inisializacion de obj
	private boolean noStripify = false;//Variable de inisializacion de obj
	private double creaseAngle = 60.0;//Variable de inisializacion de obj
	private URL filename = null;//Variable de inisializacion de obj
	private int anima=0;//Variable de animacion
	private int delay=5;//Variable de delay para designar un retardo
	private int acabe=0;//variable contador para retardar para designar animaciones
	private double x0,z0,ix0,iz0;//varibales de coordenadas
	private String dir;//cadena para identificar que tipo de direccion correponde a su animacion
	
    //Constructor de clase
	public Letra()
	{
		
	}
	
	//Metodo set para establecer una cadena grafica
	public void setpalabra(String pal)
	{
		this.palabra=pal;
	}
	
	//Este metodo es llamado para saber que tipo de grafico tiene que ser cargado al mundo grafico
	public void asigpal(int dire)
	{
		switch(dire)
		{
		case 0:	
			dir="cerov";
		break;
		case 1:	
			dir="unov";
			break;
		case 2:	
			dir="ceror";
			break;
		case 3:	
			dir="unor";
			break;
		case 4:	
			dir="cube";
			break;
		
		}
	}
	
	
	  //Metodo que se encarga de mostrar un arhcivo obj en el mundo 3D, segun el atributo filename
	  //Tambien se le aplica una traslacion y un escalacion a ese objeto
	  public BranchGroup animacionLEtra() {
			
		obtrns.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		tras = new Vector3d(ax,ay,2);
		
	     	a3dTrans.setTranslation(tras);
		   // System.err.println(a3dTrans);
		    Vector3d op = new Vector3d(.1,.1,.1);
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
		
	  //Metodo para asignar el nombre del archivo obj que podria ponerse en el mundo grafico
	  public void inici() {
	        if (filename == null) {
	       	 filename = Resources.getResource("./modelo/"+dir+".obj");
	           if (filename == null) {
	               System.err.println("modelo/"+dir+".obj not founds");
	               System.exit(1);
	           }
		}
		}	
	  
		//Metodo para asignar un objeto en el mundo grafico con un archivo obj, pero aplicando
	    //transformaciones diferentes que seria una rotacion una traslacion y una esclacion 
		 public  BranchGroup cubo1(){

	    	obtrns2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    	inicia();
			tras = new Vector3d(ax,ay,az);
			a3dTrans2.rotY(-Math.PI/1.80d);
	     	a3dTrans2.setTranslation(tras);
		    System.err.println(a3dTrans);
		    Vector3d op = new Vector3d(.1,.1,.1);
		    a3dTrans2.setScale(op);	
		    obtrns2.setTransform(a3dTrans2);
				    

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
			  
			obtrns2.addChild(s.getSceneGroup());
			obj.addChild(obtrns2);
			return obj;
			 
		 }


    //Metodo set para establecer coordenadas de la atributos de clase
    public void setcord(int cord)
    {
    	int x=7;
    	switch(cord)
    	{
    	case 0://cordenada inicial letra verde
    		 ax= 0.5f;
    		 ay= -0.50f;
    		 az= -0.5f;	
    			anima=0;
    			acto=0;
    			delay=6;
    			 acabe=0;
    			 avance=0;
    		break;
    	case 1://cordenada cubo 1(-.70,.-50,-.65)
   		 ax=-.70f;
   		 ay= -0.6f;
   		 az= -0.65f;	
   		anima=1;
   		acto=0;
   		delay=20;
   	 acabe=0;
   	avance=0;
    		break;
    	case 2:
      		 ax=-.90f;
       		 ay= -0.5f;
       		 az= -0.95f;	
       		anima=1;
       		this.acto= 0;
       		delay=6;
       	 acabe=0;
       	 avance=0;
     //	System.out.print(acto);
    		break;
    		
    	case 3://cordenada cubo 1(-.70,.-50,-.65)
      		 ax=-.65f;
      		 ay= -0.6f;
      		 az= -0.95f;	
      		anima=1;
      		acto=0;
      		delay=20;
      	 acabe=0;
      	avance=0;
       		break;
    	}
    }
    
    //Metodo para incializar una hebra  y que comienze la animacion
    public void inicia(){
    	animacion.start();
    }
    
    //Metodo para detener una hebra que seria la de la animacion
    public void deten(){
    	animacion.stop();
    }
    
    //Metodo set para establecer valores de clase
    public void setcero(double x,double z){
    	this.x0=x;
    	this.z0=z;
    }
    
    //Metodo set para establcer variables graficas
    public void setceroini(double x,double z){
    	this.ix0=x;
    	this.iz0=z;
    }
    
		
	//Metodo para retornar variable que nos indica fin de animacion
	public int acab()
	{
		return this.acabe;
	}
	
	/* este metodo es abstracto contenido en implements sirve
	 * para hacer el uso de hebras dentro de una clase instanciada
	 * aqui se haran las animaciones de letras
	 */
	@Override
	public void run() {
		Thread ct = Thread.currentThread();  

        while (ct == animacion) {
            try {
            	 Y = Y + 0.09; //Variable global declarada como double
Vector3d op = new Vector3d(.02,.02,.02);
                 transform.rotY(Y); //Se rota en base al eje Y
                 GT.setTransform(transform); //Se actualiza el gr?fico
                 if(anima==0)
                 {
                	 switch(acto)
                 {
                 case 0:
                 a3dTrans.rotY(Y);
                 az += 0.0025;
                 tras = new Vector3d(ax,ay,az);
                 a3dTrans.setTranslation(tras);


    		    a3dTrans.setScale(op);	
         		obtrns.setTransform(a3dTrans);
         		avance++; 

                	 
                	 if(avance==120)
                	 {
                		 acto++;
                		 avance=0;
                	 }
                	 break;
                 case 1:
                	  a3dTrans.rotY(Y);
                      ay -= 0.0025;
                      tras = new Vector3d(ax,ay,az);
                      a3dTrans.setTranslation(tras);


         		    a3dTrans.setScale(op);	
              		obtrns.setTransform(a3dTrans);
              		avance++; 
            	 
               	 if(avance==40)
               	 {
               		 acto++;
               		 avance=0;
               	 }
                	 
                	 break;

                 case 2:
               	  a3dTrans.rotY(Y);
                  ax -= 0.0025;
                  az -= 0.0005;
                  tras = new Vector3d(ax,ay,az);
                  a3dTrans.setTranslation(tras);


     		    a3dTrans.setScale(op);	
          		obtrns.setTransform(a3dTrans);
          		avance++; 

           	 if(avance==120)
           	 {
           		 acto++;
           		 avance=0;
           	 }
                	 break;
                 case 3:
                  	  a3dTrans.rotY(Y);
                      az -= 0.0025;
                      tras = new Vector3d(ax,ay,az);
                      a3dTrans.setTranslation(tras);


         		    a3dTrans.setScale(op);	
              		obtrns.setTransform(a3dTrans);
              		avance++; 

               	 if(avance==80)
               	 {
               		 acto++;
               		 avance=0;
               	 }
                	 break;
                 case 4:
                 	  a3dTrans.rotY(Y);
                      ax -= 0.0025;
                      az -= 0.0005;
                      tras = new Vector3d(ax,ay,az);
                      a3dTrans.setTranslation(tras);


          		    a3dTrans.setScale(op);	
               		obtrns.setTransform(a3dTrans);
               		avance++; 

                	 if(avance==90)
                	 {
                		 acto++;
                		 avance=0;
                	 }
                	 
                	 
                	 break;
                 case 5:
                	 
                	  a3dTrans.rotY(Y);
                      ay += 0.0025;
                      tras = new Vector3d(ax,ay,az);
                      a3dTrans.setTranslation(tras);


          		    a3dTrans.setScale(op);	
               		obtrns.setTransform(a3dTrans);
               		avance++; 

                	 if(avance==80)
                	 {
                		 acto++;
                		 avance=0;
                	 }
                	 
                	 break;
                 case 6:
                  	 a3dTrans.rotY(Y);
                     ax += 0.0005;
                     az -= 0.0025;
                     tras = new Vector3d(ax,ay,az);
                     a3dTrans.setTranslation(tras);


         		    a3dTrans.setScale(op);	
              		obtrns.setTransform(a3dTrans);
              		avance++; 

           		System.out.print((int)z0);
               	 switch((int)z0)
               	 {
               	 case 0:
               acto++; 
          	 avance=0;
               		 break;
               	 case 1:
                   	 if(avance==90)
                   	 {
        
                   		 acto++;   
                   		 avance=0;

                   	 }
               		 break;
               	 case 2:
               		 if(avance==190)
                   	 {
        
                   		 acto++;   
                   		 avance=0;

                   	 }
               		 break;
               	 case 3:
               		 if(avance==280)
                   	 {
        
                   	 acto++;   
                	 avance=0;
                   	 }
               		 break;
               		 
               	 }
               	 
                   	 break;
                 case 7:
                  	  a3dTrans.rotY(Y);
                     ax -= 0.0025;
                     az -= 0.0005;
                     tras = new Vector3d(ax,ay,az);
                     a3dTrans.setTranslation(tras);


         		    a3dTrans.setScale(op);	
              		obtrns.setTransform(a3dTrans);
              		avance++; 

              		switch((int)x0)
                  	 {
                  	 case 0:
                  		 if(avance==360)
                      	 {
           
                      	 acto++;   
                      	 avance=0;

                      	 }
     
               
                  		 break;
                  	 case 1:
                      	 if(avance==260)
                      	 {
           
                      		 acto++;   
                          	 avance=0;


                      	 }
                  		 break;
                  	 case 2:
                  		 if(avance==160)
                      	 {
           
                      		 acto++;   
                          	 avance=0;


                      	 }
                  		 break;
                  	 case 3:
                  		 if(avance==55)
                      	 {
           
                      	 acto++;  
                      	 avance=0;

                      	 }
                  		 break;
                  		 
                  	 }
               	 
                   	 break;
                 case 8:

               	  a3dTrans.rotY(Y);
                  ay -= 0.0025;
                  tras = new Vector3d(ax,ay,az);
                  a3dTrans.setTranslation(tras);


      		    a3dTrans.setScale(op);	
           		obtrns.setTransform(a3dTrans);
           		avance++; 

            	 if(avance==80)
            	 {
            		 acto++;
            		 acabe=1;

            	 }
                	 break;
                 
                 }
            }
                 else
                 {
              		
                	 switch(acto)
                 {
                 case 0:
                	  a3dTrans.rotY(Y);
                      ay += 0.0025;
                      tras = new Vector3d(ax,ay,az);
                      a3dTrans.setTranslation(tras);


          		    a3dTrans.setScale(op);	
               		obtrns.setTransform(a3dTrans);
               		avance++; 

             	 
                	 if(avance==80)
                	 {
                		 acto++;
                		 avance=0;
                	 }
                	 
                	 break;
                 case 1:
               	  a3dTrans.rotY(Y);
                     az += 0.0025;
                     tras = new Vector3d(ax,ay,az);
                     a3dTrans.setTranslation(tras);


         		    a3dTrans.setScale(op);	
              		obtrns.setTransform(a3dTrans);
              		avance++; 

            	 
               	 if(avance==70)
               	 {
               		 acto++;
               		 avance=0;
               	 }
               	 
               	 break;
                 case 2:
                  	  a3dTrans.rotY(Y);
                        ay -= 0.0025;
                        tras = new Vector3d(ax,ay,az);
                        a3dTrans.setTranslation(tras);


            		    a3dTrans.setScale(op);	
                 		obtrns.setTransform(a3dTrans);
                 		avance++; 

               	 
                  	 if(avance==80)
                  	 {
                  		 acto++;
                  		 avance=0;
                  		 acabe=1;
                  	 }
                  	 
                  	 break;
                 }
                	 }

                Thread.sleep(delay); //Se espera un tiempo antes de seguir la ejecuci?n
            } catch (InterruptedException ex) {

            }

        }
		
	}

}
