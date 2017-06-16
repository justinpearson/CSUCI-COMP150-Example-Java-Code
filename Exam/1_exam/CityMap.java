import javax.swing.JPanel;
import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class CityMap {
    public static void main(String[] args) throws IOException {

        ////////////////////////////////////////////////
        // Do not edit anything in main(). 
        // If your LabeledPoint, City, and Itinerary 
        // classes work properly, main should run 
        // as described in the problem statement.
        ////////////////////////////////////////////////

        // Setup.
        JFrame frame=new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        DrawPanel draw=new DrawPanel();
        frame.getContentPane().add(draw);
        frame.setSize(1000,600); 
        frame.setVisible(true);


        // Part A: Test the LabeledPoint class.

        draw.add( new LabeledPoint("Hello",100,50));
        draw.add( new LabeledPoint("World",200,50));

        // Should match CityMap-Part-A.png



        // Part B: Load the cities and plot them.

        List<City> cities = City.load("cities_xy_nospaces.tsv");        

        for( City c : cities ) {
            draw.add(c);
        }

        // Should match CityMap-Part-B.png



        // Part C: Make an itinerary and put top 4 biggest cities into it.

        Itinerary itinerary = new Itinerary();
        for( int i=0; i<4; i++ ) {
            itinerary.add(cities.get(i));
        }
        draw.add(itinerary);

        System.out.println("4-city itinerary length: " + itinerary.totalDistance() + " pixels.");


        // Should match CityMap-Part-C.png




        // Bonus:

        // Example of cool functionality: Show me the shortest tour of all 50 cities.
        // I.e., in what order should I visit the cities so that I visit all of them,
        // while traveling the shortest distance?

        // Finding the order of the cities with the shortest distance is 
        // a famously hard problem called the "Traveling Salesman Problem".
        // You will probably learn some methods for solving it in COMP 151 
        // (or any class on algorithms). 

        Itinerary tour = new Itinerary();

        tour.setColor(Color.BLUE);

        final String[] cityNames = {"New York City", "Philadelphia", "Baltimore", "Washington", "Virginia Beach", 
        "Raleigh", "Charlotte", "Miami", "Jacksonville", "Atlanta", "Nashville", "Memphis", 
        "New Orleans", "Houston", "San Antonio", "Austin", "Fort Worth", "Dallas", 
        "Oklahoma City", "Tulsa", "Wichita", "Kansas City", "Omaha", "Denver", 
        "Colorado Springs", "Albuquerque", "El Paso", "Tucson", "Mesa", "Phoenix", 
        "Las Vegas", "San Diego", "Long Beach", "Los Angeles", "Fresno", "San Jose", 
        "San Francisco", "Oakland", "Sacramento", "Portland", "Seattle", "Minneapolis", 
        "Milwaukee", "Chicago", "Indianapolis", "Louisville", "Columbus", "Detroit", 
        "Cleveland", "Boston", "New York City"};

        // For each name,
        for( String cityName : cityNames ) {
        // Find and add the city with that name.
            for( City c : cities ) {
                if( c.label.equals(cityName)) {
                    tour.add(c);
                }
            }
        }

        draw.add(tour);
        System.out.println("Shortest tour: " + tour.totalDistance() + " pixels.");


        // Should match CityMap-Part-Bonus.png





        // Draw it. (Do not edit.)
        draw.repaint();

        System.out.println("Done.");        
    }





}

//////////////////////////////////////////////
// Do not edit DrawPanel.
//////////////////////////////////////////////


class DrawPanel extends JPanel{
    ArrayList<Drawable> drawables = new ArrayList<Drawable>();

    // Ugh: 
    // synchronized(this) tells compiler 'only 1 thread at a time 
    // is allowed to access this data'. Needed here because we 
    // make the Jframe visible, then add a ton of stuff to the 
    // DrawPanel while Swing is trying to keep the state of the JFrame 
    // up-to-date with its own drawing threads. Adding shit to the DrawPanel 
    // while Swing is trying to update the window
    // results in a ConcurrentModificationException at runtime. 
    // Adding 'synchronized' fixes it.
    //
    // Can bypass this nonsense by just making the JFrame visible
    // as the last step in the program, but then the students wouldn't be able to 
    // single-step thru with the debugger.  :(


    // http://www.javamex.com/tutorials/synchronization_concurrency_synchronized1.shtml

    public void add( Drawable d ) {
        synchronized(this) {
           drawables.add(d);
        }
    }

    public void paintComponent(Graphics g)
    { 
        synchronized(this) {
            super.paintComponent(g);
            setBackground(Color.WHITE);
            for( Drawable d : drawables ) {
                d.draw(g);
            }
        }
    }
}




///////////////////////////////////////
// Student code starts here.
///////////////////////////////////////


