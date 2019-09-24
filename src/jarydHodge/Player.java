package jarydHodge;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {

	public Player(String n){
		Bat bat = new Bat();
		name = n;
		hitpoints = startHP;
		healsLeft = 5;
		weapons.add(bat);
	}
	
	public void addWeapon(WeaponInterface w){
		weapons.add(w);
		}
	
	public int printOptions(){
		int a;
		for (a = 0; a < weapons.size(); a++){
			//System.out.println(a +". Use " + weaponName.get(a-1) +" (" + weaponDamage.get(a-1) +" damage, " + weaponUses.get(a-1) +" uses)");
			WeaponInterface w = weapons.get(a);
			System.out.println(a+1 +". Use " + w.getName() +" (" + w.getDamage() +" damage, " + w.getUses()  +" uses)");
		}
		return a+1;
	}
	
	
	
	public boolean searchForWeapon(String weapon, int uses){
		boolean b = false;
		for (int a = 0; a<weapons.size();a++){
			WeaponInterface w = weapons.get(a);
			if(w.getName().equalsIgnoreCase(weapon)){
				w.addUses(uses);
				b = true;
			}
		}
		return b;
	}
	
	
	public void isBroke(String weapon){
		for (int a = 0; a<weapons.size();a++){
			WeaponInterface w = weapons.get(a);
			if(w.getName().equalsIgnoreCase(weapon)&& w.getUses()<1){
				weapons.remove(a);
				System.out.println(name +"'s "+ weapon +" is out of uses!");
			}
		}
	}
	

	public boolean isDead(){
		boolean dead= false;
		
		if (hitpoints <=0){
			System.out.println(name + " died!");
			dead = true;
		}
		return dead;
	}
	
	public void heal()
	{
		//check if they are already fully healed
		if (healsLeft > 0){
			if (hitpoints == startHP)
				{
					System.out.println("Already fully healed!");
				}
			//check if they need a full heal
			else if (hitpoints <= (startHP-10))
				{
					hitpoints += 10;
					System.out.println("Recovered 10 HP. " + name + " now has " + hitpoints +" HP.");
					healsLeft -=1;
				}
			//check if they need a partial heal
			else if ((hitpoints <startHP) && (hitpoints >(startHP -10)))
				{
					System.out.println("Recovered " + (startHP-hitpoints) + " HP. " + name + " now has "+ startHP + " HP.");			
					hitpoints = startHP;
					healsLeft-=1;
				}
			}
		else
			System.out.println("No potions remaining.");
	}
	
	public int hitpoints;
	public int startHP = 100;
	public String name;
	public int healsLeft;
	public ArrayList<WeaponInterface> weapons = new ArrayList<WeaponInterface>();
	
	/*	public ArrayList<String> weaponName = new ArrayList<String>();
	public ArrayList<Integer> weaponDamage = new ArrayList<Integer>();
	public ArrayList<Integer> weaponUses = new ArrayList<Integer>();
	public ArrayList<Gun> guns = new ArrayList<Gun>();*/

}
