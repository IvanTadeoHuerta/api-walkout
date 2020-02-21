package com.tech.pro.walker.api.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tech.pro.walker.api.models.dao.IWalkerDao;
import com.tech.pro.walker.api.models.entity.Walker;

@Service
public class IWalkerServiceImp implements IWalkerService, UserDetailsService {
	
	@Autowired
	private IWalkerDao iWalkerDao;

	@Override
	@Transactional(readOnly=true)
	public List<Walker> findAll() {
		return iWalkerDao.findAll();
	}

	@Override
	@Transactional
	public Walker save(Walker w) {
		return iWalkerDao.save(w);
	}

	@Override
	@Transactional(readOnly=true)
	public Optional<Walker> findById(Long id_walker) {
		return iWalkerDao.findById(id_walker);
	}

	@Override
	@Transactional
	public void deleteById(Long id_walker) {
	    iWalkerDao.deleteById(id_walker);
	}

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Walker walker = iWalkerDao.findByUsuario(username);
		
		List<GrantedAuthority> authorities = walker.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getRol()))
				.collect(Collectors.toList());

		return new User(walker.getUsuario(), walker.getPwd(), walker.isEstatus(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly = true)
	public Walker findByUsuario(String username) {
		return iWalkerDao.findByUsuario(username);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Walker> findAllWalkersByRol(Long id_rol) {
		return iWalkerDao.findAllWalkersByRol(id_rol);
	}
	

	@Override
	@Transactional
	public void updateContrasenia(String contrasenia, Long id_walker) {
		iWalkerDao.updateContrasenia(contrasenia, id_walker);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Walker> findByEmail(String email) {
		return iWalkerDao.findByEmail( email );
	}
	
	
	
	

}
