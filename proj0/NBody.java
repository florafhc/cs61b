public class NBody{
  /** read radius of the universe */
  public static double readRadius(String file){
    int num;
    double radius;
    In in = new In(file);
    num = in.readInt();
    radius = in.readDouble();
    return radius;
  }
  /** read planets from the txt file */
  public static Planet[] readPlanets(String file){
    int num, index;
    double radius;
    double xxPos, yyPos, xxVel, yyVel, mass;
    String img;
    In in = new In(file);
    num = in.readInt();
    radius = in.readDouble();
    Planet[] planets = new Planet[num];
    index = 0;
    while (!in.isEmpty()){
      xxPos = in.readDouble();
      yyPos = in.readDouble();
      xxVel = in.readDouble();
      yyVel = in.readDouble();
      mass = in.readDouble();
      img = in.readString();
      planets[index] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, img);
      index = index + 1;
      if (index >= num){
        break;
      }
    }
    return planets;
  }

  public static void main(String[] args){
    /** Collecting all needed input */
    double T, dt, radius;
    String filename;
    Planet[] planets;
    T = Double.parseDouble(args[0]);
    dt = Double.parseDouble(args[1]);
    filename = args[2];
    radius = readRadius(filename);
    planets = readPlanets(filename);
    int num = planets.length;

    /** Animation */
    StdDraw.enableDoubleBuffering();
    double time = 0;
    while (time < T){
      double[] xForces = new double[num];
      double[] yForces = new double[num];
      for (int i = 0; i < num; i += 1){
        xForces[i] = planets[i].calcNetForceExertedByX(planets);
        yForces[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i = 0; i < num; i += 1){
        planets[i].update(dt, xForces[i], yForces[i]);
      }
      /** Drawing the background */
      String bg_image = "images/starfield.jpg";
      StdDraw.setScale(-radius, radius);
      StdDraw.clear();
      StdDraw.picture(0, 0, bg_image);
      /** draw all planets */
      for (int i = 0; i < num; i += 1){
        planets[i].draw();
      }
      StdDraw.show();
      time = time + dt;

    }

    /** print the universe */
    StdOut.printf("%d\n", planets.length);
    StdOut.printf("%.2e\n", radius);
    for (int i = 0; i < planets.length; i++) {
    StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}
  }
}
