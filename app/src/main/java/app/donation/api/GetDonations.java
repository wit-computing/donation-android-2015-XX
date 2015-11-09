package app.donation.api;

import android.content.Context;

import java.util.List;

import app.donation.model.Donation;
import app.donation.model.User;

public class GetDonations extends Request
{
  DonationServiceAPI api;
  User user;

  public GetDonations(User user, DonationServiceAPI api, Context context, Response<Donation> callback, String message)
  {
    super(context, callback, message);
    this.api = api;
    this.user = user;
  }

  @Override
  protected List<Donation> doRequest(Object... params) throws Exception
  {
    List<Donation> donationList = api.getDonations(user);
    return donationList;
  }
}
