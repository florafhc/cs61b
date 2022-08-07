public class Planet {
  public double xxPos; // current x position
  public double yyPos; // current y position
  public double xxVel; // current velocity in the x direction
  public double yyVel; // current velocity in the y direction
  public double mass; // mass
  public String imgFileName; // the name of file depicts the planet

  /** Constructors for the planet */
  public Planet(double xP, double yP, double xV, double yV, double m, String img){
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  public Planet(Planet p){
    xxPos = p.xxPos;
    yyPos = p.yyPos;
    xxVel = p.xxVel;
    yyVel = p.yyVel;
    mass = p.mass;
    imgFileName = p.imgFileName;
  }

  /** Calculate distance between two planet */
  public double calcDistance(Planet p){
    double distance;
    double dx;
    double dy;
    dx = p.xxPos - this.xxPos;
    dy = p.yyPos - this.yyPos;
    distance = Math.sqrt(dx * dx + dy * dy);
    return distance;
  }

  /** Calculate force exerted by another planet */
  public double calcForceExertedBy(Planet p){
    double distance;
    double G = 6.67e-11;
    double force;
    distance = this.calcDistance(p);
    force = G * this.mass * p.mass / (distance * distance);
    return force;
  }

  /** Calculate force exerted by X & Y */
  public double calcForceExertedByX(Planet p){
    double dx, dy, distance, force, forceX;
    dx = p.xxPos - this.xxPos;
    distance = this.calcDistance(p);
    force = this.calcForceExertedBy(p);
    forceX = force * dx / distance;
    return forceX;
  }

  public double calcForceExertedByY(Planet p){
    double dx, dy, distance, force, forceY;
    dy = p.yyPos - this.yyPos;
    distance = this.calcDistance(p);
    force = this.calcForceExertedBy(p);
    forceY = force * dy / distance;
    return forceY;
  }

  /** Calculate net force on X & Y */
  public double calcNetForceExertedByX(Planet[] list){
    double netforceX = 0;
    double forceX;
    int len = list.length;
    for (int i = 0; i < len; i += 1){
      if (this.equals(list[i]) != true){
        forceX = this.calcForceExertedByX(list[i]);
        netforceX = netforceX + forceX;
      }
    }
    return netforceX;
  }
  public double calcNetForceExertedByY(Planet[] list){
    double netforceY = 0;
    double forceY;
    int len = list.length;
    for (int i = 0; i < len; i += 1){
      if (this.equals(list[i]) != true){
        forceY = this.calcForceExertedByY(list[i]);
        netforceY = netforceY + forceY;
      }
    }
    return netforceY;
  }

  /** update the velocity and position of the planet */
  public void update(double dt, double forceX, double forceY){
    double xxA, yyA; //acceleration on X & Y
    xxA = forceX / mass;
    yyA = forceY / mass;
    xxVel = xxVel + xxA * dt;
    yyVel = yyVel + yyA * dt;
    xxPos = xxPos + dt * xxVel;
    yyPos = yyPos + dt * yyVel;
  }

  /** show the planet on the canvas */
  public void draw(){
    String file = "images/" + imgFileName;
    StdDraw.picture(xxPos, yyPos, file);
    //StdDraw.show();
  }
}
