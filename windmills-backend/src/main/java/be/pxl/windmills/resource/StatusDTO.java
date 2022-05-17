package be.pxl.windmills.resource;

import java.time.LocalDateTime;

public class StatusDTO {
	private Long id;
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
