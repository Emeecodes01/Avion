package com.mobigod.avin.mapper

import com.mobigod.avin.models.auth.TokenModel
import com.mobigod.domain.entities.auth.Token

/**Created by: Emmanuel Ozibo
//on: 04, 2020-02-04
//at: 21:01*/
class TokenViewMapper: BaseViewMapper<TokenModel, Token> {

    override fun mapFromViewModel(par: TokenModel): Token {
        return Token(par.accessToken, par.expiresIn, par.tokenType)
    }

    override fun mapToViewModel(par: Token): TokenModel {
        return TokenModel(par.accessToken, par.expiresIn, par.tokenType)
    }

}