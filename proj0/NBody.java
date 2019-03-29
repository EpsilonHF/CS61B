/*
 * Definition of NBody class
 */

public class NBody{
    public static double readRadius(String path) {
        In in = new In(path);
        int n = in.readInt();
        double r = in.readDouble();

        return r;
    }

    public static Planet[] readPlanets(String path) {
        In in = new In(path);
        int n = in.readInt();
        double r = in.readDouble();
        Planet[] planets = new Planet[n];

        for (int i = 0; i < n; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            Planet p = new Planet(xP, yP, xV, yV, m, img);
            planets[i] = p;
        }
        
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);
        StdDraw.setScale(-r, r);
        StdDraw.picture(0, 0, "images/starfield.jpg");

        for (Planet p: planets)
            p.draw();

        StdDraw.enableDoubleBuffering();
        double[] xForces = new double[planets.length];
        double[] yForces = new double[planets.length];

        for (int time = 0; time < T; time += dt) {

            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet p: planets)
                p.draw();

            StdDraw.show();
            StdDraw.pause(10);
        }
        
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);

        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                          planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}
