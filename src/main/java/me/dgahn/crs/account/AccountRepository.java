package me.dgahn.crs.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class AccountRepository {

  private final EntityManager em;

  public void save(Account account) {
    em.persist(account);
  }

  public Account findByAccountId(Long accountId) {
    return em.find(Account.class, accountId);
  }

  public Optional<Account> findById(String id) {
    List<Account> account = em.createQuery("SELECT a FROM Account a WHERE a.id = :id", Account.class)
                              .setParameter("id", id)
                              .getResultList();
    return account.size() == 0 ? Optional.empty() : Optional.of(account.get(0));
  }

}
