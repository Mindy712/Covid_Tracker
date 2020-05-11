package gottlieb.covidTracker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CovidTrackerFrame extends JFrame {


    JTextField country;
    JButton getCountry;
    JButton getGlobal;

    public CovidTrackerFrame() {
        setSize(600, 800);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Covid-19 Stats");
        setLayout(new BorderLayout());

        CovidTrackerView view = new CovidTrackerView();
        add(view.panel, BorderLayout.CENTER);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com//")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidTrackerService service = retrofit.create((CovidTrackerService.class));

        CovidTrackerController covidTrackerController = new CovidTrackerController(service, view);


        JPanel chooseOutput = new JPanel();
        add(chooseOutput, BorderLayout.NORTH);

        country = new JTextField(25);
        chooseOutput.add(country);

        getCountry = new JButton("Country Data");
        chooseOutput.add(getCountry);
        getCountry.addActionListener(actionEvent -> {
            covidTrackerController.requestCountryData(country.getText());
        });

        getGlobal = new JButton("Global Data");
        chooseOutput.add(getGlobal);
        getGlobal.addActionListener(actionEvent -> {
            covidTrackerController.requestGlobalData();
        });
    }

    public static void main(String[] args) {
        new CovidTrackerFrame().setVisible(true);

    }
}
