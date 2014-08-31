import de.justkile.zapfmaster2000.controller.InstallationController;

class UrlMappings {

	static mappings = {
		"/rest/install/status"(controller: 'installation')
		"/rest/install/firstadmin"(controller: 'installation', action: 'createFirstAdmin')
		
		"/rest/login/admin"(controller: 'login', action: 'loginAdmin')
        "/rest/login/account"(controller: 'login', action: 'loginAccount')
        "/rest/login/user"(controller: 'login', action: 'loginUser')

		"/rest/admin/loginstatus"(controller: 'login', action: 'retrieveLoginStatus')

        "/rest/box/login"(controller: 'zapfKit', action: 'processRfidTag')

        "/rest/draftkit"(controller: 'zapfKit', action: 'retrieveZapfKitsForAccount')
        "/rest/draftkit/create"(controller: 'zapfKit', action: 'createZapfKit')
        "/rest/draftkit/account/$accountId"(controller: 'zapfKit', action: 'retrieveZapfKits')
        "/rest/draftkit/$zapfKitId/rfid"(controller: 'zapfKit', action: 'retrieveRfidTagAsync')
        "/rest/draftkit/$zapfKitId/switchkeg"(controller: 'zapfKit', action: 'switchKeg')

        "/rest/box/draw"(controller: 'zapfKit', action: 'performDrawing')

		"/rest/account"(controller: 'account', action: 'retrieveAccounts')
		"/rest/account/create"(controller: 'account', action: 'createAccount')

        "/rest/admin/account"(controller: 'admin', action: 'retrieveAccountAdmins')
        "/rest/admin/account/$accountId/create"(controller: 'admin', action: 'createAccountAdmin')

        "/rest/meta/version"(controller: 'meta', action: 'retrieveVersion')

        "/rest/register/user"(controller: 'register', action: 'registerUser')
        "/rest/register/rfid"(controller: 'register', action: 'registerRfidCard')

        "/rest/push/news"(controller:  'news', action: 'retrieveNextNewsAsync')
        "/rest/news"(controller: 'news', action: 'retrieveNews')

        "/rest/statistics/rankings/bestUserList"(controller: 'statistics', action: 'retrieveUserRanking')
        "/rest/statistics/userStats"(controller: 'statistics', action: 'retrieveUserStats')

        "/rest/members"(controller: 'user', action: 'retrieveUsers')


		"/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here 
            }
        }

        "/"(resource:"/index.html")
        "500"(view:'/error')
	}
}
