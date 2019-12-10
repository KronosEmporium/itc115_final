import java.util.Date;

class LibraryItem {

    private int itemId = 0;
    private String itemName = "";
    private String genre = "";
    // private enum mediatype {
    //     BOOK,
    //     CD,
    //     DVD,
    //     MAGAZINE
    // };
    private boolean reserved = false;
    private boolean checkedIn = true;
    private int[] duedate = new int[]{0,0,0};
    private int maxReserveTime = 0;
    private double finesAccrued = 0.0;
    
    public int getId() {
        return itemId;
    }
    
    public void setId(int newId) {
        itemId = newId;
    }
    
    public String getName() {
        return this.itemName;
    }
    
    public void setName(String newName) {
        itemName = newName;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public void setGenre(String newGenre) {
        genre = newGenre;
    }
    
    // public enum getMediatype() {
    //     return mediatype;
    // }
    
    // public void setMediatype(enum newMediatype) {
    //     mediatype = newMediatype;
    // }
    
    public boolean getReserved() {
        return reserved;
    }
    
    public void setReserved(boolean newRes) {
        reserved = newRes;
    }
    
    public boolean getCheckedIn() {
        return checkedIn;
    }
    
    public void setCheckedIn(boolean newCheckIn) {
        checkedIn = newCheckIn;
    }
    
    public int[] getDuedate() {
        return duedate;
    }
    
    public void setDuedate(int[] newDuedate) {
        duedate = newDuedate;
    }
    
    public int getMaxReserveTime() {
        return maxReserveTime;
    }
    
    public void setMaxReserveTime(int newMaxReserveTime) {
        maxReserveTime = newMaxReserveTime;
    }
    
    public double getFinesAccrued() {
        return finesAccrued;
    }
    
    public void setFinesAccrued(double newFine) {
        finesAccrued = newFine;
    }
    
    public void checkOut(int[] ddate) {

        // Check out an item if it's not reserved. Set the duedate to the item's
        // allotted return limit (in each item type's class). Also, make sure to
        // zero out the late fees since it's just been checked out.
        if (!reserved) {
            
            finesAccrued = 0;
            
            // Original idea: Calculate return date by item's max reservation time
            //Date d = new Date();
            // Our Date has a format of WEEKDAY, MONTH, NUMBERDATE, TIME, TIMEZONE, YEAR
            // We will convert the Date into an array of strings to manipulate the numberdate
            // to return the day the item is due, as well as calculate if it'll be next month.
            // (there's probably an easier way but I didn't bring my book :/)
            // String[] d2 = d.toString().split(" ",0);
            
            
            int daysinmonth;
            
            // Order in date array is day, month, year
            int newDueDay = ddate[0];
            int newDueMonth = ddate[1];
            int newDueYear = ddate[2];
            
            // newDueDay = d2[2] + getMaxReserveTime();
            // newDueMonth = findIndex(months, d2[1]);
            // newDueYear = d2[5];
            
            // Check if it's February otherwise calculate day total in month
            if (newDueMonth == 2) {
                daysinmonth = 28;
            } else if ((newDueMonth % 6) % 2 == 0) {
                daysinmonth = 30;
            } else {
                daysinmonth = 31;
            }
            
            if (newDueDay > daysinmonth) {
                
                newDueDay -= daysinmonth;
                
                if (newDueMonth == 12) {
                    newDueMonth = 1;
                    newDueYear++;
                }
                
                newDueDay -= daysinmonth;
            }
            
            duedate[0] = newDueDay;
            duedate[1] = newDueMonth;
            duedate[2] = newDueYear;
        }
    }

    // Didn't fully implement date-difference calculation
    public void calculateFine(int daysoverdue) {
        // Calculate the fine based on days past since due date
        
        // Order for date array is day, month, year
        finesAccrued = daysoverdue * .5;
        
    }
    
    public void printInfo() {
        System.out.println(String.format("ID: %d\nName: %s\nGenre: %s\nDue Date: %d/%d/%d\nFines: %.2f", itemId, itemName, genre, duedate, finesAccrued));
    }
    // // Check for an item in String array and return index
    // public int findIndex(String[] arr, String srcstr) {
    //     for ( int i = 0; i < arr.length; i++ ) {
    //         if (arr[i] == srcstr) {
    //             return i;
    //         }
    //     }
    //     return 0;
    // }
}

class Book extends LibraryItem {
    
    public int getMaxReserveTime() {
        return super.getMaxReserveTime() + 7;
    }
}

class CD extends LibraryItem {
    
    public int getMaxReserveTime() {
        return super.getMaxReserveTime() + 5;
    }
}

class DVD extends LibraryItem {
    
    public int getMaxReserveTime() {
        return super.getMaxReserveTime() + 3;
    }
}

class Magazine extends LibraryItem {
    
    public int getMaxReserveTime() {
        return super.getMaxReserveTime() + 3;
    }
}

// Test
public class Main {
    public static void main (String[] args) {
        Book x = new Book();
        int[] testdate = new int[]{1,1,2020};
        x.checkOut(testdate);
        x.printInfo();
    }
}
