package gottlieb.covidTracker;

import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class CovidTrackerServiceTest {
    @Test
    public void getCurrentStats() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com//")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidTrackerService service = retrofit.create((CovidTrackerService.class));

        //when
        CovidTracker tracker = service.getCurrentStats().execute().body();

        //then
        assertNotNull(tracker);

        final CovidTracker.Global global = tracker.global;
        assertNotNull(global.newConfirmed);
        assertNotNull(global.newDeaths);
        assertNotNull(global.newRecovered);
        assertNotNull(global.totalConfirmed);
        assertNotNull(global.totalDeaths);
        assertNotNull(global.newDeaths);

        final CovidTracker.Countries countries = tracker.countries.get(0);
        assertNotNull(countries.country);
        assertNotNull(countries.country_key);
        assertNotNull(countries.newConfirmed);
        assertNotNull(countries.newDeaths);
        assertNotNull(countries.newRecovered);
        assertNotNull(countries.totalConfirmed);
        assertNotNull(countries.totalDeaths);
        assertNotNull(countries.totalRecovered);

    }
    @Test
    public void getSpecifiedCountry() throws IOException {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com//")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidTrackerService service = retrofit.create((CovidTrackerService.class));

        //when
        CovidTracker tracker = service.getCurrentStats().execute().body();

        List<CovidTracker.Countries> findCountries = tracker.countries;
        CovidTracker.Countries country = findCountries.get(findCountry(findCountries, 0, findCountries.size() - 1, "united states of america"));
        assertNotNull(country.country);
        System.out.println(country.country);
        assertNotNull(country.country_key);
        assertNotNull(country.newConfirmed);
        assertNotNull(country.newDeaths);
        assertNotNull(country.newRecovered);
        assertNotNull(country.totalConfirmed);
        assertNotNull(country.totalDeaths);
        assertNotNull(country.totalRecovered);
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