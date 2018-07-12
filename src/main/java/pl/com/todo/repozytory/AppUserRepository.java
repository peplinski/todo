package pl.com.todo.repozytory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.com.todo.model.appUser.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

    //List<AppUser>findAllByNameIsLike(String name);
    //AppUser findByNameAndAddressOrSurname();
}
