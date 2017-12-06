package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Score;
import uwr.onlinejudge.server.database.models.Solution;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findById(long id);

    List<Score> findBySolution(Solution test);
}
