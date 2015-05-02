import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Event 
{
    private Calendar checkin;
    private Calendar checkout;
    private int room;
    private User owner;

    public Event(Calendar in, Calendar out, int newRoom, User creator) 
    {
        checkin = in;
        checkout = out;
        room = newRoom;
        owner = creator;
    }

    public Calendar getCheckin() 
    {
        return checkin;
    }

    public Calendar getCheckout()
    {
        return checkout;
    }

    public Calendar getDate() 
    {
        return null;
    }

    public User getOwner()
    {
        return owner;
    }

    public int getRoom() 
    {
        return room;
    }

    @Override
    public String toString() 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String output = new String();
        output = room + " " + owner.userid + " " + sdf.format(checkin.getTime()) + " - " + sdf.format(checkout.getTime());
        return output;
    }
}
