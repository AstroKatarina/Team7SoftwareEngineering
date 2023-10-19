//Software Engineering Team #7
//Model File for Laser Tag Project

import java.util.ArrayList;
class Model
{
	ArrayList<Player> players = new ArrayList<Player>();

	Model()
	{
		

	}




	public void update()
	{
		
	}

	public void addPlayer(int id, String codename, int team, int equipmentid)
	{
		Player player= new Player(id,codename,team,equipmentid);
	    players.add(player);
	}
}