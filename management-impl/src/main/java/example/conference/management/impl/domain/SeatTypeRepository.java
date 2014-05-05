package example.conference.management.impl.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatTypeRepository extends MongoRepository<SeatType, String> {
}
