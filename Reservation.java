
public class Reservation 
{
   private String id;
   private String checkIn;
   private String checkOut;
   private int roomNo;
   
   public Reservation(String id, String checkIn, String checkOut, int roomNo)
   {
      this.id = id;
      this.checkIn = checkIn;
      this.checkOut = checkOut;
      this.roomNo = roomNo;
   }
}
