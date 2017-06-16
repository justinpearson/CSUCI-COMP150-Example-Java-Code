import java.util.List;
import java.util.ArrayList;

public class HardwareStore {
    public static void main(String[] args) {

        ////////////////////////        
        // Example item usage
        ////////////////////////        

        // 10 wrenches in stock, $2.41 profit for each
        Item it = new Item("wrench",10,2.41);
        // "10 new wrench in inventory."

        it.sell(3);
        // "Sold 3 of wrench (7 left in stock)"

        System.out.println("# wrenches sold: " + it.numSold);       
        // "# wrenches sold: 3"

        System.out.println("# wrenches remaining: " + it.quantityRemaining);
        // "# wrenches remaining: 7"

        System.out.println("Profit made: $" + it.getProfit());
        // "Profit made: $7.23"

        System.out.println("Are we out of wrenches? " + it.soldOut());
        // "Are we out of wrenches? false"

        it.sell(7); 
        // "Sold 7 of wrench (0 left in stock)"

        System.out.println("Are we out of wrenches? " + it.soldOut());      
        // "Are we out of wrenches? true"

        it.sell(1);
        // "Out of item wrench, sry!! (have: 0, customer wanted: 1)"




        ////////////////////////        
        // Stock up
        ////////////////////////

        List<Item> inventory = new ArrayList<Item>();
        inventory.add(new Item("wrench",10,2.41));
        inventory.add(new Item("ziplock bags",100,1.84));
        inventory.add(new Item("duct tape",40,3.55));
        inventory.add(new Item("garbage bags",100,0.53));
        inventory.add(new Item("shovel",4,8.22));
        inventory.add(new HazardousItem("propane tank",13,4.55,3));
        inventory.add(new HazardousItem("white gas",6,10.36,4));


        ////////////////////////////
        // Sell items at random
        ////////////////////////////

        // I'll hard-code the random indices
        // just in case Random(1234) yields different results
        // btwn Mac / PC. (It shouldn't, but I don't trust these newfangled computers)
        int[] item_indices = {1,2,2,4,1,0,2,6,6,5,2,1,2,2,3,5,4,5,3,0,
                              4,5,5,0,5,0,1,3,6,0,0,4,6,3,3,5,6,2,5,5,
                              5,2,0,3,1,3,3,6,2,0,1,3,6,0,0};

        for( int i=0; i<item_indices.length; i++ ) {
            Item whichItem = inventory.get( item_indices[i] );
            whichItem.sell(1);
        }



        ////////////////////////////
        // How's the store doing?
        ////////////////////////////



        System.out.println("==============================================");
        System.out.println("Part A (20 points): what am I out of?");

        List<Item> outOfThese = new ArrayList<Item>();

        // Your code here.

        for( Item i : outOfThese ) {
            System.out.println("Out of " + i.name);
        }



        System.out.println("==============================================");
        System.out.println("Part B (15 points): how much profit did I make?");

        double total_profit = 0;

        // Your code here.

        System.out.println("Total profit: $" + total_profit);


        System.out.println("==============================================");
        System.out.println("Part C (15 points): how many hazardous items do I have in stock?");

        int num_haz = 0;

        // Your code here.

        System.out.println("Have " + num_haz + " hazardous items in stock.");
    }

}



class Item {
    String name;
    int    quantityRemaining;
    double profitPerUnit;  // how much we make per unit sold.
    int    numSold;        // how many we've sold so far (inits to 0).
    public Item( String name, int quantityRemaining, double profitPerUnit) {

        // Your code here.

    }

    public void sell(int qty) {
        // If we have at least qty items remaining, sell them.
        // (I.e., update the instance variables appropriately.)
        // Else, apologize.

        // Your code here.
    }

    public boolean soldOut() { 
        // return true if we don't have any remaining,
        // false otherwise.

        // Your code here.
    }

    public double getProfit() {
        // How much profit did we make on selling these items?

        // Your code here.
    }
}


class HazardousItem extends Item {
    int hazardLevel;
    public HazardousItem( String n, int q, double p, int h) {
        super(n,q,p);
        hazardLevel = h;
    }
}


