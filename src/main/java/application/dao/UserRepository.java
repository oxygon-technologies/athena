package application.dao;


import org.springframework.stereotype.Repository;

import application.model.User;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
	
}
