package ivemergencydispatch.repositories;

import ivemergencydispatch.models.Emergency;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyRepository extends MongoRepository<Emergency, String> {

}
