import java.util.Random;

public class SimpleFighterFactory {

	public Fighter[] createFighter(String type, int width, int height, int num, int num2, int team, int team2) {
		Fighter fighter = null;
		int canvasWidth = width;
		int canvasHeight = height;
		
		// make a list of warriors
		if (type.equals("warrior")) {
			Fighter[] listOfFighters = new Fighter[num + num2];
			   int radius = 10;
			   Random rand = new Random();
				
			   for (int q = 0; q <= num-1; q++) {
				   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
				   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
				   listOfFighters[q] = new Warrior(radius, x, y, team);;
				}
			   for (int q = num; q <= (num + num2)-1; q++) {
				   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
				   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
				   listOfFighters[q] = new Warrior(radius, x, y, team2);;
			   }
				
			   return listOfFighters;
		// make a list of archers
		} else if (type.equals("archer")) {
			 Fighter[] listOfFighters = new Fighter[num+num2];
			   int radius = 10;
			   Random rand = new Random();
				
			   for (int q = 0; q <= num2-1; q++) {
				   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
				   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
				   listOfFighters[q] = new Archer(radius, x, y, team2);;
				}
			   for (int q = num2; q <= (num + num2)-1; q++) {
				   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
				   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
				   listOfFighters[q] = new Archer(radius, x, y, team);;
			   }
			   
			   return listOfFighters;
		// make a list of cavalry
		} else {
			 Fighter[] listOfFighters = new Fighter[num+num2];
			   int radius = 10;
			   Random rand = new Random();
				
			   for (int q = 0; q <= num2-1; q++) {
				   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
				   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
				   listOfFighters[q] = new Cavalry(radius, x, y, team2);;
				}
			   for (int q = num2; q <= (num + num2)-1; q++) {
				   int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
				   int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
				   listOfFighters[q] = new Cavalry(radius, x, y, team);;
			   }
				
			   return listOfFighters;
		}
	}
}
