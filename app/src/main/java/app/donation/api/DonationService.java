package app.donation.api;

import java.util.List;

import app.donation.model.Donation;
import app.donation.model.User;
import retrofit.Call;
import retrofit.http.GET;

public interface DonationService
{
  @GET("/api/users")
  Call<List<User>> getUsers();

  @GET("/api/donations")
  Call<List<Donation>> getDonations();
}