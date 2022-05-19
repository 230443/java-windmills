package be.pxl.windmills.Service;

import be.pxl.windmills.resource.StatusDTO;

import java.util.List;

public interface StatusService {
	StatusDTO getLastStatus();
	StatusDTO getStatusByDateTime(String dateTime);
	List<StatusDTO> getAll(int page, int size);
}
