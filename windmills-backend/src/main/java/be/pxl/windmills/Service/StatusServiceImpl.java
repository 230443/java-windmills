package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Status;
import be.pxl.windmills.Repository.StatusRepository;
import be.pxl.windmills.resource.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;


@Service
public class StatusServiceImpl implements StatusService {

	private final StatusRepository statusRepository;

	@Autowired
	public StatusServiceImpl(StatusRepository statusRepository) {
		this.statusRepository = statusRepository;
	}


	@Override
	public StatusDTO getLastStatus() {
		return mapStatusToDTO(statusRepository.findTopByOrderByDateTimeDesc());
	}

	@Override
	public List<StatusDTO> getStatusByDateTime(int page, int size, String start, String end) {

		Pageable paging = PageRequest.of(page, size);

		LocalDateTime startDate;
		LocalDateTime endDate;

		if(start == null) {
			startDate = LocalDateTime.MIN;
		}
		else {
			startDate = LocalDateTime.parse(start);
		}

		if(end == null) {
			endDate = LocalDateTime.of(9999, 12, 31, 23, 59, 59);
		}
		else {
			endDate = LocalDateTime.parse(end);
		}

		return statusRepository
				.findStatusByDateTimeBetween(startDate, endDate, paging)
				.stream()
				.map(this::mapStatusToDTO)
				.collect(toList());
	}


	private StatusDTO mapStatusToDTO(Status status) {
		StatusDTO statusDTO = new StatusDTO();
		statusDTO.setIsActive(status.getIsActive());
		statusDTO.setTurbineSpeed(status.getTurbineSpeed());
		statusDTO.setWindSpeed(status.getWindSpeed());
		statusDTO.setDateTime(status.getDateTime());
		statusDTO.setId(status.getId());

		return statusDTO;
	}

	private Status mapDTOToStatus(StatusDTO statusDTO) {
		Status status = new Status();
		status.setIsActive(statusDTO.getIsActive());
		status.setTurbineSpeed(statusDTO.getTurbineSpeed());
		status.setWindSpeed(statusDTO.getWindSpeed());

		return status;
	}
}
