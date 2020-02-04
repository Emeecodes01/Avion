package com.mobigod.data.mapper

import com.mobigod.data.models.auth.TokenEntity
import com.mobigod.domain.entities.token.Token

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 09:38*/
class TokenMapper: BaseMapper<TokenEntity, Token>{
    override fun mapFromEntity(entity: TokenEntity): Token {
        return Token(entity.accessToken, entity.expiresIn, entity.tokenType)
    }

    override fun mapToEntity(domain: Token): TokenEntity {
        return TokenEntity(domain.accessToken, domain.expiresIn, domain.tokenType)
    }

}