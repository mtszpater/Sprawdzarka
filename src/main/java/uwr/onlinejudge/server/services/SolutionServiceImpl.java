package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.form.SolutionForm;
import uwr.onlinejudge.server.repositories.SolutionRepository;

@Service
public class SolutionServiceImpl implements SolutionService {
    SolutionRepository solutionRepository;
    ObjectMapper objectMapper;

    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository, ObjectMapper objectMapper) {
        this.solutionRepository = solutionRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public Solution getSolution(long solutionId) {
        return solutionRepository.findOne(solutionId);
    }

    @Override
    public void save(SolutionForm solutionForm) {
        solutionRepository.save(objectMapper.convertValue(solutionForm, Solution.class));
    }
}
