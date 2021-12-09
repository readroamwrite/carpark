package net.codej.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.codej.TUser;

public interface TUserRepository extends JpaRepository<TUser,Integer> {

}
