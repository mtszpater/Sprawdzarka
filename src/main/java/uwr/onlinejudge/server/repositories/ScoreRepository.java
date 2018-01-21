package uwr.onlinejudge.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.models.Score;
import uwr.onlinejudge.server.models.Solution;
import uwr.onlinejudge.server.models.Test;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findBySolution(Solution test);

    Score findBySolutionAndTest(Solution solution, Test test);

    void deleteByTest(Test test);
}
