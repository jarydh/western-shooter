package jarydHodge;

public abstract class Gun implements WeaponInterface {

	public Gun(String n, int d, int u){
		name = n;
		damage = d;
		uses = u;
	}
	
	public void attack(Player user, Player target){
		System.out.println("Ouch! " + user.name + " shot " + target.name + " with a " + name + "! It dealt " + damage + " damage!");
		target.hitpoints -= damage;
		uses -=1;
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
		uses += u;
	}
	
	public String name;
	public int damage;
	public int uses;
}
