package Module;


public class DateModule {
    private int day;
    private int minute;
    private int year;
    private int hour;
    private int month;
    private String dateC;
    private String curDate;


    public DateModule(String date) {
        dateC = date;

        System.out.println("date enter" + date);
        String[] dateSplit = date.split("-"); //spliting the date string by hyphen and getting the day,month and year
        int[] dateArray = new int[dateSplit.length];
        for (int i = 0; i < dateSplit.length; i++) {
            dateArray[i] = Integer.parseInt(dateSplit[i]);
            System.out.println(dateArray[i]);
        }
        setDate(dateArray[2], dateArray[1], dateArray[0]);
    }

    public DateModule(int hour, int minute, String date) {
        dateC = date;

        String[] dateSplit = date.split("-"); //spliting the date string by hyphen and getting the day,month and year
        int[] dateArray = new int[dateSplit.length];
        System.out.println("date Array" + dateSplit[0] + dateSplit[1] + dateSplit[2]);
        System.out.println(hour + minute);
        for (int i = 0; i < dateSplit.length; i++)
            dateArray[i] = Integer.parseInt(dateSplit[i]);

        setDate(dateArray[2], dateArray[1], dateArray[0]);
        System.out.println(this.day);
        System.out.println(this.month);
        System.out.println(this.year);

        if ((hour < 24) && (minute < 61)) {
            this.hour = hour;
            this.minute = minute;
        } else {
            System.out.println("Please enter valid time");
        }

    }


    public String getTime() {
        return hour + ":" + minute;
    }


    public void setCurDate(String curDate) {
        this.curDate = curDate;
    }

    public String getDateC() {
        return dateC;
    }


    public int getDay() {

        return day;
    }

    public void setDay(int day) {
        if (day > 0 && day < 32) this.day = day;
    }

    public void setDate(int day, int month, int year) {
        if ((day > 0 && day < 32) && ((month > 0 && month < 13))) {
            this.day = day;
            this.month = month;
            this.year = year;
            System.out.println(day + "dday");
            System.out.println(month + "month");
            System.out.println(year + "year");
        } else {
            System.out.println("Please enter a valid date");
        }

    }

    public String getCurDate() { //instead of simple formatter,formatting date to different types.
        String date;
        if ((day >= 1) && (day <= 9)) {
            date = "0" + day + "-" + month + "-" + year;
            if (((month >= 1) && (month <= 9))) {

                date = year + "-" + "0" + month + "-0" + day;
            }
        } else if ((month >= 1) && (month <= 9)) {
            date = year + "-0" + month + "-" + day;
            if ((day >= 1) && (day <= 9)) {

                date = year + "-" + "0" + month + "-0" + day;
            }
        } else {
            date = year + "-" + month + "-" + day;

        }
        return date;
    }

    public String getDate() {
        String date;
        if ((day >= 1) && (day <= 9)) {
            date = "0" + day + "-" + month + "-" + year;
            if (((month >= 1) && (month <= 9))) {
                date = "0" + day + "-0" + month + "-" + year;

            }
        } else if ((month >= 1) && (month <= 9)) {
            date = day + "-0" + month + "-" + year;
            if ((day >= 1) && (day <= 9)) {
                date = "0" + day + "-0" + month + "-" + year;

            }
        } else {
            date = day + "-" + month + "-" + year;

        }
        setCurDate(date);
        return date;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    @Override
    public String toString() {
        return "D " + getDate() + "/" + "Time: " + hour + ":" + minute;
    }

    public int calcDate() {
        //calculating total days of the provided date;
        int totaldays = Math.round(365 * this.year + this.year / 4 - this.year / 100 + this.year / 400 + this.day +
                (153 * month + 8) / 5);
        return totaldays;
    }


}

