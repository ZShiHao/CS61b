public class NBody{

	public static double readRadius(String file){
		In in=new In(file);
		in.readDouble();
		return in.readDouble();
	}


	public static Body[] readBodies(String file){
		In in=new In(file);
		int n=in.readInt();
		Body[] bodies=new Body[n];
		int i;
		double xxPos;
		double yyPos;
		double xxVel;
		double yyVel;
		double mass;
		String imgFileName;
		in.readDouble();
		for(i=0;i<n;i++){
			xxPos=in.readDouble();
			yyPos=in.readDouble();
			xxVel=in.readDouble();
			yyVel=in.readDouble();
			mass=in.readDouble();
			imgFileName=in.readString();
			bodies[i]=new Body(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
		}
		return bodies;
	}

	public static void main(String[] args) {
		double T=Double.parseDouble(args[0]);
		double dt=Double.parseDouble(args[1]);
		String filename=args[2];
		Body[]  bodies;
		int i;
		double t;
		bodies=readBodies(filename);


		double[] xForces=new double[bodies.length];
		double[] yForces=new double[bodies.length];


		StdDraw.enableDoubleBuffering();

		for(t=0;t<=T;t=t+dt){
			StdDraw.picture(0.5,0.5,"/images/starfield.jpg");
			for(i=0;i<bodies.length;i++){
				xForces[i]=bodies[i].calcNetForceExertedByX(bodies);
				yForces[i]=bodies[i].calcNetForceExertedByY(bodies);
			}
			for(i=0;i<bodies.length;i++){
				bodies[i].update(dt,xForces[i],yForces[i]);
				bodies[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);

		}

	}


}