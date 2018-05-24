package com.market.survey.core.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.market.survey.core.domain.Party;
import com.market.survey.core.domain.Role;
import com.market.survey.core.repository.PartyRepository;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private PartyRepository partyRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
    	Party party=partyRepository.findByName(username);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
       for (Role role : party.getRoles()){
   
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new org.springframework.security.core.userdetails.User(party.getName(), party.getPassword(), grantedAuthorities);
    }
}

