package gottlieb.covidTracker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import javax.swing.*;
import java.util.List;

public class CovidTrackerController {
    private CovidTrackerService service;

    public CovidTrackerController(CovidTrackerService service) {
        this.service = service;
    }

    public void requestGlobalData() {
        service.getCurrentStats().enqueue(new Callback<CovidTracker>() {

            @Override
            public void onResponse(Call<CovidTracker> call, Response<CovidTracker> response) {
                CovidTracker.Global global = response.body().global;
                CovidTrackerFrame.title.setText("World");
                CovidTrackerFrame.newConfirmed.setText("New confirmed: " + String.valueOf(global.newConfirmed));
                CovidTrackerFrame.totalConfirmed.setText("Total confirmed: " + String.valueOf(global.totalConfirmed));
                CovidTrackerFrame.newDeaths.setText("New deaths: " + String.valueOf(global.newDeaths));
                CovidTrackerFrame.totalDeaths.setText("Total deaths: " + String.valueOf(global.totalDeaths));
                CovidTrackerFrame.newRecovered.setText("New recovered: " + String.valueOf(global.newRecovered));
                CovidTrackerFrame.totalRecovered.setText("Total recovered: " + String.valueOf(global.totalRecovered));
                CovidTrackerFrame.errorMessage.setText("");
            }

            @Override
            public void onFailure(Call<CovidTracker> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestCountryData(String userCountry) {
        service.getCurrentStats().enqueue(new Callback<CovidTracker>() {

            @Override
            public void onResponse(Call<CovidTracker> call, Response<CovidTracker> response) {
                List<CovidTracker.Countries> countries = response.body().countries;
                try {
                    CovidTracker.Countries country = countries.get(findCountry(countries, 0, countries.size(), userCountry));
                    CovidTrackerFrame.title.setText(country.country);
                    CovidTrackerFrame.newConfirmed.setText("New confirmed: " + String.valueOf(country.newConfirmed));
                    CovidTrackerFrame.totalConfirmed.setText("Total confirmed: " + String.valueOf(country.totalConfirmed));
                    CovidTrackerFrame.newDeaths.setText("New deaths: " + String.valueOf(country.newDeaths));
                    CovidTrackerFrame.totalDeaths.setText("Total deaths: " + String.valueOf(country.totalDeaths));
                    CovidTrackerFrame.newRecovered.setText("New recovered: " + String.valueOf(country.newRecovered));
                    CovidTrackerFrame.totalRecovered.setText("Total recovered: " + String.valueOf(country.totalRecovered));
                    CovidTrackerFrame.errorMessage.setText("");
                }
                catch (StackOverflowError e)
                {
                    CovidTrackerFrame.title.setText("");
                    CovidTrackerFrame.newConfirmed.setText("");
                    CovidTrackerFrame.totalConfirmed.setText("");
                    CovidTrackerFrame.newDeaths.setText("");
                    CovidTrackerFrame.totalDeaths.setText("");
                    CovidTrackerFrame.newRecovered.setText("");
                    CovidTrackerFrame.totalRecovered.setText("");
                    CovidTrackerFrame.errorMessage.setText("Invalid country. Please try again.");
                }
            }

            @Override
            public void onFailure(Call<CovidTracker> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private int findCountry(List<CovidTracker.Countries> countries, int start, int size, String country) {
            int mid = (start + size) / 2;

            if (country.equalsIgnoreCase(countries.get(mid).country)) {
                return mid;
            } else if ((country.compareToIgnoreCase(countries.get(mid).country)) <= -1) {
                return findCountry(countries, 0, mid, country);
            } else if ((country.compareToIgnoreCase(countries.get(mid).country)) >= 1) {
                return findCountry(countries, mid, size, country);
            }
        return -1;
    }
}
