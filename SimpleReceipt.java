import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class SimpleReceipt implements ReceiptStrategy 
{
    @Override
    public String produce(String currentUser) 
    {
        String toReturn = "";
        Scanner in;
        int total = 0;
        toReturn += "User ID: " + currentUser + "\nUser name: " + currentUser + "\nReservations: ";
        try 
        {
            in = new Scanner(new File("reservations.txt"));

            Pattern datePatt = Pattern.compile("[0-9]{2}?/[0-9]{2}?/[0-9]{4}?");
            while (in.hasNext()) 
            {
                int room = Integer.parseInt(in.next());
                String name = in.next();
                String start = in.next(datePatt);
                in.next();
                String end = in.next(datePatt);

                if (currentUser.equals(name)) 
                {
                    for (int check : HotelCalifornia.tempRooms) 
                    {
                        if (check == room) 
                        {
                            toReturn += "\n" + room;
                            if (room <= 10) 
                            {
                                total += 80;
                            } 
                            else 
                            {
                                total += 200;
                            }
                        }
                    }

                }
            }

            toReturn += "\nTotal cost: " + total;
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(ComprehensiveReceipt.class.getName()).log(Level.SEVERE, null, ex);
        }

        return toReturn;
    }
}
