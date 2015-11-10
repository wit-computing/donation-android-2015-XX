package app.donation.main;

import java.util.ArrayList;
import java.util.List;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.donation.model.Donation;
import app.donation.model.Donor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DonationApp extends Application
{
  public boolean         donationServiceAvailable = false;
  public Donor currentUser;

  public final int       target       = 10000;
  public int             totalDonated = 0;
  public List <Donor>     users        = new ArrayList<Donor>();
  public List <Donation> donations    = new ArrayList<Donation>();

  public String          service_url  = "http://10.0.2.2:9000";
  public DonationServiceProxy donationService;

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

  public void newUser(Donor user)
  {
    users.add(user);
  }

  public boolean validUser (String email, String password)
  {
    for (Donor user : users)
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
    donationService = retrofit.create(DonationServiceProxy.class);
    Log.v("Donation", "Donation App Started");
  }
}