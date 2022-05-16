package be.pxl.windmills.Repository;

import be.pxl.windmills.Model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<Weather, Long>
{

}
