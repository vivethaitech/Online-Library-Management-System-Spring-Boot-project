package com.vivi.Online.Libraray.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vivi.Online.Libraray.Management.System.Entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity,Long>{

}
