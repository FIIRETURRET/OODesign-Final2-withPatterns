import java.awt.Graphics;


//Armor
public class Armor extends ObjectDecorator{
	Fighter fighter;
	
	public Armor(Fighter fighter) {
		this.fighter = fighter;
		fighter.setHealth(fighter.getHealth() + 50);
		fighter.setDescription(fighter.getDescription()+" Wearing Armor");
	}
	
	public Fighter returnNewFighter() {
		return fighter;
	}
	
	public String getDescription() {
		return fighter.getDescription() + " Wearing Armor";
	}
	
	public int getHealth() {
		return fighter.getHealth();
	}

	@Override
	public void attack(Fighter fighter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Fighter fighter, Fighter myself, ContainerBox box) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void takeDamage(int damage) {
		// TODO Auto-generated method stub
		
	}

	

}
