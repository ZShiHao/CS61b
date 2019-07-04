public class Body{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double g=6.67e-11;

	public Body(double xP,double yP,double xV,double yV,double m,String img){
		xxPos=xP;
		yyPos=yP;
		xxVel=xV;
		yyVel=yV;
		mass=m;
		imgFileName=img;
	}

	public Body(Body b){
		xxPos=b.xxPos;
		yyPos=b.yyPos;
		xxVel=b.xxVel;
		yyVel=b.yyVel;
		mass=b.mass;
		imgFileName=b.imgFileName;
	}

	public  double calcDistance(Body b){
		double r;
		r=Math.pow(b.xxPos-xxPos,2)+Math.pow(b.yyPos-yyPos,2);
		return Math.sqrt(r);
	}


	public double calcForceExertedBy(Body b){
		double f;
		double r;
		r=calcDistance(b);
		f=(g*mass*b.mass)/(r*r);
		return f;
	}

	public double calcForceExertedByX(Body b){
		double f;
		double fx;
		f=calcForceExertedBy(b);
		fx=f*(b.xxPos-xxPos)/calcDistance(b);
		return fx;
	}

	public double calcForceExertedByY(Body b){
		double f;
		double fy;
		f=calcForceExertedBy(b);
		fy=f*(b.yyPos-yyPos)/calcDistance(b);
		return fy;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double fnetx;
		int i;
		for(i=0,fnetx=0;i<allBodys.length;i++){
			if(this.equals(allBodys[i])) continue;
			fnetx=fnetx+calcForceExertedByX(allBodys[i]);
		}
		return fnetx;
	}

	public double calcNetForceExertedByY(Body[] allBodys){
		double fnety;
		int i;
		for(i=0,fnety=0;i<allBodys.length;i++){
			if(this.equals(allBodys[i])) continue;
			fnety=fnety+calcForceExertedByY(allBodys[i]);
		}
		return fnety;
	}

	public void update(double dt,double fx,double fy){
		double ax,ay;
		ax=fx/mass;
		ay=fy/mass;
		xxVel=xxVel+ax*dt;
		yyVel=yyVel+ay*dt;
		xxPos=xxPos+xxVel*dt;
		yyPos=yyPos+yyVel*dt;
	}

	public void draw(){
		double r=2.5e+11;
		//System.out.println("images".concat(imgFileName));
		StdDraw.picture(xxPos/r,yyPos/r,"images/".concat(imgFileName));

	}






}