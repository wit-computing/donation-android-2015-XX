package app.donation.activity;

import app.donation.R;
import app.donation.activity.Login;
import app.donation.activity.Signup;
import app.donation.api.GetUsers;
import app.donation.api.Response;
import app.donation.main.DonationApp;
import app.donation.model.User;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.util.List;

public class Welcome extends AppCompatActivity implements Response<User>
{
  private DonationApp app;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_welcome);

    app = (DonationApp) getApplication();

  }

  @Override
  public void onResume()
  {
    super.onResume();
    app.currentUser = null;
    new GetUsers(app.donationServiceAPI, this, this, "Retrieving list of users").execute();
  }

  void serviceUnavailableMessage()
  {
    Toast toast = Toast.makeText(this, "Donation Service Unavailable. Try again later", Toast.LENGTH_LONG);
    toast.show();
  }

  public void loginPressed (View view)
  {
    if (app.donationServiceAvailable)
    {
      startActivity (new Intent(this, Login.class));
    }
    else
    {
      serviceUnavailableMessage();
    }
  }

  public void signupPressed (View view)
  {
    if (app.donationServiceAvailable)
    {
      startActivity (new Intent(this, Signup.class));
    }
    else
    {
      serviceUnavailableMessage();
    }
  }

  @Override
  public void setResponse(List<User> aList)
  {
    app.users = aList;
    app.donationServiceAvailable = true;
  }

  @Override
  public void setResponse(User anObject)
  {}

  @Override
  public void errorOccurred(Exception e)
  {
    app.donationServiceAvailable = false;
    serviceUnavailableMessage();
  }
}