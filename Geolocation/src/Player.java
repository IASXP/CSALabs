public class Player {
    String name;
    int number;

    public Player()
    {
        name = "Default";
        number = -1;
    }
    public Player (String name1, int number1)
    {
        name = name1;
        number = number1;
    }

    public String playerInfo()
    {
        return ("Player: " + name + ", " + "#" + number);
    }



}
