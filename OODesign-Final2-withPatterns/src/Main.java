import java.awt.Color;
import java.awt.Point;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;


// entry point
public class Main {
	static ImageComponent imageComponent;
	static JMenuBar menuBar;
	static JMenu menu;
	static Hashtable<String, String> cds = new Hashtable<String, String>();
	
	public static void main(String[] args) throws Exception{
		
		cds.put("Icon 1","https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/Edouard_Detaille_-_Vive_L%27Empereur_-_Google_Art_Project.jpg/350px-Edouard_Detaille_-_Vive_L%27Empereur_-_Google_Art_Project.jpg");
		cds.put("Icon 2","https://upload.wikimedia.org/wikipedia/commons/thumb/3/3a/Trp-Sword-14226124129-v06.png/250px-Trp-Sword-14226124129-v06.png");
		cds.put("Icon 3","http://www.enworld.org/forum/attachment.php?attachmentid=91140&d=1511285946&stc=1");
		cds.put("Icon 4","https://www.candymachines.com/images/bulk_vending_machines/snack_soda_vending_machines/seaga-infinity-INF5B-soda-beverage-vending-machine.jpg");
		URL initialURL = new URL((String)cds.get("Icon 1"));
		menuBar = new JMenuBar();
		menu = new JMenu("Actions");
		menuBar.add(menu);
		// TODO Auto-generated method stub

		/**Fighter war1 = new Warrior();
		war1.setLocation(1,1);
		System.out.println(war1.getDescription());
		System.out.println(war1.getPoint());
		
		Fighter archer1 = new Archer();
		archer1.setLocation(10,10);
		System.out.println(archer1.getDescription());
		System.out.println(archer1.getPoint());**/
		
		// Run UI in the Event Dispatcher Thread (EDT), instead of Main thread
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	    	public void run() {
	    		JFrame frame = new JFrame("A World of Balls");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	       
	            
	            frame.setJMenuBar(menuBar);
	            
	            Icon icon = new ImageProxy(initialURL);
	            ImageIcon imageIcon = new ImageIcon(initialURL);
	            imageComponent = new ImageComponent(icon);
	            frame.setIconImage(imageIcon.getImage());
	            BattleWorld battleworld = new BattleWorld(640,480);
	            frame.setContentPane(battleworld); // BallWorld is a JPanel
	            frame.pack();            // Preferred size of BallWorld
	            frame.setVisible(true);  // Show it
	            for (Enumeration<String> e = cds.keys(); e.hasMoreElements();) {
	    			String name = (String)e.nextElement();
	    			JMenuItem menuItem = new JMenuItem(name);
	    			menu.add(menuItem); 
	    			menuItem.addActionListener(event -> {
	    				//imageComponent.setIcon(new ImageProxy(getCDUrl(event.getActionCommand())));
	    				frame.setIconImage(new ImageIcon(getCDUrl(event.getActionCommand())).getImage());
	    				//frame.setContentPane(new JLabel(new ImageIcon(getCDUrl(event.getActionCommand()))));
	    				frame.repaint();
	    			});
	    		}
	         }
	    });
	}
	
	static URL getCDUrl(String name) {
		try {
			return new URL((String)cds.get(name));
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
