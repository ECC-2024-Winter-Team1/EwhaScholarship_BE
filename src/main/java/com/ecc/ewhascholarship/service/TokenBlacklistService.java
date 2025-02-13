package com.ecc.ewhascholarship.service;

import com.ecc.ewhascholarship.domain.TokenBlacklist;
import com.ecc.ewhascholarship.repository.TokenBlacklistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

    @Autowired
    private TokenBlacklistRepository tokenBlacklistRepository;

    public void blacklistToken(String token) {
        if (!tokenBlacklistRepository.existsByToken(token)) {
            TokenBlacklist tokenBlacklist = new TokenBlacklist(null, token);
            tokenBlacklistRepository.save(tokenBlacklist);
        }
    }

    public boolean isTokenBlacklisted(String token) {
        return tokenBlacklistRepository.existsByToken(token);
    }
}
