package be.pxl.windmills.Model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "status")
public class Status {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@CreationTimestamp
	private LocalDateTime dateTime;
	private Boolean isActive;
	private Double windSpeed;
	private Double turbineSpeed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Double getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(Double windSpeed) {
		this.windSpeed = windSpeed;
	}

	public Double getTurbineSpeed() {
		return turbineSpeed;
	}

	public void setTurbineSpeed(Double turbineSpeed) {
		this.turbineSpeed = turbineSpeed;
	}
}
