package gottlieb.covidTracker;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class CovidTrackerController {
    private CovidTrackerService service;
    private CovidTrackerView view;

    public CovidTrackerController(CovidTrackerService service, CovidTrackerView view) {
        this.service = service;
        this.view = view;
    }

    public void requestGlobalData() {
        service.getCurrentStats().enqueue(new Callback<CovidTracker>() {

            @Override
            public void onResponse(Call<CovidTracker> call, Response<CovidTracker> response) {
                CovidTracker.Global global = response.body().global;
                view.setGlobal(global);
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
                CovidTracker.Countries country = countries.get(findCountry(countries, 0, countries.size(), userCountry));
                view.setCountry(country);
            }

            @Override
            public void onFailure(Call<CovidTracker> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


    private int findCountry(List<CovidTracker.Countries> countries, int start, int size, String country) {
        int mid = (start + size) / 2;

        if (country.equalsIgnoreCase(countries.get(mid).country))
        {
            return mid;
        }
        else if ((country.compareToIgnoreCase(countries.get(mid).country)) <= -1)
        {
            return findCountry(countries, 0, mid, country);
        }
        else if ((country.compareToIgnoreCase(countries.get(mid).country)) >= 1)
        {
            return findCountry(countries, mid, size, country);
        }
        return -1;
    }
}
