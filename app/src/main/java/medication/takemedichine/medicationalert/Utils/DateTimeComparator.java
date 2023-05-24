package medication.takemedichine.medicationalert.Utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;

import medication.takemedichine.medicationalert.Utils.DateTimeSorter;


public class DateTimeComparator implements Comparator {
    DateFormat f = new SimpleDateFormat("dd/mm/yyyy hh:mm");

    public int compare(Object a, Object b) {
        String o1 = ((DateTimeSorter)a).getDateTime();
        String o2 = ((DateTimeSorter)b).getDateTime();

        try {
            return f.parse(o1).compareTo(f.parse(o2));
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

