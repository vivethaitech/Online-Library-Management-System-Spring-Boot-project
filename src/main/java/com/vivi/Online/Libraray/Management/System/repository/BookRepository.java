package com.vivi.Online.Libraray.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivi.Online.Libraray.Management.System.Entity.Book;

public interface BookRepository extends JpaRepository<Book,Long>{
	Book findByTitle(String title);
	boolean existsByTitle(String title);

}
