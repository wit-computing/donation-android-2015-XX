package app.donation.api;

import android.content.Context;

import java.util.List;

import app.donation.main.DonationApp;
import app.donation.model.Donation;
import app.donation.model.User;

public class CreateDonation extends Request
{
  DonationServiceAPI api;
  Donation donation;
  User user;

  public CreateDonation(User user, Donation donation, DonationServiceAPI api, Context context, Response<Donation> callback, String message)
  {
    super(context, callback, message);
    this.api = api;
    this.user = user;
    this.donation = donation;
  }

  @Override
  protected Donation doRequest(Object... params) throws Exception
  {
    Donation returnedDonation = api.createDonation(user, donation);
    return returnedDonation;
  }
}