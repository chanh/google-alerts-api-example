package co.chanh;

import org.nnh.bean.Alert;
import org.nnh.bean.HowMany;
import org.nnh.bean.HowOften;
import org.nnh.service.GAService;

import java.util.Arrays;
import java.util.List;

/**
 * Just for playing around with the Google Alerts API in Java.
 *
 * Refer to http://sourceforge.net/projects/javagooglealertsapi/ for more example code on Google Alerts API
 */
public class Main {

  private static List<String> alertQueries = Arrays.asList(
    "Company A",
    "Company B"
  );

  public static void main(String[] args) throws Exception
  {

    // First of all, we have to login.. return empty string if login successful else return an error string
    GAService service = new GAService("email", "password");
    service.doLogin();

    // Get all alerts.
    System.out.println("My current alerts:");
    List<Alert > lstAlert = service.getAlerts();
    for(Alert alert : lstAlert)
    {
        System.out.println(alert.getSearchQuery());
    }

    System.out.println();

    for(String query : alertQueries)
    {
      // Create alert. return an alert id if created successful else return an error string
      Alert alert = new Alert();
      alert.setHowMany(HowMany.ONLY_THE_BEST_RESULTS);
      alert.setHowOften(HowOften.ONCE_A_DAY);
      alert.setSearchQuery(query);
      String result = service.createAlert(alert);

      System.out.println("Creating alert for " + query + ". Result: " + result);
    }

  }
}
