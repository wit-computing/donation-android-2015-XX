package app.donation.api;

import android.content.Context;

import java.util.List;

import app.donation.model.User;

public class CreateUser extends Request
{
  DonationServiceAPI api;
  User user;

  public CreateUser(User user, DonationServiceAPI api, Context context, Response<User> callback, String message)
  {
    super(context, callback, message);
    this.api = api;
    this.user = user;
  }

  @Override
  protected User doRequest(Object... params) throws Exception
  {
    User returnedUser = api.createUser(user);
    return returnedUser;
  }
}