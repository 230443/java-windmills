package be.pxl.windmills.Service;

import be.pxl.windmills.Model.Status;
import be.pxl.windmills.Repository.StatusRepository;
import be.pxl.windmills.resource.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	public StatusDTO getStatusByDateTime(String date) {
		return null;
	}


	@Override
	public List<StatusDTO> getAll(int page, int size) {
		Pageable paging = PageRequest.of(page, size);
		return statusRepository
				.findAll(paging)
				.getContent()
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
