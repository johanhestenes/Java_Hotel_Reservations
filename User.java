import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User 
{
    String userid = new String();
    final ArrayList<Event> events = new ArrayList<>();
    final ArrayList<Event> tempEvents = new ArrayList<>();

    public Calendar stringToDate(String date) throws ParseException 
    {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date dt = sdf.parse(date);
        Calendar g = Calendar.getInstance();
        g.setTime(dt);
        return g;
    }

    public ArrayList getEvents() 
    {
        return events;
    }
    
    public int getCostTemp()
    {
        int total = 0;
        for (Event event: tempEvents)
        {
            if (event.getRoom() <= 10)
            {
                total += 80;
            }   
            else 
            {
              total += 200;
            }
        }
        
        return total;
    }
    
    public int getCostTotal()
    {
        int total = 0;
        for (Event event: events)
        {
            if (event.getRoom() <= 10)
            {
                total += 80;
            }
            
            else 
            {
              total += 200;
            }
        }
        
        total += getCostTemp();
        return total;
    }
}
