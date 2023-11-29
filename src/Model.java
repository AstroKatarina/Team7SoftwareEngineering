//Software Engineering Team #7
//Model File for Laser Tag Project

import java.io.IOException;
import java.util.ArrayList;
class Model
{

	static ArrayList<Player> Players;

	public static int redTeamScore = 0;
	public static int greenTeamScore= 0;
	
	Model()
	{
		Players = new ArrayList<Player>();
	}

	public void addPlayer(Player player)
	{
		Players.add(Players.size(), player);
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

	public ArrayList<Player> getPlayerList(){
		return Players;
	}

	public void printOut()
	{
		for(int i = 0; i < Players.size(); i++){
			Player player = Players.get(i);
			System.out.println("ID: " + player.ID + "; CodeName: " + player.CodeName + "; Team: " + player.Team + "; Score: "+ player.Score + "; EquipmentID: " + player.EquipmentID);
		}
		
	}

    // Function to search ArrayList for a given ID and return index
    public static int searchInt(int searchTerm) {
        for (int i = 0; i < Players.size(); i++) {
            if (Players.get(i).getEquipmentID() == searchTerm) {
                return i; // Return the index if found
            }
        }
        return -1; // Return -1 if not found
    }

	public static void addScore(int hit, int score)
	{
		// Enter code to properly deal with information from traffic generator
		int hitPlayerID = hit;
		int scorePlayerID = score;

		// Search the ArrayList to find the proper index

		// Call the search function
        int searchTerm = hitPlayerID;
        int foundIndex = searchInt(searchTerm);

        if (foundIndex != -1) {
            System.out.println("Integer " + searchTerm + " found at index: " + foundIndex);
        } else {
            System.out.println("Integer " + searchTerm + " not found.");
        }

		// Call the search function again for the scorer
		int searchTerm2 = scorePlayerID;
        int foundIndex2 = searchInt(searchTerm2);

        if (foundIndex2 != -1) {
            System.out.println("Integer " + searchTerm + " found at index: " + foundIndex2);
        } else {
            System.out.println("Integer " + searchTerm + " not found.");
        }

		// CHECK hit player for base hit

		// Red Base Hit
		if((hitPlayerID == 53) && (Players.get(foundIndex2).Team == 0)) // Scorer on Green Team
		{
			Players.get(foundIndex).Score += 100;
			Players.get(foundIndex).BaseHit = true;
			View.eventTextArea.append(Players.get(foundIndex2).CodeName + " has hit the base and now has a score of " + Players.get(foundIndex2).Score+"\n");
			View.eventTextArea.setCaretPosition(View.eventTextArea.getDocument().getLength());
			// Add B next to codename
		}

		// Green Base Hit
		else if((hitPlayerID == 43) && (Players.get(foundIndex2).Team == 1)) // Scorer on Red Team
		{
			Players.get(foundIndex).Score += 100;
			Players.get(foundIndex).BaseHit = true;
			View.eventTextArea.append(Players.get(foundIndex2).CodeName + " has hit the base and now has a score of " + Players.get(foundIndex2).Score+"\n");
			View.eventTextArea.setCaretPosition(View.eventTextArea.getDocument().getLength());
			// Add B next to codename
		}

		// Update score
		else {
			Players.get(foundIndex2).Score += 10;
			System.out.println(Players.get(foundIndex2).CodeName + " has hit " + Players.get(foundIndex).CodeName + " and now has a score of " + Players.get(foundIndex2).Score+"\n");
			View.eventTextArea.append(Players.get(foundIndex2).CodeName + " has hit " + Players.get(foundIndex).CodeName + " and now has a score of " + Players.get(foundIndex2).Score+"\n");
			View.eventTextArea.setCaretPosition(View.eventTextArea.getDocument().getLength());
		}	
		

		// Check for Team Kill
		// If team kill send own eqipment ID
		if(Players.get(foundIndex).Team == Players.get(foundIndex2).Team)
		{
			try {
				UDPClient.sendData(Players.get(foundIndex2).EquipmentID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// Otherwise send hit player's ID
		else
		{
			try {
				UDPClient.sendData(Players.get(foundIndex).EquipmentID);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sortPlayerScores()
	{
		Player player1, player2, temp;
		int highestIndex;
		for(int i = 0; i < Players.size();i++)
		{
			player1 = Players.get(i);
			temp = player1;
			highestIndex = i;
			for(int j = i; j < Players.size(); j++)
			{
				player2 = Players.get(j);
				if (player1!=player2)
				{
					if(player2.Score > temp.Score)
					{
						highestIndex = j;
					}
				}
			}
			Players.set(i,Players.get(highestIndex));
			Players.set(highestIndex,temp);
		}

	}

}