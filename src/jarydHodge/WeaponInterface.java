package jarydHodge;

public interface WeaponInterface {

	public void attack(Player user, Player target);
	
	public String getName();
	
	public int getDamage();
	
	public int getUses();
	
	public void addUses(int a);
			
}
