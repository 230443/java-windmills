package be.pxl.windmills.Repository;

import be.pxl.windmills.Model.CurrentWeather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<CurrentWeather, Long>
{

}
