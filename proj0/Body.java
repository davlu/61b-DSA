public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double grav_constant = 6.67e-11;
    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }
    public Body(Body b){
        this.xxPos = b.xxPos;
        this.yyPos = b.yyPos;
        this.xxVel = b.xxVel;
        this.yyVel = b.yyVel;
        this.mass = b.mass;
        this.imgFileName = b.imgFileName;
    }
    public double calcDistance(Body b){
        return Math.sqrt((this.xxPos-b.xxPos)*(this.xxPos-b.xxPos)+((this.yyPos-b.yyPos)*(this.yyPos-b.yyPos)));
    }
    public double calcForceExertedBy(Body b){
        return grav_constant * this.mass * b.mass / (this.calcDistance(b)*this.calcDistance(b));
    }
    public double calcForceExertedByX(Body b){
        return this.calcForceExertedBy(b) * (b.xxPos-this.xxPos)/this.calcDistance(b);
    }
    public double calcForceExertedByY(Body b){
        return this.calcForceExertedBy(b) * (b.yyPos-this.yyPos)/this.calcDistance(b);
    }
    public double calcNetForceExertedByX(Body[] b){
        double force = 0.0;
        for(Body x: b){
            if(x.equals(this)){
                continue;
            }
            else{
                force += calcForceExertedByX(x);
            }
        }
        return force;
    }
    public double calcNetForceExertedByY(Body[] b){
        double force = 0.0;
        for(Body x: b){
            if(x.equals(this)){
                continue;
            }
            else{
                force += calcForceExertedByY(x);
            }
        }
        return force;
    }
    public void update(double dt, double fX, double fY){ /**why cant static?*/
        double aX = fX/this.mass;
        double aY = fY/this.mass;
        double newVx = this.xxVel+dt*aX;
        double newVy = this.yyVel+dt*aY;
        double newPx = this.xxPos + newVx*dt;
        double newPy = this.yyPos + newVy*dt;
        this.xxVel = newVx;
        this.yyVel = newVy;
        this.xxPos = newPx;
        this.yyPos = newPy;
    }
    public void draw(){
        StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
    }
}