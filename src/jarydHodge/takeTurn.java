package jarydHodge;

import java.io.*;
import java.util.*;

public class takeTurn {
	
	static Scanner kbReader =  new Scanner(System.in); 
	static Random rand = new Random();
	
	public takeTurn(){

	}

	public static void getPlayers(){
		boolean invalidEntry = true;
		int choice = 0;
		
		do{
			System.out.print("How many players are playing? _ " );
			String c = kbReader.nextLine();
			
			try{
				//Turn the input string into an integer
				choice = Integer.parseInt(c);
				
				//If number of players is invalid
				if (choice ==1)
					System.out.println("You can't play by yourself!\n");
				else if (choice > 4)
					System.out.println("You can't play with too many players!\n");
				
				//If number of players is valid
				else if (choice > 0)
					invalidEntry = false;
				
				//If number is negative
				else
					System.out.println("Invalid input.\n");
				
			} catch (NumberFormatException e){
				//If the input string could not be turned into an integer
				System.out.println("Invalid input.\n");
			}
		} while (invalidEntry);
		
		do {
			//Get the name for each player
			System.out.print("Enter the name for player " + (playerList.size()+1) + ". _ ");
			String name = kbReader.nextLine();
			
			//Create a new player object for each player
			Player p = new Player(name);
			
			//Add each player to the player list
			playerList.add(p);
		} while (playerList.size()<choice);
	}
	
	
	public static void gamePlay(){
		boolean gameOver = false;
		int turnNum = 0;
		Player p = new Player("");
		while(!gameOver){
			for(int turn = 0;turn <playerList.size(); turn ++){
				turnNum++;
				p = playerList.get(turn);
				System.out.println("\n\n\nTURN " + (turnNum) +"\n\nCurrent hitpoints:");
				printStats();
				System.out.println("\nIt is "+ p.name + "'s turn.");
				gameOver = doTurn(p);
			}
			if (gameOver){
				System.out.println("\n\nGAME OVER/n" + p.name + " wins!");
				break;
			}
		}
	}
	
	
	public static void printStats(){
		for (int a = 0; a <playerList.size();a++){
			Player p = playerList.get(a);
			/*System.out.println("Player " + (a+1) + ": " + p.name);
			System.out.println("Hitpoints: " + p.hitpoints + "\n"); */
			System.out.println(p.name + ": "+ p.hitpoints);
		}
	}
	
	
	public static boolean doTurn (Player p){
		active = p;
		String c;
		boolean bad = true;
		int choice = 0;
		int num = 0;
		boolean gameOver = false;
		
		do{
			System.out.println("\nChoice an action from the choices below by entering the action number:");
			num = active.printOptions();
			System.out.println(num +". Recover 10 HP ("+ p.healsLeft +" potions remaining.)");
			System.out.println((num+1) +". Search for new weapon (40% success rate)");
			c = kbReader.nextLine();
			try{
				choice = Integer.parseInt(c);
				if (choice <= num +1 && choice > 0)
					bad = false;
				else
					System.out.println("Invalid Input");
			} catch(NumberFormatException e){
				System.out.println("Invalid Input");
				}
		} while (bad);
		
		
		if (choice == num){
			active.heal();
		}
		else if (choice == (num+1)){
			search(active);
		}
		else{
			WeaponInterface w = active.weapons.get(choice-1);
			Player target = getTarget(active);
			w.attack(active, target);
			active.isBroke(w.getName());
			if(target.isDead()){
				playerList.remove(target);
				if(playerList.size() <= 1){
					gameOver = true;
				}
			}
		}
		return gameOver;
	}
	
	
	
	public static Player getTarget(Player active){
		int count = 1;
		boolean bad = true;
		String c;
		int choice = 0;
		
		do{		
		System.out.println("\nWhich player would you like to target?");
		for (int a = 0; a < playerList.size();a ++){
			Player p = playerList.get(a);
			if(!(p.name.equals(active.name))){
				System.out.println(count +". " + p.name);
				count ++;
			}
		}
			c = kbReader.nextLine();
			try{
				choice = Integer.parseInt(c);
				if (choice <= playerList.size()-1 && choice > 0)
					bad = false;
				else
					System.out.println("Invalid Input");
					count = 1;
			} catch(NumberFormatException e){
				System.out.println("Invalid Input");
				count = 1;
				}
		} while (bad);
		choice -= 1;
		if (choice >= playerList.indexOf(active))
			choice +=1;
		
		return playerList.get(choice);
	}
	
	
	public static void search(Player p){
		int weapon = rand.nextInt(100)+1;
		int uses = rand.nextInt(2)+1;
		
		if (weapon < 60)
			System.out.println("You seach and search but come up empty handed.");
		else if (weapon >= 60 && weapon <80){
			uses += 3;
			if(p.searchForWeapon("Pistol", uses)){
				System.out.println("Found " + uses + " pistol ammunition.");
			}
			else {
				System.out.println("Found pistol with " + uses + " ammunition.");
				p.addWeapon(new Pistol(uses));
			}
		}
		else if (weapon >= 80 && weapon < 90){
				uses += 2;
				if(p.searchForWeapon("Rifle", uses)){
					System.out.println("Found " + uses + " rifle ammunition.");
				}
				else {
					System.out.println("Found rifle with " + uses + " ammunition.");
					p.addWeapon(new Rifle(uses));
				}	
			}
		else if (weapon >= 90 && weapon < 97){
			uses += 1;
			if(p.searchForWeapon("Shotgun", uses)){
				System.out.println("Found " + uses + " shotgun ammunition.");
			}
			else {
				System.out.println("Found shotgun with " + uses + " ammunition.");
				p.addWeapon(new Shotgun(uses));
			}
		}
			else if (weapon >= 97 && weapon < 100){
				if(p.searchForWeapon("Ray Gun", uses)){
					System.out.println("Found " + uses + " ray gun ammunition.");
				}
				else {
					System.out.println("Found ray gun with " + uses + " ammunition.");
					p.addWeapon(new RayGun(uses));
				}
			}
		}
	
	
	public static Player active;
	public static ArrayList<Player> playerList = new ArrayList<Player>();
}
