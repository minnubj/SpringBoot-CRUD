package jvlcode.SimplePro.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jvlcode.SimplePro.Entity.UserEntity;

public interface UserRespository extends JpaRepository<UserEntity,Long>{
      
}
