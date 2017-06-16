import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class HardwareStore_solution {
	public static void main(String[] args) {

		List<Item> allItems = new ArrayList<Item>();

		////////////////////////		
		// Example item usage
		////////////////////////		

		// 10 wrenches in stock, $2.41 profit for each
		Item it = new Item("wrench",10,2.41);
		// "10 new wrench in inventory."

		it.sell(3);
		// "Sold 3 of wrench (7 left in stock)"

		System.out.println("# wrenches sold: " + it.numSold);		
		// # wrenches sold: 3

		System.out.println("# wrenches remaining: " + it.quantityRemaining);
		// # wrenches remaining: 7

		System.out.println("Profit made: $" + it.getProfit());
		// Profit made: $7.23

		System.out.println("Are we out of wrenches? " + it.soldOut());
		// Are we out of wrenches? false

		it.sell(7); 
		// "Sold 7 of wrench (0 left in stock)"

		System.out.println("Are we out of wrenches? " + it.soldOut());		
		// Are we out of wrenches? true

		it.sell(1);
		// "Out of item wrench, sry!! (have: 0, customer wanted: 1)"




		////////////////////////		
		// Stock up
		////////////////////////

		allItems.add(new Item("wrench",10,2.41));
		allItems.add(new Item("ziplock bags",100,1.84));
		allItems.add(new Item("duct tape",40,3.55));
		allItems.add(new Item("garbage bags",100,0.53));
		allItems.add(new Item("shovel",4,8.22));
		allItems.add(new HazardousItem("propane tank",13,4.55,3));
		allItems.add(new HazardousItem("white gas",6,10.36,4));


		////////////////////////////
		// Sell items at random
		////////////////////////////

		// I'll hard-code the random indices
		// in case Random(1234) yields different results
		// btwn Mac / PC.
		int[] item_indices = {1,2,2,4,1,0,2,6,6,5,2,1,2,2,3,5,4,5,3,0,
						 	  4,5,5,0,5,0,1,3,6,0,0,4,6,3,3,5,6,2,5,5,
						 	  5,2,0,3,1,3,3,6,2,0,1,3,6,0,0};

		for( int i=0; i<item_indices.length; i++ ) {
			Item whichItem = allItems.get( item_indices[i] );
			whichItem.sell(1);
		}



		////////////////////////////
		// How's the store doing?
		////////////////////////////



		System.out.println("==============================================");
		System.out.println("Part A (20 points): what am I out of?");


		List<Item> out = new ArrayList<Item>();

		for( Item i : allItems ) {
			if( i.soldOut() ) {
				out.add(i);
			}
		}


		for( Item i : out ) {
			System.out.println("Out of " + i.name);
		}		



		System.out.println("==============================================");
		System.out.println("Part B (15 points): how much profit did I make?");

		double total_profit = 0;

		for( Item i : allItems ) {
			total_profit += i.getProfit();
		}

		System.out.println("Total profit: $" + total_profit);




		System.out.println("==============================================");
		System.out.println("Part C (15 points): how many hazardous items do I have in stock?");
		// Warning: this was vague -- if you have 3 white gasses, does that count as 3 items or 1 item?
		int num_haz = 0;

		for( Item i : allItems ) {
			if( i instanceof HazardousItem ) {
				num_haz += i.quantityRemaining;
			}
		}
		System.out.println("Have " + num_haz + " hazardous items in stock.");

	}

}



class Item {
	String name;
	int    quantityRemaining;
	double profitPerUnit;  // how much we make per unit sold.
	int    numSold;        // how many we've sold.
	public Item( String n, int q, double p) {
		name = n; quantityRemaining = q; profitPerUnit = p;
		System.out.println(q + " new " + n + " in inventory.");
	}

	public void sell(int qty) {
		// If we have at least qty items remaining, sell them.
		// (I.e., update the instance variables appropriately.)
		// Else, apologize.

		if( quantityRemaining >= qty ) {
			numSold += qty;
			quantityRemaining -= qty;
			System.out.println("Sold " + qty + " of " + name + " (" + quantityRemaining + " left in stock)");
		}
		else {
			System.out.println("Out of item " + name + ", sry!!" +
							   " (have: " + quantityRemaining + 
							   ", customer wanted: " + qty + ")" );
		}

	}

	public boolean soldOut() { 
		// return true if we don't have any remaining,
		// false otherwise.

		return quantityRemaining == 0;
	}

	public double getProfit() {
		// How much profit did we make on selling these items?

		return profitPerUnit * numSold;
	}
}


class HazardousItem extends Item {
	int hazardLevel;
	public HazardousItem( String n, int q, double p, int h) {
		super(n,q,p);
		hazardLevel = h;
	}
}


