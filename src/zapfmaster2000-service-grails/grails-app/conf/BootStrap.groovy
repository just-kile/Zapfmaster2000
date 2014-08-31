class BootStrap {

    def newsService

    def init = { servletContext ->
        newsService.init()
    }
    def destroy = {
    }
}
