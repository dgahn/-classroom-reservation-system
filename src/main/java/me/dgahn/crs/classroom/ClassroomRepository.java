package me.dgahn.crs.classroom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ClassroomRepository {

  private final EntityManager em;

  public Optional<List<Classroom>> findAll() {
    List<Classroom> result = em.createQuery("SELECT c FROM Classroom c", Classroom.class)
                               .getResultList();
    return Optional.of(result);
  }

  public Optional<Classroom> findBydClassroomId(Long classroomId) {
    Classroom classroom = em.find(Classroom.class, classroomId);

    return classroom == null ? Optional.empty() : Optional.of(classroom);
  }

}
