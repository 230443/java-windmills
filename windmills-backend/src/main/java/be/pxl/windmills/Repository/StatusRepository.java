package be.pxl.windmills.Repository;
import be.pxl.windmills.Model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

	Status findTopByOrderByDateTimeDesc();
}