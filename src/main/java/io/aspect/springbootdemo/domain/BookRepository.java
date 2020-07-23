package io.aspect.springbootdemo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findAll(final Pageable pageable);

    List<Book> findByAuthor(final String author);

    List<Book> findByAuthorAndStatus(final String author, final int status);

    List<Book> findByDescriptionEndsWith(final String suffix);

    // @Query("select b from Book b where length(b.name) > ?1")
    @Query(value = "select * from book where length(name) > ?1", nativeQuery = true)
    List<Book> findByJPQL(final int length);

    @Transactional
    @Modifying
    @Query("update Book b set b.status = ?2 where b.id = ?1")
    int updateByJPQL(final long id, final int status);

    @Transactional
    @Modifying
    @Query("delete from Book b where b.id = ?1")
    int deleteByJPQL(final long id);
    //???
}
