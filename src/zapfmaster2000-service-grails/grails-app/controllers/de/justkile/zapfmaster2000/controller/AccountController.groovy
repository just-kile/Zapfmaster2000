package de.justkile.zapfmaster2000.controller

import de.justkile.zapfmaster2000.model.Account;
import grails.transaction.Transactional
import org.restapidoc.annotation.RestApi
import org.restapidoc.annotation.RestApiError
import org.restapidoc.annotation.RestApiErrors
import org.restapidoc.annotation.RestApiMethod
import org.restapidoc.annotation.RestApiParam
import org.restapidoc.annotation.RestApiParams
import org.restapidoc.pojo.RestApiParamType;

@RestApi(name = "Account Services", description = "Methods to manage accounts.")
class AccountController {

	static responseFormats = ['json', 'xml']

    @RestApiMethod(description="Retrieve all accounts.")
    @RestApiParams(params=[])
	@Transactional(readOnly = true)
    def retrieveAccounts() {
		def accounts = []
		Account.list().each {
			accounts << ['accountId': it.id, 'name': it.name]
		}
		respond accounts
	}

    @RestApiMethod(description="Creats a new account.")
    @RestApiParams(params=[
            @RestApiParam(name="name", type="string", paramType = RestApiParamType.QUERY, description = "Name of the account to create")
    ])
    @RestApiErrors(apierrors=[
            @RestApiError(code="400",description="No name was provided.")
    ])
	@Transactional
	def createAccount() {
		if (params.name) {
			new Account(name:params.name).save()
			respond status:'OK'
		} else {
			response.sendError 400
		}
	}
}
