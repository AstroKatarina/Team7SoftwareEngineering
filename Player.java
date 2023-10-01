class Player
{
    int ID = 0;
    String CodeName = " ";
    int Score = 0;
    int Team = 0;
    //green==1,red==2
    int EquipmentID = 0;

Player(int id)
{
    ID = id;

}

void PlayerReset()
{
    ID = 0;
    CodeName = " ";
    Score = 0;
    Team = 0;
    EquipmentID = 0;

}

public static void main(String[] args)
{
    Player player= new Player(4);
    System.out.println(player.ID);
    return;
}

}
