package example.conference.management.impl.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@RepositoryRestResource
public interface SeatTypeRepository extends JpaRepository<SeatType, String> {
}
