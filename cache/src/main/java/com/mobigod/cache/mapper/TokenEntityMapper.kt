package com.mobigod.cache.mapper

import com.mobigod.cache.models.TokenPrefEntity
import com.mobigod.data.models.auth.TokenEntity

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 09:01*/


class TokenEntityMapper: CoreMapper<TokenPrefEntity, TokenEntity>{
    override fun mapFromDbEntity(value: TokenPrefEntity): TokenEntity {
        return TokenEntity(
            value.accessToken,
            value.expiresIn,
            value.tokenType)
    }

    override fun mapToDbEntity(value: TokenEntity): TokenPrefEntity {
        return TokenPrefEntity(accessToken = value.accessToken,
            expiresIn = value.expiresIn, tokenType = value.tokenType)
    }


}