import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
import java.util.Random;
import javax.swing.*;
/**
 * The control logic and main display panel for game.
 */
//gameworld
public class BattleWorld extends JPanel {
   private static final int UPDATE_RATE = 30;  // Frames per second (fps)
   
   int numWarriorsTeam1 = 10;
   int numWarriorsTeam2 = 10;
   
   int numArchersTeam1 = 10;
   int numArchersTeam2 = 10;
   
   int numCavalryTeam1 = 10;
   int numCavalryTeam2 = 10;
   
   SimpleFighterFactory factory = new SimpleFighterFactory();
   
   Fighter[] listOfWarriors;
   Fighter[] listOfArchers;
   Fighter[] listOfCavalry;
   private General general;
   
   private ContainerBox box;  // The container rectangular box
  
   private DrawCanvas canvas; // Custom canvas for drawing the box/ball
   private int canvasWidth;
   private int canvasHeight;
  
   /**
    * Constructor to create the UI components and init the game objects.
    * Set the drawing canvas to fill the screen (given its width and height).
    * 
    * @param width : screen width
    * @param height : screen height
    */
   public BattleWorld(int width, int height) {
  
      canvasWidth = width;
      canvasHeight = height;
      
      // Init the ball at a random location (inside the box) and moveAngle
      Random rand = new Random();
      int radius = 10;
      int x = rand.nextInt(canvasWidth - radius * 2 - 20) + radius + 10;
      int y = rand.nextInt(canvasHeight - radius * 2 - 20) + radius + 10;
      int speed = 5;
      int angleInDegree = rand.nextInt(360);
      
      listOfWarriors = factory.createFighter("warrior", canvasWidth, canvasHeight, numWarriorsTeam1, numWarriorsTeam2, 1, 2);
      listOfArchers = factory.createFighter("archer", canvasWidth, canvasHeight, numArchersTeam1, numArchersTeam2, 1, 2);
      listOfCavalry = factory.createFighter("cavalry", canvasWidth, canvasHeight, numCavalryTeam1, numCavalryTeam2, 1, 2);
      
      for (int f=0; f < listOfWarriors.length; f++) {
    	  if(rand.nextInt(100) >= 50) {
    		  listOfWarriors[f] = new Armor(listOfWarriors[f]).returnNewFighter();
    		  if(rand.nextInt(100) <= 15) {
    			  listOfWarriors[f] = new EnchantedWeapon(listOfWarriors[f]).returnNewFighter();  
    		  }
    	  }
      }
      for (int f=0; f < listOfArchers.length; f++) {
    	  if(rand.nextInt(100) >= 50) {
    		  listOfArchers[f] = new Armor(listOfArchers[f]).returnNewFighter();  
    		  if (rand.nextInt(100) <= 15) {
    			  listOfArchers[f] = new EnchantedWeapon(listOfArchers[f]).returnNewFighter();
    		  }
    	  }
      }
      for (int f=0; f < listOfCavalry.length; f++) {
    	  if(rand.nextInt(100) >= 50) {
    		  listOfCavalry[f] = new Armor(listOfCavalry[f]).returnNewFighter();
    		  if (rand.nextInt(100) <= 15) {
    			  listOfCavalry[f] = new EnchantedWeapon(listOfCavalry[f]).returnNewFighter();
    		  }
    	  }
      }
      
      
      
      
      
      general = new General(listOfWarriors, listOfArchers, listOfCavalry);
     
      // Init the Container Box to fill the screen
      box = new ContainerBox(0, 0, canvasWidth, canvasHeight, Color.BLACK, Color.WHITE);
     
      // Init the custom drawing panel for drawing the game
      canvas = new DrawCanvas();
      this.setLayout(new BorderLayout());
      this.add(canvas, BorderLayout.CENTER);
      
      // Handling window resize.
      this.addComponentListener(new ComponentAdapter() {
         @Override
         public void componentResized(ComponentEvent e) {
            Component c = (Component)e.getSource();
            Dimension dim = c.getSize();
            canvasWidth = dim.width;
            canvasHeight = dim.height;
            // Adjust the bounds of the container to fill the window
            box.set(0, 0, canvasWidth, canvasHeight);
         }
      });
  
      // Start the ball bouncing
      gameStart();
   }
   
   /** Start the ball bouncing. */
   public void gameStart() {
      // Run the game logic in its own thread.
      Thread gameThread = new Thread() {
         public void run() {
            while (true) {
               // Execute one time-step for the game 
               gameUpdate();
               // Refresh the display
               repaint();
               // Delay and give other thread a chance
               try {
                  Thread.sleep(1000 / UPDATE_RATE);
               } catch (InterruptedException ex) {}
            }
         }
      };
      gameThread.start();  // Invoke GaemThread.run()
   }
   
   /** 
    * One game time-step. 
    * Update the game objects, with proper collision detection and response.
    */
   public void gameUpdate() {
	  for (int x = 0; x < (numWarriorsTeam1 + numWarriorsTeam2); x++) {
		  listOfWarriors[x].update(general.findClosestFighter(listOfWarriors[x]), listOfWarriors[x], box);
	  }
	  for (int x = 0; x < numArchersTeam1 + numArchersTeam2; x++) {
		  listOfArchers[x].update(general.findClosestFighter(listOfArchers[x]), listOfArchers[x], box);
	  }
	  for (int x = 0; x < numCavalryTeam1 + numCavalryTeam2; x++) {
		  listOfCavalry[x].update(general.findClosestFighter(listOfCavalry[x]), listOfCavalry[x], box);
	  }
      
   }
   
   /** The custom drawing panel for the bouncing ball (inner class). */
   class DrawCanvas extends JPanel {
      /** Custom drawing codes */
      @Override
      public void paintComponent(Graphics g) {
         super.paintComponent(g);    // Paint background
         // Draw the box and the ball
         box.draw(g);
         for (int x = 0; x < (numWarriorsTeam1 + numWarriorsTeam2); x++) {
        	 listOfWarriors[x].draw(g);
         }
         for (int x = 0; x < numArchersTeam1 + numArchersTeam2; x++) {
        	 listOfArchers[x].draw(g);
        	 listOfArchers[x].drawAttack(g);
         }
         for (int x = 0; x < numCavalryTeam1 + numCavalryTeam2; x++) {
        	 listOfCavalry[x].draw(g);
         }
         
         // Display ball's information
         g.setColor(Color.WHITE);
         g.setFont(new Font("Courier New", Font.PLAIN, 12));
      }
  
      /** Called back to get the preferred size of the component. */
      @Override
      public Dimension getPreferredSize() {
         return (new Dimension(canvasWidth, canvasHeight));
      }
   }
}