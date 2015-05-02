import java.util.ArrayList;

public class Room
{
    int PRICE, roomNumber;
    private ArrayList<Event> events = new ArrayList<>();

    public int getPrice() 
    {
        return PRICE;
    }

    public int getRoomNUmber() 
    {
        return roomNumber;
    }
    

    public void addEvent(Event e) 
    {
        events.add(e);
    }

    public ArrayList<Event> getEvents() 
    {
        return events;
    }

    public void clearEvents() 
    {
        events.clear();
    }
}
