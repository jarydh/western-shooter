package jarydHodge;

public class Bat implements WeaponInterface {
	
	public Bat(){
		
	}
	
	public void attack(Player user, Player target){
		System.out.println("Ouch! " + user.name + " hit " + target.name + " with a bat! It dealt " + damage + " damage!");
		target.hitpoints -= damage;
		if (uses > 0)
			uses-= 1;
		else{
			System.out.println(user.name + "'s " + name +" is out of ammunition!");
			user.weapons.remove(this);
		}
	}
	
	public int getDamage(){
		return damage;
	}
	
	public int getUses(){
		return uses;
	}
	
	public String getName(){
		return name;
	}
	
	public void addUses(int u){
		uses +=u;
	}
	
	
	public int damage = 10;
	public String name = "Bat";
	public int uses = 5;
}
