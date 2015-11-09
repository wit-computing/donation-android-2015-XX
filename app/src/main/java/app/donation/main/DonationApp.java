package app.donation.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.donation.model.Donation;
import app.donation.model.User;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DonationApp extends Application
{
  public boolean         donationServiceAvailable = false;
  public User            currentUser;

  public final int       target       = 10000;
  public int             totalDonated = 0;
  public List <User>     users        = new ArrayList<User>();
  public List <Donation> donations    = new ArrayList<Donation>();

  public String          service_url  = "http://10.0.2.2:9000";
  public DonationService donationService;

  public boolean newDonation(Donation donation)
  {
    boolean targetAchieved = totalDonated > target;
    if (!targetAchieved)
    {
      donations.add(donation);
      totalDonated += donation.amount;
    }
    else
    {
      Toast toast = Toast.makeText(this, "Target Exceeded!", Toast.LENGTH_SHORT);
      toast.show();
    }
    return targetAchieved;
  }

  public void newUser(User user)
  {
    users.add(user);
  }

  public boolean validUser (String email, String password)
  {
    for (User user : users)
    {
      if (user.email.equals(email) && user.password.equals(password))
      {
        currentUser = user;
        return true;
      }
    }
    return false;
  }

  @Override
  public void onCreate()
  {
    super.onCreate();
    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(service_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    donationService = retrofit.create(DonationService.class);
    Log.v("Donation", "Donation App Started");
  }
}