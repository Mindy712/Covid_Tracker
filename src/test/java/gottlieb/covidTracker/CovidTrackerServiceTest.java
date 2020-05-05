package gottlieb.covidTracker;

import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

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

        final CovidTracker.countries countries = tracker.countries.get(0);
        assertNotNull(countries.country);
        assertNotNull(countries.country_key);
        assertNotNull(countries.newConfirmed);
        assertNotNull(countries.newDeaths);
        assertNotNull(countries.newRecovered);
        assertNotNull(countries.totalConfirmed);
        assertNotNull(countries.totalDeaths);
        assertNotNull(countries.totalRecovered);

    }
}