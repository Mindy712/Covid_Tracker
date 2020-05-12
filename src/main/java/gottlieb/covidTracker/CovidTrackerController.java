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

    public void requestGlobalData(JLabel title,
                                  JLabel newConfirmed,
                                  JLabel totalConfirmed,
                                  JLabel newDeaths,
                                  JLabel totalDeaths,
                                  JLabel newRecovered,
                                  JLabel totalRecovered,
                                  JLabel errorMessage) {
        service.getCurrentStats().enqueue(new Callback<CovidTracker>() {

            @Override
            public void onResponse(Call<CovidTracker> call, Response<CovidTracker> response) {
                CovidTracker.Global global = response.body().global;
                title.setText("World");
                newConfirmed.setText("New confirmed: " + String.valueOf(global.newConfirmed));
                totalConfirmed.setText("Total confirmed: " + String.valueOf(global.totalConfirmed));
                newDeaths.setText("New deaths: " + String.valueOf(global.newDeaths));
                totalDeaths.setText("Total deaths: " + String.valueOf(global.totalDeaths));
                newRecovered.setText("New recovered: " + String.valueOf(global.newRecovered));
                totalRecovered.setText("Total recovered: " + String.valueOf(global.totalRecovered));
                errorMessage.setText("");
            }

            @Override
            public void onFailure(Call<CovidTracker> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void requestCountryData(String userCountry,
                                   JLabel title,
                                   JLabel newConfirmed,
                                   JLabel totalConfirmed,
                                   JLabel newDeaths,
                                   JLabel totalDeaths,
                                   JLabel newRecovered,
                                   JLabel totalRecovered,
                                   JLabel errorMessage) {
        service.getCurrentStats().enqueue(new Callback<CovidTracker>() {

            @Override
            public void onResponse(Call<CovidTracker> call, Response<CovidTracker> response) {
                List<CovidTracker.Countries> countries = response.body().countries;
                try {
                    CovidTracker.Countries country = countries.get(findCountry(countries, 0, countries.size(), userCountry));
                    title.setText(country.country);
                    newConfirmed.setText("New confirmed: " + String.valueOf(country.newConfirmed));
                    totalConfirmed.setText("Total confirmed: " + String.valueOf(country.totalConfirmed));
                    newDeaths.setText("New deaths: " + String.valueOf(country.newDeaths));
                    totalDeaths.setText("Total deaths: " + String.valueOf(country.totalDeaths));
                    newRecovered.setText("New recovered: " + String.valueOf(country.newRecovered));
                    totalRecovered.setText("Total recovered: " + String.valueOf(country.totalRecovered));
                    errorMessage.setText("");
                }
                catch (StackOverflowError e)
                {
                    title.setText("");
                    newConfirmed.setText("");
                    totalConfirmed.setText("");
                    newDeaths.setText("");
                    totalDeaths.setText("");
                    newRecovered.setText("");
                    totalRecovered.setText("");
                    errorMessage.setText("Invalid country. Please try again.");
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

            if (country.equalsIgnoreCase(countries.get(mid).country) || country.equalsIgnoreCase(countries.get(mid).country_key)) {
                return mid;
            } else if ((country.compareToIgnoreCase(countries.get(mid).country)) <= -1) {
                return findCountry(countries, 0, mid, country);
            } else if ((country.compareToIgnoreCase(countries.get(mid).country)) >= 1) {
                return findCountry(countries, mid, size, country);
            }
        return -1;
    }
}
