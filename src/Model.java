//Software Engineering Team #7
//Model File for Laser Tag Project

import java.util.ArrayList;
class Model
{

	ArrayList<Player> Players;
	
	Model()
	{
		Players = new ArrayList<Player>();
	}

	public void addPlayer()
	{
		Player gamer = new Player();
		Players.add(Players.size(), gamer);
	}

	// Set last player in arraylist attributes with setter
	public void setPlayerID(int ID)
	{
		Players.get(Players.size()-1).ID = ID;
	}

	// Set last player in arraylist attributes with setter
	public void setPlayerCodeName(String CodeName)
	{
		Players.get(Players.size()-1).CodeName = CodeName;
	}

	// Set last player in arraylist attributes with setter
	public void setPlayerScore(int Score)
	{
		Players.get(Players.size()-1).Score = Score;
	}

	// Set last player in arraylist attributes with setter
	public void setPlayerTeam(int Team)
	{
		Players.get(Players.size()-1).Team = Team;
	}

	// Set last player in arraylist attributes with setter
	public void setPlayerEquipmentID(int EquipmentID)
	{
		Players.get(Players.size()-1).EquipmentID = EquipmentID;
	}

	public void printOut()
	{
		for(int i = 0; i < Players.size(); i++){
			Player player = Players.get(i);
			System.out.println("ID: " + player.ID + "; CodeName: " + player.CodeName + "; Team: " + player.Team + "; Score: "+ player.Score + "; EquipmentID: " + player.EquipmentID);
		}
		
	}

}