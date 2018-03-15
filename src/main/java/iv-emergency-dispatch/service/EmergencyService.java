package ivemergencydispatch.service;

import ivemergencydispatch.model.Emergency;
import ivemergencydispatch.repository.EmergencyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

@Service
public class EmergencyService {
    @Inject
    private EmergencyRepository emergencyRepository;

    public Emergency createEmergency(Emergency emergency) {
        // Save emergency object to our database in mLab
        return emergencyRepository.save(emergency);
    }
}