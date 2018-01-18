package uwr.onlinejudge.server.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uwr.onlinejudge.server.models.Score;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Test;
import uwr.onlinejudge.server.models.form.SolutionForm;
import uwr.onlinejudge.server.repositories.ScoreRepository;
import uwr.onlinejudge.server.repositories.SolutionRepository;

@Service
public class SolutionServiceImpl implements SolutionService {
    SolutionRepository solutionRepository;
    ObjectMapper objectMapper;
    ScoreRepository scoreRepository;

    @Autowired
    public SolutionServiceImpl(SolutionRepository solutionRepository, ObjectMapper objectMapper, ScoreRepository scoreRepository) {
        this.solutionRepository = solutionRepository;
        this.objectMapper = objectMapper;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public Solution getSolution(long solutionId) {
        return solutionRepository.findOne(solutionId);
    }

    @Override
    public Solution save(SolutionForm solutionForm) {
        return solutionRepository.save(objectMapper.convertValue(solutionForm, Solution.class));
    }

    @Override
    public Score findScoreBySolutionAndTest(Solution solution, Test test) {
        return scoreRepository.findBySolutionAndTest(solution, test);
    }

    @Override
    public void save(Score score) {
        scoreRepository.save(score);
    }

}
