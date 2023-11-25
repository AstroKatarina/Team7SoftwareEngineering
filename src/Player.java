class Player
{
    int ID;
    String CodeName;
    int Score;
    int Team;
    // Green==0 red==1
    int EquipmentID;

    Player()
    {
        ID = 0;
        CodeName = "";
        Score = 0;
        Team = 0;
        EquipmentID = 0;
    }

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
        CodeName = "";
        Score = 0;
        Team = 0;
        EquipmentID = 0;
    }

}