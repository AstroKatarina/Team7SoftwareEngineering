class Player
{
    int ID = 0;
    String CodeName = " ";
    int Score = 0;
    int Team = 0;
    //green==0,red==1
    int EquipmentID = 0;

Player(int id, String codename, int team, int equipmentid)
{
    ID = id;
    CodeName = codename;
    Score = 0;
    Team = team;
    EquipmentID = equipmentid;

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

    return;
}

}
