import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;


//A warrior
public class Warrior extends Fighter{
	CollisionManager collisionManager;
	
	public Warrior(int newRadius, int newx, int newy, int team) {
		description = "A Warrior";
		type = "warrior";
		health = 50;
		speed = 2;
		radius = newRadius;
		x = newx;
		y = newy;
		this.team = team;
		location = new Point(x,y);
		collisionManager = CollisionManager.getInstance();
		damage = 10;
	}

	public void attack(Fighter target) {
		// TODO Auto-generated method stub
		if (health > 0) {
			if (target.health > 0) {
				target.takeDamage(damage);
				System.out.println(this.getDescription()+" Attacking target");
			}
		}
	}
	
	public void takeDamage(int damage) {
		health = health - damage;
		System.out.println("Warrior took damage");
	}
	
	public void update(Fighter target, Fighter myself, ContainerBox box) {
		
		// Update current target information
		this.target = target;
		// If we don't have a target don't update
		if (target == null) {
			return;
		}
		this.targetPoint = target.getPoint();
		// Get the ball's bounds, offset by the radius of the ball
		int ballMinX = box.minX + radius;
		int ballMinY = box.minY + radius;
		int ballMaxX = box.maxX - radius;
		int ballMaxY = box.maxY - radius;
		
		// Make sure the unit isn't dead
		if (health > 0) {
			// Movement
			location = new Point(x,y);
			targetPoint = target.getPoint();
			// Check if the ball moves over the bounds. If so, adjust the position and speed.
			if (x < ballMinX) {
			   x = ballMinX;     // Re-position the ball at the edge
			   
			} else if (x > ballMaxX) {
			   x = ballMaxX;	// Re-position the ball at the edge
			   
			} else if (y < ballMinY) {
			   y = ballMinY;
			   
			} else if (y > ballMaxY) {
			   y = ballMaxY;
			   
			   // Check for a collision with the unit's target
			} else if (collisionManager.findCollision(myself, target) == true){
				attack(target);
				
			} else {
				
				// Check to see if our target is alive
				if (target.health > 0) {
					// The x and y offsets to move the ball
					double dx;
					double dy;
					
					// The values for the target's x and y
					int targetx;
					int targety;
					
					// Set the target to the current target's position
					targetx = target.x;
					targety = target.y;
					
					// Find the x and y differences between the ball and the target
					int diffx = targetx - x;
					int diffy = targety - y;
					
					double diffxSquare = Math.pow(diffx, 2);
					double diffySquare = Math.pow(diffy, 2);
					// Calculate the distance between the ball and the target
					double dist = Math.sqrt(diffxSquare + diffySquare);
					
					// Calculate the x and y offsets to move the ball
					dx = (speed / dist) * diffx;
					dy = (speed / dist) * diffy;
					
					// If the ball moves past the target, keep it at the target
					if (Math.abs(diffx) < Math.abs(dx)) {
						x = targetx;
					}
					if (Math.abs(diffy) < Math.abs(dy)) {
						y = targety;
					}
					
					// Move the ball
					x += Math.round(dx);
					y += Math.round(dy);
				}
				
			}
		}
		
		location = new Point(x,y);
		
	}
	
	/** Draw itself using the given graphics context. */
	public void draw(Graphics g) {
		if (this.getHealth() > 0) {
			g.setColor(Color.blue);
			g.fillOval((int)(x - radius), (int)(y - radius), (int)(2 * radius), (int)(2 * radius));
			if (team == 1) {
				g.setColor(Color.magenta);
			} else if (team == 2) {
				g.setColor(Color.white);
			}
			
			g.fillArc((x-radius), (y-radius), (2*radius), (2*radius), 0, 90);
		}
	}

	
}
