package be.pxl.windmills.Controllers;

import be.pxl.windmills.Service.StatusService;
import be.pxl.windmills.resource.StatusDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
@RequestMapping("status")
public class StatusController {



	private final WebClient webClient = WebClient.create("http://localhost:5000");
	private final StatusService statusService;

	@Autowired
	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("/now")
	public StatusDTO currentStatus() {
		return statusService.getLastStatus();
	}

	@GetMapping("/{dateTime}")
	public StatusDTO getStatusByDateTime(@PathVariable String dateTime) {
		return statusService.getStatusByDateTime(dateTime);
	}

	@PostMapping
	public StatusDTO addStatus(@RequestBody StatusDTO statusDTO) {
		return statusService.addStatus(statusDTO);
	}
}
