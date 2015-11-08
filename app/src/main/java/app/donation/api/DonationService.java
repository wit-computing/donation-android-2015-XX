package app.donation.api;

import java.util.List;

import app.donation.model.Donation;
import app.donation.model.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

public interface DonationService
{
  @GET("/api/users")
  Call<List<User>> getUsers();

  @GET("/api/donations")
  Call<List<Donation>> getDonations();

  @POST("/api/users")
  Call<User> createUser(@Body User user);
}