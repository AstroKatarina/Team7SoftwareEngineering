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

	public void addScore()
	{
		// Enter code to properly deal with information from traffic generator
		int hitPlayerID = UDPServer.Players[0];
		int scorePlayerID = UDPServer.Players[1];

		// CHECK hit player for base hit

		// Red Base Hit
		// if((hitPlayerID == 53) //&& (NOT RED TEAM)
		{
			// Add 100 points to scorePlayerID
			// Add B next to codename
		}

		// Green Base Hit
		// if((hitPlayerID == 43) && (NOT GREEN TEAM)
		{
			// Add 100 points to scorePlayerID
			// Add B next to codename
		}

		// Update score
		// Add 10 points to scorePlayerID

		// Check for Team Kill
		// if(hitPlayerID TEAM = scoreplayerID TEAM)
		{
			// Transmit scorePlayerID by calling UDPClient(scorePlayerID)
		}
		// else
		{
			// Transmit hitPlayerID by calling UDPClient(hitPlayerID)
		}
	}

}