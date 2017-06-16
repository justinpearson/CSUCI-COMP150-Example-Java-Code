import java.util.Collections;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.Comparator;

class City implements Comparable<City> {
	String name; 
	double pop;
	double crime;
	double salary;
	double traffic;
	double rent;
	public City( String name, double pop, double crime, double salary, double traffic, double rent) {
		this.name = name;
		this.pop = pop;
		this.crime = crime;
		this.salary = salary;
		this.traffic = traffic;
		this.rent = rent;
	}

	public static SalarySort salarySort = new SalarySort();
	public static PostRentSalarySort  postRentSalarySort  = new PostRentSalarySort();

	static class SalarySort implements Comparator<City> {
		public int compare(City c1, City c2) {
			return (int)(c2.salary - c1.salary);
		}
	}
	static class PostRentSalarySort implements Comparator<City> {
		public int compare(City c1, City c2) {
			return (int)((c2.salary-12.0*c2.rent)-(c1.salary-12.0*c1.rent));
		}
	}

	public int compareTo( City c ) {
		return (int)((c.salary-12.0*c.rent)-(this.salary-12.0*this.rent));
	}

	public String toString(){
		return name + "\tPop: " + pop + "\tSal after rent: " + (salary-12*rent) ;
	}
}


public class SortCities_solution {




	public static void main(String[] args) {



		System.out.println("Part A");

		// Fill in cities...

		ArrayList<City> cities = new ArrayList<City>();
		for( int i=0; i<CITY_NAMES.length; i++ ) {
			cities.add( new City(	CITY_NAMES[i], 
									CITY_DATA[i][0], 
									CITY_DATA[i][1],
									CITY_DATA[i][2],
									CITY_DATA[i][3],
									CITY_DATA[i][4]
								));
		}



		System.out.println("Part B");

		Collections.sort(cities);

		System.out.println("Top 5 best cities, by salary-after-rent:");
		for( City c : cities.subList(0,5) ) {
			System.out.println(c);
		}



		System.out.println("Part C");

		Collections.sort(cities, City.salarySort );

		System.out.println("Top 5 best cities, by salary:");
		for( City c : cities.subList(0,5) ) {
			System.out.println(c);
		}


		Collections.sort(cities, City.postRentSalarySort );

		System.out.println("Top 5 best cities, by salary-after-rent:");
		for( City c : cities.subList(0,5) ) {
			System.out.println(c);
		}


	}

public static final String[] CITY_NAMES = {
"New York City",
"Los Angeles",
"Chicago",
"Houston",
"Philadelphia",
"Phoenix",
"San Antonio",
"San Diego",
"Dallas",
"San Jose",
"Indianapolis",
"Austin",
"Jacksonville",
"San Francisco",
"Columbus",
"Fort Worth",
"Charlotte",
"Detroit",
"El Paso",
"Memphis",
"Nashville",
"Boston",
"Seattle",
"Denver",
"Baltimore",
"Portland",
"Oklahoma City",
"Milwaukee",
"Louisville",
"Albuquerque",
"Tucson",
"Fresno",
"Sacramento",
"Long Beach",
"Kansas City",
"Mesa",
"Virginia Beach",
"Atlanta",
"Colorado Springs",
"Raleigh",
"Omaha",
"Miami",
"Oakland",
"Tulsa",
"Minneapolis",
"Cleveland",
"Wichita",
"Arlington",
"Honolulu",
"New Orleans",
"Bakersfield",
"Tampa",
"Anaheim",
"Aurora",
"Santa Ana",
"Saint Louis",
"Riverside",
"Corpus Christi",
"Pittsburgh",
"Anchorage",
"Stockton",
"Cincinnati",
"Saint Paul",
"Toledo",
"Newark",
"Greensboro",
"Plano",
"Henderson",
"Buffalo",
"Jersey City",
"Chula Vista",
"Orlando",
"Saint Petersburg",
"Norfolk",
"Chandler",
"Laredo",
"Madison",
"Durham",
"Salem",
"Garland",
"Glendale",
"Hialeah",
"Baton Rouge",
"Irvine",
"Chesapeake",
"Irving",
"Scottsdale",
"North Las Vegas",
"Fremont",
"Gilbert",
"San Bernardino",
"Boise",
"Birmingham",
"Richmond",
"Spokane",
"Rochester",
"Tacoma",
"Fontana",
"Oxnard",
"Aurora",
"Moreno Valley",
"Akron",
"Yonkers",
"Little Rock",
"Huntington Beach",
"Glendale",
"Grand Rapids",
"Salt Lake City",
"Worcester",
"Knoxville",
"Grand Prairie",
"Newport News",
"Brownsville",
"Overland Park",
"Providence",
"Jackson",
"Garden Grove",
"Oceanside",
"Fort Lauderdale",
"Rancho Cucamonga",
"Ontario",
"Tempe",
"Vancouver",
"Cape Coral",
"Pembroke Pines",
"Peoria",
"Lancaster",
"Elk Grove",
"Corona",
"Eugene",
"Salem",
"Palmdale",
"Springfield",
"Pasadena",
"Pomona",
"Hayward",
"Joliet",
"Escondido",
"Kansas City",
"Torrance",
"Alexandria",
"Sunnyvale",
"Cary",
"Lakewood",
"Hollywood",
"Paterson",
"Naperville",
"Mesquite",
"Dayton",
"Orange",
"Fullerton",
"Pasadena",
"Hampton",
"McAllen",
"Warren",
"West Valley City",
"Columbia",
"New Haven",
"Sterling Heights",
"Olathe",
"Ramapo",
"Miramar",
"Frisco",
"Elizabeth",
"Bellevue",
"Charleston",
"Carrollton",
"Coral Springs",
"Hartford",
"Roseville",
"Thornton",
"Amherst",
"Kent",
"Surprise",
"Santa Clara",
"Allentown",
"Beaumont",
"Independence",
"Provo",
"Berkeley",
"El Monte",
"Downey",
"Costa Mesa",
"Inglewood",
"Arvada",
"Carlsbad",
"Westminster",
"Gresham",
"Clearwater",
"West Jordan",
"West Covina",
"Round Rock",
"Richmond",
"Norwalk",
"Everett",
"Burbank",
"Daly City",
"Richardson",
"Pompano Beach",
"Broken Arrow",
"North Charleston",
"West Palm Beach",
"Boulder",
"Rialto",
"El Cajon",
"Edison"
};



// For each row, the values are:
// 	- city population (# people)
// 	- rate of violent crime (crimes per person per year)
// 	- average income per person ($/year)
// 	- traffic delay (minutes spent in traffic per person per day)
// 	- 1-bedroom rent ($/month)

public static final double[][] CITY_DATA = {
{8491079, 0.0055181, 31661., 9.725274725274726, 1215.},
{3928864, 0.006253900000000001, 28111., 10.054945054945057, 1083.},
{2722389, 0.011256, 28202., 8.406593406593407, 826.},
{2239558, 0.0112558, 27029., 8.571428571428573, 750.},
{1560297, 0.012382400000000002, 21946., 7.912087912087912, 942.},
{1537058, 0.0054651000000000005, 24110., 5.769230769230769, 774.},
{1436697, 0.0057091, 22568., 6.263736263736264, 683.},
{1381069, 0.004511, 33012., 6.098901098901099, 1032.},
{1281047, 0.0079216, 27011., 7.417582417582418, 722.},
{1015785, 0.0036048000000000005, 33807., 6.428571428571429, 1293.},
{858325, 0.011998, 24182., 6.758241758241758, 625.},
{912791, 0.005233, 31387., 7.252747252747254, 853.},
{853382, 0.0083598, 25433., 4.945054945054946, 778.},
{852469, 0.0073565, 47278., 10.054945054945057, 1551.},
{835957, 0.007032000000000001, 24075., 6.593406593406594, 620.},
{812238, 0.005849700000000001, 24338., 7.417582417582418, 725.},
{809958, 0.0072328, 31653., 6.593406593406594, 686.},
{680250, 0.019668900000000003, 14861., 6.593406593406594, 646.},
{679036, 0.004573300000000001, 19262., 5.2747252747252755, 605.},
{656861, 0.0180621, 21368., 6.263736263736264, 658.},
{668347, 0.0114049, 27369., 7.747252747252748, 711.},
{655884, 0.009919500000000001, 33589., 8.736263736263737, 1164.},
{668342, 0.0064080000000000005, 42369., 7.912087912087912, 913.},
{663862, 0.0057766, 32597., 7.417582417582418, 742.},
{622793, 0.015129400000000001, 24155., 6.758241758241758, 1001.},
{619360, 0.005535700000000001, 31249., 7.252747252747254, 774.},
{620602, 0.009302600000000001, 25629., 6.263736263736264, 565.},
{599642, 0.0108885, 19199., 4.615384615384616, 646.},
{597337, 0.0059705999999999995, 25623., 5.769230769230769, 567.},
{557169, 0.0076927, 26623., 4.780219780219781, 655.},
{527972, 0.0064966, 20393., 6.263736263736264, 633.},
{515986, 0.006093, 19752., 2.472527472527473, 655.},
{485199, 0.008855900000000002, 25645., 5.2747252747252755, 854.},
{473577, 0.0068130000000000005, 26866., 10.054945054945057, 1083.},
{470800, 0.013004300000000002, 26806., 4.450549450549451, 687.},
{464704, 0.0042478, 24570., 5.769230769230769, 774.},
{450980, 0.0020863, 31878., 7.0879120879120885, 939.},
{456002, 0.0115012, 35719., 8.406593406593407, 756.},
{445830, 0.0049001, 29064., 4.2857142857142865, 622.},
{439896, 0.004928500000000001, 30306., 3.791208791208792, 722.},
{446599, 0.005333600000000001, 26880., 3.956043956043956, 629.},
{430332, 0.011886800000000001, 20886., 7.747252747252748, 910.},
{413775, 0.0167914, 31930., 10.054945054945057, 1255.},
{399682, 0.0111602, 26833., 5.2747252747252755, 567.},
{407207, 0.0110868, 30734., 5.604395604395604, 756.},
{389521, 0.013955, 16812., 5.10989010989011, 592.},
{388413, 0.0088267, 25204., 3.296703296703297, 556.},
{383204, 0.006146100000000001, 25468., 7.417582417582418, 725.},
{374701, 0.0027968000000000003, 31930., 7.417582417582418, 1382.},
{384320, 0.007769900000000001, 26131., 4.615384615384616, 765.},
{368759, 0.006343400000000001, 23261., 1.978021978021978, 623.},
{358699, 0.0075225000000000005, 28779., 6.263736263736264, 758.},
{346997, 0.0035241000000000005, 23137., 10.054945054945057, 1312.},
{353108, 0.0047035, 24528., 7.417582417582418, 742.},
{334909, 0.005088500000000001, 16330., 10.054945054945057, 1312.},
{317419, 0.020700500000000004, 22551., 5.10989010989011, 631.},
{319504, 0.0051189, 22571., 6.263736263736264, 882.},
{320434, 0.008225900000000001, 23970., 2.307692307692308, 654.},
{305412, 0.0098869, 26535., 6.428571428571429, 633.},
{301010, 0.0087822, 36145., 2.802197802197802, 895.},
{302389, 0.012672300000000001, 19906., 1.978021978021978, 709.},
{298165, 0.0119196, 24538., 6.098901098901099, 554.},
{297640, 0.0076269000000000016, 25686., 5.604395604395604, 756.},
{281031, 0.011169300000000002, 18729., 4.2857142857142865, 516.},
{280579, 0.0093015, 17161., 9.725274725274726, 1059.},
{282586, 0.007662200000000001, 25757., 4.450549450549451, 598.},
{278480, 0.0017012000000000002, 40869., 7.417582417582418, 722.},
{277440, 0.0023369000000000003, 34302., 7.252747252747254, 843.},
{258703, 0.0145912, 20245., 5.43956043956044, 570.},
{262146, 0.0074359000000000005, 32289., 9.725274725274726, 1089.},
{260988, 0.0033223000000000003, 25193., 6.098901098901099, 1032.},
{262372, 0.011968900000000003, 25550., 7.417582417582418, 825.},
{253693, 0.013611900000000001, 27825., 6.263736263736264, 758.},
{245428, 0.006559000000000001, 24631., 7.0879120879120885, 939.},
{254276, 0.0028896, 32497., 5.769230769230769, 774.},
{252309, 0.0057018, 14938., 3.131868131868132, 618.},
{245691, 0.0036381000000000004, 31105., 3.296703296703297, 742.},
{251893, 0.0069937, 27748., 3.791208791208792, 711.},
{239269, 0.007528900000000001, 24569., 3.296703296703297, 566.},
{235501, 0.0027779000000000002, 21958., 7.417582417582418, 722.},
{237517, 0.004496600000000001, 22867., 5.769230769230769, 774.},
{235563, 0.004428500000000001, 14678., 7.747252747252748, 910.},
{228895, 0.0126486, 24048., 6.923076923076923, 670.},
{248531, 0.0007094, 43271., 10.054945054945057, 1312.},
{233371, 0.0037221, 30138., 7.0879120879120885, 939.},
{232406, 0.0029835000000000005, 26970., 7.417582417582418, 722.},
{230512, 0.0017147000000000002, 50525., 5.769230769230769, 774.},
{230788, 0.007170200000000001, 21349., 7.252747252747254, 843.},
{228758, 0.0024172, 38739., 10.054945054945057, 1255.},
{239277, 0.0008369, 31436., 5.769230769230769, 774.},
{215213, 0.0095551, 15322., 6.263736263736264, 882.},
{216282, 0.0026061000000000005, 27897., 2.6373626373626378, 572.},
{212247, 0.012367300000000001, 19615., 5.769230769230769, 627.},
{217853, 0.0080253, 26796., 4.780219780219781, 830.},
{212052, 0.0062583000000000005, 24034., 3.791208791208792, 546.},
{207294, 0.009935000000000001, 18757., 4.615384615384616, 685.},
{205159, 0.0098402, 25990., 7.912087912087912, 767.},
{204950, 0.0045139, 19203., 6.263736263736264, 882.},
{205437, 0.0041151, 20579., 4.2857142857142865, 1102.},
{200456, 0.0038485000000000004, 26091., 8.406593406593407, 826.},
{202976, 0.0046724, 18130., 6.263736263736264, 882.},
{197859, 0.009278600000000001, 19896., 3.791208791208792, 554.},
{200667, 0.0047727, 29499., 9.725274725274726, 1177.},
{197706, 0.014694700000000002, 29841., 4.2857142857142865, 615.},
{200809, 0.0019543000000000004, 42113., 10.054945054945057, 1312.},
{200167, 0.0015300000000000001, 29766., 10.054945054945057, 1083.},
{193792, 0.0083151, 20147., 3.956043956043956, 590.},
{190884, 0.0070605, 27333., 4.945054945054946, 707.},
{183016, 0.010029500000000002, 24470., 5.43956043956044, 716.},
{184281, 0.0105784, 23173., 6.098901098901099, 628.},
{185453, 0.0031803, 22510., 7.417582417582418, 722.},
{182965, 0.005383700000000001, 25549., 7.0879120879120885, 939.},
{183046, 0.0025294000000000002, 13556., 4.120879120879121, 542.},
{184525, 0.0017733000000000002, 39985., 4.450549450549451, 687.},
{179154, 0.0068855, 21512., 4.945054945054946, 748.},
{171155, 0.008767400000000002, 18876., 4.120879120879121, 639.},
{175078, 0.0032622000000000007, 20918., 10.054945054945057, 1312.},
{174558, 0.0043909000000000005, 27173., 6.098901098901099, 1032.},
{176013, 0.0080955, 35919., 7.747252747252748, 992.},
{174305, 0.0019357, 32187., 10.054945054945057, 882.},
{169089, 0.004226000000000001, 18782., 10.054945054945057, 882.},
{172816, 0.0051948, 26471., 5.769230769230769, 774.},
{169294, 0.0039722, 25644., 7.252747252747254, 774.},
{169854, 0.0020202, 23742., 4.945054945054946, 705.},
{164626, 0.0020617, 28615., 7.747252747252748, 992.},
{166934, 0.0019530000000000003, 29048., 5.769230769230769, 774.},
{161043, 0.0065617, 20393., 2.472527472527473, 1083.},
{163553, 0.0043251, 29332., 5.2747252747252755, 854.},
{161486, 0.0014038, 27200., 6.263736263736264, 882.},
{160561, 0.0031047000000000006, 25567., 2.1428571428571432, 621.},
{161637, 0.0037083000000000003, 23372., 4.450549450549451, 578.},
{158279, 0.005588300000000001, 18695., 2.472527472527473, 1083.},
{153991, 0.012564100000000002, 18016., 4.615384615384616, 761.},
{153887, 0.0048039, 20305., 8.571428571428573, 750.},
{153350, 0.0063178, 16986., 10.054945054945057, 1083.},
{154612, 0.00521, 25019., 10.054945054945057, 1255.},
{147928, 0.0031105000000000004, 23600., 8.406593406593407, 750.},
{150243, 0.004576800000000001, 22526., 6.098901098901099, 1032.},
{149636, 0.006615, 18771., 4.450549450549451, 687.},
{148495, 0.0018355000000000003, 36240., 10.054945054945057, 1083.},
{150575, 0.0019638, 54767., 11.043956043956044, 1239.},
{149980, 0.0012789000000000001, 45636., 6.428571428571429, 1293.},
{155227, 0.0009869, 42332., 3.791208791208792, 722.},
{149643, 0.0048998, 31175., 7.417582417582418, 742.},
{148047, 0.0049154, 26546., 7.747252747252748, 992.},
{146753, 0.009093200000000001, 15592., 9.725274725274726, 1182.},
{146128, 0.0006909, 46598., 8.406593406593407, 826.},
{144416, 0.0040168, 21641., 7.417582417582418, 722.},
{141003, 0.010080700000000001, 16427., 3.956043956043956, 543.},
{139812, 0.0011668000000000002, 32633., 10.054945054945057, 1312.},
{139677, 0.0039705, 30503., 10.054945054945057, 1312.},
{140881, 0.0033527, 40839., 10.054945054945057, 1083.},
{136879, 0.0031110000000000005, 25198., 7.0879120879120885, 939.},
{138596, 0.0026245, 21123., 4.615384615384616, 503.},
{135099, 0.005828400000000001, 21763., 6.593406593406594, 646.},
{134495, 0.004891, 18348., 4.945054945054946, 707.},
{132067, 0.0104626, 24837., 4.945054945054946, 669.},
{130282, 0.0176534, 23026., 5.769230769230769, 982.},
{131741, 0.0016792, 27047., 6.593406593406594, 646.},
{133062, 0.0021408000000000004, 31623., 4.450549450549451, 687.},
{133351, 0.0008223000000000001, 26749., 9.725274725274726, 1215.},
{134989, 0.0048351, 25005., 7.747252747252748, 992.},
{145035, 0.0009977, 43073., 7.417582417582418, 722.},
{128705, 0.010311400000000002, 19169., 9.725274725274726, 1059.},
{136426, 0.0012954, 48719., 7.912087912087912, 913.},
{130113, 0.0052339000000000005, 31554., 4.945054945054946, 754.},
{128353, 0.0019618, 32177., 7.417582417582418, 722.},
{127952, 0.0026899000000000003, 30663., 7.747252747252748, 992.},
{124705, 0.012922300000000001, 16448., 6.263736263736264, 939.},
{128615, 0.0027643000000000004, 33788., 5.2747252747252755, 854.},
{130307, 0.0025210000000000002, 26566., 7.417582417582418, 742.},
{124837, 0.0011685, 35614., 5.43956043956044, 570.},
{125560, 0.006009700000000001, 25826., 7.912087912087912, 913.},
{126275, 0.0010603000000000001, 24604., 5.769230769230769, 774.},
{122192, 0.0015121000000000002, 39622., 6.428571428571429, 1293.},
{119104, 0.006978700000000001, 17576., 4.945054945054946, 771.},
{117585, 0.0090895, 24310., 4.120879120879121, 650.},
{117494, 0.0061306, 23238., 4.450549450549451, 687.},
{114801, 0.0013978, 16865., 4.120879120879121, 610.},
{118853, 0.0060777, 40546., 10.054945054945057, 1255.},
{116631, 0.005848300000000001, 14764., 10.054945054945057, 1083.},
{114172, 0.0041265, 22761., 10.054945054945057, 1083.},
{112784, 0.0027599000000000005, 33093., 10.054945054945057, 1312.},
{111905, 0.009218200000000001, 20588., 10.054945054945057, 1083.},
{113574, 0.0017602000000000002, 33019., 7.417582417582418, 742.},
{112299, 0.0029142000000000005, 42742., 6.098901098901099, 1032.},
{112090, 0.0022283000000000003, 31019., 7.417582417582418, 742.},
{109892, 0.0037282, 21611., 7.252747252747254, 774.},
{110703, 0.008768, 28490., 6.263736263736264, 758.},
{110920, 0.0015404000000000001, 22236., 4.945054945054946, 707.},
{108455, 0.0029760000000000003, 25388., 10.054945054945057, 1083.},
{112744, 0.001158, 29695., 7.252747252747254, 853.},
{108565, 0.010676099999999999, 26084., 10.054945054945057, 1255.},
{107096, 0.0044355, 19427., 10.054945054945057, 1083.},
{106736, 0.006197200000000001, 25102., 7.912087912087912, 913.},
{105368, 0.0024504, 33964., 10.054945054945057, 1083.},
{106094, 0.0023696000000000004, 28498., 10.054945054945057, 1551.},
{108617, 0.0022401, 34984., 7.417582417582418, 722.},
{106105, 0.010104100000000001, 25665., 7.747252747252748, 992.},
{104726, 0.0019065000000000002, 28977., 5.2747252747252755, 567.},
{106749, 0.010345700000000001, 19594., 4.945054945054946, 754.},
{104031, 0.0089815, 30493., 7.747252747252748, 962.},
{105112, 0.0024491, 37734., 3.626373626373627, 952.},
{102741, 0.005453500000000001, 15862., 6.263736263736264, 882.},
{103091, 0.004985600000000001, 20656., 6.098901098901099, 1032.},
{101970, 0.0026571, 35683., 9.725274725274726, 1184.}
};



}