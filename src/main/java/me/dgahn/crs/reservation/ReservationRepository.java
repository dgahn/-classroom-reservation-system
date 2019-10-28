package me.dgahn.crs.reservation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class ReservationRepository {

  private final EntityManager em;

  public void save(Reservation reservation) {
    em.persist(reservation);
  }

  public Optional<List<Reservation>> findByBetweenDate(final LocalDateTime startDate, final LocalDateTime endDate) {
    List<Reservation> resultList = em.createQuery("SELECT r " +
        "FROM Reservation r " +
        "WHERE r.startDate BETWEEN :startDate AND :endDate " +
        " OR r.endDate BETWEEN :startDate AND :endDate", Reservation.class)
                        .setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                        .getResultList();

    return resultList.size() == 0 ? Optional.empty() : Optional.of(resultList);
  }

}
