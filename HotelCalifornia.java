import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public class HotelCalifornia 
{
    static ArrayList<User> userAccounts = new ArrayList<>();
    static Room[] rooms = new Room[20];
    static User currentUser;
    static Manager manager;
    static ArrayList<Integer> tempRooms = new ArrayList<>();
    
    public static void main(String[] args) throws ParseException, IOException 
    {
         manager = new Manager();
         populateRooms();
         try
         {
             manager.load();
         }
         catch(FileNotFoundException ex)
         {

         }
         new LoginRegistration();
    }
    
    public static void addAccount(User newUser) 
    {
        userAccounts.add(newUser);
    }

    private static void populateRooms() 
    {
        for (int i = 0; i < 10; i++) 
        {
            rooms[i] = new Economic(i);
        }
        for (int i = 10; i < 20; i++) 
        {
            rooms[i] = new Luxurious(i);
        }
    }

    public static void clearEvents()
    {
        for (Room room : rooms) {
            room.clearEvents();
        }
    }

    public static void clearUsers() 
    {
        userAccounts.clear();
    }

    public static void login(String id) 
    {
        for (User userAccount : userAccounts) 
        {
            if (userAccount.userid.equals(id)) 
            {
                currentUser = userAccount;
                return;
            }
        }
    }

    public static void login(User user) 
    {
        for (User userAccount : userAccounts)
        {
            if (userAccount.equals(user)) 
            {
                currentUser = userAccount;
                return;
            }
        }
    }
}
