package de.justkile.zapfmaster2000.controller

import javax.servlet.http.HttpServletResponse;

import de.justkile.zapfmaster2000.model.Admin;
import de.justkile.zapfmaster2000.model.Admin.AdminType;
import grails.transaction.Transactional;

class InstallationController {

	static responseFormats = ['json', 'xml']

	@Transactional(readOnly = true)
	def index() {
		if (Admin.list()) {
			respond status:'installed'
		} else {
			respond status:'new'
		}
	}

	@Transactional
	def createFirstAdmin() {
		if (Admin.list()) {
			throw new Exception('The Database has been set up already.')
		} else {
			def admin = new Admin(name: params.adminName, password:params.password, type:AdminType.GLOBAL)
			admin.save()
			respond status:'OK'
		}
	}
}
