package app.donation.api;

import android.content.Context;

import java.util.List;

import app.donation.model.Donation;

public class GetDonations extends Request
{
  DonationServiceAPI api;

  public GetDonations(DonationServiceAPI api, Context context, Response<Donation> callback, String message)
  {
    super(context, callback, message);
    this.api = api;
  }

  @Override
  protected List<Donation> doRequest(Object... params) throws Exception
  {
    List<Donation> donationList = api.getDonations();
    return donationList;
  }
}
