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
  private String service_url = "http://10.0.2.2:9000";
  private DonationService service;

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

  public List<Donation> getDonations(User user) throws Exception
  {
    Call<List<Donation>> call = (Call<List<Donation>>) service.getDonations(user.id);
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

  public String deleteUser(User user) throws Exception
  {
    Call<String> call =  (Call<String>) service.deleteUser(user.id);
    retrofit.Response<String> val = call.execute();
    return  val.body();
  }

  public Donation createDonation(User user, Donation newDonation) throws Exception
  {
    Call<Donation> call = (Call<Donation>) service.createDonation(user.id, newDonation);
    retrofit.Response<Donation> returnedDonation = null;
    returnedDonation = call.execute();
    return returnedDonation.body();
  }

  public String deleteDonation(User user, Donation donation) throws Exception
  {
    Call<String> call =  (Call<String>) service.deleteDonation(user.id, donation.id);
    retrofit.Response<String> val = call.execute();
    return  val.body();
  }
}