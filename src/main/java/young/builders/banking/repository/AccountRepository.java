package young.builders.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import young.builders.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
