package app.donation.api;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.donation.model.Donation;
import app.donation.model.User;
import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class DonationServiceAPI
{
  private  String service_url = "http://10.0.2.2:9000";
  DonationService service;

  public DonationServiceAPI()
  {
    Gson gson = new GsonBuilder().create();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(service_url)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    service = retrofit.create(DonationService.class);
  }

  public List<User> getUsers() throws Exception
  {
    Call<List<User>> call = (Call<List<User>>) service.getUsers();
    retrofit.Response<List<User>> users = null;
    users = call.execute();
    return users.body();
  }

  public List<Donation> getDonations() throws Exception
  {
    Call<List<Donation>> call = (Call<List<Donation>>) service.getDonations();
    retrofit.Response<List<Donation>> donations = null;
    donations = call.execute();
    return donations.body();
  }

  public User createUser(User newUser) throws Exception
  {
    Call<User> call = (Call<User>) service.createUser(newUser);
    retrofit.Response<User> returnedUser = null;
    returnedUser = call.execute();
    return returnedUser.body();
  }

  public Donation createDonation(Donation newDonation) throws Exception
  {
    Call<Donation> call = (Call<Donation>) service.createDonation(newDonation);
    retrofit.Response<Donation> returnedDonation = null;
    returnedDonation = call.execute();
    return returnedDonation.body();
  }
}
