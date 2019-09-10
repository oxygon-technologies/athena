package application.dao;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Optional;
 
@NoRepositoryBean
interface BaseRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
 
    void delete(T deleted);
 
    Page<T> findAll(Pageable pageable);
     
    Optional<T> findById(ID id);
    
    Optional<T> findByName(String username);
 
    //T save(T persisted);
}
