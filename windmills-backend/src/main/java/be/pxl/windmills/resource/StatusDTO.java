package be.pxl.windmills.resource;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
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

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
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

	@Override
	public String toString() {
		return "StatusDTO{" +
				"isActive=" + isActive +
				", windSpeed=" + windSpeed +
				", turbineSpeed=" + turbineSpeed +
				'}';
	}
}
