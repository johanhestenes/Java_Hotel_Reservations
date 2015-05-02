import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Manager extends User 
{
    public Manager() 
    {
        userid = "admin";
    }

    public void load() throws FileNotFoundException, ParseException 
    {
        int emptyCounter = 0;
        for (int i = 0; i < HotelCalifornia.rooms.length; i++) 
        {
            if (HotelCalifornia.rooms[i].getEvents().isEmpty()) 
            {
                emptyCounter++;
            }
        }
        if (emptyCounter < 20) 
        {
            Scanner in = new Scanner(System.in);
            boolean answered = false;
            while (!answered) {
                String answer = in.next().toLowerCase();
                switch (answer) {
                    case "y":
                        answered = true;
                        HotelCalifornia.clearEvents();
                        HotelCalifornia.clearUsers();
                        break;
                    case "n":
                        return;
                    default:
                }
            }
        }
        Scanner in = new Scanner(new File("reservations.txt"));
        Pattern datePatt = Pattern.compile("[0-9]{2}?/[0-9]{2}?/[0-9]{4}?");
        while (in.hasNext()) 
        {
            //gets the first int in line
            int roomNumber = in.nextInt();
            //gets the first string (user name)
            String id = in.next();
            //creates a user based on the name it got
            Guest temp = new Guest();
            
            if (!HotelCalifornia.userAccounts.isEmpty())
            {
                for (int i = 0; i < HotelCalifornia.userAccounts.size(); i++)
                {
                    if (HotelCalifornia.userAccounts.get(i).userid.equals(id))
                    {
                        temp = (Guest) HotelCalifornia.userAccounts.get(i);
                    }
                }
            }
            
            else
            {
                temp = new Guest(id);
                HotelCalifornia.addAccount(temp);
            }
                                  
            if (temp.userid.isEmpty())
            {
                temp = new Guest(id);
                HotelCalifornia.addAccount(temp);
            }
            
            
            //adds the user to the list of users
           
            //takes the next date
            String next = in.next(datePatt);
            //creates a calendar based on the date it got
            Calendar start = stringToDate(next);
            //takes the '-'
            next = in.next();
            //takes the next date
            next = in.next(datePatt);
            //creates a calendar based on the date it got
            Calendar end = stringToDate(next);
            //creates a calendar based on all the information it got
            Event e = new Event(start, end, roomNumber, temp);
            //adds te event it created to the user it created
            temp.addEvent(e);
        }
    }

    public void save() throws IOException, FileNotFoundException 
    {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Calendar cal = Calendar.getInstance();
        PrintWriter out = new PrintWriter("reservations.txt");
        for (int j = 0; j < HotelCalifornia.userAccounts.size(); j++) 
        {
            for(int k = 0;k<HotelCalifornia.userAccounts.get(j).events.size();k++)
            {
                 out.println(HotelCalifornia.userAccounts.get(j).events.get(k).toString());
            }
        }
        out.close();
     }


    public String displayEventsOnDay(String date) throws ParseException 
    {
        Calendar d = stringToDate(date);
        String answer = "";     
        for (int i = 0; i < HotelCalifornia.userAccounts.size(); i++) 
        {
            ArrayList<Event> tg = HotelCalifornia.userAccounts.get(i).getEvents();
            for (int j = 0; j < tg.size(); j++) 
            {
                Calendar start = tg.get(j).getCheckin();
                Calendar end = tg.get(j).getCheckout();
                if (isBetween(start, end, d)) 
                {
                    answer += tg.get(j).toString() + "\n";
                }
            }
        }
        return answer;
    }

    public String displayRoomsOnDay(String date) throws ParseException
    {
        Calendar d = stringToDate(date);
        String answer = "";
        for(int i = 0;i<HotelCalifornia.rooms.length;i++)
        {
            if(!isBooked(HotelCalifornia.rooms[i], d))
            {
                answer += "Room: " + HotelCalifornia.rooms[i].getRoomNUmber() + "\n";
            }
        }
        return answer;
    }
        
    public boolean isBooked(Room r, Calendar d) 
    {
        for (int i = 0; i < r.getEvents().size(); i++) 
        {
            Calendar s = r.getEvents().get(i).getCheckin();
            Calendar e = r.getEvents().get(i).getCheckout();
            if(isBetween(s,e,d)) 
            {
              return true;
            }
        }
        return false;
     }
    
    public boolean isBetween(Calendar start, Calendar end, Calendar date) 
    {
        if (date.equals(start) || date.equals(end)) 
        {
            return true;
        }
        return (date.after(start) && date.before(end));
    }
    

    public boolean isBetween(Date start, Date end, Date date) 
    {
        if (date.equals(start) || date.equals(end)) 
        {
            return true;
        }
        return (date.after(start) && date.before(end));
    }
    
    public boolean isBooked(Room r, Calendar start, Calendar end)
    {
        for (int i = 0; i < r.getEvents().size(); i++) 
        {
            Calendar s = r.getEvents().get(i).getCheckin();
            Calendar e = r.getEvents().get(i).getCheckout();
            if (isBetween(s, e, start) || isBetween(s,e,end))
            {
                return true;
            }
        }
        return false;
    }
}