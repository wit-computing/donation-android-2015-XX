package app.donation.api;

import android.content.Context;

import java.util.List;

import app.donation.model.Donation;
import app.donation.model.User;

public class CreateDonation extends Request
{
  DonationServiceAPI api;
  Donation donation;

  public CreateDonation(Donation donation, DonationServiceAPI api, Context context, Response<Donation> callback, String message)
  {
    super(context, callback, message);
    this.api = api;
    this.donation = donation;
  }

  @Override
  protected Donation doRequest(Object... params) throws Exception
  {
    Donation returnedDonation = api.createDonation(donation);
    return returnedDonation;
  }
}