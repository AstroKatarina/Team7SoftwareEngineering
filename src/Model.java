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

    // Function to search ArrayList for a given ID and return index
    public static int searchInt(ArrayList<Integer> numbers, int searchTerm) {
        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i) == searchTerm) {
                return i; // Return the index if found
            }
        }
        return -1; // Return -1 if not found
    }

	public void addScore()
	{
		// Enter code to properly deal with information from traffic generator
		int hitPlayerID = UDPServer.gamePlayers[0];
		int scorePlayerID = UDPServer.gamePlayers[1];

		// Search the ArrayList to find the proper index

		// Call the search function
        int searchTerm = hitPlayerID;
        int foundIndex = searchInt(Players<>, searchTerm);

        if (foundIndex != -1) {
            System.out.println("Integer " + searchTerm + " found at index: " + foundIndex);
        } else {
            System.out.println("Integer " + searchTerm + " not found.");
        }

		// Call the search function again for the scorer
		int searchTerm = scorePlayerID;
        int foundIndex2 = searchInt(Players<>, searchTerm);

        if (foundIndex2 != -1) {
            System.out.println("Integer " + searchTerm + " found at index: " + foundIndex2);
        } else {
            System.out.println("Integer " + searchTerm + " not found.");
        }

		// CHECK hit player for base hit

		// Red Base Hit
		if((hitPlayerID == 53) && (Players<foundIndex2>.Team == 0)) // Scorer on Green Team
		{
			Players<foundIndex>.score += 100;
			// Add B next to codename
		}

		// Green Base Hit
		if((hitPlayerID == 43) && (Players<foundIndex2>.Team == 1)) // Scorer on Red Team
		{
			Players<foundIndex>.score += 100;
			// Add B next to codename
		}

		// Update score
		Players<foundIndex2>.score += 10;

		// Check for Team Kill
		if(Players<foundIndex>.Team == Players<foundIndex2>.Team)
		{
			UDPClient.sendData(Players<foundIndex2>.EquipmentID);
		}
		else
		{
			UDPClient.sendData(Players<foundIndex>.EquipmentID);
		}
	}

}