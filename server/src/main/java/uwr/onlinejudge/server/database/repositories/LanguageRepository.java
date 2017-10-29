package uwr.onlinejudge.server.database.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uwr.onlinejudge.server.database.models.Language;
import uwr.onlinejudge.server.database.models.Task;

import java.util.List;

public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findById(long id);

    List<Language> findByTask(Task task);
}
