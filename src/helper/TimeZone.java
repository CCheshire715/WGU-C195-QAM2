package helper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
/**This class is used for all the time conversions.*/
public class TimeZone {
    private static ObservableList<LocalTime> starLocalTime = FXCollections.observableArrayList();
    private static ObservableList<LocalTime> endLocalTime = FXCollections.observableArrayList();
    /**This method converts the time and loads the times into the start and end combo boxes.*/
    private static void loadTimes() {
        ZonedDateTime estBHStart = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0),  ZoneId.of("America/New_York"));
        LocalTime localBHStart = estBHStart.withZoneSameInstant(ZoneId.systemDefault()).toLocalTime();
        LocalTime localBHend = localBHStart.plusHours(14);

        while (localBHStart.isBefore(localBHend)) {
            starLocalTime.add(localBHStart);
            localBHStart = localBHStart.plusMinutes(15);
            endLocalTime.add(localBHStart);
        }
    }
    /**This method is an observable list that gets the start time.
     * @return startLocalTime
     */
    public static ObservableList<LocalTime> getStarTime() {
        if (starLocalTime.size() == 0) {
            loadTimes();
        }
        return starLocalTime;
    }
    /**This method is an observable list that gets the end time.
     * @return endLocalTime
     */
    public static ObservableList<LocalTime> getEndTime() {
        if (endLocalTime.size() == 0) {
            loadTimes();
        }
        return endLocalTime;
    }
}
