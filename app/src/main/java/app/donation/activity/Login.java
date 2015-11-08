package app.donation.activity;

import java.util.List;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import app.donation.R;
import app.donation.api.GetUsers;
import app.donation.api.Response;
import app.donation.model.User;
import app.donation.main.DonationApp;


public class Login extends Activity
{
  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);
  }

  public void signinPressed (View view)
  {
    DonationApp app = (DonationApp) getApplication();

    TextView email     = (TextView)  findViewById(R.id.loginEmail);
    TextView password  = (TextView)  findViewById(R.id.loginPassword);

    if (app.validUser(email.getText().toString(), password.getText().toString()))
    {
      startActivity (new Intent(this, Donate.class));
    }
    else
    {
      Toast toast = Toast.makeText(this, "Invalid Credentials", Toast.LENGTH_SHORT);
      toast.show();
    }
  }
}