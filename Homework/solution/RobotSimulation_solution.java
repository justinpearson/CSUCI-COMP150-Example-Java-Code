import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

// source:
// https://tarajava.wordpress.com/2012/02/06/how-to-make-a-simple-animation-in-java/

public class RobotSimulation_solution
{
    public static void main(String[] args)
    {
        // Setup (do not edit).
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel draw=new DrawPanel();
        frame.getContentPane().add(draw);
        frame.setSize(1000,1000); // you can edit this 
        frame.setVisible(true);




        // Here we declare new RunnableFigures and add them to the DrawPanel with draw.add().
        if( false ) { // turn on/off the solution
            draw.add( new SpinnyLine() );
            draw.add( new SpinnyLine(600,600) );
            draw.add( new Circle(200,300) );
        }
        else {

            // Declare robots, configure them, and add them with draw.add().
            Random rand = new Random();
            for( int i=0; i<10; i++ ) {
            draw.add(new CircleRobot( 
                            rand.nextInt(1000), 
                            rand.nextInt(1000), 
                            rand.nextDouble()*2.0*Math.PI ));

                // draw.addCircle( new Circle( rand.nextInt(1000), rand.nextInt(1000) ) );
            }
            CircleRobot r1 = new CircleRobot(500,600,90);
            r1.setColor(Color.GREEN);
            draw.add(r1);

            Chasebot r2 = new Chasebot(250,250,0);
            r2.setRobot(r1);
            r2.setColor(Color.RED);
            draw.add(r2);

            Fleebot r3 = new Fleebot(300,300,180);
            r3.setColor(Color.YELLOW);
            draw.add(r3);
            r3.setRobot(r2);
        }


        // Run simulation (do not edit).
        for(int i=0;i<200;i++)
        {
            draw.run(i);      // Run the RunnableFigures
            draw.repaint();   // Redraw panel
            pause(40);
        }
        System.out.println("Done.");
    }

    public static void pause(int ms) {
        try{ Thread.sleep(40); }
        catch (Exception e) { System.out.println("Uh oh"); }
    }
}


class DrawPanel extends JPanel{
    ArrayList<RunnableFigure> figures = new ArrayList<RunnableFigure>();

    public void add( RunnableFigure f ) {
        figures.add(f);
    }

    public void run(double t) {
        for( RunnableFigure f : figures )
            f.run(t);
    }

    public void paintComponent(Graphics g)
    { 
        super.paintComponent(g);
        g.drawString("Note: can toggle btwn given code & solution with if( true / false ) on line 27...",200,200); 
        for( RunnableFigure f : figures ) 
            f.draw(g);
    }
}


interface RunnableFigure {
    public void run(double t);
    public void draw(Graphics g);
}

class Circle implements RunnableFigure {
    double x,y;
    double w=.1; // rad/sec

    public Circle() {this(0,0);}
    public Circle(int x, int y) {
        this.x = x; this.y = y; 
    }
    public void draw( Graphics g ) {
        g.setColor(Color.BLACK);
        g.drawOval((int)x,(int)y,200,200);
    }
    public void run(double t) {
        double dx = Math.cos(w*t);
        double dy = Math.sin(w*t);
        x += dx;
        y += dy;
    }
}

class SpinnyLine implements RunnableFigure {
    double x,y,x2,y2;
    double w=.2; // rad/sec

    public SpinnyLine() {this(500,500);}
    public SpinnyLine(double x, double y) 
    { this.x=x; this.y=y; this.x2=x+100; this.y2=y+100;}
    public void draw( Graphics g ) {
        g.setColor(Color.BLACK);
        g.drawLine((int)x, (int)y, (int)x2, (int)y2);
    }
    public void run(double t) {
        double dx = 100*Math.cos(w*t);
        double dy = 100*Math.sin(w*t);
        x2 = x+dx;
        y2 = y+dy;
    }
}


// Students figure out this part.

abstract class Robot implements RunnableFigure{
    public abstract void run(double t);

    double x, y, angle, vel=5;
    double radius = 40;
    Color color = Color.BLUE;

    public Robot(double x, double y, double angle) {
        this.x = x; this.y = y; this.angle = angle;
    }
    public void setColor(Color c) { color = c;}

    public double getX() {return x;}
    public double getY() {return y;}

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(
            (int)(x-radius/2),
            (int)(y-radius/2),
            (int)radius,
            (int)radius  );

        double x2 = x - 100.0*Math.sin(angle);
        double y2 = y + 100.0*Math.cos(angle);

        g.setColor(Color.BLACK);
        g.drawLine((int)x, (int)y, (int)x2, (int)y2);
    }

}




class CircleRobot extends Robot {

    public CircleRobot(double x, double y, double angle) {
        super(x,y,angle);
    }

    public void run(double t) {
        x -= vel*Math.sin(angle);
        y += vel*Math.cos(angle);
        angle += .05;
    }
}

interface TakesRobot {
    void setRobot( Robot r );
}


class Chasebot extends Robot implements TakesRobot {
    Robot target;
    public Chasebot( double x, double y, double angle ) {
        super(x,y,angle);
    }
    public void setRobot( Robot r ) {
        target = r;
    }
    public void run(double t) {
        // Move a little in his direction.
        double dx = target.getX() - x;
        double dy = target.getY() - y;
        double dist = Math.hypot(dx,dy);
        x += 3*dx/dist;
        y += 3*dy/dist;
        angle = Math.atan2(dy,dx)-Math.PI/2;
    }
}

class Fleebot extends Robot implements TakesRobot {
    Robot fleefrom;
    public Fleebot( double x, double y, double angle ) {
        super(x,y,angle);
    }
    public void setRobot( Robot r ) {
        fleefrom = r;
    }
    public void run(double t) {
        // Move away from him.
        double dx = fleefrom.getX() - x;
        double dy = fleefrom.getY() - y;
        double dist = Math.hypot(dx,dy);
        x -= 3*dx/dist;
        y -= 3*dy/dist;
        angle = Math.atan2(dy,dx)+Math.PI/2;
    }


}