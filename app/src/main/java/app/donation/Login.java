package app.donation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import app.donation.R;

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
    startActivity (new Intent(this, Donate.class));
  }
}