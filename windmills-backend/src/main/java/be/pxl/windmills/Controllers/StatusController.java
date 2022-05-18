package be.pxl.windmills.Controllers;

import be.pxl.windmills.Service.StatusService;
import be.pxl.windmills.resource.StatusDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

@RestController
@RequestMapping("status")
public class StatusController {
	// initialize logger
	private static final Logger LOGGER = LogManager.getLogger(StatusController.class);
	private final WebClient webClient = WebClient.create("http://localhost:5000");
	private final StatusService statusService;

	@Autowired
	public StatusController(StatusService statusService) {
		this.statusService = statusService;
	}

	@GetMapping("/now")
	public StatusDTO currentStatus() {
		StatusDTO status = webClient.get().uri("/status").retrieve().bodyToMono(StatusDTO.class).block();

		LOGGER.info("status: " + status);

		return status;
	}

	@GetMapping("/last")
	public StatusDTO lastStatus() {
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
