package app.donation.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import app.donation.R;
import app.donation.api.CreateUser;
import app.donation.api.Response;
import app.donation.main.DonationApp;
import app.donation.model.User;

public class Signup extends AppCompatActivity implements Response<User>
{
  private DonationApp app;

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_signup);
    app = (DonationApp) getApplication();
  }

  public void registerPressed (View view)
  {
    TextView firstName = (TextView)  findViewById(R.id.firstName);
    TextView lastName  = (TextView)  findViewById(R.id.lastName);
    TextView email     = (TextView)  findViewById(R.id.Email);
    TextView password  = (TextView)  findViewById(R.id.Password);

    User user = new User (firstName.getText().toString(), lastName.getText().toString(), email.getText().toString(), password.getText().toString());

    DonationApp app = (DonationApp) getApplication();

    new CreateUser(user, app.donationServiceAPI, this, this, "Creating new User Account").execute();

    startActivity (new Intent(this, Welcome.class));
  }

  @Override
  public void setResponse(List<User> aList)
  {}

  @Override
  public void setResponse(User user)
  {
    app.users.add(user);
    startActivity (new Intent(this, Welcome.class));
  }

  @Override
  public void errorOccurred(Exception e)
  {
    app.donationServiceAvailable = false;
    Toast toast = Toast.makeText(this, "Donation Service Unavailable. Try again later", Toast.LENGTH_LONG);
    toast.show();
    startActivity (new Intent(this, Welcome.class));
  }
}
