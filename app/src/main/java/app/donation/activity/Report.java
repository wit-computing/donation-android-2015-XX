package app.donation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.List;

import app.donation.api.GetDonations;
import app.donation.api.Response;
import app.donation.main.DonationApp;
import app.donation.R;
import app.donation.model.Donation;

public class Report extends AppCompatActivity implements Response<Donation>
{
  private ListView    listView;
  private DonationApp app;
  private DonationAdapter adapter;

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_report);

    app = (DonationApp) getApplication();

    listView = (ListView) findViewById(R.id.reportList);
    adapter = new DonationAdapter (this, app.donations);
    listView.setAdapter(adapter);

    new GetDonations(app.donationServiceAPI, this, this, "Retrieving list of donations").execute();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu)
  {
    getMenuInflater().inflate(R.menu.menu_report, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item)
  {
    switch (item.getItemId())
    {
      case R.id.menuDonate : startActivity (new Intent(this, Donate.class));
        break;
      case R.id.menuLogout : startActivity (new Intent(this, Welcome.class));
        break;
    }
    return true;
  }

  @Override
  public void setResponse(List<Donation> aList)
  {
    app.donations     = aList;
    adapter.donations = aList;
    adapter.notifyDataSetChanged();
  }

  @Override
  public void setResponse(Donation anObject)
  {

  }

  @Override
  public void errorOccurred(Exception e)
  {

  }
}

class DonationAdapter extends ArrayAdapter<Donation>
{
  private Context context;
  public List<Donation> donations;

  public DonationAdapter(Context context, List<Donation> donations)
  {
    super(context, R.layout.row_layout, donations);
    this.context   = context;
    this.donations = donations;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent)
  {
    LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    View     view       = inflater.inflate(R.layout.row_layout, parent, false);
    Donation donation   = donations.get(position);
    TextView amountView = (TextView) view.findViewById(R.id.row_amount);
    TextView methodView = (TextView) view.findViewById(R.id.row_method);

    amountView.setText("" + donation.amount);
    methodView.setText(donation.method);

    return view;
  }

  @Override
  public int getCount()
  {
    return donations.size();
  }
}