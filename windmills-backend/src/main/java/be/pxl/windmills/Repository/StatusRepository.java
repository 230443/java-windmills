package be.pxl.windmills.Repository;
import be.pxl.windmills.Model.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	Status findTopByOrderByDateTimeDesc();
	Page<Status> findAll(Pageable pageable);
	List<Status> findStatusByDateTimeBetween(LocalDateTime start, LocalDateTime end, Pageable pageable);
}
