package example.conference.management.impl.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConferenceRepository extends MongoRepository<Conference, String> {
}
