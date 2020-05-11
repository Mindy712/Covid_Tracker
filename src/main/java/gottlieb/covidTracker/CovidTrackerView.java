package gottlieb.covidTracker;

import javax.swing.*;

public class CovidTrackerView extends JComponent {
    private CovidTracker.Global global;
    private CovidTracker.Countries countries;

    public JPanel panel = new JPanel();

    public void setGlobal(CovidTracker.Global global) {
        this.global = global;
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel("World"));
        panel.add(new JLabel("New confirmed: " + String.valueOf(global.newConfirmed)));
        panel.add(new JLabel("Total confirmed: " + String.valueOf(global.totalConfirmed)));
        panel.add(new JLabel("New deaths: " + String.valueOf(global.newDeaths)));
        panel.add(new JLabel("Total deaths: " + String.valueOf(global.totalDeaths)));
        panel.add(new JLabel("New recovered: " + String.valueOf(global.newRecovered)));
        panel.add(new JLabel("Total recovered: " + String.valueOf(global.totalRecovered)));
        repaint();
    }

    public void setCountry(CovidTracker.Countries countries) {
        this.countries = countries;
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(new JLabel(countries.country));
        panel.add(new JLabel("New confirmed: " + String.valueOf(countries.newConfirmed)));
        panel.add(new JLabel("Total confirmed: " + String.valueOf(countries.totalConfirmed)));
        panel.add(new JLabel("New deaths: " + String.valueOf(countries.newDeaths)));
        panel.add(new JLabel("Total deaths: " + String.valueOf(countries.totalDeaths)));
        panel.add(new JLabel("New recovered: " + String.valueOf(countries.newRecovered)));
        panel.add(new JLabel("Total recovered: " + String.valueOf(countries.totalRecovered)));
        repaint();
    }

}
