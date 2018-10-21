import java.awt.Graphics;
import java.awt.Point;


//Basic fighter class
public abstract class Fighter{
	
	int health;
	String description = "Unkown fighter";
	String type;
	int speed;
	int radius;
	int x;
	int y;
	int team;
	Point location = new Point(x,y);
	Fighter target = null;
	Point targetPoint = new Point(1000,1000);
	int damage;
	
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String newDescription) {
		description = newDescription;
	}
	
	public void setLocation(int x, int y) {
		location = new Point(x,y);
	}
	
	public Point getPoint() {
		return location;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public int getHealth() {
		return health;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public void setDamage(int newDamage) {
		damage = newDamage;
	}
	
	public void setHealth(int newHealth) {
		health = newHealth;
	}
	
	public void setTarget(Fighter newTarget) {
		target = newTarget;
	}
	public Fighter getTarget() {
		return target;
	}
	
	public void setTargetPoint(Point newPoint) {
		targetPoint = newPoint;
	}
	public Point getTargetPoint() {
		return targetPoint;
	}
	
	public abstract void attack(Fighter fighter);
	public abstract void update(Fighter fighter, Fighter myself, ContainerBox box);
	public abstract void draw(Graphics g);
	public abstract void takeDamage(int damage);

	public void drawAttack(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
