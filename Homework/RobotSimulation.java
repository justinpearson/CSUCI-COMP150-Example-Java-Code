import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Based on:
// https://tarajava.wordpress.com/2012/02/06/how-to-make-a-simple-animation-in-java/

public class RobotSimulation
{
    public static void main(String[] args)
    {
        // Setup (do not edit).
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel draw=new DrawPanel(); // define this
        frame.getContentPane().add(draw);
        frame.setSize(1000,1000); // window size; you can edit this if you want.
        frame.setVisible(true);
        // Replace this code: Here we declare new objects
        // and add them to the DrawPanel with draw.add().
        draw.addLine( new SpinnyLine()        );
        draw.addLine( new SpinnyLine(600,600) );
        draw.addCircle( new Circle(200,300)     );
        // Run simulation (do not edit).
        for(int i=0;i<200;i++){
            draw.run(i);      // Run the objects forward in time
            draw.repaint();   // Redraw panel
            pause(40);        }
        System.out.println("Done.");
    }

    public static void pause(int ms) {
        try{ Thread.sleep(40); }
        catch (Exception e) { System.out.println("Uh oh"); }
    }
}


class DrawPanel extends JPanel{
    List<SpinnyLine> lines = new ArrayList<SpinnyLine>();
    List<Circle> circles = new ArrayList<Circle>();
    public void addLine( SpinnyLine l  ) { lines.add(l);     }
    public void addCircle( Circle c  )   { circles.add(c); }
    public void run(double t) { 
        for( SpinnyLine l : lines ) l.advance(t); 
        for( Circle c : circles ) c.timestep(t); 
    }
    public void paintComponent(Graphics g) {
        for( SpinnyLine l : lines ) l.drawline(g); 
        for( Circle c : circles ) c.drawcircle(g); 
    }
}


class Circle {
    double x,y;
    double w=.1; // rad/sec
    public Circle() {this(0,0);}
    public Circle(int x, int y) {
        this.x = x; this.y = y; }
    public void drawcircle( Graphics g ) {
        g.setColor(Color.BLACK);
        g.drawOval((int)x,(int)y,200,200); }
    public void timestep(double t) {
        double dx = Math.cos(w*t);
        double dy = Math.sin(w*t);
        x += dx;
        y += dy;
    }}



class SpinnyLine {
    double x,y,x2,y2;
    double w=.2; // rad/sec

    public SpinnyLine() {this(500,500);}
    public SpinnyLine(double x, double y) 
    { this.x=x; this.y=y; this.x2=x+100; this.y2=y+100;}

    public void drawline( Graphics g ) {
        g.setColor(Color.BLACK);
        g.drawLine((int)x, (int)y, (int)x2, (int)y2);
    }
    public void advance(double t) {
        double dx = 100*Math.cos(w*t);
        double dy = 100*Math.sin(w*t);
        x2 = x+dx;
        y2 = y+dy;
    }
}

