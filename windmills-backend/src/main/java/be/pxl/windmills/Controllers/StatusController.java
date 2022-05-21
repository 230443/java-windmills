package be.pxl.windmills.Controllers;

import be.pxl.windmills.Service.StatusService;
import be.pxl.windmills.resource.StatusDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.util.List;

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

	@PostMapping
	public StatusDTO switchTurbine(@RequestBody StatusDTO statusDTO) {
		return webClient
				.post()
				.uri("/status")
				.contentType(MediaType.APPLICATION_JSON)
				.body(Mono.just(statusDTO), StatusDTO.class)
				.retrieve()
				.onStatus(HttpStatus::is4xxClientError, clientResponse -> {
					LOGGER.error("Error: " + clientResponse.statusCode());
					throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "required parameters: isActive");
				})
				.bodyToMono(StatusDTO.class)
				.block();
	}

	@GetMapping
	public List<StatusDTO> getAllStatus(@RequestParam(value = "page", defaultValue = "0") Integer page,
										@RequestParam(value = "size", defaultValue = "30") Integer size,
										@RequestParam(value = "start", required = false) String start,
										@RequestParam(value = "end", required = false) String end) {
		LOGGER.debug(" page: " + page + " size: " + size + " start: " + start + " end: " + end);

		 return statusService.getStatusByDateTime(page, size, start, end);
	}
}
