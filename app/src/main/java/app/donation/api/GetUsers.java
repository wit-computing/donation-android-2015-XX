package app.donation.api;

import android.content.Context;

import java.util.List;

import app.donation.model.User;

public class GetUsers extends Request
{
  DonationServiceAPI api;

  public GetUsers(DonationServiceAPI api, Context context, Response<User> callback, String message)
  {
    super(context, callback, message);
    this.api = api;
  }

  @Override
  protected List<User> doRequest(Object... params) throws Exception
  {
    List<User> userList = api.getUsers();
    return userList;
  }
}