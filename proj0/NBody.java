public class NBody {
    public static void main(String[] args){
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double universe_radius = readRadius(filename);
        Body[] bodies = readBodies(filename);
        String imageToDraw = "images/starfield.jpg";
        StdDraw.setScale(-universe_radius, universe_radius);
        for(int t = 0; t<=T; t+=dt) {
            StdDraw.enableDoubleBuffering();
            double[] xForces = new double[bodies.length];
            double[] yForces = new double[bodies.length];
            for (int b =0; b<bodies.length;b++) {
                xForces[b] = bodies[b].calcNetForceExertedByX(bodies);
                yForces[b] = bodies[b].calcNetForceExertedByY(bodies);
            }
            for(int b =0; b<bodies.length;b++){
                bodies[b].update(dt, xForces[b], yForces[b]);
            }
            StdDraw.picture(0,0,imageToDraw);
            for(Body b: bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", universe_radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
    public static double readRadius(String fileName){  /**must have static*/
        In in = new In(fileName);
        in.readInt();
        double radius = in.readDouble();
        return radius;
    }
    public static Body[] readBodies(String fileName){
        In in = new In(fileName);
        Body[] bodyArray = new Body[5];
        in.readInt();
        in.readDouble();
        for(int i = 0; i<5; i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String f_name = in.readString();
            bodyArray[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, f_name);
        }
        return bodyArray;
    }
}