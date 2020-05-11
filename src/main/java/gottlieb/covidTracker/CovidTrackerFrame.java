package gottlieb.covidTracker;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.swing.*;
import java.awt.*;

public class CovidTrackerFrame extends JFrame {

    JTextField country;
    JButton getCountry;
    JButton getGlobal;

    public static JLabel title;
    public static JLabel newConfirmed;
    public static JLabel totalConfirmed;
    public static JLabel newDeaths;
    public static JLabel totalDeaths;
    public static JLabel newRecovered;
    public static JLabel totalRecovered;
    public static JLabel errorMessage;

    public CovidTrackerFrame() {
        setSize(600, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Covid-19 Stats");
        setLayout(new BorderLayout());

        JPanel info = new JPanel();
        add(info, BorderLayout.CENTER);
        info.setLayout(new BoxLayout(info, BoxLayout.PAGE_AXIS));
        info.add(title = new JLabel(""));
        info.add(newConfirmed = new JLabel(""));
        info.add(totalConfirmed = new JLabel(""));
        info.add(newDeaths = new JLabel(""));
        info.add(totalDeaths = new JLabel(""));
        info.add(newRecovered = new JLabel(""));
        info.add(totalRecovered = new JLabel(""));
        info.add(errorMessage = new JLabel(""));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.covid19api.com//")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CovidTrackerService service = retrofit.create((CovidTrackerService.class));

        CovidTrackerController covidTrackerController = new CovidTrackerController(service);


        JPanel chooseOutput = new JPanel();
        add(chooseOutput, BorderLayout.NORTH);

        country = new JTextField(25);
        chooseOutput.add(country);

        getCountry = new JButton("Country Data");
        chooseOutput.add(getCountry);
        getCountry.addActionListener(actionEvent -> {
            covidTrackerController.requestCountryData(country.getText());
            country.setText("");
        });

        getGlobal = new JButton("Global Data");
        chooseOutput.add(getGlobal);
        getGlobal.addActionListener(actionEvent -> {
            covidTrackerController.requestGlobalData();
            country.setText("");
        });
    }

    public static void main(String[] args) {
        new CovidTrackerFrame().setVisible(true);

    }
}
