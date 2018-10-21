import java.awt.Point;


//manager for object collision
public class CollisionManager {
	// Have CollisionManager be a singleton to ensure interactions don't screw up in our main game.
	private static CollisionManager uniqueInstance;
	Fighter fighter;
	Fighter target;
	boolean collision;

	private CollisionManager(){
		this.collision = false;
	}
	
	public static synchronized CollisionManager getInstance(){
		// I do not plan to implement multi-threading, however if it is added there should not be much overhead in the creation
		//	of the collisionManager as it should only happen once. Therefore, I just add synchronized to this method.
		if (uniqueInstance == null) {
			uniqueInstance = new CollisionManager();
		}
		return uniqueInstance;
	}
	
	public double findDistanceBetweenPoints(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y, 2));
	}
	
	public boolean findCollision(Fighter fighter, Fighter target) {
		int sumOfRadius = fighter.radius + target.radius;
		double distanceBetweenPoints = findDistanceBetweenPoints(fighter.location, target.location);
		if (distanceBetweenPoints < sumOfRadius) {
			collision = true;
		}
		else {
			collision = false;
		}
		
		return collision;
	}
}
