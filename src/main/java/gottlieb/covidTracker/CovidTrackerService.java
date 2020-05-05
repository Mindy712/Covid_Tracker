package gottlieb.covidTracker;

import retrofit2.Call;
import retrofit2.http.GET;

public interface CovidTrackerService {
    @GET("/summary")
    Call<CovidTracker> getCurrentStats();
}
