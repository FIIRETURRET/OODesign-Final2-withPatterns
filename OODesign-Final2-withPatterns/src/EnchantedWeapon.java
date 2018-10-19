import java.awt.Graphics;

public class EnchantedWeapon extends ObjectDecorator{

	Fighter fighter;
	
	public EnchantedWeapon(Fighter fighter) {
		this.fighter = fighter;
		fighter.setDamage(fighter.getDamage()+10);
		fighter.setDescription(fighter.getDescription()+" With an enchanted Weapon");
	}
	
	public Fighter returnNewFighter() {
		return fighter;
	}
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHealth() {
		// TODO Auto-generated method stub
		return 0;
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
