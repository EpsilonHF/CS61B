/*
 * Define class Planet
 */
public class Planet {
    
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    static double G = 6.67e-11; 
        
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double x_dist = p.xxPos - this.xxPos;
        double y_dist = p.yyPos - this.yyPos;
        double dist = Math.sqrt(x_dist*x_dist + y_dist*y_dist);
        return dist;
    }

    public double calcForceExertedBy(Planet p) {
        double force = p.mass * this.mass * G / Math.pow(calcDistance(p), 2);
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        double x_dist = p.xxPos - this.xxPos;
        return force * x_dist / dist;
    }

    public double calcForceExertedByY(Planet p) {
        double dist = calcDistance(p);
        double force = calcForceExertedBy(p);
        double y_dist = p.yyPos - this.yyPos;
        return force * y_dist / dist;
    }

    public double calcNetForceExertedByX(Planet[] plst) {
        double sum = 0;
        for (Planet p: plst) {
            if (this.equals(p))
                continue;
            sum += calcForceExertedByX(p);
        }
        return sum;
    }

    public double calcNetForceExertedByY(Planet[] plst) {
        double sum = 0;
        for (Planet p: plst) {
            if (this.equals(p))
                continue;
            sum += calcForceExertedByY(p);
        }
        return sum;
    }

    public void update(double dt, double fX, double fY) {
        double acc_x = fX / mass;
        double acc_y = fY / mass;
        xxVel += acc_x * dt;
        yyVel += acc_y * dt;
        xxPos += xxVel * dt;
        yyPos += yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}




