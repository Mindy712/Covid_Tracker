package gottlieb.covidTracker;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CovidTracker {

    @SerializedName("Global")
    Global global;

    class Global
    {
        @SerializedName("NewConfirmed")
        int newConfirmed;
        @SerializedName("TotalConfirmed")
        int totalConfirmed;
        @SerializedName("NewDeaths")
        int newDeaths;
        @SerializedName("TotalDeaths")
        int totalDeaths;
        @SerializedName("NewRecovered")
        int newRecovered;
        @SerializedName("TotalRecovered")
        int totalRecovered;
    }

    @SerializedName("Countries")
    List<countries> countries;

    class countries
    {
        @SerializedName("Country")
        String country;
        @SerializedName("Slug")
        String country_key;
        @SerializedName("NewConfirmed")
        int newConfirmed;
        @SerializedName("TotalConfirmed")
        int totalConfirmed;
        @SerializedName("NewDeaths")
        int newDeaths;
        @SerializedName("TotalDeaths")
        int totalDeaths;
        @SerializedName("NewRecovered")
        int newRecovered;
        @SerializedName("TotalRecovered")
        int totalRecovered;
    }
}
