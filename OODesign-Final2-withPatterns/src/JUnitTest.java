import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;



class JUnitTest {

	@Test
	void test() {
		testSingleton();
		testDecorator();
	}
	
	public void testSingleton() {
		System.out.println("Testing singleton pattern");
		CollisionManager collisionmanager = CollisionManager.getInstance();
		CollisionManager collisionmanager2 = CollisionManager.getInstance();
		
		if (collisionmanager == collisionmanager2) {
			System.out.println("True");
		}
		
		assertEquals(collisionmanager, collisionmanager2);
	}
	
	public void testDecorator() {
		System.out.println("Testing decorator pattern");
		Fighter warrior = new Warrior(10, 20, 20, 1);
		System.out.println(warrior.getHealth());
		warrior = new Armor(warrior);
		System.out.println(warrior.getHealth());
		
		assertTrue(warrior.getHealth() == 100);
	}

}
