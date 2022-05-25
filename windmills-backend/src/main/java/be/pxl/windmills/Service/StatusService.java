package be.pxl.windmills.Service;

import be.pxl.windmills.resource.StatusDTO;

import java.util.List;

public interface StatusService {
	StatusDTO getLastStatus();
	List<StatusDTO> getStatusByDateTime(int page, int size, String start, String end);
}
