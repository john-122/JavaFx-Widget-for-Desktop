package com.arivan.amin.widget.forecast;

import org.junit.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class OpenWeatherMapTest
{
    WeatherProvider provider;
    OpenWeatherMap weatherData;
    
    @Before
    public void setUp () throws Exception
    {
        provider = TextFileWeatherProvider.newInstance();
        weatherData = OpenWeatherMap.newInstance();
        weatherData.setWeatherProvider(provider);
    }
    
    @After
    public void tearDown () throws Exception
    {
    }
    
    @Test
    public void locationName ()
    {
        String locationName = weatherData.locationName();
        assertThat(locationName, is("As Sulaymaniyah"));
    }
    
    @Test
    public void sunrise ()
    {
        String sunrise = weatherData.sunrise();
        assertThat(sunrise, is("04:07"));
    }
    
    @Test
    public void sunset ()
    {
        String sunset = weatherData.sunset();
        assertThat(sunset, is("13:54"));
    }
    
    @Test
    public void weatherCondition ()
    {
        String condition = weatherData.weatherCondition();
        assertThat(condition, is("broken clouds"));
    }
    
    @Test
    public void weatherIcon ()
    {
        String icon = weatherData.weatherIcon();
        assertThat(icon, is("04n.png"));
    }
    @Test
    public void precipitationType ()
    {
        String type = weatherData.precipitationType();
        assertThat(type, is("rain"));
    }
    
    @Test
    public void precipitationValue ()
    {
        String value = weatherData.precipitationValue();
        assertThat(value, is("5%"));
    }
    
    @Test
    public void windDirection ()
    {
        String direction = weatherData.windDirection();
        assertThat(direction, is("North-northeast"));
    }
    
    @Test
    public void windsSpeed ()
    {
        int speed = weatherData.windsSpeed();
        assertThat(speed, is(1));
    }
    
    @Test
    public void windName ()
    {
        String windName = weatherData.windName();
        assertThat(windName, is("Calm"));
    }
    
    @Test
    public void temperatureUnit ()
    {
        String unit = weatherData.temperatureUnit();
        assertThat(unit, is("C"));
    }
    
    @Test
    public void temperatureValue ()
    {
        int value = weatherData.temperatureValue();
        assertThat(value, is(7));
    }
    
    @Test
    public void pressureUnit ()
    {
        String unit = weatherData.pressureUnit();
        assertThat(unit, is("hPa"));
    }
    
    @Test
    public void pressureValue ()
    {
        String value = weatherData.pressureValue();
        assertThat(value, is("930"));
    }
    
    @Test
    public void humidityValue ()
    {
        String value = weatherData.humidityValue();
        assertThat(value, is("78"));
    }
    
    @Test
    public void cloudsName ()
    {
        String cloudsName = weatherData.cloudsName();
        assertThat(cloudsName, is("broken clouds"));
    }
    
    @Test
    public void cloudsRate ()
    {
        String rate = weatherData.cloudsRate();
        assertThat(rate, is("56"));
    }
    
    public void secondDayWeather ()
    {
    }
    
    public void secondDayMaxTemperature ()
    {
    }
    
    public void secondMinTemperature ()
    {
    }
    
    public void thirdDayWeather ()
    {
    }
    
    public void thirdDayMaxTemperature ()
    {
    }
    
    public void thirdMinTemperature ()
    {
    }
    
    public void fourthDayWeather ()
    {
    }
    
    public void fourthDayMaxTemperature ()
    {
    }
    
    public void fourthMinTemperature ()
    {
    }
}
